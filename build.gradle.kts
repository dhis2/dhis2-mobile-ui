import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

version = "0.10.0-SNAPSHOT"
group = "org.hisp.dhis.mobile"

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.nexus)
    alias(libs.plugins.cyclonedx) apply false
    alias(libs.plugins.sonarqube)
}

/**
 * Property from the Gradle command line. To remove the snapshot suffix from the version.
 */
if (project.hasProperty("removeSnapshotSuffix")) {
    val mainVersion = (version as String).split("-SNAPSHOT")[0]
    version = mainVersion
}

sonar {
    properties {
        val branch = System.getenv("GIT_BRANCH")
        val targetBranch = System.getenv("GIT_BRANCH_DEST")
        val pullRequestId = System.getenv("PULL_REQUEST")

        property("sonar.projectKey", "dhis2_dhis2-mobile-ui")
        property("sonar.organization", "dhis2")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.projectName", "dhis2-mobile-ui")

        // Compose resource accessors are generated into build/ and picked up as sources.
        property("sonar.exclusions", "**/build/generated/**")

        // GitHub Actions resolves PULL_REQUEST to an empty string on push events, so a
        // null check alone would send push builds down the pull-request path.
        if (pullRequestId.isNullOrEmpty()) {
            if (!branch.isNullOrEmpty()) property("sonar.branch.name", branch)
        } else {
            property("sonar.pullrequest.base", targetBranch ?: "")
            property("sonar.pullrequest.branch", branch ?: "")
            property("sonar.pullrequest.key", pullRequestId)
            // Fail the CI job when the PR misses the quality gate.
            property("sonar.qualitygate.wait", true)
        }
    }
}

// Under AGP 9 the scanner resolves no sources for this module.
project(":androidApp") {
    sonar {
        properties {
            property("sonar.sources", "src/main")
        }
    }
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
        // Defer until afterEvaluate so that androidHostTestImplementation is created by Android plugin.
        afterEvaluate {
            dependencies.constraints {
                add("androidHostTestImplementation", "com.google.guava:guava") {
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
