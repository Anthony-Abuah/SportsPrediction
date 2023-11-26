package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity


data class GroupedEventsState (
    val groupedEvents: Map<String, List<EventsEntity>> = emptyMap(),
    val isLoading: Boolean = false

)