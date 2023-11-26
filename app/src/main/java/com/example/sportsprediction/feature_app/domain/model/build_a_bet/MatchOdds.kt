package com.example.sportsprediction.feature_app.domain.model.build_a_bet

data class MatchOdds(
    val eventId: Int,
    val headToHeadId: String,
    val homeTeamName: String?,
    val homeTeamId: String?,
    val awayTeamName: String?,
    val awayTeamId: String?,
    val fullTimeMatchResults: Results?,
    val firstHalfMatchResults: Results?,
    val fullTimeDoubleChance: DoubleChance?,
    val fullTimeYellowCards: List<Totals>?,
    val fullTimeMatchTotals: List<Totals>?,
    val fullTimeCornerKicksTotals: List<Totals>?,
    val fullTimeBothTeamsToScore: YesOrNo?,
)
