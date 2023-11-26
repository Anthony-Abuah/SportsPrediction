package com.example.sportsprediction.feature_app.domain.model.date_events

import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import java.util.*

data class DateEvents(
    val dateEvents: List<DateEvent>?,
){
    fun toPreferredEventsEntities(date: Date): List<EventsEntity>{
        return dateEvents?.map { it.toPreferredEventsEntity(date) } ?: emptyList()
    }

}

