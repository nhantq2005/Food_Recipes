package com.example.foodrecipes.feature_food_recipes.data.model.api

import com.example.foodrecipes.feature_food_recipes.data.model.dto.CategoryResultDto
import com.example.foodrecipes.feature_food_recipes.data.model.dto.MealItemResultDto
import com.example.foodrecipes.feature_food_recipes.data.model.dto.MealResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipesApi {
    @GET("search.php")
    suspend fun searchMeal(
        @Query("s") mealName:String
    ):MealResultDto

    @GET("categories.php")
    suspend fun getCategories(): CategoryResultDto

    @GET("random.php")
    suspend fun getRandomMeal(): MealResultDto

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ):MealItemResultDto

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}