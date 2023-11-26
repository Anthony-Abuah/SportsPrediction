package com.example.sportsprediction.feature_app.domain.model.build_a_bet

data class WinProbability(
    val eventId: Int?,
    val headToHeadId: String?,
    val homeTeamName: String?,
    val homeTeamId: Int?,
    val awayTeamName: String?,
    val awayTeamId: Int?,
    val homeWinProbability: Double?,
    val drawProbability: Double?,
    val awayWinProbability: Double?

)
