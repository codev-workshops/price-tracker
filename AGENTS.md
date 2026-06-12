# AGENTS.md — price-tracker

## Build & Run

```bash
export ANDROID_HOME=/home/ubuntu/android-sdk
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

./gradlew assembleDevDebug          # Debug APK
./gradlew testDevDebugUnitTest      # Unit tests
./gradlew lint                      # Lint (warnings only, abortOnError=false)
```

## Project Layout

```
app/
  src/main/java/com/raqun/android/
    api/            — Retrofit service, interceptors
    binding/        — Data-binding adapters & conversions
    data/           — DataBean, DataState, Error models
    di/             — Dagger component, modules (HasAndroidInjector pattern)
    extensions/     — Kotlin extension functions
    fcm/            — Firebase Cloud Messaging services
    model/          — Domain models (Product, Alarm, etc.)
    session/        — Session management
    ui/             — Activities & Fragments (MVVM)
    util/           — Helpers (SharedPref, Encrypt, Alert, etc.)
    viewmodel/      — VMFactory, ViewModels
  src/main/res/     — Layouts use AndroidX data binding
  src/test/         — JUnit unit tests
  src/androidTest/  — Espresso instrumented tests
```

## Key Conventions

- **DI**: Dagger 2.48 with `HasAndroidInjector` (not legacy `HasActivityInjector`)
- **Fragments** inject via `AndroidSupportInjection.inject(this)` in `onCreate`
- **ViewModels** obtained via `ViewModelProvider(this, vmFactory).get(…)`
- **Data Binding**: layouts use `<layout>` root; avoid `<import>` aliases — use FQCNs for variable types
- **Nullable params**: All `override` methods use non-nullable signatures matching modern AndroidX APIs
- **Fabric/Crashlytics**: Removed. `FabricUtil.kt` contains no-op stubs
- **Firebase**: Uses `FirebaseMessaging.getInstance().token` (not deprecated `FirebaseInstanceId`)

## Environment

- JDK 17, AGP 7.4.2, Kotlin 1.8.22, Gradle 7.6
- `google-services.json` is a placeholder (no real Firebase project)
- `local.properties` must contain `sdk.dir=<path-to-android-sdk>`
