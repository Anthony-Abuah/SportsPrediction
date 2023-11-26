package com.example.sportsprediction.feature_app.data.remote.dto.event_stats

import com.example.sportsprediction.feature_app.domain.model.event_stats.EventStatsData

data class EventStatsDataDto(
    val groups: List<GroupDto>?,
    val period: String?
){
    fun toEventStatsData(): EventStatsData {
        return EventStatsData(
            period,
            groups?.map { it.toGroup() } ?: emptyList()
        )
    }
}