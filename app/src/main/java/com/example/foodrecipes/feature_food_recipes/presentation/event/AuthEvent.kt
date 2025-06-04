package com.example.foodrecipes.feature_food_recipes.presentation.event

sealed class AuthEvent {
    object SignIn : AuthEvent()
    object SignOut : AuthEvent()
}