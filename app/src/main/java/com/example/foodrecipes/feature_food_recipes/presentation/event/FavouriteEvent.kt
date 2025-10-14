package com.example.foodrecipes.feature_food_recipes.presentation.event

sealed class FavouriteEvent {
    data object GetMeal:FavouriteEvent()
    data class DeleteMeal(val mealId:String):FavouriteEvent()
    data class FindMeal(val name:String):FavouriteEvent()
    data class EnteredKeyword(val keyword:String):FavouriteEvent()
}