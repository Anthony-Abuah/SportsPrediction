package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.Constants.emptyString

data class GetTeamsPastEventsState (
    val getTeamsPastEventsMessage: String = emptyString,
    val isLoading: Boolean = false
)