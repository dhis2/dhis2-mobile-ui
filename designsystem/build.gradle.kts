plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
//    id("org.jlleitschuh.gradle.ktlint")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    android()

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                api(moko.resources)
                api(moko.resourcesCompose)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "org.hisp.dhis.mobileui.designsystem"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")

    defaultConfig {
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

/*ktlint {
    verbose.set(true)
    outputToConsole.set(true)
}*/

multiplatformResources {
    multiplatformResourcesPackage = "org.hisp.dhis.mobileui.designsystem.library" // required
//    multiplatformResourcesClassName = "SharedRes" // optional, default MR
//    multiplatformResourcesVisibility = MRVisibility.Internal // optional, default Public
}
