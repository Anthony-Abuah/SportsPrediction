package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.sportsprediction.feature_app.ui.theme.PrimaryTextColor

@Composable
fun TimeDisplayText(
    title: String,
    fontSize: TextUnit
) {
    Text(
        text = title,
        fontWeight= FontWeight.Light,
        color = PrimaryTextColor,
        fontSize = fontSize
    )
}
