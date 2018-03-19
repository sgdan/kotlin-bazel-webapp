kotlin_release_version = "1.2.30"

rules_kotlin_version = "67f4a6050584730ebae7f8a40435a209f8e0b48e"

http_archive(
    name = "io_bazel_rules_kotlin",
    strip_prefix = "rules_kotlin-%s" % rules_kotlin_version,
    type = "zip",
    urls = ["https://github.com/bazelbuild/rules_kotlin/archive/%s.zip" % rules_kotlin_version],
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories", "kt_register_toolchains")

kotlin_repositories(kotlin_release_version = kotlin_release_version)

kt_register_toolchains()






git_repository(
  name = "org_pubref_rules_maven",
  remote = "https://github.com/pubref/rules_maven",
  commit = "9c3b07a6d9b195a1192aea3cd78afd1f66c80710",
)

load("@org_pubref_rules_maven//maven:rules.bzl", "maven_repositories")
maven_repositories()

load("@org_pubref_rules_maven//maven:rules.bzl", "maven_repository")
maven_repository(
    name = 'sparkjava',
    deps = [
        'com.sparkjava:spark-core:2.7.2',
    ],
    transitive_deps = [
        'b832cca2704a96c027424efafec3fe39392f5aab:com.sparkjava:spark-core:2.7.2',
        '3cd63d075497751784b2fa84be59432f4905bf7c:javax.servlet:javax.servlet-api:3.1.0',
        'e93cac2ce46e81ee8b36288cf7e19d3bac82d536:org.eclipse.jetty:jetty-client:9.4.8.v20171121',
        '9879d6c4e37400bf43f0cd4b3c6e34a3ba409864:org.eclipse.jetty:jetty-http:9.4.8.v20171121',
        'd3fe2dfa62f52ee91ff07cb359f63387e0e30b40:org.eclipse.jetty:jetty-io:9.4.8.v20171121',
        'e8350eec683b55494287f06740543e4be6f75425:org.eclipse.jetty:jetty-security:9.4.8.v20171121',
        '34614bd9a29de57ef28ca31f1f2b49a412af196d:org.eclipse.jetty:jetty-server:9.4.8.v20171121',
        'bbbb9b5de08f468c7b9b3de6aea0b098d2c679b6:org.eclipse.jetty:jetty-servlet:9.4.8.v20171121',
        'd6ec1a1613c7fa72aa6bf5d8c204750afbc3df3b:org.eclipse.jetty:jetty-util:9.4.8.v20171121',
        '695278449233cee9bae9eed930a5264b574774f0:org.eclipse.jetty:jetty-webapp:9.4.8.v20171121',
        'b0d6f87f580a9bd7fa9aaf9b7448bf63cf0ac34f:org.eclipse.jetty:jetty-xml:9.4.8.v20171121',
        '6d889f9a8b5fd2a573c6d1d518c7e119a6d8c170:org.eclipse.jetty.websocket:websocket-api:9.4.8.v20171121',
        'f83fc3fbd52109a57d09b3ef88b715a36e8acdb4:org.eclipse.jetty.websocket:websocket-client:9.4.8.v20171121',
        '82cd6d9caa68baf6557176159e6e5c37faed0e9b:org.eclipse.jetty.websocket:websocket-common:9.4.8.v20171121',
        'c6ae10f65664d90e24dede5dec23098f5f4c3a58:org.eclipse.jetty.websocket:websocket-server:9.4.8.v20171121',
        '9061f66101a8654ae1a55a3336438473f1b00713:org.eclipse.jetty.websocket:websocket-servlet:9.4.8.v20171121',
        '7fcf30c25b8f4a9379b9dad0d3f487b25272c026:org.slf4j:slf4j-api:1.7.13',
    ],
)
load("@sparkjava//:rules.bzl", "sparkjava_compile")
sparkjava_compile()
