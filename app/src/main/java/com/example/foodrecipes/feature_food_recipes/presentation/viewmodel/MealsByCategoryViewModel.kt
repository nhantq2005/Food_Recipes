package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import androidx.annotation.OptIn
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.state.MealsByCategoryState
import com.example.foodrecipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsByCategoryViewModel @OptIn(UnstableApi::class)
@Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _state = MutableStateFlow(MealsByCategoryState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    category = category
                )
                getMealsByCategory()
            }
        }
    }

    private fun getMealsByCategory() {
        job?.cancel()
        job = viewModelScope.launch {
            foodRecipesRepository.getMealsByCategory(state.value.category)
                .collect { result ->
                    when (result) {
                        is Result.Error<*> -> Unit
                        is Result.Loading<*> -> {
                            _state.value = state.value.copy(
                                isLoading = result.isLoading
                            )
                        }

                        is Result.Success<*> -> {
                            result.data?.let { meals ->
                                _state.update {
                                    it.copy(meals = meals)
                                }
                            }
                        }
                    }
                }
        }
    }
}