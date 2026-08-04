# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

DHIS2 Mobile UI is a Compose Multiplatform design system library published to Maven Central as `org.hisp.dhis.mobile:designsystem`. It targets **Android, desktop JVM, and iOS** (`iosArm64`, `iosSimulatorArm64`). Material3-based, built on Jetpack/JetBrains Compose.

Public documentation lives on the [DHIS2 developer portal](https://developers.dhis2.org/docs/mobile/mobile-ui/overview); KDoc is published to <https://dhis2.github.io/dhis2-mobile-ui/api/>.

> `iosX64` (Intel simulator) was dropped when moving to Compose Multiplatform 1.11 — Compose no longer publishes Apple x86_64 artifacts. Don't re-add it.

## Prerequisites

- **JDK 21 is required to run the build.** Paparazzi 2.0.0-alpha05's Gradle plugin *and* its runtime are compiled for Java 21. All CI workflows use `setup-java` with `java-version: '21'`.
- Compiled **output still targets Java 17** — `jvmToolchain(17)` plus `compileOptions` at `VERSION_17`. Only the `*UnitTest` tasks in `:designsystem` are pinned to a Java 21 launcher (see the `javaLauncher` block in `designsystem/build.gradle.kts`). Don't raise the toolchain to 21; that would change the published artifacts' bytecode level.
- If Gradle can't find a JDK 21 (`No matching toolchain`), point it at one via `org.gradle.java.installations.paths` in `~/.gradle/gradle.properties`, or install a JDK that lands in a location Gradle auto-detects.
- **git LFS is required.** Paparazzi golden images are LFS-tracked (`.gitattributes`: `**/snapshots/**/*.png`). Without `git lfs` installed, snapshot tests fail with `NullPointerException: Failed to read the snapshot file` because the pointer files are read as PNGs.

## Modules

- `:designsystem` — the published library (Compose MPP, Android library, desktop JVM, iOS static XCFramework). Package root `org.hisp.dhis.mobile.ui.designsystem`.
- `:showcaseApp` — runnable demo app (Android app, desktop `.dmg/.msi/.deb`, iOS framework). Depends on `:designsystem`. Main desktop class `org.hisp.dhis.showcaseapp.MainKt`; screens under `screens/` (`components/`, `actionInputs/`, `bottomSheets/`, `layouts/`, `menu/`, `parameter/`, `table/`, `previews/`).
- `convention-plugins` — included build providing `convention.publication` (Maven Central publishing, signing, Dokka javadoc jar).
- `iosApp/` — Xcode project consuming the `designsystem` XCFramework.

Source-set layout follows KMP `androidSourceSetLayoutVersion=2`. Inside `designsystem/src/commonMain/kotlin/.../designsystem/`:

- `component/` — all public Composables (~85 top-level files), with subfolders `internal/`, `layout/`, `menu/`, `model/`, `modifier/`, `navigationBar/`, `parameter/`, `state/`, `table/`
- `theme/` — `Theme.kt`, `Color.kt`, `SurfaceColor.kt`, `TextColor.kt`, `Outline.kt`, `Spacing.kt`, `Radius.kt`, `Border.kt`, `Shape.kt`, `Shadow.kt`, `Ripple.kt`, `HoverPointer.kt`
- `platform/` — `expect` declarations (e.g. `platform/dates/Calendar.kt`)
- `resource/` — `String.kt`, `Image.kt`, `Signature.kt`
- `files/`

Platform actuals live in `androidMain/`, `iosMain/`, `desktopMain/` (desktop also has a `java/` sibling source dir).

## Common commands

```bash
./gradlew ktlintCheck                         # lint (required by CI)
./gradlew ktlintFormat                        # auto-fix lint

./gradlew desktopTest                         # Compose UI tests on the desktop JVM target
./gradlew designsystem:verifyPaparazziDebug   # verify screenshots against golden images
./gradlew designsystem:testDebugUnitTest      # Android unit tests incl. Paparazzi (HTML at build/reports/paparazzi/)

./gradlew build                               # full build (all targets)
./gradlew assembleDebug                       # Android debug APK (showcaseApp)
./gradlew packageUberJarForCurrentOS          # desktop distribution jar
./gradlew :designsystem:dokkaHtml             # API docs

# Single test class:
./gradlew designsystem:testDebugUnitTest --tests "org.hisp.dhis.mobile.ui.designsystem.InputChipSnapshotTest"
./gradlew desktopTest --tests "*InputTextTest*"

# Compose compiler metrics → <module>/build/compose_metrics
./gradlew assembleDebug -PenableComposeCompilerReports=true

# Strip -SNAPSHOT from version (release publishing)
./gradlew <task> -PremoveSnapshotSuffix
```

## CI

`.github/workflows/github-ci.yml` runs on push/PR to `main`, `develop`, `release/*` and executes, in order: `ktlintCheck`, `desktopTest`, `designsystem:verifyPaparazziDebug`, `build`. A follow-up job on PRs builds and uploads the Android APK and Windows desktop jar. **Checkout uses `lfs: 'true'`** — required for the golden images.

Other workflows:

- `generate-paparazzi-golden-images.yml` — manual (`workflow_dispatch`); records, re-verifies and commits golden images to the branch it runs on.
- `continuous-deployment.yml` — push to `develop` publishes a snapshot; push to `release/*` publishes a release. Also manually dispatchable.
- `release-start.yml` — manual; cuts a `release/<version>` branch and opens a version-bump branch on `develop` via `.github/workflows/scripts/updateVersionName.py`.
- `dokka.yml` — publishes KDoc to GitHub Pages.
- `rebuild-docs.yml` — rebuilds the developer-portal docs from `docs/`.

## Screenshot testing (Paparazzi) — important

- Tests live at `designsystem/src/androidUnitTest/kotlin/...` and are **Android-only** (Paparazzi runs on the JVM with LayoutLib). Use the shared `paparazzi()` helper from `androidUnitTest/.../Utils.kt` — it pins `DeviceConfig.PIXEL_5`, theme `android:Theme.Material.Light.NoActionBar.Fullscreen`, and `RenderingMode.SHRINK`. Don't construct `Paparazzi(...)` directly, or snapshots won't be comparable.

  ```kotlin
  class InputChipSnapshotTest {
      @get:Rule val paparazzi = paparazzi()

      @Test
      fun launchChip() {
          paparazzi.snapshot {
              ColumnComponentContainer {
                  InputChip(label = "Label", selected = false, badge = "3")
              }
          }
      }
  }
  ```

- Golden images live under `designsystem/src/androidUnitTest/snapshots/images`. This path is derived by the Paparazzi plugin from the KMP unit-test source set — it is **not** configurable, and it moved from `src/test/snapshots` in Paparazzi 2.0.0-alpha05.
- **Do not regenerate goldens locally.** Push your branch, then run the "Generate Paparazzi Golden Images" workflow from GitHub Actions; it records, re-verifies and commits new images to the same branch.
- Verification failures write diffs to `build/paparazzi/failures/`.
- A root-`build.gradle.kts` `afterEvaluate` block adds a Guava `-jre` constraint on Paparazzi-enabled subprojects (workaround for cashapp/paparazzi#906). Don't remove it.
- Any Compose Multiplatform version bump will very likely shift rendering and require regenerating every golden image. Budget for it.

## Component conventions

Read a neighbouring component before adding one — the patterns are consistent and worth matching exactly.

- Public Composables live directly in `component/`, alongside the shared building blocks they delegate to (`InputShell.kt`, `BasicTextInput.kt`, `Container.kt`). `component/internal/` is for non-Composable helpers and implementation details — utilities (`DateTimeUtils`, `StringUtils`, `Keyboard`, `NonLazyGrid`) and feature subfolders (`barcode/`, `clipboard/`, `image/`, `modifiers/`, `qr/`, `signature/`).
- Most text-like inputs are thin wrappers: they forward to `BasicTextInput`, which renders `InputShell`. `InputText` is a good template.
- Standard parameter order and names: `title`, `state: InputShellState`, `supportingText: List<SupportingTextData>?`, `legendData: LegendData?`, value/`inputTextFieldValue`, `isRequiredField`, callbacks (`onValueChanged`, `onFocusChanged`, `onImeActionClick`), then `modifier: Modifier = Modifier`, `inputStyle: InputStyle = InputStyle.DarkInputStyle()`. Keep `modifier` after the required params and before styling, as existing components do.
- Every public Composable carries a KDoc block documenting **each** parameter (`@param name: description`). This is published as the library's API reference — don't skip it.
- Components pass a `testTag` down to `InputShell`; child elements derive tags by suffix (`"<TAG>_LEGEND"`, `"<TAG>_SUPPORTING_TEXT"`). Tests then assert with `onNodeWithTag(..., useUnmergedTree = true)`.
- Complex state is modelled in `component/state/` (e.g. `InputDateTimeState`, `ListCardState`) with `rememberX(...)` factories rather than loose parameters.
- New components should be added to `:showcaseApp` under the matching `screens/` folder so they're demonstrable.

## Theming

- Wrap content in `DHIS2Theme { }` (`theme/Theme.kt`), which applies `DHIS2LightColorScheme` and the Roboto-based `Typography`. Only a light scheme exists today.
- **Never hardcode colors, spacing, or radii.** Use the semantic objects: `SurfaceColor.*`, `TextColor.*`, `Outline.*`, `Spacing.*`, `Radius.*`, `Border.*`, `Shape.*`.
- Fonts come from Compose resources (`Res.font.roboto_regular`, `Res.font.roboto_medium`).

## Resources & translations

- Compose resources live in `designsystem/src/commonMain/composeResources/`, generated into `org.hisp.dhis.mobile.designsystem.generated.resources.Res`.
- **`values/strings.xml` (English) is the only file to edit by hand.** All `values-<lang>/strings.xml` files are managed by Transifex (`.tx/config`, resource `o:hisp-uio:p:mobile-ui:r:strings-xml`) and are overwritten by the sync — translation PRs land automatically.
- For dynamic lookup by key, use `provideStringResource(name)` / `provideQuantityStringResource(name, quantity)` / `resourceExists(name, type)` from `resource/String.kt`. They return `"Key not found"` rather than throwing on a missing key.

## Build conventions worth knowing

- `allWarningsAsErrors` is **on for all Kotlin compilations** (disabled only for `*Metadata*` tasks, to silence androidx / org.jetbrains.androidx KLIB duplicate warnings). Treat every warning as a build failure — deprecations included.
- `-Xexpect-actual-classes` is added globally; use `expect class` freely.
- `android.minSdk=23`, `android.compileSdk=36`, `android.targetSdk=36` come from `gradle.properties` via `findProperty(...)` — keep the two in sync.
- Versions are managed in `gradle/libs.versions.toml`. The root `version` in `build.gradle.kts` is the published library version.
- ktlint is applied to all subprojects via `org.jlleitschuh.gradle.ktlint`; `**/generated/**` is excluded. `.editorconfig` sets `ktlint_function_naming_ignore_when_annotated_with=Composable` so `@Composable fun PascalCase` is allowed.
- `gradle.properties` carries a block of `android.*` AGP 9 compatibility flags. **`android.newDsl=false` is load-bearing** — Paparazzi uses the legacy variant API (`libraryVariants`, `testVariants`, `unitTestVariants`), which is off by default in AGP 9 and removed in AGP 10.
- The `android { }` block must configure source sets through the `sourceSets { getByName("main") { … } }` lambda. Indexing (`sourceSets["main"].manifest…`) throws `ClassCastException` on AGP 9, because `getSourceSets()` is typed with the legacy `AndroidLibrarySourceSet` while the runtime objects implement only the new-DSL interface.
- Kotlin/Native release linking is memory-hungry; `org.gradle.jvmargs` is set to 6144M. Lowering it brings back `OutOfMemoryError: Java heap space` in `linkReleaseFrameworkIosArm64`.
- KGP warns that `com.android.library` + KMP is deprecated in favour of `com.android.kotlin.multiplatform.library` (removal in AGP 10). Migration is blocked: Paparazzi needs the Android unit-test infrastructure that plugin doesn't provide.

## iOS

`:designsystem` produces a static XCFramework named `designsystem`. `iosApp/` holds the consuming Xcode project (`iosApp.xcodeproj`, config in `Configuration/Config.xcconfig`). iOS actuals in `iosMain/` mirror the `platform/` expect APIs; skiko-backed graphics code uses `Paint.skiaPaint` (the old `NativePaint` / `asFrameworkPaint()` API is deprecated).

## Publishing & releases

- Publishing goes through `convention.publication` and the nexus-publish plugin against the Sonatype Central Portal.
- Credentials come from environment variables: `SONATYPE_USERNAME`, `SONATYPE_PASSWORD`, `SIGNING_PRIVATE_KEY`, `SIGNING_PASSWORD`.
- Branch flow: work targets `develop`; `develop` publishes snapshots, `release/*` publishes releases, `main` holds released code. Release branches are cut by the "Release start" workflow.
- `RELEASE.md` collects release notes as Jira `ANDROAPP-*` links; commits and branches commonly reference those ticket IDs.
