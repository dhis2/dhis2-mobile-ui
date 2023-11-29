import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        version.set("0.50.0")
        verbose.set(true)
        outputToConsole.set(true)
    }

    tasks.withType<KotlinCompile>().all {
        kotlinOptions { freeCompilerArgs += "-Xexpect-actual-classes" }
    }
}

subprojects {
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
}
