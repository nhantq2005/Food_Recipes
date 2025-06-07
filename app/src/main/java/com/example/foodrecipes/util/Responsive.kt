package com.example.foodrecipes.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Responsive{
    @Composable
    fun calculateScaleFactor(): Float {
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp
        return (screenWidth / 400f).coerceIn(0.5f, 1.5f)
    }

    // Kích thước có thể điều chỉnh tự động
    @Composable
    fun scaledDp(size: Int): Dp {
        return (size * calculateScaleFactor()).dp
    }

    @Composable
    fun scaledSp(size: Int): TextUnit {
        return (size * calculateScaleFactor()).sp
    }
}