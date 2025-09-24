package com.example.foodrecipes.feature_food_recipes.presentation.event

sealed class DetailEvent {
    data class changeOption(val option: String) : DetailEvent()
}