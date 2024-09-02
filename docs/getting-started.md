---
id: getting-started
title: Getting started
sidebar_position: 2
---

# DHIS2 Mobile UI

> **@dhis2/dhis2-mobile-ui** is
> a [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) DHIS2 design system
> library for [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) applications.

This library currently supports **desktop** and **Android** targets, in the next versions it will
support **iOS**.

## 1. Getting started

### DHIS2 UI Library Principles

The UI library more than just a collection of components, there also are principles, such as predefined colors, layout, typography, icons and patterns. You can find all of these in the [DHIS2 UI documentation](https://ui.dhis2.nu/).

### Live demos documentation

//TODO link to github releases artifact maybe?

### Installation

In the module **build.gradle.kts**:

```kotlin
dependencies {
  implementation("org.hisp.dhis.mobile:designsystem:0.2")
}
```

## 2. Usage

```kotlin
setContent {
  DHIS2Theme {
    // A surface container using the 'background' color from the theme
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = SurfaceColor.Container
    ) {
      Components()
    }
  }
}
```

### Add a Button
```kotlin
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
```