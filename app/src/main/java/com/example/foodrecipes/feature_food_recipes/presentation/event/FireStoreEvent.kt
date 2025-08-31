package com.example.foodrecipes.feature_food_recipes.presentation.event

import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem

sealed class FireStoreEvent {
    data class AddMeal(val meal:MealItem):FireStoreEvent()
    data class DeleteMeal(val mealId:String):FireStoreEvent()
    object GetMeal:FireStoreEvent()
}