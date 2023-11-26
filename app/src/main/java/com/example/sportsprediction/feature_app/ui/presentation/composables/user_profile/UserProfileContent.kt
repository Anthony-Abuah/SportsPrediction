package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor

@Composable
fun UserProfileContent(
    username: String?,
) {
    Column(modifier = Modifier.fillMaxSize()
        .background(MainBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicText(text = "$username is logged in", fontSize = 16.sp, textColor = Color.Black)
    }

}