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

### Windows (используй эти команды)
```bash
gradlew.bat build
gradlew.bat assembleDebug
gradlew.bat assembleRelease
gradlew.bat clean build
gradlew.bat installDebug
gradlew.bat lint
gradlew.bat lintDebug
```

### Unix/macOS
```bash
./gradlew build
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew clean build
./gradlew installDebug
./gradlew lint
./gradlew lintDebug
```

## Architecture

### Multi-Module Architecture
Проект использует модульную архитектуру с разделением на фичи:
- **app** - главный модуль приложения (application module)
- **features:*** - feature-модули (library modules), каждый представляет отдельную фичу
- **build-logic** - convention plugins для единообразной конфигурации модулей

### Navigation & State Management (Decompose)
Проект использует **Decompose** (v3.2.0) - библиотеку для управления навигацией и состоянием компонентов:

- **RootComponent** (`app/component/RootComponent.kt`) - корневой компонент навигации
  - Управляет стеком навигации через `ChildStack`
  - Использует `kotlinx-serialization` для сериализации конфигурации навигации
  - Определяет sealed interface `Config` для типизированной навигации

- **Feature Components** - каждая фича имеет свой компонент (например, `HomeComponent`)
  - Интерфейс компонента находится в `api` пакете (публичный API фичи)
  - Реализация компонента находится в `internal` пакете (приватная реализация)
  - Factory method в companion object для создания компонента

### Feature Module Structure
Каждый feature-модуль организован следующим образом:
```
features/<feature-name>/
├── api/                    # Публичный API модуля
│   └── FeatureComponent.kt # Интерфейс компонента и @Composable экран
└── internal/               # Приватная реализация
    └── DefaultFeatureComponent.kt
```

### Single Activity Architecture
- **MainActivity** - единственная Activity, точка входа
- **Jetpack Compose** - 100% Compose UI (без XML layouts)
- **Material 3** - компоненты Material Design 3
- **Edge-to-Edge** - полноэкранный режим с поддержкой system bars

### Convention Plugins (build-logic)
Проект использует convention plugins для переиспользования конфигурации Gradle:
- **aichallenge.android.application** - конфигурация для app модуля
- **aichallenge.android.library** - конфигурация для library модулей
- **aichallenge.android.compose** - конфигурация Compose
- **ProjectConfig.kt** - централизованные настройки (SDK versions, Java version)

### Dependencies Management
- **Gradle Version Catalog** (`gradle/libs.versions.toml`) - централизованное управление зависимостями
- Все версии библиотек определены в каталоге
- Доступ к зависимостям через `libs.library.name`

### Build Configuration
- **Java 21** target/source compatibility
- **Kotlin JVM Target**: 21
- **Kotlin Serialization** включен для Decompose
- **ProGuard**: настроен для release, но minification отключен

## Development Guidelines

### Adding New Feature Module
1. Создай новый модуль в `features/` директории
2. Добавь модуль в `settings.gradle.kts`: `include(":features:feature-name")`
3. Используй convention plugins в `build.gradle.kts`:
   ```kotlin
   plugins {
       id("aichallenge.android.library")
       id("aichallenge.android.compose")
       alias(libs.plugins.kotlin.serialization)
   }
   ```
4. Создай структуру `api/` и `internal/` пакетов
5. Создай интерфейс компонента в `api/` с factory method
6. Создай реализацию компонента в `internal/`
7. Создай `@Composable` функцию экрана в `api/`

### Adding Navigation to New Screen
1. Добавь новый тип в `RootComponent.Config` sealed interface
2. Добавь новый sealed class в `RootComponent.Child`
3. Обнови функцию `child()` в `DefaultRootComponent`
4. Обнови `Children` в `MainActivity` для обработки нового child
5. Используй `StackNavigation.navigate()` для навигации к новому экрану

### When Adding New Composables
- Размещай UI компоненты в соответствующих пакетах feature-модулей
- Используй Material 3 компоненты из `androidx.compose.material3`
- Следуй существующей теме в `app/ui/theme`
- Добавляй `@Preview` аннотации для preview в Android Studio

### When Modifying Dependencies
- Обновляй версии в `gradle/libs.versions.toml`
- Ссылайся на библиотеки через version catalog: `libs.library.name`
- Для общих зависимостей используй convention plugins в `build-logic`

### AI Assisted Guidelines
* Ты должен общаться на русском языке
* Разработка ведется на ОС Windows
* Используй `gradlew.bat` вместо `./gradlew` для команд Gradle
* Не оставляй НИКАКИХ комментариев в коде