package com.example.foodrecipes.feature_food_recipes.domain.repository

import com.example.foodrecipes.feature_food_recipes.domain.model.Category
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.util.Result
import kotlinx.coroutines.flow.Flow

interface FoodRecipesRepository {
    suspend fun searchMeal(
        name:String
    ):Flow<Result<Meal>>

    suspend fun getCategories():Flow<Result<List<Category>>>

    suspend fun getRandomMeal():Flow<Result<Meal>>

    suspend fun getMealsByCategory(
        category:String
    ):Flow<Result<List<MealItem>>>

    suspend fun getMealsByArea(
        area:String
    ):Flow<Result<List<MealItem>>>

}