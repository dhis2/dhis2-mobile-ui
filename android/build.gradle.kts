plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("org.jlleitschuh.gradle.ktlint")
    id("kotlin-kapt")
}

group = "org.hisp.dhis"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("com.airbnb.android:showkase:1.0.0-beta18")
    kapt("com.airbnb.android:showkase-processor:1.0.0-beta18")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "org.hisp.dhis.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    namespace = "org.hisp.dhis.android"
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
}
