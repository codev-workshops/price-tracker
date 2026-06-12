# price-tracker
Price Tracking Application

This is a Kotlin Android project modernized to current AndroidX / Gradle tooling.

## Prerequisites

| Tool | Version |
|------|---------|
| JDK | 17 (OpenJDK) |
| Android SDK | Platform 33, Build-tools 33.0.2 |
| Gradle | 7.6 (wrapper included) |

## Setup

```bash
# Set environment
export ANDROID_HOME=/path/to/android-sdk
export JAVA_HOME=/path/to/java-17

# Install SDK components (if needed)
sdkmanager "platforms;android-33" "build-tools;33.0.2" "platform-tools"

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

* **Language**: Kotlin 1.8.22
* **Build**: AGP 7.4.2, Gradle 7.6
* **Architecture**: MVVM with AndroidX Lifecycle + ViewModelProvider
* **DI**: Dagger 2.48 (android-support with `HasAndroidInjector`)
* **Networking**: Retrofit 2.9 + OkHttp 4.11 + RxJava2
* **UI**: Data Binding, Material Design, Bottom Navigation, CoordinatorLayout
* **Firebase**: Cloud Messaging 23.2.1
* **Min SDK**: 21 / **Target SDK**: 33

## Modernization Notes

This project was migrated from a 2017-era Android setup:
- `android.support.*` → `androidx.*`
- `android.arch.lifecycle.*` → `androidx.lifecycle.*`
- Dagger `HasActivityInjector` / `HasSupportFragmentInjector` → `HasAndroidInjector`
- `ViewModelProviders.of()` → `ViewModelProvider()`
- Fabric / Crashlytics removed (replaced with no-op stubs)
- Firebase Cloud Messaging updated to modern API
- Fragment / RecyclerView nullable parameter signatures updated

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
