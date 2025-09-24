package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.event.FireStoreEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.FavouriteState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor():ViewModel() {
    private var _state = MutableStateFlow(FavouriteState())
    var state = _state.asStateFlow()
    private val fireStoreViewModel = FireStoreViewModel()

    init {
        viewModelScope.launch {
            val meals = fireStoreViewModel.getMealsForUser()
            _state.value = state.value.copy(listFavourite = meals)
            Log.d("Favourite", "List favourite: $meals")
        }
    }
}