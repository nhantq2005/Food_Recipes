package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state = viewModel.state.collectAsState()


    BottomBar(
        navController = navController
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = state.value.meal?.strMealThumb,
                contentDescription = null
            )
            Spacer(Modifier.height(15.dp))
            Text(text = state.value.meal?.strMeal.toString())
        }
    }
}