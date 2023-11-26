package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TitleText(
    title: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Text(
        text = title,
        fontWeight= FontWeight.Bold,
        color = textColor,
        fontSize = fontSize
    )
}

@Composable
fun AppBarTitle(
    title: String,
    drawableResource: Int,
    fontSize: TextUnit,
    textColor: Color
) {
    Row(horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically) {
        BasicImage(drawableResource = drawableResource, width = 20.dp, height = 20.dp, backgroundColor = Color.Transparent)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            fontWeight= FontWeight.Bold,
            color = textColor,
            fontSize = fontSize
        )
    }

}



@Composable
fun PredictionBarTitle(
    title: String,
    fontSize: TextUnit,
    textColor: Color
) {
    Row(horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            fontWeight= FontWeight.Bold,
            color = textColor,
            fontSize = fontSize
        )
    }

}



