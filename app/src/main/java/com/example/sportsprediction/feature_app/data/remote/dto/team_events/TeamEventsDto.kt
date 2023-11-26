package com.example.sportsprediction.feature_app.data.remote.dto.team_events

import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent

data class TeamEventsDto(
    val data: DataDto?
){
    fun toListOfTeamEvents(): List<TeamEvent>{
        return data?.toTeamEventsList() ?: emptyList()
    }
}