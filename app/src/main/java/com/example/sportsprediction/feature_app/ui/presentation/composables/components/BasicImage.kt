package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BasicImage (
    drawableResource: Int,
    width: Dp,
    height: Dp,
    backgroundColor: Color

    )
{
    Box(
        modifier = Modifier
            .size(width, height)
            .background(backgroundColor, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center)
    {
        Image(
            painter = painterResource(id = drawableResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
        )
    }

}
