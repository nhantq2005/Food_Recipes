package com.example.foodrecipes.feature_food_recipes.presentation.event

import androidx.lifecycle.ViewModel
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class MealByCategoryEvent {
    data class AddMeal(val meal: MealItem) : MealByCategoryEvent()
}