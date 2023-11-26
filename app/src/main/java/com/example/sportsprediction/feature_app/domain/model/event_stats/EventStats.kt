package com.example.sportsprediction.feature_app.domain.model.event_stats

import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Corner_Kicks
import com.example.sportsprediction.core.util.Constants.DrawValue
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.FirstHalf
import com.example.sportsprediction.core.util.Constants.FullTime
import com.example.sportsprediction.core.util.Constants.Goalkeeper_Saves
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.LoseValue
import com.example.sportsprediction.core.util.Constants.Offsides
import com.example.sportsprediction.core.util.Constants.SecondHalf
import com.example.sportsprediction.core.util.Constants.Shots_On_Target
import com.example.sportsprediction.core.util.Constants.Total_Shots
import com.example.sportsprediction.core.util.Constants.WinValue
import com.example.sportsprediction.core.util.Constants.Yellow_Cards
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.RoundInfo
import com.example.sportsprediction.feature_app.domain.model.general.Tournament
import com.example.sportsprediction.feature_app.domain.model.team_stats.TeamStats
import java.util.*

data class EventStats(
    val data: List<EventStatsData>?
)
{
    private var fullTimeCornerTotals: Double? = null
    private var halfTimeCornerTotals: Double? = null
    private var secondHalfCornerTotals: Double? = null
    private var fullTimeCardTotals: Double? = null
    private var halfTimeCardTotals: Double? = null
    private var secondHalfCardTotals: Double? = null
    private var fullTimeGoalKeeperSavesTotals: Double? = null
    private var halfTimeGoalKeeperSavesTotals: Double? = null
    private var secondHalfGoalKeeperSavesTotals: Double? = null
    private var fullTimeShotsTotals: Double? = null
    private var halfTimeShotsTotals: Double? = null
    private var secondHalfShotsTotals: Double? = null
    private var fullTimeShotsOnTargetTotals: Double? = null
    private var halfTimeShotsOnTargetTotals: Double? = null
    private var secondHalfShotsOnTargetTotals: Double? = null
    private var fullTimeOffsideTotals: Double? = null
    private var halfTimeOffsideTotals: Double? = null
    private var secondHalfOffsideTotals: Double? = null

    private var fullTimeHomeTeamCornerTotals: Double? = null
    private var halfTimeHomeTeamCornerTotals: Double? = null
    private var secondHalfHomeTeamCornerTotals: Double? = null
    private var fullTimeHomeTeamCardTotals: Double? = null
    private var halfTimeHomeTeamCardTotals: Double? = null
    private var secondHalfHomeTeamCardTotals: Double? = null
    private var fullTimeHomeTeamGoalKeeperSavesTotals: Double? = null
    private var halfTimeHomeTeamGoalKeeperSavesTotals: Double? = null
    private var secondHalfHomeTeamGoalKeeperSavesTotals: Double? = null
    private var fullTimeHomeTeamShotsTotals: Double? = null
    private var halfTimeHomeTeamShotsTotals: Double? = null
    private var secondHalfHomeTeamShotsTotals: Double? = null
    private var fullTimeHomeTeamShotsOnTargetTotals: Double? = null
    private var halfTimeHomeTeamShotsOnTargetTotals: Double? = null
    private var secondHalfHomeTeamShotsOnTargetTotals: Double? = null
    private var fullTimeHomeTeamOffsideTotals: Double? = null
    private var halfTimeHomeTeamOffsideTotals: Double? = null
    private var secondHalfHomeTeamOffsideTotals: Double? = null

    private var fullTimeAwayTeamCornerTotals: Double? = null
    private var halfTimeAwayTeamCornerTotals: Double? = null
    private var secondHalfAwayTeamCornerTotals: Double? = null
    private var fullTimeAwayTeamCardTotals: Double? = null
    private var halfTimeAwayTeamCardTotals: Double? = null
    private var secondHalfAwayTeamCardTotals: Double? = null
    private var fullTimeAwayTeamGoalKeeperSavesTotals: Double? = null
    private var halfTimeAwayTeamGoalKeeperSavesTotals: Double? = null
    private var secondHalfAwayTeamGoalKeeperSavesTotals: Double? = null
    private var fullTimeAwayTeamShotsTotals: Double? = null
    private var halfTimeAwayTeamShotsTotals: Double? = null
    private var secondHalfAwayTeamShotsTotals: Double? = null
    private var fullTimeAwayTeamShotsOnTargetTotals: Double? = null
    private var halfTimeAwayTeamShotsOnTargetTotals: Double? = null
    private var secondHalfAwayTeamShotsOnTargetTotals: Double? = null
    private var fullTimeAwayTeamOffsideTotals: Double? = null
    private var halfTimeAwayTeamOffsideTotals: Double? = null
    private var secondHalfAwayTeamOffsideTotals: Double? = null

    private var fullTimeMatchResult: Double? = null
    private var halfTimeMatchResult: Double? = null
    private var secondHalfMatchResult: Double? = null
    private var fullTimeCornerResult: Double? = null
    private var halfTimeCornerResult: Double? = null
    private var secondHalfCornerResult: Double? = null
    private var fullTimeCardResult: Double? = null
    private var halfTimeCardResult: Double? = null
    private var secondHalfCardResult: Double? = null
    private var fullTimeGoalKeeperSavesResult: Double? = null
    private var halfTimeGoalKeeperSavesResult: Double? = null
    private var secondHalfGoalKeeperSavesResult: Double? = null
    private var fullTimeShotsResult: Double? = null
    private var halfTimeShotsResult: Double? = null
    private var secondHalfShotsResult: Double? = null
    private var fullTimeShotsOnTargetResult: Double? = null
    private var halfTimeShotsOnTargetResult: Double? = null
    private var secondHalfShotsOnTargetResult: Double? = null
    private var fullTimeOffsideResult: Double? = null
    private var halfTimeOffsideResult: Double? = null
    private var secondHalfOffsideResult: Double? = null

    private fun result(home: Double?, away: Double?, playingLocation: String?): Double? {
        var theResult: Double? = nullDouble
        if (home != null) {
            if (away != null) {
                if (home == away) {
                    theResult = DrawValue
                }
                else if (home > away && playingLocation?.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) {
                    theResult = WinValue
                }
                else if (away > home && playingLocation?.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) {
                    theResult = LoseValue
                }
                else if (home > away && playingLocation?.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) {
                    theResult = LoseValue
                }
                else if (away > home && playingLocation?.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) {
                    theResult = WinValue
                }
            }
        }
        return theResult
    }


    fun toTeamStats(
        mainTeamName: String?,
        mainTeamId: Int?,
        location: String?,
        eventId: Int?,
        date: Date?,
        roundInfo: RoundInfo?,
        tournament: Tournament?,
        tournamentName: String?,

        fullTimeGoalsTotals: Double?,
        halfTimeGoalsTotals: Double?,
        secondHalfGoalsTotals: Double?,
        fullTimeHomeTeamGoalsTotals: Double?,
        halfTimeHomeTeamGoalsTotals: Double?,
        secondHalfHomeTeamGoalsTotals: Double?,
        fullTimeAwayTeamGoalsTotals: Double?,
        halfTimeAwayTeamGoalsTotals: Double?,
        secondHalfAwayTeamGoalsTotals: Double?
    ): TeamStats {

        fullTimeMatchResult = result(fullTimeHomeTeamGoalsTotals, fullTimeAwayTeamGoalsTotals, location)
        halfTimeMatchResult = result(halfTimeHomeTeamGoalsTotals, halfTimeAwayTeamGoalsTotals, location)
        secondHalfMatchResult = result(secondHalfHomeTeamGoalsTotals, secondHalfAwayTeamGoalsTotals, location)

        if (data != null) {
            for (eventsStatsData in data) {
                if (eventsStatsData.groups != null) {
                    for (group in eventsStatsData.groups) {
                        if (group.statisticsItems != null) {
                            for (statisticsItem in group.statisticsItems) {

                                // Full Time
                                //Get full time results
                                if (eventsStatsData.period?.lowercase(Locale.getDefault()) == FullTime.lowercase(
                                        Locale.getDefault()
                                    )
                                ) {

                                    // Corner kicks
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Corner_Kicks.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamCornerTotals = statisticsItem.home
                                        fullTimeAwayTeamCornerTotals = statisticsItem.away
                                        fullTimeCornerTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeCornerResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Offsides
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Offsides.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamOffsideTotals = statisticsItem.home
                                        fullTimeAwayTeamOffsideTotals = statisticsItem.away
                                        fullTimeOffsideTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeOffsideResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Total Shots
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Total_Shots.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamShotsTotals = statisticsItem.home
                                        fullTimeAwayTeamShotsTotals = statisticsItem.away
                                        fullTimeShotsTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeShotsResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Shots On Target
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Shots_On_Target.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamShotsOnTargetTotals = statisticsItem.home
                                        fullTimeAwayTeamShotsOnTargetTotals = statisticsItem.away
                                        fullTimeShotsOnTargetTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeShotsOnTargetResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Goalkeeper Saves
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Goalkeeper_Saves.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamGoalKeeperSavesTotals = statisticsItem.home
                                        fullTimeAwayTeamGoalKeeperSavesTotals = statisticsItem.away
                                        fullTimeGoalKeeperSavesTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeGoalKeeperSavesResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Yellow Cards
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Yellow_Cards.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        fullTimeHomeTeamCardTotals = statisticsItem.home
                                        fullTimeAwayTeamCardTotals = statisticsItem.away
                                        fullTimeCardTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        fullTimeCardResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }
                                }


                                // Half Time
                                if (eventsStatsData.period?.lowercase(Locale.getDefault()) == FirstHalf.lowercase(
                                        Locale.getDefault()
                                    )
                                ) {

                                    // Corner kicks
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Corner_Kicks.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamCornerTotals = statisticsItem.home
                                        halfTimeAwayTeamCornerTotals = statisticsItem.away
                                        halfTimeCornerTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeCornerResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Offsides
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Offsides.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamOffsideTotals = statisticsItem.home
                                        halfTimeAwayTeamOffsideTotals = statisticsItem.away
                                        halfTimeOffsideTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeOffsideResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Total Shots
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Total_Shots.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamShotsTotals = statisticsItem.home
                                        halfTimeAwayTeamShotsTotals = statisticsItem.away
                                        halfTimeShotsTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeShotsResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Shots On Target
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Shots_On_Target.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamShotsOnTargetTotals = statisticsItem.home
                                        halfTimeAwayTeamShotsOnTargetTotals = statisticsItem.away
                                        halfTimeShotsOnTargetTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeShotsOnTargetResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Goalkeeper Saves
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Goalkeeper_Saves.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamGoalKeeperSavesTotals = statisticsItem.home
                                        halfTimeAwayTeamGoalKeeperSavesTotals = statisticsItem.away
                                        halfTimeGoalKeeperSavesTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeGoalKeeperSavesResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Yellow Cards
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Yellow_Cards.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        halfTimeHomeTeamCardTotals = statisticsItem.home
                                        halfTimeAwayTeamCardTotals = statisticsItem.away
                                        halfTimeCardTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        halfTimeCardResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }
                                }


                                // Second Half
                                if (eventsStatsData.period?.lowercase(Locale.getDefault()) == SecondHalf.lowercase(
                                        Locale.getDefault()
                                    )
                                ) {

                                    // Corner kicks
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Corner_Kicks.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamCornerTotals = statisticsItem.home
                                        secondHalfAwayTeamCornerTotals = statisticsItem.away
                                        secondHalfCornerTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfCornerResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Offsides
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Offsides.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamOffsideTotals = statisticsItem.home
                                        secondHalfAwayTeamOffsideTotals = statisticsItem.away
                                        secondHalfOffsideTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfOffsideResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Total Shots
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Total_Shots.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamShotsTotals = statisticsItem.home
                                        secondHalfAwayTeamShotsTotals = statisticsItem.away
                                        secondHalfShotsTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfShotsResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Shots On Target
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Shots_On_Target.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamShotsOnTargetTotals = statisticsItem.home
                                        secondHalfAwayTeamShotsOnTargetTotals = statisticsItem.away
                                        secondHalfShotsOnTargetTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfShotsOnTargetResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Goalkeeper Saves
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Goalkeeper_Saves.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamGoalKeeperSavesTotals =
                                            statisticsItem.home
                                        secondHalfAwayTeamGoalKeeperSavesTotals =
                                            statisticsItem.away
                                        secondHalfGoalKeeperSavesTotals =
                                            statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfGoalKeeperSavesResult =
                                            result(statisticsItem.home, statisticsItem.away, location)
                                    }

                                    // Yellow Cards
                                    if (statisticsItem.name?.lowercase(Locale.getDefault()) == Yellow_Cards.lowercase(
                                            Locale.getDefault()
                                        )
                                    ) {
                                        secondHalfHomeTeamCardTotals = statisticsItem.home
                                        secondHalfAwayTeamCardTotals = statisticsItem.away
                                        secondHalfCardTotals = statisticsItem.away?.let { statisticsItem.home?.plus(it) }
                                        secondHalfCardResult = result(statisticsItem.home, statisticsItem.away, location)
                                    }
                                }


                            }
                        }
                    }
                }
            }
        }

        return TeamStats(
            mainTeamName ?: emptyString,
            mainTeamId ?: nullInteger,
            location ?: emptyString,
            eventId ?: nullInteger,
            date ?: Date(),
            roundInfo,
            tournament,
            tournamentName,


            fullTimeGoalsTotals ?: nullDouble,
            halfTimeGoalsTotals ?: nullDouble,
            secondHalfGoalsTotals ?: nullDouble,
            fullTimeCornerTotals ?: nullDouble,
            halfTimeCornerTotals ?: nullDouble,
            secondHalfCornerTotals ?: nullDouble,
            fullTimeCardTotals ?: nullDouble,
            halfTimeCardTotals ?: nullDouble,
            secondHalfCardTotals ?: nullDouble,
            fullTimeGoalKeeperSavesTotals ?: nullDouble,
            halfTimeGoalKeeperSavesTotals ?: nullDouble,
            secondHalfGoalKeeperSavesTotals ?: nullDouble,
            fullTimeShotsTotals ?: nullDouble,
            halfTimeShotsTotals ?: nullDouble,
            secondHalfShotsTotals ?: nullDouble,
            fullTimeShotsOnTargetTotals ?: nullDouble,
            halfTimeShotsOnTargetTotals ?: nullDouble,
            secondHalfShotsOnTargetTotals ?: nullDouble,
            fullTimeOffsideTotals ?: nullDouble,
            halfTimeOffsideTotals ?: nullDouble,
            secondHalfOffsideTotals ?: nullDouble,

            fullTimeHomeTeamGoalsTotals ?: nullDouble,
            halfTimeHomeTeamGoalsTotals ?: nullDouble,
            secondHalfHomeTeamGoalsTotals ?: nullDouble,
            fullTimeHomeTeamCornerTotals ?: nullDouble,
            halfTimeHomeTeamCornerTotals ?: nullDouble,
            secondHalfHomeTeamCornerTotals ?: nullDouble,
            fullTimeHomeTeamCardTotals ?: nullDouble,
            halfTimeHomeTeamCardTotals ?: nullDouble,
            secondHalfHomeTeamCardTotals ?: nullDouble,
            fullTimeHomeTeamGoalKeeperSavesTotals ?: nullDouble,
            halfTimeHomeTeamGoalKeeperSavesTotals ?: nullDouble,
            secondHalfHomeTeamGoalKeeperSavesTotals ?: nullDouble,
            fullTimeHomeTeamShotsTotals ?: nullDouble,
            halfTimeHomeTeamShotsTotals ?: nullDouble,
            secondHalfHomeTeamShotsTotals ?: nullDouble,
            fullTimeHomeTeamShotsOnTargetTotals ?: nullDouble,
            halfTimeHomeTeamShotsOnTargetTotals ?: nullDouble,
            secondHalfHomeTeamShotsOnTargetTotals ?: nullDouble,
            fullTimeHomeTeamOffsideTotals ?: nullDouble,
            halfTimeHomeTeamOffsideTotals ?: nullDouble,
            secondHalfHomeTeamOffsideTotals ?: nullDouble,

            fullTimeAwayTeamGoalsTotals ?: nullDouble,
            halfTimeAwayTeamGoalsTotals ?: nullDouble,
            secondHalfAwayTeamGoalsTotals ?: nullDouble,
            fullTimeAwayTeamCornerTotals ?: nullDouble,
            halfTimeAwayTeamCornerTotals ?: nullDouble,
            secondHalfAwayTeamCornerTotals ?: nullDouble,
            fullTimeAwayTeamCardTotals ?: nullDouble,
            halfTimeAwayTeamCardTotals ?: nullDouble,
            secondHalfAwayTeamCardTotals ?: nullDouble,
            fullTimeAwayTeamGoalKeeperSavesTotals ?: nullDouble,
            halfTimeAwayTeamGoalKeeperSavesTotals ?: nullDouble,
            secondHalfAwayTeamGoalKeeperSavesTotals ?: nullDouble,
            fullTimeAwayTeamShotsTotals ?: nullDouble,
            halfTimeAwayTeamShotsTotals ?: nullDouble,
            secondHalfAwayTeamShotsTotals ?: nullDouble,
            fullTimeAwayTeamShotsOnTargetTotals ?: nullDouble,
            halfTimeAwayTeamShotsOnTargetTotals ?: nullDouble,
            secondHalfAwayTeamShotsOnTargetTotals ?: nullDouble,
            fullTimeAwayTeamOffsideTotals ?: nullDouble,
            halfTimeAwayTeamOffsideTotals ?: nullDouble,
            secondHalfAwayTeamOffsideTotals ?: nullDouble,
            fullTimeMatchResult ?: nullDouble,
            halfTimeMatchResult ?: nullDouble,
            secondHalfMatchResult ?: nullDouble,
            fullTimeCornerResult ?: nullDouble,
            halfTimeCornerResult ?: nullDouble,
            secondHalfCornerResult ?: nullDouble,
            fullTimeCardResult ?: nullDouble,
            halfTimeCardResult ?: nullDouble,
            secondHalfCardResult ?: nullDouble,
            fullTimeGoalKeeperSavesResult ?: nullDouble,
            halfTimeGoalKeeperSavesResult ?: nullDouble,
            secondHalfGoalKeeperSavesResult ?: nullDouble,
            fullTimeShotsResult ?: nullDouble,
            halfTimeShotsResult ?: nullDouble,
            secondHalfShotsResult ?: nullDouble,
            fullTimeShotsOnTargetResult ?: nullDouble,
            halfTimeShotsOnTargetResult ?: nullDouble,
            secondHalfShotsOnTargetResult ?: nullDouble,
            fullTimeOffsideResult ?: nullDouble,
            halfTimeOffsideResult ?: nullDouble,
            secondHalfOffsideResult ?: nullDouble
        )
    }

}