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
