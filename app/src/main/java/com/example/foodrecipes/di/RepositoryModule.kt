package com.example.foodrecipes.di

import com.example.foodrecipes.feature_food_recipes.data.repository.FoodRecipesRepositoryImpl
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFoodRecipesRepository(
        foodRecipesRepositoryImpl: FoodRecipesRepositoryImpl
    ): FoodRecipesRepository

}