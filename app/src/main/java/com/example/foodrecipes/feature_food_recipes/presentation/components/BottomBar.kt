package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen
import com.example.foodrecipes.util.Responsive

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    navController: NavController,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit

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
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                                if (currenRoute == item.route) {
                                    Icon(
                                        item.selectedIcon,
                                        contentDescription = "Navigation Icon",
                                        modifier = Modifier.size(Responsive.scaledDp(25))
                                    )
                                } else {
                                    Icon(
                                        item.unselectIcon,
                                        contentDescription = "Navigation Icon",
                                        modifier = Modifier.size(Responsive.scaledDp(25))
                                    )

                                }
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            content()
        }
    }
}