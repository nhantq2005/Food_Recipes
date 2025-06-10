package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen
import com.example.foodrecipes.util.Responsive

@Composable
fun SmallMealItem(
    mealItem: Any,
    navController: NavController
){
    var mealID:String = ""
    var mealName:String = ""
    var mealThumb:String = ""
    //Check datatype
    when (mealItem){
        is MealItem -> {
            mealID = mealItem.idMeal
            mealName = mealItem.strMeal
            mealThumb = mealItem.strMealThumb
        }
        is Meal -> {
            mealID = mealItem.idMeal
            mealName = mealItem.strMeal
            mealThumb = mealItem.strMealThumb
        }
    }
    //Card UI
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(Responsive.scaledDp(250))
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(
                    Screen.DetailScreen.route
                        + "?id=${mealID}"
                )
            },
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = mealThumb,
                contentDescription = "Meal Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = mealName,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = Responsive.scaledSp(18)
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    overflow = TextOverflow.Ellipsis)
                Icon(Icons.Default.AddCircleOutline, contentDescription = "Add Icon")
            }
        }
    }
}