package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.CategoryItem
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.MealsByCategoryViewModel

@Composable
fun MealsByCategoryScreen(
    navController: NavController
){
    val viewModel = hiltViewModel<MealsByCategoryViewModel>()
    val state = viewModel.state.collectAsState()

    BottomBar(
        navController = navController
    ) {
        if(state.value.isLoading){
            CircularProgressIndicator()
        }
        else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                items(state.value.meals) { meal ->
                    SmallMealItem(mealItem = meal, navController = navController)
                    Spacer(modifier = Modifier.padding(10.dp))
                }

            }
        }
    }
}