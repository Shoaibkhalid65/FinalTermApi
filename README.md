# Products API Android

A modern Android application that fetches and displays user data from a REST API. Built with **Jetpack Compose**, **Retrofit**, and **Material Design 3**, this project demonstrates clean networking patterns, reactive UI, and advanced Compose animations.

---

## Features

- **REST API Integration** – Fetches user data from [Fake Store API](https://fakestoreapi.com/users) using Retrofit 3
- **Jetpack Compose UI** – Fully declarative UI built with Material3 components
- **Search Bar** – Collapsible animated search bar in the top app bar
- **Category Filtering** – Filter chips to narrow content by category
- **Favorite Animation** – Spring-physics heart icon animation with scale and color transitions
- **Blur Effects** – Conditional blur via `RenderEffect` (Android 12+) with a graceful fallback
- **Curved Row Layout** – Custom composable that distributes items along a quadratic Bézier curve using `PathMeasure`
- **Dynamic Color** – Material You dynamic color support on Android 12+

---

## Architecture & Tech Stack

| Layer | Technology |
|-------|-----------|
| UI | Jetpack Compose, Material3 |
| Networking | Retrofit 3, OkHttp Logging Interceptor |
| Serialization | KotlinX Serialization |
| Image Loading | Coil |
| Async | Kotlin Coroutines (`Dispatchers.IO`) |
| Navigation | Jetpack Navigation Compose |

### Key Files

```
app/src/main/java/com/example/finaltermapi/
├── MainActivity.kt          # Entry point; sets up edge-to-edge Compose content
├── HomeScreen.kt            # Main screen: grid of users, search bar, filter chips
├── ApiService.kt            # Retrofit interface (GET /users)
├── RetrofitClint.kt         # Retrofit singleton with OkHttp logging
├── Model.kt                 # Data classes: User, Product, Rating
├── FavoriteAnimation.kt     # Heart icon with spring-physics animation
├── BlurPlayGround.kt        # Blur effect demo (RenderEffect / fallback)
├── CurveyRowPlayground.kt   # Items laid out along a Bézier curve
└── ui/theme/
    ├── Color.kt             # Custom color palette
    ├── Theme.kt             # Light/dark MaterialTheme with dynamic colors
    └── Type.kt              # Material3 typography
```

---

## Getting Started

### Prerequisites

- Android Studio **Hedgehog (2023.1.1)** or newer
- JDK 11+
- Android device or emulator running **API 26 (Android 8.0)** or higher

### Clone & Run

```bash
git clone https://github.com/Shoaibkhalid65/products-api-android.git
cd products-api-android
```

Open the project in Android Studio, let Gradle sync, then click **Run ▶**.

---

## API

The app calls the public [Fake Store API](https://fakestoreapi.com):

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/users` | Returns a list of user objects |

**User model fields:** `id`, `username`, `email`, `password`

---

## Build Configuration

| Property | Value |
|----------|-------|
| Min SDK | 26 (Android 8.0) |
| Target SDK | 36 |
| Compile SDK | 36 |
| Kotlin | 2.2.21 |
| AGP | 8.13.2 |
| Compose BOM | 2025.12.01 |

---

## License

This project is open source and available for educational use.
