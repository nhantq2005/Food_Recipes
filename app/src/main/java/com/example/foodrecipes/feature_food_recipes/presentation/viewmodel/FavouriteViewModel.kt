package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import com.example.foodrecipes.feature_food_recipes.presentation.event.FavouriteEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.FavouriteState
import com.example.foodrecipes.util.Collection
import com.example.foodrecipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val foodRecipesRepository: FoodRecipesRepository
) : ViewModel() {
    private var _state = MutableStateFlow(FavouriteState())
    var state = _state.asStateFlow()
    private var job: Job? = null

    init {

        job?.cancel()
        job = viewModelScope.launch {
            mealRepository.getMeals(Collection.Meals.collectionName).collect { meals ->
                _state.value = state.value.copy(listFavourite = meals)
                Log.d("Favourite", "List favourite: $meals")
            }
        }
    }

    fun onEvent(event: FavouriteEvent) {
        when (event) {
            is FavouriteEvent.DeleteMeal -> {
                mealRepository.deleteMeal(event.mealId)
                //Save recent meal
                getMealById(event.mealId)
            }

            FavouriteEvent.GetMeal -> {
                job?.cancel()
                job = viewModelScope.launch {
                    mealRepository.getMeals(Collection.Meals.collectionName).collect { meals ->
                        _state.value = state.value.copy(listFavourite = meals)
                    }
                }
            }

            is FavouriteEvent.FindMeal -> {
                job?.cancel()
                job = viewModelScope.launch {
                    mealRepository.findMeal(event.name).collect { meals ->
                        _state.value = state.value.copy(listFavourite = meals)
                    }
                }
            }

            is FavouriteEvent.EnteredKeyword -> {
                _state.value = state.value.copy(keyword = event.keyword)
            }

            FavouriteEvent.RestoreMeal -> {
                state.value.recentMeal?.let { mealRepository.addMeal(it) }

            }
        }
    }

    private fun getMealById(id: String) {
        job?.cancel()
        viewModelScope.launch {
            foodRecipesRepository.getMealById(id)
                .collect { result ->
                    when (result) {
                        is Result.Error<*> -> Unit

                        is Result.Loading<*> -> {
                            _state.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Result.Success<*> -> {
                            result.data?.let { meal ->
                                _state.update {
                                    it.copy(
                                        recentMeal = MealItem(
                                            idMeal = meal.idMeal,
                                            strMeal = meal.strMeal,
                                            strMealThumb = meal.strMealThumb
                                        ),
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }
}