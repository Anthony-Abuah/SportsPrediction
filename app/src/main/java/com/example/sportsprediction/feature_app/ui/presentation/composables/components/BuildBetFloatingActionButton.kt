package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor


@Composable
fun BuildBetFloatingActionButton(
    showBetSlip: () -> Unit,
) {
    FloatingActionButton(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(LocalSpacing.current.default),
        shape = RoundedCornerShape(LocalSpacing.current.medium),
        onClick = showBetSlip,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = LocalSpacing.current.medium)
    ) {
        BasicText(text = "Build bet", fontSize = 12.sp, textColor = Color.White)
    }

}





@Composable
fun BuildBetFloatingActionButton1(
    numberOfEvents: String,
    showBetSlip: () -> Unit,
) {

    FloatingActionButton(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(LocalSpacing.current.noPadding),
        shape = MaterialTheme.shapes.medium,
        onClick = showBetSlip,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = LocalSpacing.current.medium)
    ) {
        Text(
            text = numberOfEvents,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

}



