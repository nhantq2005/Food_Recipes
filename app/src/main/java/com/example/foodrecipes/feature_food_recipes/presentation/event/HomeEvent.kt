package com.example.foodrecipes.feature_food_recipes.presentation.event

sealed class HomeEvent {
    data class EnteredWord(val word: String) : HomeEvent()
    data class ChangeAera(val area: String) : HomeEvent()
    data object OnSearchClick : HomeEvent()
    data class GetMealByArea(val area: String) : HomeEvent()
    data object GetArea : HomeEvent()
    data object GetRandomMeal: HomeEvent()
}