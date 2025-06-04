package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.domain.model.Category
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen

@Composable
fun CategoryItem(
    category: Category,
    navController: NavController
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(7.dp, RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(Screen.MealsByCategoryScreen.route
                + "?category=${category.strCategory}")
            },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = "Category Image",
                modifier = Modifier.height(100.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(15.dp))

            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = category.strCategory)
        }
    }

}