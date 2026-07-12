# Fix KtLint and Detekt Version Inconsistency

The project is currently failing to sync because `build-logic/convention/build.gradle.kts` uses incorrect coordinates for the KtLint Gradle plugin dependency (`org.jlleitschuh.gradle.ktlint:12.1.1` instead of `org.jlleitschuh.gradle:ktlint-gradle:12.1.1`). Additionally, there are inconsistent versions for Detekt and KtLint across the project.

This plan centralizes these dependencies in the Version Catalog (`libs.versions.toml`) and updates all relevant build files to use them consistently.

## User Review Required

> [!NOTE]
> I will be aligning Detekt to version `1.23.8` and KtLint to version `12.1.1` as these appear to be the intended versions based on the current (broken) configuration.

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///Users/christoffer/Documents/personal/repo-android-2/JustComposeLabs/gradle/libs.versions.toml)
- Add `detekt` version (`1.23.8`) and `ktlint` version (`12.1.1`).
- Add `detekt-gradlePlugin` and `ktlint-gradlePlugin` to the `[libraries]` section.
- Add `detekt` and `ktlint` to the `[plugins]` section.

#### [MODIFY] [build.gradle.kts](file:///Users/christoffer/Documents/personal/repo-android-2/JustComposeLabs/build.gradle.kts)
- Update plugins block to use `alias(libs.plugins.detekt)` and `alias(libs.plugins.ktlint)`.

#### [MODIFY] [build-logic/convention/build.gradle.kts](file:///Users/christoffer/Documents/personal/repo-android-2/JustComposeLabs/build-logic/convention/build.gradle.kts)
- Update dependencies block to use `libs.detekt.gradlePlugin` and `libs.ktlint.gradlePlugin`.

## Verification Plan

### Automated Tests
- Run `./gradlew :build-logic:convention:compileKotlin` to verify the convention plugins compile correctly.
- Run `./gradlew help` (or any simple task) to trigger a project sync and verify all dependencies are resolved.
