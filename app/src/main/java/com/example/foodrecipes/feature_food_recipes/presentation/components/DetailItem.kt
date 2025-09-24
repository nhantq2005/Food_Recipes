package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.runtime.Composable

data class DetailItem(
    val label:String,
    val content: @Composable () -> Unit
)