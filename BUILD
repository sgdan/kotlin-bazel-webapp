load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

kt_jvm_library(
    name = "code",
    srcs = glob(["src/main/kotlin/**/*.kt"]),
    deps = ["@sparkjava//:compile"],
)

java_binary(
    name = "tempjar",
    main_class = "com.github.sgdan.kbwapp.ApplicationKt",
    resource_strip_prefix = "src/main/webapp",
    resources = glob(["src/main/webapp/**/*.xml"]),
    runtime_deps = [":code"],
)

# Copy jar to war extension
genrule(
    name = "webapp",
    srcs = [":tempjar_deploy.jar"],
    outs = ["webapp.war"],
    cmd = "cp $< $@",
)
