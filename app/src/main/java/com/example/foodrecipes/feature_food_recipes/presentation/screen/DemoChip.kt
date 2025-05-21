package com.example.foodrecipes.feature_food_recipes.presentation.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroupExample() {
    val chipOptions = listOf("Nam", "Nữ", "Khác")
    var selectedChip by remember { mutableStateOf<String?>(null) }
Column(modifier = Modifier.padding(30.dp)) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        chipOptions.forEach { label ->
            FilterChip(
                selected = selectedChip == label,
                onClick = {
                    selectedChip = label
                },
                label = { Text(label) },
                leadingIcon =
                    {
                        if (selectedChip == label) Icon(
                            Icons.Default.Check,
                            contentDescription = null
                        )
                        else {
                            null
                        }
                    }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
    Text("Bạn đã chọn: ${selectedChip ?: "Chưa chọn"}")
}

}