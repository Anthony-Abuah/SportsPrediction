package com.example.sportsprediction.feature_app.domain.model.team_event

import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.Neutral
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.*
import java.util.*

data class TeamEvent(
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

    ){
    fun toTeamNameEventEntity(teamId: Int, date: Date, teamName: String): TeamEventEntity {
        return TeamEventEntity(
            null,
            date = date,
            mainTeamId = teamId,
            mainTeamName = teamName,
            mainTeamPlayingLocation = if (teamId == homeTeamId) Home else if (teamId == awayTeamId) Away else Neutral,
            awayScore = awayScore ?: nullInteger,
            awayTeamName = awayTeamName ?: emptyString,
            awayTeamId = awayTeamId ?: nullInteger,
            headToHeadId = headToHeadId ?: emptyString,
            homeScore = homeScore ?: nullInteger,
            homeTeamName = homeTeamName ?: emptyString,
            homeTeamId = homeTeamId ?: nullInteger,
            eventId = eventId ?: nullInteger,
            previousLegEventId = previousLegEventId ?: nullInteger,
            startTimestamp = startTimestamp ?: nullInteger,
            status = status ?: emptyString,
            tournamentName = tournament?.name ?: emptyString,
            country = tournament?.categoryName ?: emptyString,
            tournamentId = tournament?.tournamentId ?: nullInteger,
            roundName = roundInfo?.name ?: emptyString,
            roundInfo = roundInfo ?: RoundInfo(nullInteger, emptyString, nullInteger, emptyString),
            tournament = tournament ?: Tournament(emptyString, nullInteger, emptyString, nullInteger, emptyString, emptyString, nullInteger),
            homeTeamInfo = homeTeamInfo ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            awayTeamInfo = awayTeamInfo ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            homeScores = homeScores ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger),
            awayScores = awayScores ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger)

        )
    }
}
