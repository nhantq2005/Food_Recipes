package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.util.Log
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
import com.example.foodrecipes.feature_food_recipes.domain.util.convertIngredientName
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.IngredientItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val state = viewModel.state.collectAsState()

//    state.value.listIngredient = listOf(state.value.meal?.strIngredient1, state.value.meal?.strIngredient2)

    BottomBar(
        navController = navController
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = state.value.id)
            AsyncImage(
                model = state.value.meal?.strMealThumb,
                contentDescription = null
            )
            Spacer(Modifier.height(15.dp))
            Text(text = state.value.meal?.strMeal.toString())
            Spacer(Modifier.height(15.dp))
            state.value.listIngredient.zip(state.value.listMeasure) { ingredient, measure ->
                IngredientItem(ingredient, measure)
                Spacer(Modifier.height(5.dp))

                ingredient.let { Log.d("DetailScreen", it) }



            }
        }

    }
}