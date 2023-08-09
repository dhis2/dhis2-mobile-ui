plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("multiplatform")
    id("org.jlleitschuh.gradle.ktlint")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation("androidx.activity:activity-compose:1.7.2")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "org.hisp.dhis.android"

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        applicationId = "org.hisp.dhis.android"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    enableExperimentalRules.set(true)
    filter {
        exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
    }
}
