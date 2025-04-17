package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val foodRecipesRepository: FoodRecipesRepository
): ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()


}