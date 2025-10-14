package com.example.foodrecipes.feature_food_recipes.domain.repository

import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    fun getMeals(collection: String): Flow<List<MealItem>>
    fun addMeal(meal: MealItem)
    fun deleteMeal(mealId: String)
    fun addRecentMeal(meal: Meal)
    fun findMeal(name: String): Flow<List<MealItem>>
}