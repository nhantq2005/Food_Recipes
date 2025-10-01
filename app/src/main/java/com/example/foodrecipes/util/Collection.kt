package com.example.foodrecipes.util

import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.FireStoreViewModel

sealed class Collections(
    val collectionName:String
){
    data object Meals:Collections("meals")
    data object Current:Collections("current")
}