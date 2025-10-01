package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.presentation.components.BottomBar
import com.example.foodrecipes.feature_food_recipes.presentation.components.SmallMealItem
import com.example.foodrecipes.feature_food_recipes.presentation.state.UserData
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.AccountViewModel
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.DetailViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.util.Responsive

@Composable
fun AccountScreen(
    userData: UserData?,
    navController: NavController,
    onSignOut: () -> Unit
) {
    val accountViewModel = hiltViewModel<AccountViewModel>()
    val state = accountViewModel.state.collectAsState()
    userData?.profilePictureUrl?.let { Log.d("IMG", it) }
    BottomBar(
        navController
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                userData?.username?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = Responsive.scaledSp(25),
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                AsyncImage(
                    model =
                        if (userData?.profilePictureUrl == null)
                            Icons.Default.AccountCircle
                        else
                            userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(Responsive.scaledDp(100))
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Text(
                    text = "History",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface, fontSize = Responsive.scaledSp(20))
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxHeight(0.6f)
                ) {
                    items(state.value.currentMeals){meal->
                        SmallMealItem(
                            mealItem = meal,
                            navController = navController,
                            icon = {}
                        )
                    }
                }
                Text(
                    text = "Option",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface, fontSize = Responsive.scaledSp(20))
                )
                Button(onClick = onSignOut, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Output, contentDescription = "Sign Out")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign Out")
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
fun PreviewAccountScreen() {
    FoodRecipesTheme(dynamicColor = false) {
//        AccountScreen(rememberNavController(), {})

    }
}