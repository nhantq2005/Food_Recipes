package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.domain.model.MealItem
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.MealByCategoryEvent
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.MealsByCategoryViewModel
import com.example.foodrecipes.util.Responsive

@Composable
fun MealsByCategoryScreen(
    navController: NavController
) {
    val mealByCategoryViewModel = hiltViewModel<MealsByCategoryViewModel>()
    val state = mealByCategoryViewModel.state.collectAsState()

    BottomBar(
        navController = navController
    ) {
        if (state.value.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Fastfood,
                        contentDescription = "Grid Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(Responsive.scaledDp(40))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = state.value.category,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = Responsive.scaledSp(30),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(state.value.meals) { meal ->
                        SmallMealItem(mealItem = meal, navController = navController, icon = {
                            Icon(
                                Icons.Default.AddCircleOutline,
                                contentDescription = "Add Icon",
                                modifier = Modifier
                                    .clickable {
                                        mealByCategoryViewModel.onEvent(
                                            MealByCategoryEvent.AddMeal(
                                                MealItem(
                                                    idMeal = meal.idMeal,
                                                    strMeal = meal.strMeal,
                                                    strMealThumb = meal.strMealThumb,
                                                    timestamp = System.currentTimeMillis()
                                                )
                                            )
                                        )
                                    }
                            )
                        })
                    }
                }
            }
        }
    }
}