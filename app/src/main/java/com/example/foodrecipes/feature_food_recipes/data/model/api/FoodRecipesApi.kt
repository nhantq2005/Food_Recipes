package com.example.foodrecipes.feature_food_recipes.data.model.api

import com.example.foodrecipes.feature_food_recipes.data.model.dto.AreaResultDto
import com.example.foodrecipes.feature_food_recipes.data.model.dto.CategoryResultDto
import com.example.foodrecipes.feature_food_recipes.data.model.dto.MealItemResultDto
import com.example.foodrecipes.feature_food_recipes.data.model.dto.MealResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipesApi {
    //Tìm kiếm theo tên
    @GET("search.php")
    suspend fun searchMeal(
        @Query("s") mealName:String
    ):MealResultDto?

    //Lấy danh sách category
    @GET("categories.php")
    suspend fun getCategories(): CategoryResultDto?

    //Món ngẫu nhiên
    @GET("random.php")
    suspend fun getRandomMeal(): MealResultDto?

    //Lọc theo category
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ):MealItemResultDto?

    //Lọc theo vùng
    @GET("filter.php")
    suspend fun getMealsByArea(
        @Query("a") area: String
    ): MealItemResultDto?

    @GET("list.php?a=list")
    suspend fun getArea(): AreaResultDto?

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): MealResultDto?

    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }
}