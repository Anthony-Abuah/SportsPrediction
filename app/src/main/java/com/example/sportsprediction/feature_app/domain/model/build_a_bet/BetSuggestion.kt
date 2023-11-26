package com.example.sportsprediction.feature_app.domain.model.build_a_bet

data class BetSuggestion(
    val mainTeamName: String?,
    val mainTeamId: String?,
    val opposingTeamName: String?,
    val opposingTeamId: String?,
    val eventId: String?,
    val headToHeadId: String?,
    val mainTeamPlayingLocation: String?,
    val marketName: String?,
    val numerator: String?,
    val denominator: String?,
    val percentageText: String?,
    val marketCategory: String?,
    val marketType: String?,
    val matchPeriod: String?,
    val team: String?
)
