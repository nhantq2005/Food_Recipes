package com.example.foodrecipes.feature_food_recipes.presentation.state

data class AuthState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)