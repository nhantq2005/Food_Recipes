package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.Category

data class CategoryState (
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)