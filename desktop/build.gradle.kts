import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jlleitschuh.gradle.ktlint")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "mobile-ui"
            packageVersion = "1.0.0"
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    filter {
        exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "org.hisp.dhis.mobileui"
    multiplatformResourcesClassName = "SharedRes"
}

tasks.named("runKtlintCheckOverCommonMainSourceSet") {
    mustRunAfter("generateMRcommonMain")
}

tasks.named("runKtlintCheckOverJvmMainSourceSet") {
    mustRunAfter("generateMRjvmMain")
}
