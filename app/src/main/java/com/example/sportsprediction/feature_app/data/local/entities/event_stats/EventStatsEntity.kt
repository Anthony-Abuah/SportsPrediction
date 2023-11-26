package com.example.sportsprediction.feature_app.data.local.entities.event_stats

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.Constants.Event_Stats_Entity
import com.example.sportsprediction.feature_app.domain.model.general.RoundInfo
import com.example.sportsprediction.feature_app.domain.model.general.Tournament
import java.util.*


@Entity(tableName = Event_Stats_Entity)
data class EventStatsEntity(
    @PrimaryKey val eventStatsEntityId: Int? = null,
    val mainTeamName: String?,
    val mainTeamId: Int?,
    val homeTeamName: String?,
    val homeTeamId: Int?,
    val awayTeamName: String?,
    val awayTeamId: Int?,
    val opponentName: String? = if (mainTeamName == homeTeamName) awayTeamName else homeTeamName,
    val opponentId: Int? = if (mainTeamId == homeTeamId) awayTeamId else homeTeamId,
    val startTimestamp: Int?,
    val location: String?,
    val eventId: Int?,
    val date: Date?,
    val roundInfo: RoundInfo?,
    val tournament: Tournament?,
    val tournamentName: String?,


    val fullTimeGoalsTotals: Double?,
    val firstHalfGoalsTotals: Double?,
    val secondHalfGoalsTotals: Double?,
    val fullTimeCornerTotals: Double?,
    val firstHalfCornerTotals: Double?,
    val secondHalfCornerTotals: Double?,
    val fullTimeCardTotals: Double?,
    val firstHalfCardTotals: Double?,
    val secondHalfCardTotals: Double?,
    val fullTimeGoalKeeperSavesTotals: Double?,
    val firstHalfGoalKeeperSavesTotals: Double?,
    val secondHalfGoalKeeperSavesTotals: Double?,
    val fullTimeShotsTotals: Double?,
    val firstHalfShotsTotals: Double?,
    val secondHalfShotsTotals: Double?,
    val fullTimeShotsOnTargetTotals: Double?,
    val firstHalfShotsOnTargetTotals: Double?,
    val secondHalfShotsOnTargetTotals: Double?,
    val fullTimeOffsideTotals: Double?,
    val firstHalfOffsideTotals: Double?,
    val secondHalfOffsideTotals: Double?,


    val fullTimeHomeTeamGoalsTotals: Double?,
    val firstHalfHomeTeamGoalsTotals: Double?,
    val secondHalfHomeTeamGoalsTotals: Double?,
    val fullTimeHomeTeamCornerTotals: Double?,
    val firstHalfHomeTeamCornerTotals: Double?,
    val secondHalfHomeTeamCornerTotals: Double?,
    val fullTimeHomeTeamCardTotals: Double?,
    val firstHalfHomeTeamCardTotals: Double?,
    val secondHalfHomeTeamCardTotals: Double?,
    val fullTimeHomeTeamGoalKeeperSavesTotals: Double?,
    val firstHalfHomeTeamGoalKeeperSavesTotals: Double?,
    val secondHalfHomeTeamGoalKeeperSavesTotals: Double?,
    val fullTimeHomeTeamShotsTotals: Double?,
    val firstHalfHomeTeamShotsTotals: Double?,
    val secondHalfHomeTeamShotsTotals: Double?,
    val fullTimeHomeTeamShotsOnTargetTotals: Double?,
    val firstHalfHomeTeamShotsOnTargetTotals: Double?,
    val secondHalfHomeTeamShotsOnTargetTotals: Double?,
    val fullTimeHomeTeamOffsideTotals: Double?,
    val firstHalfHomeTeamOffsideTotals: Double?,
    val secondHalfHomeTeamOffsideTotals: Double?,


    val fullTimeAwayTeamGoalsTotals: Double?,
    val firstHalfAwayTeamGoalsTotals: Double?,
    val secondHalfAwayTeamGoalsTotals: Double?,
    val fullTimeAwayTeamCornerTotals: Double?,
    val firstHalfAwayTeamCornerTotals: Double?,
    val secondHalfAwayTeamCornerTotals: Double?,
    val fullTimeAwayTeamCardTotals: Double?,
    val firstHalfAwayTeamCardTotals: Double?,
    val secondHalfAwayTeamCardTotals: Double?,
    val fullTimeAwayTeamGoalKeeperSavesTotals: Double?,
    val firstHalfAwayTeamGoalKeeperSavesTotals: Double?,
    val secondHalfAwayTeamGoalKeeperSavesTotals: Double?,
    val fullTimeAwayTeamShotsTotals: Double?,
    val firstHalfAwayTeamShotsTotals: Double?,
    val secondHalfAwayTeamShotsTotals: Double?,
    val fullTimeAwayTeamShotsOnTargetTotals: Double?,
    val firstHalfAwayTeamShotsOnTargetTotals: Double?,
    val secondHalfAwayTeamShotsOnTargetTotals: Double?,
    val fullTimeAwayTeamOffsideTotals: Double?,
    val firstHalfAwayTeamOffsideTotals: Double?,
    val secondHalfAwayTeamOffsideTotals: Double?,


    val fullTimeMatchResult: Double?,
    val firstHalfMatchResult: Double?,
    val secondHalfMatchResult: Double?,
    val fullTimeCornerResult: Double?,
    val firstHalfCornerResult: Double?,
    val secondHalfCornerResult: Double?,
    val fullTimeCardResult: Double?,
    val firstHalfCardResult: Double?,
    val secondHalfCardResult: Double?,
    val fullTimeGoalKeeperSavesResult: Double?,
    val firstHalfGoalKeeperSavesResult: Double?,
    val secondHalfGoalKeeperSavesResult: Double?,
    val fullTimeShotsResult: Double?,
    val firstHalfShotsResult: Double?,
    val secondHalfShotsResult: Double?,
    val fullTimeShotsOnTargetResult: Double?,
    val firstHalfShotsOnTargetResult: Double?,
    val secondHalfShotsOnTargetResult: Double?,
    val fullTimeOffsideResult: Double?,
    val firstHalfOffsideResult: Double?,
    val secondHalfOffsideResult: Double?
){

}
