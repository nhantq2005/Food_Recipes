package com.example.foodrecipes.feature_food_recipes.presentation.screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodrecipes.R
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.SearchTextField
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.event.FavouriteEvent
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.FavouriteViewModel
//import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.FireStoreViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.util.Responsive


@Composable
fun FavouriteScreen(
    navController: NavController
) {
    val favouriteViewModel = hiltViewModel<FavouriteViewModel>()
    val state by favouriteViewModel.state.collectAsState()

    if(state.keyword == ""){
        favouriteViewModel.onEvent(FavouriteEvent.GetMeal)
    }
    BottomBar(
        navController
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .padding(Responsive.scaledDp(10))
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Grid Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(Responsive.scaledDp(40))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Favourite",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = Responsive.scaledSp(30),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
//                Spacer(modifier = Modifier.height(10.dp))
                SearchTextField(
                    text = state.keyword,
                    onValueChange = {
                        favouriteViewModel.onEvent(FavouriteEvent.EnteredKeyword(it))
                        favouriteViewModel.onEvent(FavouriteEvent.FindMeal(state.keyword))

                    }
                )
            }

//            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    items(state.listFavourite) { meal ->
                        SmallMealItem(mealItem = meal, navController = navController, icon = {
                            Image(
                                painter = painterResource(R.drawable.delete),
                                contentDescription = "Delete Icon",
                                modifier = Modifier
                                    .size(Responsive.scaledDp(24))
                                    .clickable {
                                        favouriteViewModel.onEvent(FavouriteEvent.DeleteMeal(mealId = meal.idMeal))
                                    }
                            )
                        })
                    }

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
        FavouriteScreen(
            navController = rememberNavController()
        )

    }
}

