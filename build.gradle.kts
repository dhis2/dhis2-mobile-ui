plugins {
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
}

buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath(moko.resourcesGradlePlugin)
    }
}
