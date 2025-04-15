package com.example.foodrecipes.util

sealed class Screen(
    val route: String,
    val label: String,
) {
    object HomeScreen : Screen("home_screen", "Home")
    object CategoryScreen:Screen("category_screen","Category")
    object FavouriteScreen:Screen("favourite_screen","Favourite")
    object AccountScreen:Screen("account_screen","Account")
    object DetailScreen:Screen("detail_screen","Detail")
    object LoginScreen:Screen("login_screen","Login")
    object MealByCategoryScreen:Screen("meal_by_category_screen","MealByCategory")
}