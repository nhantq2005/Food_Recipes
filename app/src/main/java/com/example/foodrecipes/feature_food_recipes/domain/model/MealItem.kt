package com.example.foodrecipes.feature_food_recipes.domain.model

import com.google.type.DateTime
import java.time.LocalDateTime

data class MealItem(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val timestamp: Long = 0
)