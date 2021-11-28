pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id (quarkusPluginId) version quarkusPluginVersion
        kotlin("plugin.serialization") version "1.5.31"
        id("org.kordamp.gradle.jandex") version "0.11.0"
    }
}
rootProject.name = "tower-sample-ams"

include(
    "domain",
    "domain:job",
    "domain:notification",
    "domain:user",
)

include(
    "server",
    "server:adapter",
    "server:job",
    "server:notification"
)

project(":domain").children.forEach {
    changeProjectName(it,it.name + "-domain")
}

fun changeProjectName(project: ProjectDescriptor,name:String){
    val path = file(project.projectDir.toString())
    project.name = name
}