package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.CategoryItem
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.CategoryViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun CategoryScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<CategoryViewModel>()
    val state = viewModel.state.collectAsState()

    BottomBar(
        navController,
        title = {
            Row {
                Icon(Icons.Filled.AutoAwesomeMosaic, contentDescription = "Grid Icon")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Categories")
            }
        }
    ) {
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                items(state.value.categories) { category ->
                    CategoryItem(category = category, navController = navController)
                    Spacer(modifier = Modifier.padding(10.dp))
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
        CategoryScreen(rememberNavController())

    }
}