package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodrecipes.R
import com.example.foodrecipes.ui.theme.FoodRecipesTheme
import com.example.foodrecipes.ui.theme.beVietNamFont
import com.example.foodrecipes.util.Responsive

@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,


        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.fillMaxWidth()
            .shadow(10.dp, shape = RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),


    ) {
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google icon",
            modifier = Modifier.size(Responsive.scaledDp(35))

        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = stringResource(id = R.string.sign_in_google),
            color = Color.Black,
            fontSize = Responsive.scaledSp(25),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(vertical = 10.dp)
        )
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
        SignInButton(){}

    }
}