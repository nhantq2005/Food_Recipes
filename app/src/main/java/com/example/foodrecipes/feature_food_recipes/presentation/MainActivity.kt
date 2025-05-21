package com.example.foodrecipes.feature_food_recipes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.FavouriteButton
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.AccountScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.CategoryScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.ChipGroupExample
import com.example.foodrecipes.feature_food_recipes.presentation.screen.DetailScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.FavouriteScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.HomeScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.LoginScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.MealsByCategoryScreen
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRecipesTheme {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
//                    composable(Screen.HomeScreen.route) {
//                        HomeScreen(navController)
//                    }
//                    composable(Screen.CategoryScreen.route) {
//                        CategoryScreen(navController)
//                    }
//                    composable(Screen.FavouriteScreen.route) {
//                        FavouriteScreen(navController)
//                    }
//                    composable(Screen.AccountScreen.route) {
//                        AccountScreen(navController)
//                    }
//                    composable(Screen.DetailScreen.route + "?id={id}",
//                        arguments = listOf(
//                            navArgument("id") {
//                                type = NavType.StringType
//                                defaultValue = ""
//                            }
//                        )
//                    ) {
//                        DetailScreen(navController)
//                    }
//                    composable(Screen.LoginScreen.route) {
//                        Text(text = "Login Screen")
//                    }
//                    composable(
//                        Screen.MealsByCategoryScreen.route + "?category={category}",
//                        arguments = listOf(
//                            navArgument("category") {
//                                type = NavType.StringType
//                                defaultValue = ""
//                            }
//                        )
//                    ) {
//                        MealsByCategoryScreen(navController)
//                    }
//                }

                LoginScreen()
            }
        }
    }
}
