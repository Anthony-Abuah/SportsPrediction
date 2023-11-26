package com.example.sportsprediction.feature_app.data.remote.dto.event_odds

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import java.util.Date

data class DataDto(
    val choiceGroup: String?,
    val choices: List<ChoiceDto>?,
    val fid: Int?,
    val id: Int?,
    val isLive: Boolean?,
    val marketId: Int?,
    val marketName: String?,
    val sourceId: Int?,
    val structureType: Int?,
    val suspended: Boolean?
){
    fun toEventOddsEntity(date: Date?, eventId: Int?, homeTeamId: Int?, awayTeamId: Int?): EventOddsEntity {
        return EventOddsEntity(
            eventOddsEntityId = null,
            date = date ?: Date(),
            eventId = eventId ?: nullInteger,
            homeTeamId = homeTeamId ?: nullInteger,
            awayTeamId = awayTeamId ?: nullInteger,
            marketName = marketName ?: emptyString,
            choices = choices?.map { it.toChoice() } ?: emptyList(),
            choiceGroup = choiceGroup ?: emptyString,
            isLive = isLive ?: false,
            isSuspended = suspended ?: false,
            sourceId = sourceId ?: nullInteger
        )
    }
}
