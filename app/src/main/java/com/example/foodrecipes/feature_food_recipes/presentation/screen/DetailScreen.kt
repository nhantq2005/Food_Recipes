package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.presentation.components.IngredientItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state = viewModel.state.collectAsState()

    val ingredients = state.value.listIngredient.zip(state.value.listMeasure)
//    BottomBar(
//        navController = navController
//    ) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = state.value.meal?.strMealThumb,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            //Gradient Effect
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.DarkGray
                            ),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = state.value.meal?.strMeal.toString(),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(Modifier.height(15.dp))

        Spacer(Modifier.height(15.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//                items(state.value.listIngredient.zip(state.value.listMeasure)) { ingredient, measure ->
//                    IngredientItem(ingredient, measure)
//
//                }
            items(ingredients.size) { index ->
                IngredientItem(ingredients[index].first, ingredients[index].second)
            }

            item(span = { GridItemSpan(2) }) {
                Column {
                    Text(text = "Instructions")
                    Text(text = state.value.meal?.strInstructions.toString())
                }
            }
        }

//        }
        FloatingActionButton(onClick = {}) {
            Text(text = "Floating Action Button")
        }
    }
}