import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

version = rootProject.version
group = rootProject.group

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.testing.snapshot.paparazzi)
    id("convention.publication")
}

kotlin {
    jvmToolchain(17)

    android {
        namespace = "org.hisp.dhis.mobile.ui.designsystem"
        compileSdk = (findProperty("android.compileSdk") as String).toInt()
        minSdk = (findProperty("android.minSdk") as String).toInt()

        androidResources {
            enable = true
        }

        withHostTest {
            isIncludeAndroidResources = true
        }

        lint {
            abortOnError = false
            warningsAsErrors = false
        }
    }

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
        commonMain.dependencies {
            implementation(libs.bundles.compose.common)
            api(libs.compose.material.icons.extended)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.multiplatform.markdown.renderer)
            implementation(libs.multiplatform.markdown.renderer.m3)
            implementation(libs.multiplatform.markdown.renderer.coil3)
        }

        androidMain.dependencies {
            implementation(libs.zxing.core)
            implementation(libs.signature.pad)
        }

        getByName("androidHostTest").dependencies {
            implementation(libs.test.junit)
        }

        getByName("desktopMain").dependencies {
            implementation(libs.compose.desktop)
            implementation(libs.zxing.core)
        }

        getByName("desktopTest").dependencies {
            implementation(libs.compose.ui.test.junit4)
            implementation(compose.desktop.currentOs)
        }
    }
}

// Keep exposing compose resources as Android resources: consumers such as the Capture app use
// them through R (e.g. R.string.date_birth, R.font.roboto_regular).
androidComponents {
    onVariants { variant ->
        variant.sources.res?.addStaticSourceDirectory("src/commonMain/composeResources")
    }
}

// Paparazzi requires a Java 21 runtime; library bytecode stays on the Java 17 toolchain.
tasks.withType<Test>().configureEach {
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
