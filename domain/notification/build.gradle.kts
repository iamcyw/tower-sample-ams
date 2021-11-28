plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.kordamp.gradle.jandex")
}

allopen()

val serializationVersion: String by project
val quarkusPlatformVersion: String by project

kotlin {
    kotlinJsTargets()
    kotlinJvmTargets()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain:job-domain"))

                compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")
            }
        }

        val jvmMain by getting {
            dependencies {
                api(project(":domain:job-domain"))

                compileOnly("io.quarkus:quarkus-hibernate-orm-panache-kotlin:$quarkusPlatformVersion")
            }
        }
    }
}