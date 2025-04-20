package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.FavouriteButton


@Composable
fun FavouriteScreen(
    navController: NavController
){
    BottomBar(
        navController
    ) {
        FavouriteButton()
    }
}

