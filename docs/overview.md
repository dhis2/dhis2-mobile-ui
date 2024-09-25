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
support **iOS** and **web**.

### Android

**DHIS2 Mobile UI library** provides native support for Android. When targeting Android, you can
leverage the full power of Jetpack Compose, Google's modern UI toolkit for Android. The Compose
Multiplatform library seamlessly integrates with Android Studio and the Android build system.

### Desktop

**DHIS2 Mobile UI library** also supports desktop applications. When targeting desktop, you use a
similar set of Compose APIs, but with desktop-specific configuration. It is designed to work
exclusively with Kotlin Multiplatform (KMP) projects.
