package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

data class MealsByCategoryState (
    val meals: List<MealItem> = emptyList(),
    val category:String = "",
    val isLoading: Boolean = false,
    val error: String? = null

)