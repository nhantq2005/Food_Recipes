package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.data.model.mapper.getIngredients
import com.example.foodrecipes.feature_food_recipes.data.model.mapper.getMeasures
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.state.DetailState
import com.example.foodrecipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    init {

        savedStateHandle.get<String>("id")?.let { id ->
            viewModelScope.launch {
                _state.value = state.value.copy(
                    id = id
                )
                getMealById()
            }

        }


    }


    private fun getMealById() {
        job?.cancel()
        viewModelScope.launch {
            foodRecipesRepository.getMealById(state.value.id)
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
                                    it.copy(meal = meal, listIngredient = meal.getIngredients(), listMeasure = meal.getMeasures())
                                }
                            }
                            Log.d("DetailViewModel", "getMealById: ${state.value.listIngredient}")
                        }
                    }
                }
        }
    }
}