---
id: getting-started
title: Getting started
sidebar_position: 2
---

# Getting started

> **@dhis2/dhis2-mobile-ui** is
> a [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) DHIS2 design system
> library for [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) applications.

## DHIS2 UI Library Principles

**DHIS2 Mobile UI library** is an open source design system for building DHIS2 Android and more
mobile targets in the future. The system is based on a foundation of principles, patterns, and
guidelines for designing user-friendly apps. A reusable component library provides the building
blocks to develop DHIS2 apps with consistent user experience.

The UI library is more than just a collection of components, there also are principles, such as
predefined colors, layout, typography, icons and patterns. You can find all of these in
the [DHIS2 UI documentation](https://ui.dhis2.nu/).

## 1. Installation

### Android

In the module **build.gradle.kts**:

```kotlin
dependencies {
    implementation("org.hisp.dhis.mobile:designsystem-android:0.3.0")
}
```

### Desktop

In the module **build.gradle.kts**:

```kotlin
dependencies {
    implementation("org.hisp.dhis.mobile:designsystem-desktop:0.3.0")
}
```

### Multiplatform project

In the module **build.gradle.kts**:

```kotlin
dependencies {
    implementation("org.hisp.dhis.mobile:designsystem:0.3.0")
}
```

## 2. Usage

```kotlin 
setContent {
    DHIS2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = SurfaceColor.Container
        ) {
            Button(
                text = provideStringResource("show_more"),
                icon = {
                    Icon(
                        painter = provideDHIS2Icon(resourceName = "dhis2_blood_a_n_positive"),
                        contentDescription = ""
                    )
                },
                style = ButtonStyle.KEYBOARDKEY
            ) {}
        }
    }
}
```
