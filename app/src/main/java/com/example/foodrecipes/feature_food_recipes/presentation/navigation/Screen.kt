package com.example.foodrecipes.feature_food_recipes.presentation.navigation

sealed class Screen(
    val route: String,
    val label: String,
) {
    data object HomeScreen : Screen("home_screen", "Home")
    data object CategoryScreen:Screen("category_screen","Category")
    data object FavouriteScreen:Screen("favourite_screen","Favourite")
    data object AccountScreen:Screen("account_screen","Account")
    data object DetailScreen:Screen("detail_screen","Detail")
    data object LoginScreen:Screen("login_screen","Login")
    data object MealsByCategoryScreen:Screen("meals_by_category_screen","MealsByCategory")
}