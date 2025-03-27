package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun FavouriteButton(){
    var isClicked by remember {
        mutableStateOf(false)
    }
    Icon(
        if (isClicked) {
            Icons.Default.Favorite
        }
        else{
            Icons.Default.FavoriteBorder
        },
        contentDescription = "Favourite Button",
        modifier = Modifier.clickable {
            isClicked = !isClicked
        },
        tint = Color(0xFFff3b5c)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewFavouriteButton(){
    FoodRecipesTheme {
        FavouriteButton()
    }
}