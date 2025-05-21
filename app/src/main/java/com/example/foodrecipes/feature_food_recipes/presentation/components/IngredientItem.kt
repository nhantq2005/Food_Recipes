package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.domain.util.convertIngredientName

@Composable
fun IngredientItem(strIngredient: String, strMeasure: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .shadow(5.dp, RoundedCornerShape(5.dp)),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = "https://www.themealdb.com/images/ingredients/${strIngredient}.png",
                contentDescription = "Ingredient Image",
                modifier = Modifier.padding(10.dp)

            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(strIngredient)
                Spacer(modifier = Modifier.height(5.dp))
                Text(strMeasure)

            }
        }
    }
}