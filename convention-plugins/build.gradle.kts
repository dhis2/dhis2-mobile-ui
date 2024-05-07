plugins {
    `kotlin-dsl` // Is needed to turn our build logic written in Kotlin into the Gradle Plugin
}

repositories {
    mavenCentral()
    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
}

dependencies {
    implementation("io.github.gradle-nexus:publish-plugin:1.3.0")
}