import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

version = rootProject.version
group = rootProject.group

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.library)
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

    val xcf = XCFramework()

    listOf(
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
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
                api(compose.materialIconsExtended)
                implementation(compose.components.resources)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.multiplatform.markdown.renderer)
                implementation(libs.multiplatform.markdown.renderer.m3)
                implementation(libs.multiplatform.markdown.renderer.coil3)
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
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "org.hisp.dhis.mobile.ui.designsystem"

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/composeResources")
            resources.srcDirs("src/commonMain/composeResources")
        }
    }

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

// Paparazzi 2.0.0-alpha05's runtime is compiled for Java 21, but the module's
// toolchain stays on 17 so the published bytecode keeps targeting 17. Run only
// the Android unit test tasks (which host the Paparazzi snapshot tests) on 21.
val javaToolchains = extensions.getByType<JavaToolchainService>()
tasks.withType<Test>().matching { it.name.endsWith("UnitTest") }.configureEach {
    javaLauncher.set(
        javaToolchains.launcherFor {
            languageVersion.set(JavaLanguageVersion.of(21))
        },
    )
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
