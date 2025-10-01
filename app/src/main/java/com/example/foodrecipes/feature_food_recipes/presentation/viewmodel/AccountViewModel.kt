package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.presentation.state.AccountState
import com.example.foodrecipes.util.Collections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountViewModel:ViewModel() {
    private val _state = MutableStateFlow(AccountState())
    val state = _state.asStateFlow()
    val fireStoreViewModel = FireStoreViewModel()

    init {
        viewModelScope.launch {
            val meals = fireStoreViewModel.getMealsForUser(Collections.Current.collectionName)
            _state.value = state.value.copy(currentMeals = meals)
            Log.d("CurrentMeal", "List current: $meals")
        }
    }
}