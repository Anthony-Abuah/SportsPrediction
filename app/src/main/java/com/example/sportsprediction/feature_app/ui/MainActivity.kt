package com.example.sportsprediction.feature_app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomNavGraph
import com.example.sportsprediction.feature_app.ui.theme.SportsPredictionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportsPredictionTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                ) {
                    BottomNavGraph()
                }
            }
        }
    }
}

