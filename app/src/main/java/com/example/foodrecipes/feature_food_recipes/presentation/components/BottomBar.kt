package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen

@Composable
fun BottomBar() {

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
//                val currenRoute = navController.currentDestination?.route
                        NavigationBarItem(
                            selected = /*currenRoute==*/item.isSelect,
                            onClick = { /*navController.navigate(item.route)*/ },
                            icon = {
                                Icon(
                                    item.selectedIcon,
                                    contentDescription = "Navigation Icon"
                                )
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
            .fillMaxSize()
        ) {
        }
    }
}