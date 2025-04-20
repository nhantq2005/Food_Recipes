package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen

@Composable
fun AccountScreen(
    navController: NavController
){
    BottomBar(
        navController
    ) {
        Text(text = "Account Screen")
    }
}