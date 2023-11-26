package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.ListOfTeamEvent

data class ShowTeamsPastEventsState (
    val showTeamsPastEventsMessage: ListOfTeamEvent = emptyList(),
    val isLoading: Boolean = false
)