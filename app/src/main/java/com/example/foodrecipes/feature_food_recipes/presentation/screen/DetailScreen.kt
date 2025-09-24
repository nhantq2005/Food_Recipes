package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Crop
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.presentation.components.Chip
import com.example.foodrecipes.feature_food_recipes.presentation.components.DetailItem
import com.example.foodrecipes.feature_food_recipes.presentation.components.IngredientItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.DetailEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.DetailState
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.util.Responsive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    val detailItems = remember {
        listOf(
            DetailItem(
                label = "Ingredients",
                content = { Ingredients(state = state) }
            ),
            DetailItem(
                label = "Instructions",
                content = { InstructionItem(state = state) }
            )
        )
    }

    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
//                .padding(10.dp)
        ) {
            Box {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .shadow(5.dp, shape = RoundedCornerShape(10.dp)),
                    model = state.value.meal?.strMealThumb,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "Back",
                        modifier = Modifier
                            .clickable {
                                navController.navigateUp()
                            }
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            )
                            .padding(Responsive.scaledDp(6)),
                        tint = MaterialTheme.colorScheme.onSurface

                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Button(onClick = {
                            val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(state.value.meal?.strYoutube))
                            context.startActivity(intent)
                        }, contentPadding = PaddingValues(Responsive.scaledDp(5))) {
                            Icon(Icons.Default.PlayCircleOutline, contentDescription = "play icon")
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = "Watch video")
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            Icons.Default.AddCircleOutline, contentDescription = "Add fav meal",
                            modifier = Modifier
                                .clickable {
//                                navController.navigateUp()
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = CircleShape
                                )
                                .padding(Responsive.scaledDp(6)),
                            tint = MaterialTheme.colorScheme.onSurface

                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = state.value.meal?.strMeal.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Category: ${state.value.meal?.strCategory}",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Area: ${state.value.meal?.strArea}",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                detailItems.forEach { detailItem ->

                    Chip(
                        isSelected = state.value.selectedOption == detailItem.label,
                        lablel = detailItem.label.toString(),
                        isShowIcon = false,
                        onClick = {
                            viewModel.onEvent(DetailEvent.changeOption(detailItem.label))
                        }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }
//            Spacer(Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
//            detailItems.forEach { detailItem ->
//                if (detailItem.label == state.value.selectedOption) {
//                    detailItem.content()
//                }
//            }
                when (state.value.selectedOption) {
                    "Ingredients" -> Ingredients(state)
                    "Instructions" -> InstructionItem(state)
                }
            }
        }


    }
}


@Composable
fun Ingredients(state: State<DetailState>) {
    val ingredients = state.value.listIngredient.zip(state.value.listMeasure)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(Responsive.scaledDp(10)),
        horizontalArrangement = Arrangement.spacedBy(Responsive.scaledDp(7)),
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(ingredients.size) { index ->
            IngredientItem(ingredients[index].first, ingredients[index].second)
        }
    }
}

@Composable
fun InstructionItem(state: State<DetailState>) {
    LazyColumn(
        modifier = Modifier.padding(10.dp)
    ) {
        item {
//            Text(text = "Instructions",
//                style = MaterialTheme.typography.bodyMedium.copy(
//                    fontSize = Responsive.scaledSp(25)
//                ))
//            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = state.value.meal?.strInstructions.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = Responsive.scaledSp(17)
                )
            )
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