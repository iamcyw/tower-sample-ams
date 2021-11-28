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

    implementation(project(":domain:job-domain"))
    implementation(project(":domain:notification-domain"))

}