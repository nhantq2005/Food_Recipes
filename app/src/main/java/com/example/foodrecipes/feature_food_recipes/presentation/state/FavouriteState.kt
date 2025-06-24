package com.example.foodrecipes.feature_food_recipes.presentation.state

import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

data class FavouriteState (
    val listFavourite: List<MealItem> = emptyList()
)