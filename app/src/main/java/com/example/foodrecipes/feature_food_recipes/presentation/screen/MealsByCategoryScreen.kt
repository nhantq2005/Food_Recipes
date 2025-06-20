package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.CategoryItem
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.MealsByCategoryViewModel
import com.example.foodrecipes.util.Responsive

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Fastfood,
                        contentDescription = "Grid Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(Responsive.scaledDp(40))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = state.value.category,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = Responsive.scaledSp(30),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
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
}