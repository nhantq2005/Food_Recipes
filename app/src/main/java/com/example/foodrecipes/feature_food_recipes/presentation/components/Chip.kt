package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun Chip(){
    FilterChip(
        label = {
            Icon(Icons.Default.Check, contentDescription = null)
            Text(text = "A")},
        selected = true,
        onClick = {},

    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChip(){
    FoodRecipesTheme {
        Chip()
    }
}