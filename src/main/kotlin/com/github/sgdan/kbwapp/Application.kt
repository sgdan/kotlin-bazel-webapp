package com.github.sgdan.kbwapp

import net.sourceforge.jtds.jdbcx.JtdsDataSource
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.MapListHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import spark.Request
import spark.Response
import spark.Spark.get
import spark.servlet.SparkApplication
import javax.naming.InitialContext
import javax.naming.NamingException
import javax.sql.DataSource
import javax.jms.Queue
import javax.jms.ConnectionFactory

class Application : SparkApplication {
    val log: Logger = getLogger(Application::class.java)

    private val ds: DataSource by lazy {
        try {
            log.info("Looking up datasource via JNDI...")
            lookup("java:comp/env/testds")
        } catch (e: NamingException) {
            log.error("JNDI lookup failed", e)
            log.info("Connecting to database directly...")
            JtdsDataSource().apply {
                serverName = "localhost"
                portNumber = 1433
                user = "testuser"
                password = "testpass"
                databaseName = "testdb"
            }
        }
    }

    private fun lookup(ds: String): DataSource {
        return InitialContext().lookup(ds) as DataSource
    }

    override fun init() {
        get("/hello") { _, _ -> "Hello there!" }

        get("/mq") { req, res ->
            try {
                log.info("Looking up jms queue via JNDI...")
                val ic = InitialContext()
                val queue = ic.lookup("java:comp/env/JndiTestQueue") as Queue
                val cf = ic.lookup("java:comp/env/JndiConnectionFactory") as ConnectionFactory
                log.info("queue: $queue, ${queue::class.java}")
                log.info("cf: $cf, ${cf::class.java}")
                //com.ibm.ws.sib.api.jms.impl.JmsQueueImpl
                val context = cf.createContext()
                context.createProducer().send(queue, "msg-sent-to-queue")
                val msg = context.createConsumer(queue).receive()
                "${msg.jmsTimestamp}: ${msg.getBody(String::class.java)}"
            } catch (e: Exception) {
                log.error("Unable to connect to MQ", e)
                "Error $e"
            }
        }

        get("/database", { _: Request, _: Response ->
            try {
                log.debug("DataSource: $ds")

                // create table if it doesn't exist
                val qr = QueryRunner(ds)
                qr.update("""if not exists (
                    select * from sysobjects where name = 'requests' and xtype = 'U'
                ) create table requests (id numeric identity, time datetime)""")

                // insert an entry for this request
                qr.update("insert into requests (time) values (getdate())")

                val reqs: List<Map<String, Any>> = qr.query("select * from requests order by id desc", MapListHandler())
                "There have been ${reqs.size} requests, last 5 were at:<br>" +
                        reqs.take(5).map {
                            it["time"]
                        }.joinToString("<br>")
            } catch (e: Exception) {
                log.error("Unable to retrieve requests from database", e)
                "Error $e"
            }
        })
    }
}

fun main(args: Array<String>) {
    println("Running on http://localhost:4567/hello")
    Application().init()
}
