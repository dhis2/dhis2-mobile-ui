import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm("desktop")

    jvmToolchain(17)

    android {
        namespace = "org.hisp.dhis.mobile.ui.common"
        compileSdk = (findProperty("android.compileSdk") as String).toInt()
        minSdk = (findProperty("android.minSdk") as String).toInt()

        androidResources {
            enable = true
        }
    }

    // Add iOS targets
    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "ShowcaseApp" // Or your desired framework name
            isStatic = true // Set to true if you need a static framework
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.compose.common)
            api(project(":designsystem"))
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.compose.material3.windowsizeclass)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        getByName("desktopMain").dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "org.hisp.dhis.showcaseapp.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "mobile-ui"
            packageVersion = "1.0.0"
        }
    }
}
