package com.example.sportsprediction.feature_app.data.remote.dto.event_odds

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.event_odds.Choice

data class ChoiceDto(
    val change: Int?,
    val fractionalValue: Double?,
    val initialFractionalValue: Double?,
    val name: String?,
    val sourceId: Int?,
    val winning: Boolean?
) {
    fun toChoice(): Choice {
        return Choice(
            change ?: nullInteger, fractionalValue ?: nullDouble, initialFractionalValue ?: nullDouble, name ?: emptyString, sourceId ?: nullInteger, winning ?: false
        )
    }
}