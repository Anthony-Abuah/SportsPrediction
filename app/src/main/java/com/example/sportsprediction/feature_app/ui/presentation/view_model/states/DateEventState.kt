package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents
import java.util.*


data class DateEventState (
    val dateEvents: DateEventsEntity = DateEventsEntity(null, Date(), DateEvents(emptyList())),
    val isLoading: Boolean = false

)