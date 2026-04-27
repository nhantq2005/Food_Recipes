
# Food Recipes App

Food Recipes is a modern Android application that empowers users to discover, search, and save recipes from various cuisines around the world. Built with Kotlin, Jetpack Compose, Hilt, and Firebase, the app leverages a robust architecture and the latest Android technologies to deliver a seamless and engaging user experience.

## Features

- Google Sign-In (Firebase Authentication)
- Browse recipes by country and category
- Search recipes by name
- View detailed recipe information: ingredients, instructions, images
- Save favorite recipes
- Access your list of favorite recipes
- Modern, responsive user interface

## Architecture & Technologies

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Dependency Injection:** Dagger Hilt
- **Networking:** Retrofit, OkHttp
- **Image Loading:** Coil
- **State Management:** ViewModel, StateFlow
- **Firebase:** Authentication, Firestore, Realtime Database
- **Navigation:** Navigation Compose

## Project Structure

- `app/src/main/java/com/example/foodrecipes/feature_food_recipes/presentation/` – UI, screens, ViewModels
- `app/src/main/java/com/example/foodrecipes/feature_food_recipes/domain/` – Models, repositories
- `app/src/main/java/com/example/foodrecipes/feature_food_recipes/data/` – Data sources, API integrations

## Getting Started

1. Clone the repository to your local machine
2. Open the project with Android Studio
3. Sync Gradle and build the project
4. Add your `google-services.json` file to the `app/` directory (if not already present)
5. Run the application on your device or emulator

## Contributing

Contributions are welcome! Please open a pull request or create an issue if you would like to contribute or report a bug.

