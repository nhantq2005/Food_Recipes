package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.state.CategoryState
import com.example.foodrecipes.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) : ViewModel() {
    val _state = MutableStateFlow(CategoryState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {
            getCategories()
        }

    }

    private fun getCategories() {
        viewModelScope.launch {
            foodRecipesRepository.getCategories()
                .collect { result ->
                    when (result) {
                        is Result.Error<*> -> Unit
                        is Result.Loading<*> -> {
                            _state.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }

                        is Result.Success<*> -> {
                            result.data?.let { categories ->
                                _state.update {
                                    it.copy(categories = categories)
                                }
                            }
                        }
                    }
                }
        }
    }
}