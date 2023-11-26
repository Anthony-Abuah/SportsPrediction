package com.example.sportsprediction.feature_app.data.local.entities.event_stats

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Event_Stats_Entity
import com.example.sportsprediction.core.util.ListOfEventStats
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfAwayTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCardsResult
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCardsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCornerResult
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCornerTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfHomeTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfMatchResult
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfOffsideResult
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfOffsideTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeAwayTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeCardsResult
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeCardsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeCornerResult
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeCornerTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeHomeTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeMatchResult
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeOffsideResult
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeOffsideTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfAwayTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfCardsResult
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfCardsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfCornerResult
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfCornerTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfHomeTeamGoalsTotals
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfMatchResult
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfOffsideResult
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfOffsideTotals


@Dao
interface EventStatsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThisEventStats(eventStats: EventStatsEntity)

    @Query ("DELETE FROM $Event_Stats_Entity")
    suspend fun deleteAllEventsStats()

    @Query ("SELECT * FROM $Event_Stats_Entity")
    suspend fun getTeamStats(): List<EventStatsEntity>

    @Query ("DELETE FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun deleteEventStatsForThisTeam(mainTeamId: Int)

    @Query ("DELETE FROM $Event_Stats_Entity WHERE eventId LIKE :eventId")
    suspend fun deleteEventStatsForThisEvent(eventId: Int)

    @Query ("SELECT * FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getParticularTeamStats(mainTeamId: Int): List<EventStatsEntity>

    @Query ("SELECT * FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND eventId LIKE :eventId")
    suspend fun getThisEventStatsForThisTeam(mainTeamId: Int, eventId: Int): EventStatsEntity?

    @Query ("SELECT COUNT(mainTeamId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getNumberOfTeamStats(mainTeamId: Int): Int


    @Query ("SELECT * FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getAllOfThisTeamStats(mainTeamId: Int): ListOfEventStats?



    @Query ("SELECT * FROM $Event_Stats_Entity WHERE eventId LIKE :eventId")
    suspend fun getThisTeamEventStats(eventId: Int): EventStatsEntity?



    @Query ("SELECT eventId FROM $Event_Stats_Entity WHERE eventId LIKE :eventId")
    suspend fun getThisEventId(eventId: Int): Int?


    @Query ("SELECT eventId FROM $Event_Stats_Entity WHERE eventId LIKE :eventId AND mainTeamId LIKE :mainTeamId")
    suspend fun getThisEventId(eventId: Int, mainTeamId: Int): Int?

    @Query ("SELECT eventId FROM $Event_Stats_Entity")
    suspend fun getAllEventIds(): List<Int>?

    @Query ("SELECT eventId FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getThisTeamEventIds(mainTeamId: Int): List<Int>?



    //Goals
    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeGoalsTotals > :value")
    suspend fun getFullTimeGoalsOver(value: Int, mainTeamId: Int): Double?

    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeGoalsTotals > :minusOne AND $FullTimeGoalsTotals < :value")
    suspend fun getFullTimeGoalsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?



    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfGoalsTotals > :value")
    suspend fun getFirstHalfGoalsOver(value: Int, mainTeamId: Int): Double?

    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfGoalsTotals > :minusOne AND $FirstHalfGoalsTotals < :value")
    suspend fun getFirstHalfGoalsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfGoalsTotals > :value")
    suspend fun getSecondHalfGoalsOver(value: Int, mainTeamId: Int): Double?

    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfGoalsTotals > :minusOne AND $SecondHalfGoalsTotals < :value")
    suspend fun getSecondHalfGoalsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time match result using mainTeamId
    @Query ("SELECT COUNT($FullTimeMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeMatchResult LIKE :result")
    suspend fun getFullTimeMatchResult(result: Double, mainTeamId: Int): Double?

    // First Half match result using mainTeamId
    @Query ("SELECT COUNT($FirstHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfMatchResult LIKE :result")
    suspend fun getFirstHalfMatchResult(result: Double, mainTeamId: Int): Double?

    // Second Half match result using mainTeamId
    @Query ("SELECT COUNT($SecondHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfMatchResult LIKE :result")
    suspend fun getSecondHalfMatchResult(result: Double, mainTeamId: Int): Double?


    //Both Halves Overs
    // Both Halves overs using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfGoalsTotals > :value AND $SecondHalfGoalsTotals > :value")
    suspend fun getBothHalvesOvers(mainTeamId: Int, value: Double): Double?


    //Both Halves Unders
    // Both Halves overs using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfGoalsTotals < :value AND $SecondHalfGoalsTotals < :value")
    suspend fun getBothHalvesUnders(mainTeamId: Int, value: Double): Double?


    // Total Both Halves overs using mainTeamId
    @Query ("SELECT COUNT($FirstHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getBothHalvesTotals(mainTeamId: Int): Double?


    //Full Time Both Teams To Score
    // Full time Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeHomeTeamGoalsTotals > :zero AND $FullTimeAwayTeamGoalsTotals > :zero")
    suspend fun getFullTimeBothTeamsToScore(mainTeamId: Int, zero: Double): Double?


    // Full time Total Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT($FullTimeHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFullTimeTotalBothTeamsToScore(mainTeamId: Int): Double?


    // First Half Both Teams To Score
    // First Half Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfHomeTeamGoalsTotals > :zero AND $FirstHalfAwayTeamGoalsTotals > :zero")
    suspend fun getFirstHalfBothTeamsToScore(mainTeamId: Int, zero: Double): Double?

    // First Half Total Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT($FirstHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFirstHalfTotalBothTeamsToScore(mainTeamId: Int): Double?


    // Second Half Both Teams To Score
    // Second Half Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfHomeTeamGoalsTotals > :zero AND $SecondHalfAwayTeamGoalsTotals > :zero")
    suspend fun getSecondHalfBothTeamsToScore(mainTeamId: Int, zero: Double): Double?

    // Second Half Total Both Teams To Score using mainTeamId
    @Query ("SELECT COUNT($SecondHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getSecondHalfTotalBothTeamsToScore(mainTeamId: Int): Double?


    //Full Time Handicap
    // Full time Home Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeHomeTeamGoalsTotals - :handicap > $FullTimeAwayTeamGoalsTotals")
    suspend fun getFullTimeHomeHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time Away Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeAwayTeamGoalsTotals - :handicap > $FullTimeHomeTeamGoalsTotals")
    suspend fun getFullTimeAwayHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time total Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeAwayTeamGoalsTotals + $FullTimeHomeTeamGoalsTotals BETWEEN :first AND :last")
    suspend fun getFullTimeTotalHandicapResult(mainTeamId: Int, first: Double, last: Double): Double?


    //Half Time Handicap
    // Full time Home Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfHomeTeamGoalsTotals - :handicap > $FirstHalfAwayTeamGoalsTotals")
    suspend fun getFirstHalfHomeHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time Away Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfAwayTeamGoalsTotals - :handicap > $FirstHalfHomeTeamGoalsTotals")
    suspend fun getFirstHalfAwayHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time total Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfHomeTeamGoalsTotals + $FirstHalfAwayTeamGoalsTotals BETWEEN :first AND :last")
    suspend fun getFirstHalfTotalHandicapResult(mainTeamId: Int, first: Double, last: Double): Double?


    //Second Half Handicap
    // Full time Home Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfHomeTeamGoalsTotals - :handicap > $SecondHalfAwayTeamGoalsTotals")
    suspend fun getSecondHalfHomeHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time Away Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfAwayTeamGoalsTotals - :handicap > $SecondHalfHomeTeamGoalsTotals")
    suspend fun getSecondHalfAwayHandicapResult(mainTeamId: Int, location: String, handicap: Double): Double?


    // Full time total Handicap result using mainTeamId
    @Query ("SELECT COUNT(eventId) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND ($SecondHalfHomeTeamGoalsTotals + $SecondHalfAwayTeamGoalsTotals BETWEEN :first AND :last)")
    suspend fun getSecondHalfTotalHandicapResult(mainTeamId: Int, first: Double, last: Double): Double?



    //Full Time MultiGoals
    // Full time match multi-goals using mainTeamId
    @Query ("SELECT COUNT($FullTimeGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeGoalsTotals > :firstValue AND $FullTimeGoalsTotals < :lastValue")
    suspend fun getFullTimeMatchMultiGoals(mainTeamId: Int, firstValue: Double, lastValue: Double): Double?

    // Full time home multi-goals using mainTeamId
    @Query ("SELECT COUNT($FullTimeHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeHomeTeamGoalsTotals > :firstValue AND $FullTimeHomeTeamGoalsTotals < :lastValue")
    suspend fun getFullTimeMainTeamHomeMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    // Full time away multi-goals using mainTeamId
    @Query ("SELECT COUNT($FullTimeAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeAwayTeamGoalsTotals > :firstValue AND $FullTimeAwayTeamGoalsTotals < :lastValue")
    suspend fun getFullTimeMainTeamAwayMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    //Full Time Multi Goals
    // Full time Total Multi goals using mainTeamId
    @Query ("SELECT COUNT($FullTimeGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFullTimeTotalMultiGoals(mainTeamId: Int): Double?


    //First Half MultiGoals
    // First Half match multi-goals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfGoalsTotals > :firstValue AND $FirstHalfGoalsTotals < :lastValue")
    suspend fun getFirstHalfMultiGoals(mainTeamId: Int, firstValue: Double, lastValue: Double): Double?

    // First Half home multi-goals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfHomeTeamGoalsTotals > :firstValue AND $FirstHalfHomeTeamGoalsTotals < :lastValue")
    suspend fun getFirstHalfMainTeamHomeMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    // First Half away multi-goals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfAwayTeamGoalsTotals > :firstValue AND $FirstHalfAwayTeamGoalsTotals < :lastValue")
    suspend fun getFirstHalfMainTeamAwayMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    //First Half Multi Goals
    // First Half Total Multi goals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFirstHalfTotalMultiGoals(mainTeamId: Int): Double?



    //Second Half MultiGoals
    // Second Half match multi-goals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfGoalsTotals > :firstValue AND $SecondHalfGoalsTotals < :lastValue")
    suspend fun getSecondHalfMultiGoals(mainTeamId: Int, firstValue: Double, lastValue: Double): Double?

    // Second Half home multi-goals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfHomeTeamGoalsTotals > :firstValue AND $SecondHalfHomeTeamGoalsTotals < :lastValue")
    suspend fun getSecondHalfMainTeamHomeMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    // Second Half away multi-goals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfAwayTeamGoalsTotals > :firstValue AND $SecondHalfAwayTeamGoalsTotals < :lastValue")
    suspend fun getSecondHalfMainTeamAwayMultiGoals(mainTeamId: Int, location: String, firstValue: Double, lastValue: Double): Double?

    //Second Half Multi Goals
    // Second Half Total Multi goals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getSecondHalfTotalMultiGoals(mainTeamId: Int): Double?



    // Full Time Clean Sheets
    // Full time home clean sheets using mainTeamId
    @Query ("SELECT COUNT($FullTimeHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeAwayTeamGoalsTotals LIKE :zero")
    suspend fun getFullTimeMainTeamHomeCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    // Full time away clean sheets using mainTeamId
    @Query ("SELECT COUNT($FullTimeAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FullTimeHomeTeamGoalsTotals LIKE :zero")
    suspend fun getFullTimeMainTeamAwayCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    //Full Time clean sheets
    // Full time Total clean sheets using mainTeamId
    @Query ("SELECT COUNT($FullTimeGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFullTimeTotalCleanSheets(mainTeamId: Int): Double?


    // First Half home clean sheets using mainTeamId
    @Query ("SELECT COUNT($FirstHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfAwayTeamGoalsTotals LIKE :zero")
    suspend fun getFirstHalfMainTeamHomeCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    // First Half away clean sheets using mainTeamId
    @Query ("SELECT COUNT($FirstHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $FirstHalfHomeTeamGoalsTotals LIKE :zero")
    suspend fun getFirstHalfMainTeamAwayCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    // First Half clean sheets
    // First Half Total clean sheets using mainTeamId
    @Query ("SELECT COUNT($FirstHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFirstHalfTotalCleanSheets(mainTeamId: Int): Double?


    // Second Half home clean sheets using mainTeamId
    @Query ("SELECT COUNT($SecondHalfHomeTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfAwayTeamGoalsTotals LIKE :zero")
    suspend fun getSecondHalfMainTeamHomeCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    // Second Half away clean sheets using mainTeamId
    @Query ("SELECT COUNT($SecondHalfAwayTeamGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND location LIKE :location AND $SecondHalfHomeTeamGoalsTotals LIKE :zero")
    suspend fun getSecondHalfMainTeamAwayCleanSheets(mainTeamId: Int, location: String, zero: Double): Double?

    // Second Half clean sheets
    // Second Half Total clean sheets using mainTeamId
    @Query ("SELECT COUNT($SecondHalfGoalsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getSecondHalfTotalCleanSheets(mainTeamId: Int): Double?


    //Full Time Double Chance
    // Full time Double Chance using mainTeamId
    @Query ("SELECT COUNT($FullTimeMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND ($FullTimeMatchResult LIKE :firstResult OR $FullTimeMatchResult LIKE :lastResult)")
    suspend fun getFullTimeDoubleChance(mainTeamId: Int, firstResult: Double, lastResult: Double): Double?

    //Full Time Double Chance
    // Full time Double Chance using mainTeamId
    @Query ("SELECT COUNT($FullTimeMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFullTimeTotalDoubleChance(mainTeamId: Int): Double?


    //First Half Double Chance
    // Full time Double Chance using mainTeamId
    @Query ("SELECT COUNT($FirstHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND ($FirstHalfMatchResult LIKE :firstResult OR $FirstHalfMatchResult LIKE :lastResult)")
    suspend fun getFirstHalfDoubleChance(mainTeamId: Int, firstResult: Double, lastResult: Double): Double?


    //First Half Double Chance
    // First Half Double Chance using mainTeamId
    @Query ("SELECT COUNT($FirstHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getFirstHalfTotalDoubleChance(mainTeamId: Int): Double?


    //Second Half Double Chance
    // Full time Double Chance using mainTeamId
    @Query ("SELECT COUNT($SecondHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND ($SecondHalfMatchResult LIKE :firstResult OR $SecondHalfMatchResult LIKE :lastResult)")
    suspend fun getSecondHalfDoubleChance(mainTeamId: Int, firstResult: Double, lastResult: Double): Double?


    //Second Half Double Chance
    // Second Half Double Chance using mainTeamId
    @Query ("SELECT COUNT($SecondHalfMatchResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getSecondHalfTotalDoubleChance(mainTeamId: Int): Double?




    //Corners
    // Full time corners totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCornerTotals > :value")
    suspend fun getFullTimeCornersOver(value: Int, mainTeamId: Int): Double?

    // Full time corners totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCornerTotals > :minusOne AND $FullTimeCornerTotals < :value")
    suspend fun getFullTimeCornersUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?



    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCornerTotals > :value")
    suspend fun getFirstHalfCornersOver(value: Int, mainTeamId: Int): Double?

    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCornerTotals > :minusOne AND $FirstHalfCornerTotals < :value")
    suspend fun getFirstHalfCornersUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCornerTotals > :value")
    suspend fun getSecondHalfCornersOver(value: Int, mainTeamId: Int): Double?

    // Full time goals totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCornerTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCornerTotals > :minusOne AND $SecondHalfCornerTotals < :value")
    suspend fun getSecondHalfCornersUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time corner result using mainTeamId
    @Query ("SELECT COUNT($FullTimeCornerResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCornerResult LIKE :result")
    suspend fun getFullTimeCornerKicksResult(result: Double, mainTeamId: Int): Double?

    // First Half corner result using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCornerResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCornerResult LIKE :result")
    suspend fun getFirstHalfCornerKicksResult(result: Double, mainTeamId: Int): Double?

    // Second Half corner result using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCornerResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCornerResult LIKE :result")
    suspend fun getSecondHalfCornerKicksResult(result: Double, mainTeamId: Int): Double?




    //Cards
    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCardsTotals > :value")
    suspend fun getFullTimeCardsOver(value: Int, mainTeamId: Int): Double?

    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCardsTotals > :minusOne AND $FullTimeCardsTotals < :value")
    suspend fun getFullTimeCardsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?



    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCardsTotals > :value")
    suspend fun getFirstHalfCardsOver(value: Int, mainTeamId: Int): Double?

    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCardsTotals > :minusOne AND $FirstHalfCardsTotals < :value")
    suspend fun getFirstHalfCardsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCardsTotals > :value")
    suspend fun getSecondHalfCardsOver(value: Int, mainTeamId: Int): Double?

    // Full time yellow cards totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCardsTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCardsTotals > :minusOne AND $SecondHalfCardsTotals < :value")
    suspend fun getSecondHalfCardsUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time yellow cards result using mainTeamId
    @Query ("SELECT COUNT($FullTimeCardsResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeCardsResult LIKE :result")
    suspend fun getFullTimeCardsResult(result: Double, mainTeamId: Int): Double?

    // First Half yellow cards result using mainTeamId
    @Query ("SELECT COUNT($FirstHalfCardsResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfCardsResult LIKE :result")
    suspend fun getFirstHalfCardsResult(result: Double, mainTeamId: Int): Double?

    // Second Half yellow cards result using mainTeamId
    @Query ("SELECT COUNT($SecondHalfCardsResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfCardsResult LIKE :result")
    suspend fun getSecondHalfCardsResult(result: Double, mainTeamId: Int): Double?





    //Offside
    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeOffsideTotals > :value")
    suspend fun getFullTimeOffsideOver(value: Int, mainTeamId: Int): Double?

    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($FullTimeOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeOffsideTotals > :minusOne AND $FullTimeOffsideTotals < :value")
    suspend fun getFullTimeOffsideUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?



    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfOffsideTotals > :value")
    suspend fun getFirstHalfOffsideOver(value: Int, mainTeamId: Int): Double?

    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($FirstHalfOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfOffsideTotals > :minusOne AND $FirstHalfOffsideTotals < :value")
    suspend fun getFirstHalfOffsideUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfOffsideTotals > :value")
    suspend fun getSecondHalfOffsideOver(value: Int, mainTeamId: Int): Double?

    // Full time offside totals using mainTeamId
    @Query ("SELECT COUNT($SecondHalfOffsideTotals) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfOffsideTotals > :minusOne AND $SecondHalfOffsideTotals < :value")
    suspend fun getSecondHalfOffsideUnder(minusOne: Int, value: Int, mainTeamId: Int): Double?


    // Full time offside result using mainTeamId
    @Query ("SELECT COUNT($FullTimeOffsideResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FullTimeOffsideResult LIKE :result")
    suspend fun getFullTimeOffsideResult(result: Double, mainTeamId: Int): Double?

    // First Half offside result using mainTeamId
    @Query ("SELECT COUNT($FirstHalfOffsideResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $FirstHalfOffsideResult LIKE :result")
    suspend fun getFirstHalfOffsideResult(result: Double, mainTeamId: Int): Double?

    // Second Half offside result using mainTeamId
    @Query ("SELECT COUNT($SecondHalfOffsideResult) FROM $Event_Stats_Entity WHERE mainTeamId LIKE :mainTeamId AND $SecondHalfOffsideResult LIKE :result")
    suspend fun getSecondHalfOffsideResult(result: Double, mainTeamId: Int): Double?






}


