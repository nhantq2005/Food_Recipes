package com.example.foodrecipes.feature_food_recipes.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

import com.example.foodrecipes.feature_food_recipes.presentation.screen.DetailScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.FavouriteScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.HomeScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.MealsByCategoryScreen
import com.example.foodrecipes.feature_food_recipes.presentation.screen.SignInScreen
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.AuthViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.GoogleAuthUiClient
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRecipesTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination =
                        if (googleAuthUiClient.getSignedInUser() == null)
                            Screen.LoginScreen.route
                        else
                        Screen.HomeScreen.route
                ) {
                    composable(Screen.HomeScreen.route) {
                        HomeScreen(
                            navController = navController,
                            userData = googleAuthUiClient.getSignedInUser()
                        )
                    }
                    composable(Screen.CategoryScreen.route) {
                        CategoryScreen(navController)
                    }
                    composable(Screen.FavouriteScreen.route) {
                        FavouriteScreen(navController)
                    }
                    composable(Screen.AccountScreen.route) {
                        AccountScreen(
                            googleAuthUiClient.getSignedInUser(),
                            navController
                        ) {
                            lifecycleScope.launch {
                                googleAuthUiClient.signOut()
                                navController.navigate(Screen.LoginScreen.route)
                            }
                        }
                    }
                    composable(
                        Screen.DetailScreen.route + "?id={id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {
                        DetailScreen(navController)
                    }
                    composable(Screen.LoginScreen.route) {
                        val viewModel = viewModel<AuthViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate(Screen.HomeScreen.route)
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                navController.navigate(Screen.HomeScreen.route)
                                viewModel.resetState()
                            }
                        }

                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }


                    composable(
                        Screen.MealsByCategoryScreen.route + "?category={category}",
                        arguments = listOf(
                            navArgument("category") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {
                        MealsByCategoryScreen(navController)
                    }
                }

//                HomeScreen(
//                    navController = navController,
//                    userData = googleAuthUiClient.getSignedInUser()
//                )
//                HomeScreen(rememberNavController())
//                CategoryScreen(rememberNavController())
            }
        }
    }
}
