package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor


@Composable
fun BuildBetFloatingActionButton(
    showBetSlip: () -> Unit,
) {
    /*Card(modifier = Modifier.width(LocalSpacing.current.extraLarge).height(LocalSpacing.current.topAppBarSize),
        shape = RoundedCornerShape(LocalSpacing.current.medium),
        backgroundColor = Color.Transparent
    ) {*/
        FloatingActionButton(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(LocalSpacing.current.default),
            shape = RoundedCornerShape(LocalSpacing.current.medium),
            onClick = showBetSlip,
            backgroundColor = PrimaryThemeColor,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = LocalSpacing.current.medium)
        ) {
            BasicText(text = "Build bet", fontSize = 12.sp, textColor = Color.White)
        }
    //}
}





@Composable
fun BuildBetFloatingActionButton1(
    showBetSlip: () -> Unit,
) {

    FloatingActionButton(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(LocalSpacing.current.default),
        shape = RoundedCornerShape(LocalSpacing.current.medium),
        onClick = showBetSlip,
        backgroundColor = PrimaryThemeColor,
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = LocalSpacing.current.medium)
    ) {
        BasicText(text = "Build bet", fontSize = 12.sp, textColor = Color.White)
    }
    //}
}



