---
id: recomposition
title: Recomposition
sidebar_position: 1
---

# Recomposition

The Compose Compiler plugin can generate reports / metrics around certain compose-specific concepts
that can be useful to understand what is happening with some of your compose code at a fine-grained
level.
You can read more about
it [here](https://github.com/androidx/androidx/blob/androidx-main/compose/compiler/design/compiler-metrics.md).

To generate the reports / metrics, run the following Gradle command

```shell
./gradlew assembleDebug -PenableComposeCompilerReports=true
```

This would generate the output at `<module>/build/compose_metrics`.