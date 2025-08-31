package com.example.foodrecipes.feature_food_recipes.presentation.event

sealed class FavouriteEvent {
    object getMeal:FavouriteEvent()
    data class deleteMeal(val mealId:String):FavouriteEvent()
}