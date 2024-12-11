import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

version = "0.4.1-COMPOSE-1-7-1-SNAPSHOT"
group = "org.hisp.dhis.mobile"

plugins {
    kotlin("multiplatform") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("org.jetbrains.dokka") version "1.9.20"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    alias(libs.plugins.compose.compiler)
}

/**
 * Property from the Gradle command line. To remove the snapshot suffix from the version.
 */
if (project.hasProperty("removeSnapshotSuffix")) {
    val mainVersion = (version as String).split("-SNAPSHOT")[0]
    version = mainVersion
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        version.set("0.50.0")
        verbose.set(true)
        outputToConsole.set(true)
        filter {
            exclude("**/generated/**")
        }
    }

    tasks.withType<KotlinCompilationTask<*>>().all {
        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    tasks.withType<KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors.set(true)

            if (project.providers.gradleProperty("enableComposeCompilerReports").isPresent) {
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath +
                        "/compose_metrics",
                )
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        layout.buildDirectory.asFile.get().absolutePath +
                        "/compose_metrics",
                )
            }
        }
    }

    plugins.withId("app.cash.paparazzi") {
        // Defer until afterEvaluate so that testImplementation is created by Android plugin.
        afterEvaluate {
            dependencies.constraints {
                add("testImplementation", "com.google.guava:guava") {
                    attributes {
                        attribute(
                            TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                            objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM),
                        )
                    }
                    because(
                        "LayoutLib and sdk-common depend on Guava's -jre published variant." +
                            "See https://github.com/cashapp/paparazzi/issues/906.",
                    )
                }
            }
        }
    }
}

val ossrhUsername: String? = System.getenv("OSSRH_USERNAME")
val ossrhPassword: String? = System.getenv("OSSRH_PASSWORD")

nexusPublishing {
    this.repositories {
        sonatype {
            username.set(ossrhUsername)
            password.set(ossrhPassword)
        }
    }
}
