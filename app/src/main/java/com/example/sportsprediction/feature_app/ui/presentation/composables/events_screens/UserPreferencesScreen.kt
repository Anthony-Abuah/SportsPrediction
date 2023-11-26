package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.PredictionsScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.shared_preferences.UserPreferencesContent

@Composable
fun UserPreferencesScreen(
    navigateBack: () -> Unit
){

    Scaffold(
        topBar = {
            PredictionsScreenTopBar(label = "Preferences") {
                navigateBack()
            }
        },
        content = { it
                UserPreferencesContent()

        }
    )
}