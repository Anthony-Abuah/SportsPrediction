package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun TipsterListContent(
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
    ) {
        val ten = (1..10)
        val scrollState = rememberScrollState(0)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState, true)
                .padding(bottom = LocalSpacing.current.smallMedium)
        ) {
            ten.forEach { _ ->
                TipsterListCard {}
                Spacer(modifier = Modifier.height(8.dp))
            }
        }




    }

}