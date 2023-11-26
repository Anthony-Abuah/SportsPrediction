package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity

data class ConfidenceLevelState (
    val confidenceLevel: Double = 0.0,
    val isLoading: Boolean = false
)