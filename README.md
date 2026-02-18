# BostaTask

Android app that lists video games by genre and shows game details using the [RAWG API](https://rawg.io/apidocs).

---
### Tech stack

- Kotlin 2.2.21, Jetpack Compose, Material 3.
- Navigation Compose, Koin (DI), Retrofit + OkHttp + Gson.
- Coil, Paging 3, Coroutines, flow.
- Min SDK 26.

---

### Architecture choice

- I used MVI because it makes UI state predictable and easy to handle with Compose.

---

### Assumptions / shortcuts

- Added trailers and screenshots section in the Game Details screen.
- Search filters the current list only (no API search).
- Simple error messages and retry button when api call get error.
- The application supports both Light Mode and Dark Mode.
- No offline or caching; needs internet.
- API key in `local.properties`; no key = app won’t load data.
---

## Setup

1. Clone the repo.
2. Add `RAWG_API_KEY=your_key` to `local.properties` (get a key at [RAWG](https://rawg.io/apidocs)).
3. Sync Gradle and run on a device or emulator (min SDK 26).
