package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.presentation.components.IngredientItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun DetailScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    val ingredients = state.value.listIngredient.zip(state.value.listMeasure)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.value.meal?.strYoutube))
                context.startActivity(intent)
            }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "play icon")
                    Text(text = "Watch video")
                }

            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(10.dp)
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
        }
    }
}

// Small Phones (~5.0" - 5.5")
@Preview(
    name = "Small Android Phone",
    device = "spec:width=360dp,height=800dp,dpi=270",

    ) // Ví dụ Pixel 4a, Galaxy A10

// Medium Phones (~6.1" - 6.4") - Phổ biến nhất
@Preview(
    name = "Medium Android Phone",
    device = "spec:width=411dp,height=891dp,dpi=420",
) // Ví dụ Galaxy S21, Pixel 6, Xiaomi Redmi Note 11

// Large Phones (~6.7" - 6.9")
@Preview(
    name = "Large Android Phone", device = "spec:width=480dp,height=960dp,dpi=480"
) // Ví dụ Galaxy S21 Ultra, Xiaomi Note 12 Pro+

// Extra Large Phones (Flagship, Ultra)
@Preview(
    name = "Extra Large Android Phone", device = "spec:width=540dp,height=1170dp,dpi=560"
) // Ví dụ Galaxy S23 Ultra, Pixel 7 Pro

@Preview(
    name = "Real Android Phone", device = "spec:width=407dp,height=904dp,dpi=560"
) // Ví dụ Galaxy S23 Ultra, Pixel 7 Pro
@Composable
private fun HomeScreenPreview() {
    FoodRecipesTheme(dynamicColor = false) {
//        HomeScreen(
//            navController = rememberNavController()
//        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    FoodRecipesTheme {
        DetailScreen(rememberNavController())

    }
}