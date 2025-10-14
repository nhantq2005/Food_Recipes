package com.example.foodrecipes.feature_food_recipes.presentation.event

import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

sealed class DetailEvent {
    data class changeOption(val option: String) : DetailEvent()
    data class addMeal(val mealItem: MealItem) : DetailEvent()
}