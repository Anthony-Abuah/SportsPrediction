package com.example.sportsprediction.feature_app.data.remote.dto.event_odds

import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import java.util.Date

data class EventOddsDto(
    val data: List<DataDto>?
){
    fun toListOfEventOddsEntity(date: Date?, eventId: Int?, homeTeamId: Int?, awayTeamId: Int?): List<EventOddsEntity>{
        return data?.map { it.toEventOddsEntity(date, eventId, homeTeamId, awayTeamId) } ?: emptyList()
    }
}