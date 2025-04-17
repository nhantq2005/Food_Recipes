package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.event.HomeEvent
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val state by homeViewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { homeViewModel.onEvent(HomeEvent.GetRandomMeal) }) {
            Text("GET RANDOM MEAL")
        }
        Text(
            text = "ID: ${state.randomMeal?.idMeal}\n" +
                    "Random Meal: ${state.randomMeal?.strMeal}\n" +
                    "Category: ${state.randomMeal?.strCategory}"
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            state.area.forEach { area ->
                Spacer(modifier = Modifier.width(5.dp))
                FilterChip(
                    selected = false,
                    onClick = { /*TODO*/ },
                    label = { Text(area.strArea) }
                )
            }
        }
    }

}