package com.example.sportsprediction.feature_app.domain.model.general

data class Scores(
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
)