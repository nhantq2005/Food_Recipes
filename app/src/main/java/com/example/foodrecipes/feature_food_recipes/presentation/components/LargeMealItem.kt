package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal

@Composable
fun LargeMealItem(
    meal: Meal,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
//            .padding(vertical = 10.dp)
    ) {
        Row(
//            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = meal.strMeal)
            }
        }
    }
}
