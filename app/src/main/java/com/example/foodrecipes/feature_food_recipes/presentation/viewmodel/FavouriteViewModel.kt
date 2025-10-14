package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import com.example.foodrecipes.feature_food_recipes.presentation.event.FavouriteEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.FavouriteState
import com.example.foodrecipes.util.Collection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val mealRepository: MealRepository
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
            }

            FavouriteEvent.GetMeal -> {
                job?.cancel()
                job = viewModelScope.launch {
                    mealRepository.getMeals(Collection.Meals.collectionName).collect { meals ->
                        _state.value = state.value.copy(listFavourite = meals)
                        Log.d("Favourite", "List favourite: $meals")
                    }
                }
            }

            is FavouriteEvent.FindMeal -> {
                job?.cancel()
                job = viewModelScope.launch {
                    mealRepository.findMeal(event.name).collect { meals ->
                        _state.value = state.value.copy(listFavourite = meals)
                        Log.d("Favourite", "List favourite: $meals")
                        Log.d("Favourite", "Name: ${state.value.keyword}")
                    }
                }
            }

            is FavouriteEvent.EnteredKeyword -> {
                _state.value = state.value.copy(keyword = event.keyword)
            }
        }
    }
}