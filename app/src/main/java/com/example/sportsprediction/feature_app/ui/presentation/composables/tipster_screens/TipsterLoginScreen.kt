package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.PredictionsScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster.TipsterLoginContent
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster.TipsterRegistrationContent

@Composable
fun TipsterLoginScreen(
    navigateBack: () -> Unit,
    navigateToTipsterRegistrationScreen: () -> Unit
){
    Scaffold(
        topBar = {
            PredictionsScreenTopBar(label = "Tipster Login") {
                navigateBack()
            }
        },
    ){it
        TipsterLoginContent(navigateToTipsterRegistrationScreen)
    }
}