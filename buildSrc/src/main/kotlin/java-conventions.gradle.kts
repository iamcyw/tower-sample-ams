import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins.withType(JavaBasePlugin::class) {
    project.setProperty("sourceCompatibility", JavaVersion.VERSION_11)
}

val towerMessageVersion: String by project
val quarkusPlatformVersion: String by project
val enforced = dependencies.platform("io.iamcyw.tower:messaging-dependencies:$towerMessageVersion")
val quarkusEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusPlatformVersion")
val camelEnf = dependencies.enforcedPlatform("io.quarkus.platform:quarkus-camel-bom:$quarkusPlatformVersion")
plugins.withType(JavaPlugin::class) {
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, enforced)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, quarkusEnf)
    dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, camelEnf)
    configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME) {
        dependencies.add(quarkusEnf)
    }
}

tasks.withType(Test::class) {
    useJUnitPlatform()
    maxHeapSize = "1024M"
}

tasks.withType(JavaCompile::class) {
    options.encoding = "UTF-8"
    sourceCompatibility = JavaVersion.VERSION_11.majorVersion
    targetCompatibility = JavaVersion.VERSION_11.majorVersion
}

tasks.withType(KotlinCompile::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
    kotlinOptions.javaParameters = true
    sourceCompatibility = JavaVersion.VERSION_11.majorVersion
    targetCompatibility = JavaVersion.VERSION_11.majorVersion
}

tasks.withType(Javadoc::class) {
    options {
        encoding("UTF-8").source(JavaVersion.VERSION_11.majorVersion)
        (this as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}