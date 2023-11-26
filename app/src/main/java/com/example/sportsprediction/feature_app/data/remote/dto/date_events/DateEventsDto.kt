package com.example.sportsprediction.feature_app.data.remote.dto.date_events

import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents


data class DateEventsDto(
    val data: List<DateEventDto>?
){
    fun toDateEvents(): DateEvents {
        return DateEvents(
            dateEvents = data?.map { it.toDateEvent() } ?: emptyList()
        )
    }
}