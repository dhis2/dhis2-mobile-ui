plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("multiplatform")
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()
    sourceSets {
        androidMain.dependencies {
            implementation(project(":common"))
            implementation("androidx.activity:activity-compose:1.8.2")
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    implementation(project(":designsystem"))
}
