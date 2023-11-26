package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity


data class TeamNameEventState (
    val teamEventEntity: TeamEventEntity = TeamEventEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
    val isLoading: Boolean = false

)