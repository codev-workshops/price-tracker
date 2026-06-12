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
    api/            — Retrofit service (suspend funs), interceptors
    binding/        — Data-binding adapters & conversions
    data/           — DataBean, DataState, Error models
    di/             — Hilt modules (AppModule, ApiModule)
    extensions/     — Kotlin extension functions
    fcm/            — Firebase Cloud Messaging services
    model/          — Domain models (Product, Alarm, etc.)
    session/        — Session management
    ui/             — Activities, Fragments (MVVM), Compose screens
    util/           — Helpers (SharedPref, Encrypt, Alert, etc.)
  src/main/res/
    layout/         — XML layouts with AndroidX data binding
    navigation/     — Jetpack Navigation graph (nav_graph.xml)
  src/test/         — JUnit unit tests
  src/androidTest/  — Espresso instrumented tests
```

## Key Conventions

- **DI**: Hilt 2.52 with `@AndroidEntryPoint` on Activities/Fragments and `@HiltViewModel` on ViewModels
- **Fragments**: annotated with `@AndroidEntryPoint`; no manual injection calls
- **ViewModels**: annotated with `@HiltViewModel @Inject constructor(...)`, obtained via `ViewModelProvider(this).get(...)`
- **Async**: Kotlin Coroutines (`viewModelScope.launch`) — no RxJava
- **API layer**: Retrofit suspend functions returning models directly
- **Navigation**: Jetpack Navigation for main tabs (NavHostFragment in MainActivity); other screens use Activity-based navigation
- **UI**: Hybrid — XML Data Binding layouts (existing) + Jetpack Compose screens (new). Compose screens use `observeAsState()` on LiveData
- **Data Binding**: layouts use `<layout>` root; avoid `<import>` aliases — use FQCNs for variable types
- **Nullable params**: All `override` methods use non-nullable signatures matching modern AndroidX APIs
- **Firebase**: Uses `FirebaseMessaging.getInstance().token` (not deprecated `FirebaseInstanceId`)
- **Image loading**: Coil 2.7.0 (`coil-compose` for Compose, `coil` for XML views)

## Environment

- JDK 17, AGP 8.5.0, Kotlin 2.0.21, Gradle 8.7
- `google-services.json` is a placeholder (no real Firebase project)
- `local.properties` must contain `sdk.dir=<path-to-android-sdk>`
- compileSdk 35, minSdk 24, targetSdk 35
