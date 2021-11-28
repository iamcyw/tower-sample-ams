plugins {
    kotlin("multiplatform")
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

                compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")
            }
        }

        val jvmMain by getting {
            dependencies {
                api("io.quarkus:quarkus-hibernate-orm-panache-kotlin:$quarkusPlatformVersion")

            }
        }
    }
}