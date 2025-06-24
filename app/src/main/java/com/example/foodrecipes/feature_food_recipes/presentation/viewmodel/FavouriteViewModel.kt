package com.example.foodrecipes.feature_food_recipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.domain.repository.FoodRecipesRepository
import com.example.foodrecipes.feature_food_recipes.presentation.state.FavouriteState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor():ViewModel() {
    private val _state = MutableStateFlow(FavouriteState())
    val state = _state.asStateFlow()

    fun getMealList(){
        val db = Firebase.firestore

        db.collection("foodrecipesdb").addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if(value != null){
                val listFavourite = value.toObjects(MealItem::class.java)
                _state.value = state.value.copy(
                    listFavourite = listFavourite
                )
            }
        }
    }
}