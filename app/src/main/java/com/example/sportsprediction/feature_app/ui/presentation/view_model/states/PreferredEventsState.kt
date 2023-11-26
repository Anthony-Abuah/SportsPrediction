package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity


data class PreferredEventsState (
    val preferredEvents: List<EventsEntity> = emptyList(),
    val isLoading: Boolean = false
)