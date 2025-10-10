import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

version = rootProject.version
group = rootProject.group

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.testing.snapshot.paparazzi)
    id("convention.publication")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm("desktop")

    wasmJs {
        browser()
    }

    val xcf = XCFramework()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "designsystem"
            isStatic = true
            xcf.add(this)
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
                api(compose.materialIconsExtended)
                implementation(compose.components.resources)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val commonTest by getting

        val androidMain by getting {
            dependencies {
                implementation(libs.zxing.core)
                implementation(libs.signature.pad)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.test.junit)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.zxing.core)
            }
        }

        val desktopTest by getting {
            dependencies {
                implementation(compose.desktop.uiTestJUnit4)
                implementation(compose.desktop.currentOs)
            }
        }

        val wasmJsMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.components.resources)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "org.hisp.dhis.mobile.ui.designsystem"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/composeResources")
    sourceSets["main"].resources.srcDirs("src/commonMain/composeResources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    lint {
        abortOnError = false
        warningsAsErrors = false
    }
}

tasks.withType(DokkaTask::class).configureEach {
    dokkaSourceSets {
        configureEach {
            moduleName.set("Mobile UI")
        }
    }
    val dokkaBaseConfiguration = """
    {
      "customAssets": ["${file("../assets/logo-icon.svg").absolutePath.replace("\\", "/")}"]
    }
    """
    pluginsMapConfiguration.set(
        mapOf(
            "org.jetbrains.dokka.base.DokkaBase" to dokkaBaseConfiguration,
        ),
    )
}
