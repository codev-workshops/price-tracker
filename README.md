# price-tracker
Price Tracking Application

This is a Kotlin Android project modernized to current AndroidX / Jetpack tooling.

## Prerequisites

| Tool | Version |
|------|---------|
| JDK | 17 (OpenJDK) |
| Android SDK | Platform 35, Build-tools 35.0.0 |
| Gradle | 8.7 (wrapper included) |

## Setup

```bash
# Set environment
export ANDROID_HOME=/path/to/android-sdk
export JAVA_HOME=/path/to/java-17

# Install SDK components (if needed)
sdkmanager "platforms;android-35" "build-tools;35.0.0" "platform-tools"

# Create local.properties
echo "sdk.dir=$ANDROID_HOME" > local.properties

# Build
./gradlew assembleDevDebug

# Run unit tests
./gradlew testDevDebugUnitTest
```

## Build Variants

| Flavor | Suffix | Description |
|--------|--------|-------------|
| dev | `.dev` | Development |
| beta | `.beta` | Beta |
| prod | *(none)* | Production |

## Tech Stack

* **Language**: Kotlin 2.0.21
* **Build**: AGP 8.5.0, Gradle 8.7
* **Architecture**: MVVM with Jetpack Lifecycle, Hilt DI, Jetpack Navigation
* **DI**: Hilt 2.52 (`@AndroidEntryPoint`, `@HiltViewModel`)
* **Networking**: Retrofit 2.11.0 + OkHttp 4.12.0 + Kotlin Coroutines 1.9.0
* **UI**: Hybrid — XML Data Binding + Jetpack Compose (Material3)
* **Navigation**: Jetpack Navigation 2.8.4 (main tabs via NavHostFragment)
* **Image Loading**: Coil 2.7.0 (coil-compose for Compose screens)
* **Firebase**: Cloud Messaging 24.1.0
* **Min SDK**: 24 / **Target SDK**: 35

## Modernization History

This project was migrated from a 2017-era Android setup through multiple phases:
- **Phase 1**: Build system — AGP 3.0 → 8.5.0, Kotlin 1.1 → 2.0.21, Gradle 4.x → 8.7, SDK 26 → 35
- **Phase 2**: AndroidX — `android.support.*` → `androidx.*`, `android.arch.lifecycle.*` → `androidx.lifecycle.*`
- **Phase 3**: Libraries — Picasso → Coil, Fabric → Firebase Crashlytics, Retrofit/OkHttp bump
- **Phase 4**: DI — Dagger 2.x → Hilt 2.52
- **Phase 5**: Async — RxJava2 → Kotlin Coroutines
- **Phase 6**: Navigation — Activity-based → Jetpack Navigation (main tabs)
- **Phase 7**: UI — Jetpack Compose screens added alongside existing XML layouts

## Screenshots

<img src="https://raw.githubusercontent.com/savepopulation/price-tracker/master/art/home.png"
height="384" width="210"> <img src="https://github.com/savepopulation/price-tracker/blob/master/art/product_detail.png"
height="384" width="210"> <img src="https://raw.githubusercontent.com/savepopulation/price-tracker/master/art/notifications.png"
height="384" width="210"> <img src="https://raw.githubusercontent.com/savepopulation/price-tracker/master/art/more.png"
height="384" width="210">

## License

MIT License

Copyright (c) 2017 Taylan Sbrcn

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
