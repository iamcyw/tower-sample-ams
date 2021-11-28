plugins {
    kotlin("jvm")
    id("java-conventions")
}

//allopen()

dependencies {
//    enforcedPlatform("io.quarkus.platform:quarkus-camel-bom:2.5.0.Final")

    implementation("io.iamcyw.tower:messaging-core")

    implementation(project(":domain:job-domain"))

    implementation("org.apache.camel.quarkus:camel-quarkus-amqp")
}