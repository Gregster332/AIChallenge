# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**AIChallenge** is an Android application built with Jetpack Compose and Kotlin. This is a modern Android app using the latest development practices with a single-activity architecture.

- **Package**: com.gregzenkov.aichallenge
- **Min SDK**: 24
- **Target SDK**: 36
- **Kotlin**: 2.0.21
- **Compose BOM**: 2024.09.00

## Build Commands

### Build the project
```bash
./gradlew build
```

### Build debug APK
```bash
./gradlew assembleDebug
```

### Build release APK
```bash
./gradlew assembleRelease
```

### Clean build
```bash
./gradlew clean build
```

### Install debug build on connected device/emulator
```bash
./gradlew installDebug
```

## Testing

### Run all unit tests
```bash
./gradlew test
```

### Run unit tests for debug variant
```bash
./gradlew testDebugUnitTest
```

### Run all instrumented tests (requires connected device/emulator)
```bash
./gradlew connectedAndroidTest
```

### Run instrumented tests for debug
```bash
./gradlew connectedDebugAndroidTest
```

### Run a specific test class
```bash
./gradlew test --tests "com.gregzenkov.aichallenge.ExampleUnitTest"
```

### Run tests with coverage
```bash
./gradlew testDebugUnitTest jacocoTestReport
```

## Code Quality

### Check for lint issues
```bash
./gradlew lint
```

### Generate lint report
```bash
./gradlew lintDebug
```

## Architecture

### Project Structure
- **Single Activity Architecture**: MainActivity is the single entry point
- **Jetpack Compose**: 100% Compose UI (no XML layouts)
- **Material 3**: Using Material Design 3 components
- **Edge-to-Edge**: App uses edge-to-edge display mode

### Source Organization
```
app/src/main/java/com/gregzenkov/aichallenge/
├── MainActivity.kt          # Single activity, entry point
└── ui/
    └── theme/              # Compose theme configuration
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

### Key Components
- **MainActivity**: ComponentActivity with Compose setContent, uses edge-to-edge mode
- **Theme System**: Custom theme under `ui.theme` package using Material 3

### Dependencies Management
- Uses Gradle version catalogs (`gradle/libs.versions.toml`) for centralized dependency management
- All library versions are defined in the catalog file

### Build Configuration
- **Java 21** target/source compatibility
- **Kotlin JVM Target**: 21
- **ProGuard**: Configured for release builds but minification is currently disabled
- Uses latest Kotlin Compose compiler plugin (v2.0.21)

## Development Guidelines

### When Adding New Composables
- Place UI components in appropriate packages under `ui/`
- Use Material 3 components from `androidx.compose.material3`
- Follow the existing theme system in `ui.theme`
- Add `@Preview` annotations for composables to enable preview in Android Studio

### When Modifying Dependencies
- Update version numbers in `gradle/libs.versions.toml`
- Reference libraries using the version catalog in build files: `libs.library.name`

### Testing Structure
- **Unit tests**: `app/src/test/java/` - JUnit tests, no Android dependencies
- **Instrumented tests**: `app/src/androidTest/java/` - tests requiring Android framework/device
- Test runner: `androidx.test.runner.AndroidJUnitRunner`

### AI Assisted Guidelines

* Ты должен общаться на русском языке
* Разработка ведется на ОС Windows
