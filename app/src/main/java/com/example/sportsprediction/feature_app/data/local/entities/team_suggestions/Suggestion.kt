package com.example.sportsprediction.feature_app.data.local.entities.team_suggestions


data class Suggestion(
    val teamName: String?,
    val teamId: Int?,
    val market: String?,
    val outcome: Double?,
    val sampleSpace: Double?,
    val streakProbability: Double? = if (sampleSpace?.let{outcome?.div(it)}?.isNaN() == true) 0.0 else sampleSpace?.let {outcome?.div(it)},
    val value: String?,
    val marketCategory: String?, // Goals, Corners, Offsides, etc
    val marketType: String?, // Overs, Unders, Yes, No, Win, Lose, Draw
    val matchPeriod: String?, // Full Time, First Half, Second Half
    val team: String?, // Both Teams, main team, opponent
)
