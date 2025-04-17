package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(){
    val viewModel = hiltViewModel<CategoryViewModel>()
    val state = viewModel.state.collectAsState()

    LazyColumn {
        items(state.value.categories) { category->
            Text(text = category.strCategory)
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}