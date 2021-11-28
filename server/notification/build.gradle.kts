plugins {
    kotlin("jvm")
    id("java-conventions")
    id("org.kordamp.gradle.jandex")
}

allopen()

val serializationVersion:String by project
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")


    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.iamcyw.tower:messaging-core")

    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-scheduler")
    implementation("io.quarkus:quarkus-qute")

    implementation("org.apache.camel.quarkus:camel-quarkus-mail")

    implementation(project(":domain:job-domain"))
    implementation(project(":domain:notification-domain"))
    implementation(project(":domain:user-domain"))

}