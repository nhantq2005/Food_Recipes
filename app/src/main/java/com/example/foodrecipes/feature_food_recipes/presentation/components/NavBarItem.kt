package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val label: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectIcon: ImageVector,
    val isSelect: Boolean
)