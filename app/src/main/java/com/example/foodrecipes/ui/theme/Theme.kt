package com.example.foodrecipes.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle

private val lightScheme = lightColorScheme(

    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    outline = outlineLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight

)

val typography = Typography(
    displayLarge = TextStyle(fontFamily = beVietNamFont),
    displayMedium = TextStyle(fontFamily = beVietNamFont),
    displaySmall = TextStyle(fontFamily = beVietNamFont),
    headlineLarge = TextStyle(fontFamily = beVietNamFont),
    headlineMedium = TextStyle(fontFamily = beVietNamFont),
    headlineSmall = TextStyle(fontFamily = beVietNamFont),
    titleLarge = TextStyle(fontFamily = beVietNamFont),
    titleMedium = TextStyle(fontFamily = beVietNamFont),
    titleSmall = TextStyle(fontFamily = beVietNamFont),
    bodyLarge = TextStyle(fontFamily = beVietNamFont),
    bodyMedium = TextStyle(fontFamily = beVietNamFont),
    bodySmall = TextStyle(fontFamily = beVietNamFont),
    labelLarge = TextStyle(fontFamily = beVietNamFont),
    labelMedium = TextStyle(fontFamily = beVietNamFont),
    labelSmall = TextStyle(fontFamily = beVietNamFont)
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    outline = outlineDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark
)

@Composable
fun FoodRecipesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}