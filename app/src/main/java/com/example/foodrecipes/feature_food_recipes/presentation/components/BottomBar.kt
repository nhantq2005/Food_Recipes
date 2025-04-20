package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AutoAwesomeMosaic
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    navController: NavController,
    content: @Composable () ->Unit
) {

    val items = listOf(
        NavBarItem(
            label = Screen.HomeScreen.label,
            route = Screen.HomeScreen.route,
            selectedIcon = Icons.Default.Home,
            unselectIcon = Icons.Outlined.Home,
            isSelect = true
        ),
        NavBarItem(
            label = Screen.CategoryScreen.label,
            route = Screen.CategoryScreen.route,
            selectedIcon = Icons.Filled.AutoAwesomeMosaic,
            unselectIcon = Icons.Outlined.AutoAwesomeMosaic,
            isSelect = false
        ),
        NavBarItem(
            label = Screen.FavouriteScreen.label,
            route = Screen.FavouriteScreen.route,
            selectedIcon = Icons.Default.Favorite,
            unselectIcon = Icons.Default.FavoriteBorder,
            isSelect = false
        ),
        NavBarItem(
            label = Screen.AccountScreen.label,
            route = Screen.AccountScreen.route,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectIcon = Icons.Outlined.AccountCircle,
            isSelect = false
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Icon(Icons.Filled.AutoAwesomeMosaic, contentDescription = "Grid Icon")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Categories")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
                tonalElevation = 5.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items.forEachIndexed { _, item ->
                val currenRoute = navController.currentDestination?.route
                        NavigationBarItem(
                            selected = currenRoute == item.route,
                            onClick = {
                                navController.navigate(item.route)
                                      },
                            icon = {
                                if(currenRoute == item.route) {
                                    Icon(
                                        item.selectedIcon,
                                        contentDescription = "Navigation Icon"
                                    )
                                }else{
                                    Icon(
                                        item.unselectIcon,
                                        contentDescription = "Navigation Icon"
                                    )

                                }
                            },
                            label = {
                                Text(text = item.label)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .padding(5.dp)
            .fillMaxSize()
        ) {
            content()
        }
    }
}