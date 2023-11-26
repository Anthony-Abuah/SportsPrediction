package com.example.sportsprediction.feature_app.data.remote.dto.team_events

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent
import com.example.sportsprediction.feature_app.data.remote.dto.general.*
import com.example.sportsprediction.feature_app.domain.model.general.*

data class EventDto(
    val aggregatedWinnerCode: Int?,
    val awayRedCards: Int?,
    val awayScore: AwayScoreDto?,
    val awayTeam: AwayTeamDto?,
    val changes: ChangesDto?,
    val customId: String?,
    val detailId: Int?,
    val finalResultOnly: Boolean?,
    val hasEventPlayerHeatMap: Boolean?,
    val hasEventPlayerStatistics: Boolean?,
    val hasGlobalHighlights: Boolean?,
    val hasXg: Boolean?,
    val homeScore: HomeScoreDto?,
    val homeTeam: HomeTeamDto?,
    val id: Int?,
    val previousLegEventId: Int?,
    val roundInfo: RoundInfoDto?,
    val slug: String?,
    val startTimestamp: Int?,
    val status: StatusDto?,
    val time: TimeDto?,
    val tournament: TournamentDto?,
    val winnerCode: Int?
){
    fun toTeamEvent(): TeamEvent {
        return TeamEvent(
            awayScore = awayScore?.normaltime ?: nullInteger,
            awayTeamName = awayTeam?.name ?: emptyString,
            awayTeamId = awayTeam?.id ?: nullInteger,
            headToHeadId = customId ?: emptyString,
            homeScore = homeScore?.normaltime ?: nullInteger,
            homeTeamName = homeTeam?.name ?: emptyString,
            homeTeamId = homeTeam?.id ?: nullInteger,
            eventId = id ?: nullInteger,
            previousLegEventId = previousLegEventId ?: nullInteger,
            startTimestamp = startTimestamp ?: nullInteger,
            status = status?.description ?: emptyString,
            tournamentName = tournament?.name ?: emptyString,
            country = tournament?.category?.name ?: emptyString,
            tournamentId = tournament?.id ?: nullInteger,
            roundName = roundInfo?.name ?: emptyString,
            roundInfo = roundInfo?.toRoundInfo() ?: RoundInfo(nullInteger, emptyString, nullInteger, emptyString),
            tournament = tournament?.toTournament() ?: Tournament(emptyString, nullInteger, emptyString, nullInteger, emptyString, emptyString, nullInteger),
            homeTeamInfo = homeTeam?.toHomeTeamInfo() ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            awayTeamInfo = awayTeam?.toAwayTeamInfo() ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            homeScores = homeScore?.toHomeScores() ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger),
            awayScores = awayScore?.toAwayScores() ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger)

        )
    }
}