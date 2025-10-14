package com.example.foodrecipes.feature_food_recipes.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodrecipes.R
import com.example.foodrecipes.feature_food_recipes.presentation.components.SignInButton
import com.example.foodrecipes.feature_food_recipes.presentation.state.AuthState
import com.example.foodrecipes.feature_food_recipes.presentation.viewmodel.AuthViewModel
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.ui.theme.pacificoFont
import com.example.foodrecipes.util.Responsive

@Composable
fun SignInScreen(
    state: AuthState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.meal),
                    contentDescription = "Meal image",
                    modifier = Modifier.size(Responsive.scaledDp(150))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Cook\nNow!",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontFamily = pacificoFont,
                        fontSize = Responsive.scaledSp(60),
                        lineHeight = 70.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(10.dp, shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(Responsive.scaledDp(16))
            ) {
                Text(
                    text = "Welcome\nBack!", style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = Responsive.scaledSp(35),
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(Responsive.scaledDp(24)))
                SignInButton(onClick = onSignInClick)
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
//        SignInScreen()

    }
}