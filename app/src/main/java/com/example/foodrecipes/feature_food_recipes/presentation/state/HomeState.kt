package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.Area
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

data class HomeState(
    val randomMeal: Meal? = null,
    val searhWord: String = "",
    val meals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val mealsByArea:List<MealItem> = emptyList(),
    val area:List<Area> = emptyList(),
    val selectedArea:String = ""
)
