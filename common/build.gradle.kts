plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint")
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
                implementation(project(":designsystem"))
                api(moko.resources)
                api(moko.resourcesCompose)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "org.hisp.dhis.mobileui.common"

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

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    filter {
        exclude { entry ->
            entry.file.toString().contains("generated")
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.hisp.dhis.mobileui.library"
    multiplatformResourcesClassName = "SharedRes" // optional, default MR
}
