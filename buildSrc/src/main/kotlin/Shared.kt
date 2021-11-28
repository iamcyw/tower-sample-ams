import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl

fun KotlinJsProjectExtension.kotlinJsTargets() {
    js {
        kotlinJsTargets()
    }
}

fun KotlinMultiplatformExtension.kotlinJsTargets() {
    js(IR) {
        kotlinJsTargets()
    }
}

fun KotlinMultiplatformExtension.kotlinJvmTargets() {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.majorVersion
                freeCompilerArgs = listOf("-Xjsr305=strict")
            }
        }
    }
}

fun Project.allopen() {
    //id("org.jetbrains.kotlin.plugin.allopen") version "1.5.31"
    plugins.apply("org.jetbrains.kotlin.plugin.allopen")

    extensions.getByType<AllOpenExtension>().apply {
        annotation("javax.persistence.Entity")
        annotation("javax.enterprise.context.ApplicationScoped")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }

}

private fun KotlinJsTargetDsl.kotlinJsTargets() {
//    compilations.all {
//        kotlinOptions {
//            moduleKind = "umd"
//            sourceMap = project.hasProperty("SNAPSHOT")
//        }
//    }
    browser {
        testTask {
            useKarma {
                useChromeHeadless()
            }
        }
    }
}