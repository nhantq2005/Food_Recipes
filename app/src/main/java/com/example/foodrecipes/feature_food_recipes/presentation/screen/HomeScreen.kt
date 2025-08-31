package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodrecipes.R
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.Chip
import com.example.foodrecipes.feature_food_recipes.presentation.components.LargeMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.components.SearchTextField
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.HomeEvent
import com.example.foodrecipes.feature_food_recipes.presentation.state.HomeState
import com.example.foodrecipes.feature_food_recipes.presentation.state.UserData
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.FireStoreViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.HomeViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.util.Responsive

@Composable
fun HomeScreen(
    navController: NavController,
    userData: UserData?
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val state by homeViewModel.state.collectAsState()
//    val fireStoreViewModel = hiltViewModel<FireStoreViewModel>()

    BottomBar(navController = navController) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(Responsive.scaledDp(16))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column{
                        Text(
                            text = "Hello ${userData?.username}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium, fontSize = Responsive.scaledSp(20)
                            )
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = stringResource(R.string.meal_question),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = Responsive.scaledSp(28),
                                color = Color.Black
                            )
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.home_meal),
                        contentDescription = "meal",
                        modifier = Modifier.size(Responsive.scaledDp(100))

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                SearchTextField(
                    state.searhWord,
                    onValueChange = {
                        homeViewModel.onEvent(HomeEvent.EnteredWord(it))
                        homeViewModel.onEvent(HomeEvent.OnSearchClick)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(10.dp)
            ) {
//                if (state.isLoading) {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        CircularProgressIndicator()
//                    }
//                }
//                else {
//                MealRecommendation(
//                    state = state,
//                    viewModel = homeViewModel,
//                    navController = navController
//                )
//                }
                if (state.searhWord != "")
                    MealsResult(state = state, navController = navController)
                else
                    MealRecommendation(
                        state = state,
                        viewModel = homeViewModel,
                        navController = navController
                    )
            }
        }
    }
}

@Composable
fun MealsResult(
    state: HomeState,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(state.meals) { mealItem ->
            SmallMealItem(mealItem = mealItem, navController = navController)
        }
    }
}

@Composable
fun MealRecommendation(
    state: HomeState,
    viewModel: HomeViewModel,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = { GridItemSpan(2) }, key = "header_today_meal") {
            Column {
                Text(
                    stringResource(R.string.today_meal),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = Responsive.scaledSp(20),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                state.randomMeal?.let { LargeMealItem(it, navController = navController) }
            }

        }

        item(span = { GridItemSpan(2) }) {
            Column {
                Text(
                    stringResource(R.string.aera),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = Responsive.scaledSp(20),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    items(state.area) { aera ->
                        Chip(
                            lablel = aera.strArea,
                            isSelected = aera.strArea == state.selectedArea,
                            onClick = {
                                viewModel.onEvent(HomeEvent.ChangeAera(area = aera.strArea))
                                Log.d("SELECTED AREA", state.selectedArea)
                            }
                        )
                    }
                }
            }
        }
        items(state.mealsByArea) { mealItem ->
            SmallMealItem(mealItem = mealItem, navController = navController)
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
fun PreviewMealRecommendation() {
    FoodRecipesTheme {
//        MealRecommendation()

    }
}