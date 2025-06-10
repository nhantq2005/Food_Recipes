package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.event.HomeEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.HomeState
import com.example.foodrecipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            getArea()
            getRandomMeal()
            getMealsByArea(state.value.selectedArea)
            state.value.randomMeal?.let { Log.d("API", it.strCategory) }
        }

        Log.d("LIST", state.value.mealsByArea.toString())
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.EnteredWord -> {
                _state.value = state.value.copy(
                    searhWord = event.word
                )
            }

            HomeEvent.GetArea -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    getArea()
                }
            }

            is HomeEvent.GetMealByArea -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    getMealsByArea(event.area)
                }
            }

            HomeEvent.GetRandomMeal -> {

            }

            HomeEvent.OnSearchClick -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    searchMeal()
                }
            }

            //Change value of aera when user click other chip
            is HomeEvent.ChangeAera -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        selectedArea = event.area
                    )
                    getMealsByArea(event.area)
                }
            }
        }
    }

    //SEARCH MEAL
    private fun searchMeal() {
        viewModelScope.launch {
            foodRecipesRepository.searchMeal(state.value.searhWord)
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
                                    it.copy(meals = meal)
                                }
                            }
                        }
                    }
                }
        }
    }

    //GET RANDOM MEAL
    private fun getRandomMeal() {
        viewModelScope.launch {
            foodRecipesRepository.getRandomMeal()
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
                                    it.copy(randomMeal = meal)
                                }
                            }
                        }
                    }
                }
        }
    }

    //GET AREA
    private fun getArea() {
        viewModelScope.launch {
            foodRecipesRepository.getArea()
                .collect { result ->
                    when (result) {
                        is Result.Error<*> -> Unit

                        is Result.Loading<*> -> {
                            _state.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Result.Success<*> -> {
                            result.data?.let { area ->
                                _state.update {
                                    it.copy(area = area)
                                }
                            }
                            _state.update {
                                it.copy(selectedArea = it.area[0].strArea)
                            }
                            getMealsByArea(state.value.selectedArea)
                        }
                    }
                }
        }
    }

    //GET MEAL BY AREA
    private fun getMealsByArea(area: String) {
        viewModelScope.launch {
            foodRecipesRepository.getMealsByArea(area)
                .collect { result ->
                    when (result) {
                        is Result.Error<*> -> Unit

                        is Result.Loading<*> -> {
                            _state.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Result.Success<*> -> {
                            result.data?.let { meals ->
                                _state.update {
                                    it.copy(mealsByArea = meals)
                                }
                            }
                        }
                    }
                }
        }
    }
}