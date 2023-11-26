package com.example.sportsprediction.feature_app.data.remote.dto.team_events

import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent

data class DataDto(
    val events: List<EventDto>?,
    val hasNextPage: Boolean?
){
    fun toTeamEventsList(): List<TeamEvent>{
        return events?.map { it.toTeamEvent() } ?: emptyList()
    }
}