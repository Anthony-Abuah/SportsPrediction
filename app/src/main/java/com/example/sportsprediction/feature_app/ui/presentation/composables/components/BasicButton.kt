package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.feature_app.ui.theme.PredictionsButtonBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PredictionsButtonBackgroundColorDisabled

@Composable
fun BasicButton(
    buttonName: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PredictionsButtonBackgroundColor,
            contentColor = Color.White,
            disabledBackgroundColor = PredictionsButtonBackgroundColorDisabled
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = buttonName,
            style = androidx.compose.material.MaterialTheme.typography.button
        )
    }
}


@Composable
fun RegisterTipsterButton(
    buttonName: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PredictionsButtonBackgroundColor,
            contentColor = Color.White,
            disabledBackgroundColor = PredictionsButtonBackgroundColorDisabled
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = buttonName,
            style = androidx.compose.material.MaterialTheme.typography.button
        )
    }
}
