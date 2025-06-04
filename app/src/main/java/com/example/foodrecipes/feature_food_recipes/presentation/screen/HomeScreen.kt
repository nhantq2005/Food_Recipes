package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodrecipes.R
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.LargeMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.HomeEvent
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.HomeViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
//    val homeViewModel = hiltViewModel<HomeViewModel>()
//    val state by homeViewModel.state.collectAsState()
    BottomBar(navController) {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {
                Text(text = "Hello name")
                Text(text = stringResource(id = R.string.meail_question))
            }
//            Button(onClick = { homeViewModel.onEvent(HomeEvent.GetRandomMeal) }) {
//                Text("GET RANDOM MEAL")
//            }
//            AsyncImage(
//                state.randomMeal?.strMealThumb ?: "",
//                contentDescription = "Random Meal"
//            )
//            Text(
//                text = "ID: ${state.randomMeal?.idMeal}\n" +
//                        "Random Meal: ${state.randomMeal?.strMeal}\n" +
//                        "Category: ${state.randomMeal?.strCategory}"
//            )
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                state.area.forEach { area ->
//                    Spacer(modifier = Modifier.width(5.dp))
//                    FilterChip(
//                        selected = false,
//                        onClick = { /*TODO*/ },
//                        label = { Text(area.strArea) }
//                    )
//                }
//            }
//            state.randomMeal?.let { LargeMealItem(meal = it) }
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
    name = "Large Android Phone",
    device = "spec:width=480dp,height=960dp,dpi=480"
) // Ví dụ Galaxy S21 Ultra, Xiaomi Note 12 Pro+

// Extra Large Phones (Flagship, Ultra)
@Preview(
    name = "Extra Large Android Phone",
    device = "spec:width=540dp,height=1170dp,dpi=560"
) // Ví dụ Galaxy S23 Ultra, Pixel 7 Pro

@Preview(
    name = "Real Android Phone",
    device = "spec:width=407dp,height=904dp,dpi=560"
) // Ví dụ Galaxy S23 Ultra, Pixel 7 Pro
@Composable
private fun SignInScreenPreview() {
    FoodRecipesTheme(dynamicColor = false) {
        HomeScreen(
            navController = rememberNavController()
        )

    }
}