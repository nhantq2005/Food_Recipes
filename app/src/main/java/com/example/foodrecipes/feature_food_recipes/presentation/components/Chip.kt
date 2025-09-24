package com.example.foodrecipes.feature_food_recipes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ChipDefaults
import com.example.foodrecipes.ui.theme.FoodRecipesTheme

@Composable
fun Chip(
    lablel: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    isShowIcon: Boolean = true
) {
    Column {
        FilterChip(
            selected = isSelected,
            onClick = onClick,
            label = {
                Text(
                    text = lablel,
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Medium)
                )
            },
            leadingIcon =
                {
                    if (isSelected && isShowIcon)
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null
                        )
                    else {
                        null
                    }
                },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                selectedLabelColor = MaterialTheme.colorScheme.onSecondary,
                selectedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChip() {
    FoodRecipesTheme {
//        Chip()
    }
}