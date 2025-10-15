import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

version = "0.6.0-SNAPSHOT"
group = "org.hisp.dhis.mobile"

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka)
    alias(libs.plugins.nexus)
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

            // Suppress KLIB duplicate library warnings for metadata compilation
            // These occur due to androidx.* and org.jetbrains.androidx.* library overlaps
            if (name.contains("Metadata", ignoreCase = true)) {
                allWarningsAsErrors.set(false)
            }

            if (project.providers.gradleProperty("enableComposeCompilerReports").isPresent) {
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                        layout.buildDirectory.asFile
                            .get()
                            .absolutePath +
                        "/compose_metrics",
                )
                freeCompilerArgs.addAll(
                    "-P",
                    "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                        layout.buildDirectory.asFile
                            .get()
                            .absolutePath +
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

val centralPortalUsername: String? = System.getenv("SONATYPE_USERNAME")
val centralPortalPassword: String? = System.getenv("SONATYPE_PASSWORD")

nexusPublishing {
    this.repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(centralPortalUsername)
            password.set(centralPortalPassword)
        }
    }
}
