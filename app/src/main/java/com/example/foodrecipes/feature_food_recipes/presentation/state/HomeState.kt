package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.Meal

data class HomeState(
    val randomMeal: Meal? = null,
    val searhWord: String = "",
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
