import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
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
    android {
        namespace = "org.hisp.dhis.mobile.ui.designsystem"
        compileSdk = (findProperty("android.compileSdk") as String).toInt()
        minSdk = (findProperty("android.minSdk") as String).toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }

        withHostTestBuilder {}.configure {}

        lint {
            abortOnError = false
            warningsAsErrors = false
        }
    }

    jvmToolchain(17)

    jvm("desktop")

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

        val androidHostTest by getting {
            kotlin.srcDir("src/androidUnitTest/kotlin")
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
