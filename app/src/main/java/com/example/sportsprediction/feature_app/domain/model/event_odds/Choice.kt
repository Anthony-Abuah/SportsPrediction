package com.example.sportsprediction.feature_app.domain.model.event_odds

data class Choice(
    val change: Int?,
    val fractionalValue: Double?,
    val initialFractionalValue: Double?,
    val name: String?,
    val sourceId: Int?,
    val winning: Boolean?
)