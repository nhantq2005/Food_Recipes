package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.Meal

data class DetailState(
    val id:String="",
    val selectedOption:String = "Ingredients",
    val meal: Meal? = null,
    val isLoading:Boolean = false,
    val listIngredient:List<String> = emptyList(),
    val listMeasure:List<String> = emptyList()

)