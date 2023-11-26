package com.example.sportsprediction.feature_app.data.remote.dto.event_stats

import com.example.sportsprediction.feature_app.domain.model.event_stats.Group

data class GroupDto(
    val groupName: String?,
    val statisticsItems: List<StatisticsItemDto>?
){
    fun toGroup(): Group {
        return Group(
            groupName,
            statisticsItems?.map { it.toStatsItem() } ?: emptyList()
        )
    }
}