# kotlin-bazel-webapp
Simple hello world webapp using Kotlin, Sparkjava and Bazel

Use bazel to build the war:
```
bazel build //:webapp
```
It's a fat war so run like executable jar:
```
java -jar bazel-genfiles/webapp.war
```
Then go to http://localhost:4567/hello
 
### Testing with the WebSphere docker image

Used together with https://github.com/sgdan/docker-websphere-sqlserver can use the following:
- https://localhost:9443/webapp/hello Simple hello world example
- https://localhost:9443/webapp/database Stores and retrieves some timestamps in a database table
- https://localhost:9443/webapp/mq Sends and receives a test message to a message queue
  (requires WebSphere to be configured with a JNDI queue "JndiTestQueue" and connection factory "JndiConnectionFactory")
