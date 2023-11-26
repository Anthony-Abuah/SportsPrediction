package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity



data class ListOfTeamEventState (
    val listOfTeamEvent: List<TeamEventEntity> = emptyList(),
    val isLoading: Boolean = false
)