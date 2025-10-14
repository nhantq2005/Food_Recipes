package com.example.foodrecipes.feature_food_recipes.presentation.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodrecipes.feature_food_recipes.domain.model.Meal
import com.example.foodrecipes.feature_food_recipes.presentation.navigation.Screen
import com.example.foodrecipes.util.Responsive

@Composable
fun LargeMealItem(
    meal: Meal,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    Screen.DetailScreen.route
                            + "?id=${meal.idMeal}"
                )
            }
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            AsyncImage(
                model = meal.strMealThumb,
                contentDescription = null,
                modifier = Modifier
                    .size(Responsive.scaledDp(130))
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = meal.strMeal, style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = Responsive.scaledSp(25),
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = Responsive.scaledSp(14),
                            fontWeight = FontWeight.SemiBold
                        ).toSpanStyle()
                    ) {
                        append("Category: ")
                    }
                    append(meal.strCategory)
                }
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = Responsive.scaledSp(14),
                            fontWeight = FontWeight.SemiBold
                        ).toSpanStyle()
                    ) {
                        append("Aera: ")
                    }
                    append(meal.strArea)
                }
                )

                Spacer(modifier = Modifier.height(3.dp))
                if (meal.strTags != "") {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = Responsive.scaledSp(14),
                                    fontWeight = FontWeight.SemiBold
                                ).toSpanStyle()
                            ) {
                                append("Tag: ")
                            }
                            append(meal.strTags)
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
