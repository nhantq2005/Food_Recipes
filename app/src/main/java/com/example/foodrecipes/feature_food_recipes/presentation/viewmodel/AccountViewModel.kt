package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.repository.MealRepository
import com.example.foodrecipes.feature_food_recipes.presentation.state.AccountState
import com.example.foodrecipes.util.Collection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AccountState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            mealRepository.getMeals(Collection.Current.collectionName).collect { meals ->
                _state.value = state.value.copy(currentMeals = meals)
                Log.d("CurrentMeal", "List current: $meals")
            }
        }
    }
}