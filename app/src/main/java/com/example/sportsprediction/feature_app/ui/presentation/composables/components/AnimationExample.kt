package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


@Composable
fun AnimationExample() {
    var visibility by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = { visibility = !visibility }) {
            Text(text = "Click Here!")
        }
        AnimatedVisibility(
            visible = visibility,
            enter = slideIn(initialOffset = { IntOffset(100,100) }),
            exit = slideOut(targetOffset = { IntOffset(-100,100) })
            ) {
            Box(modifier = Modifier
                .size(100.dp)
                .background(Color.Red)){
                Text(text = "This is how its supposed to be")
            }
        }

    }

}
