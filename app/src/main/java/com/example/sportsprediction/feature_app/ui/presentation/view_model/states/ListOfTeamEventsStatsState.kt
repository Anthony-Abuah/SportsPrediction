package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity


data class ListOfTeamEventsStatsState (
    val listOfAllTeamEventsStats: List<EventStatsEntity> = emptyList(),
    val isLoading: Boolean = false
)