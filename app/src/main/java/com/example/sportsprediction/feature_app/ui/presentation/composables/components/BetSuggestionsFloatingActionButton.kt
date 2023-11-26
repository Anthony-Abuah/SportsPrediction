package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor


@Composable
fun BetSuggestionsFloatingActionButton(
    showBetSlip: () -> Unit,
) {
    FloatingActionButton(
        modifier = Modifier.padding(LocalSpacing.current.semiLarge),
        shape = RoundedCornerShape(LocalSpacing.current.medium),
        onClick = showBetSlip,
        backgroundColor = PrimaryThemeColor,
        contentColor = Color.White
    ) {
        BasicText(text = "Build bet", fontSize = 8.sp, textColor = Color.White)
    }
}