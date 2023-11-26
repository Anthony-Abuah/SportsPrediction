package com.example.sportsprediction.feature_app.data.remote.dto.general

import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.Scores

data class AwayScoreDto(
    val aggregated: Int?,
    val current: Int?,
    val display: Int?,
    val extra1: Int?,
    val extra2: Int?,
    val normaltime: Int?,
    val overtime: Int?,
    val penalties: Int?,
    val period1: Int?,
    val period2: Int?

) {
    fun toAwayScores(): Scores {
        return Scores(
            aggregated ?: nullInteger,
            current ?: nullInteger,
            display ?: nullInteger,
            extra1 ?: nullInteger,
            extra2 ?: nullInteger,
            normaltime ?: nullInteger,
            overtime ?: nullInteger,
            penalties ?: nullInteger,
            period1 ?: nullInteger,
            period2 ?: nullInteger
        )
    }
}