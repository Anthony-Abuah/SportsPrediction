package com.example.sportsprediction.feature_app.data.local.entities.team_suggestions

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.providerId
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion


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
){
    fun toBetSuggestion(opponentTeamName: String, opponentTeamId: String, eventId: String, headToHeadId: String, playingLoacation: String): BetSuggestion{
        return BetSuggestion(
            mainTeamName = teamName,
            mainTeamId = teamId?.toString() ?: emptyString,
            opposingTeamName = opponentTeamName,
            opposingTeamId = opponentTeamId,
            eventId = eventId,
            headToHeadId = headToHeadId,
            mainTeamPlayingLocation = playingLoacation,
            marketName = market,
            numerator = outcome?.toString() ?: emptyString,
            denominator = sampleSpace?.toString() ?: emptyString,
            percentageText = streakProbability?.toString() ?: emptyString,
            marketCategory = marketCategory ?: emptyString,
            marketType = marketType ?: emptyString,
            matchPeriod = matchPeriod ?: emptyString,
            team = team ?: emptyString,
        )
    }
}
