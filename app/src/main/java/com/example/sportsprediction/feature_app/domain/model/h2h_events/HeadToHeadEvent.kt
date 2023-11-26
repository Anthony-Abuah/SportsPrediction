package com.example.sportsprediction.feature_app.domain.model.h2h_events

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.*
import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent

data class HeadToHeadEvent(
    val awayScore: Int?,
    val awayTeamName: String?,
    val awayTeamId: Int?,
    val headToHeadId: String?,
    val homeScore: Int?,
    val homeTeamName: String?,
    val homeTeamId: Int?,
    val eventId: Int?,
    val previousLegEventId: Int?,
    val startTimestamp: Int?,
    val status: String?,
    val tournamentName: String?,
    val country: String?,
    val tournamentId: Int?,
    val roundName: String?,
    val roundInfo: RoundInfo?,
    val tournament: Tournament?,
    val homeTeamInfo: TeamInfo?,
    val awayTeamInfo: TeamInfo?,
    val homeScores: Scores?,
    val awayScores: Scores?

) {
    fun toTeamEvent(): TeamEvent {
        return TeamEvent(
            awayScore ?: nullInteger,
            awayTeamName ?: emptyString,
            awayTeamId ?: nullInteger,
            headToHeadId ?: emptyString,
            homeScore ?: nullInteger,
            homeTeamName ?: emptyString,
            homeTeamId ?: nullInteger,
            eventId ?: nullInteger,
            previousLegEventId ?: nullInteger,
            startTimestamp ?: nullInteger,
            status ?: emptyString,
            tournamentName ?: emptyString,
            country ?: emptyString,
            tournamentId ?: nullInteger,
            roundName ?: emptyString,
            roundInfo ?: RoundInfo(nullInteger, emptyString, nullInteger, emptyString),
            tournament ?: Tournament(emptyString, nullInteger, emptyString, nullInteger, emptyString, emptyString, nullInteger),
            homeTeamInfo ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            awayTeamInfo ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            homeScores ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger),
            awayScores ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger)
        )

    }
}
