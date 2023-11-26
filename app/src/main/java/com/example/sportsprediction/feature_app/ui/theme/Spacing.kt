package com.example.sportsprediction.feature_app.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val noElevation: Dp = 0.dp,
    val noPadding: Dp = 0.dp,
    val borderStroke: Dp = 1.dp,
    val textSpace: Dp = 1.dp,
    val extraSmall: Dp = 2.dp,
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val smallMedium: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val semiLarge: Dp = 24.dp,
    val large: Dp = 32.dp,
    val teamLogo: Dp = 50.dp,
    val topAppBarSize: Dp = 50.dp,
    val textFieldHeight: Dp = 60.dp,
    val bottomNavBarSize: Dp = 60.dp,
    val extraLarge: Dp = 64.dp,
    val dialogBox: Dp = 150.dp
)


val LocalSpacing = compositionLocalOf { Spacing() }


val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current