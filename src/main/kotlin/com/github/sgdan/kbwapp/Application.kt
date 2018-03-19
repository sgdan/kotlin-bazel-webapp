package com.github.sgdan.kbwapp

import spark.Spark.get
import spark.servlet.SparkApplication

class Application : SparkApplication {
    override fun init() {
        get("/hello", { _, _ -> "Hi there" })
    }
}

fun main(args: Array<String>) {
    println("Running!")
    Application().init() // -> http://localhost:4567/hello
}
