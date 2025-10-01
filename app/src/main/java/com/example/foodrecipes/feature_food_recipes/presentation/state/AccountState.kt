package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

data class AccountState(
    val currentMeals: List<MealItem> = emptyList<MealItem>()
)