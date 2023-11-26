package com.example.sportsprediction.feature_app.data.remote.dto.h2h_events

import com.example.sportsprediction.feature_app.domain.model.h2h_events.HeadToHeadEvent

data class HeadToHeadEventsDto(
    val data: List<DataDto>?
){

    fun toListOfHeadToHeadEvents(): List<HeadToHeadEvent>{
        return data?.map { it.toHeadToHeadEvent() } ?: emptyList()
    }


}