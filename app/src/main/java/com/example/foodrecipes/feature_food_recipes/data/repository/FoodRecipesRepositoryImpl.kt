package com.example.foodrecipes.feature_food_recipes.data.repository

import coil.network.HttpException
import com.example.foodrecipes.feature_food_recipes.data.model.api.FoodRecipesApi
import com.example.foodrecipes.feature_food_recipes.data.model.mapper.toCategory
import com.example.foodrecipes.feature_food_recipes.data.model.mapper.toMeal
import com.example.foodrecipes.feature_food_recipes.data.model.mapper.toMealItem
import com.example.foodrecipes.feature_food_recipes.domain.model.Category
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class FoodRecipesRepositoryImpl @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) : FoodRecipesRepository {
    override suspend fun searchMeal(
        name: String
    ): Flow<Result<Meal>> {
        return flow {
            emit(Result.Loading(true))

            val remoteMealResultDto = try {
                foodRecipesApi.searchMeal(name)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            }

            remoteMealResultDto?.let { mealResultDto ->
                mealResultDto.meals?.let { meals ->
                    emit(Result.Success(meals[0].toMeal()))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getCategories(): Flow<Result<List<Category>>> {
        return flow {
            emit(Result.Loading(true))

            val remoteCategoryResultDto = try {
                foodRecipesApi.getCategories()
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            }

            remoteCategoryResultDto?.let { categoryResultDto ->
                categoryResultDto.categories?.let { categories ->
                    emit(Result.Success(categories.map { it.toCategory() }))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
        }
    }

    override suspend fun getRandomMeal(): Flow<Result<Meal>> {
        return flow {
            emit(Result.Loading(true))

            val remoteMealResultDto = try {
                foodRecipesApi.getRandomMeal()
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            }

            remoteMealResultDto?.let { mealResultDto ->
                mealResultDto.meals?.let { meals ->
                    emit(Result.Success(meals[0].toMeal()))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getMealsByCategory(
        category: String
    ): Flow<Result<List<MealItem>>> {
        return flow {
            emit(Result.Loading(true))

            val remoteMealResultDto = try {
                foodRecipesApi.getMealsByCategory(category)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            }

            remoteMealResultDto?.let { mealResultDto ->
                mealResultDto.meals?.let { meals ->
                    emit(Result.Success(meals.map { it.toMealItem() }))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
            emit(Result.Loading(false))
        }
    }

    override suspend fun getMealsByArea(
        area: String
    ): Flow<Result<List<MealItem>>> {
        return flow {
            emit(Result.Loading(true))

            val remoteMealResultDto = try {
                foodRecipesApi.getMealsByArea(area)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Error"))
                emit(Result.Loading(false))
                return@flow
            }

            remoteMealResultDto?.let { mealResultDto ->
                mealResultDto.meals?.let { meals ->
                    emit(Result.Success(meals.map { it.toMealItem() }))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
            emit(Result.Loading(false))
        }
    }

}