---
id: screenshot-testing
title: Screenshot testing
sidebar_position: 2
---

# Screenshot Testing

We are using [Paparazzi](https://cashapp.github.io/paparazzi/) for screenshot testing.

## Screenshot testing

```kotlin
class InputChipSnapshotTest {

  @get:Rule
  val paparazzi = paparazzi()

  @Test
  fun launchChip() {
    paparazzi.snapshot {
      ColumnComponentContainer {
        InputChip(label = "Label", selected = false, badge = "3")
        InputChip(label = "Label", selected = true, badge = "3")
      }
    }
  }
}
```

## Running tests

`./gradlew designsystem:testDebugUnitTest`

Runs tests and generates an HTML report at `/build/reports/paparazzi/` showing all test runs
and snapshots.

## Saving golden images to repo

`./gradlew designsystem:recordPaparazziDebug`

Saves snapshots as golden values to a predefined source-controlled location (defaults to
`/src/test/snapshots`).

## Verifying

`./gradlew designsystem:verifyPaparazziDebug`

Runs tests and verifies against previously-recorded golden values. Failures generate diffs at
`/build/paparazzi/failures`.