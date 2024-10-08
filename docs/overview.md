---
id: overview
title: Overview
sidebar_position: 1
---

# Overview

**DHIS2 Mobile UI library** is an open source design system based
on [Compose multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/), a modern UI toolkit
that enables building user interfaces across multiple platforms, including Android and desktop,
using a single codebase. This library simplifies the development process by allowing developers to
write Compose UI code once and target both Android and desktop platforms with minimal changes.

## Targets

This library currently supports **desktop** and **Android** targets, in the next versions it will
support **iOS**.

### Android
**DHIS2 Mobile UI library** provides native support for Android. When targeting Android, you can
leverage the full power of Jetpack Compose, Google's modern UI toolkit for Android. The Compose
Multiplatform library seamlessly integrates with Android Studio and the Android build system. 
However, if your project uses Java and XML layouts, you can still integrate the library by embedding Compose 
components within your existing views.

#### Using with Java and XML

If your project uses Java and XML layouts, you can still integrate the library by embedding Compose components within your existing views. For guidance on interoperability between XML and Compose, refer to the [Android Developers Interoperability Guide](https://developer.android.com/develop/ui/compose/migrate/interoperability-apis).

### Desktop

**DHIS2 Mobile UI library** also supports desktop applications. When targeting desktop, you use a
similar set of Compose APIs, but with desktop-specific configuration. It is designed to work
exclusively with Kotlin Multiplatform (KMP) projects.

## Feedback and Contributions

We welcome feedback to improve the Mobile UI library and its showcase application. Feel free to [open issues](https://dhis2.atlassian.net/jira/software/c/projects/ANDROAPP/boards/113?quickFilter=730) on Jira.
