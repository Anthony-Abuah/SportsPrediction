package com.example.sportsprediction.feature_app.data.remote.dto.event_stats

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.feature_app.domain.model.event_stats.StatsItem

data class StatisticsItemDto(
    val name: String?,
    val home: String?,
    val away: String?,
    val compareCode: Int?,
    val statisticsType: String?,
    val valueType: String?,
    val homeValue: Number?,
    val awayValue: Number?
){
    fun toStatsItem(): StatsItem {
        return StatsItem(
            away = awayValue?.toDouble() ?: nullDouble,
            home = homeValue?.toDouble() ?: nullDouble,
            name = name ?: emptyString


            /*away = if (away.isNullOrBlank()) 0.0 else if (away.toDouble().isNaN()) 0.0 else if (away.contains("%")) 0.0 else away.toDouble(),
            home = if (home.isNullOrBlank()) 0.0 else if (home.toDouble().isNaN()) 0.0 else if (home.contains("%")) 0.0 else home.toDouble(),
*/

        )
    }

}