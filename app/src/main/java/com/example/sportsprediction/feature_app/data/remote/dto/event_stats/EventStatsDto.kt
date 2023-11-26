package com.example.sportsprediction.feature_app.data.remote.dto.event_stats

import com.example.sportsprediction.feature_app.domain.model.event_stats.EventStats

data class EventStatsDto(
    val data: List<EventStatsDataDto>?
){
    fun toEventStats(): EventStats {
        return EventStats(
            data?.map { it.toEventStatsData() } ?: emptyList()
        )
    }


}