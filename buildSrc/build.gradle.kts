plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://maven.aliyun.com/repository/central")
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    implementation("io.github.gradle-nexus:publish-plugin:1.1.0")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.6.0")
    implementation(gradleApi())
}