allprojects {
    group = "io.iamcyw.ams"

    repositories {
        mavenCentral()
    }

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.MINUTES)
    }
}