# DHIS2 Mobile UI

> **@dhis2/dhis2-mobile-ui** is a [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) DHIS2 design system library for [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) applications.

**DHIS2 Mobile UI library** is based in the [DHIS2 Mobile Design System Figma library](https://www.figma.com/file/eRk6bt0B8BJlTO9PZXirHN/DHIS2-Mobile-Design-System).

This library currently supports **desktop** and **Android** targets, in the next versions it will support **iOS** and **web**.

## Installation

In the module **build.gradle.kts**:

```kotlin
dependencies {
  implementation("org.hisp.dhis.mobile:designsystem:1.0-SNAPSHOT")
}
```

# Usage

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
