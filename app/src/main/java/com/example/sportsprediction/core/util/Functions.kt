package com.example.sportsprediction.core.util

import androidx.compose.ui.graphics.Color
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Corner_Kicks
import com.example.sportsprediction.core.util.Constants.DrawValue
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.Goalkeeper_Saves
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.LoseValue
import com.example.sportsprediction.core.util.Constants.Match_Goals
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.Offsides
import com.example.sportsprediction.core.util.Constants.Shots_On_Target
import com.example.sportsprediction.core.util.Constants.Total_Shots
import com.example.sportsprediction.core.util.Constants.WinValue
import com.example.sportsprediction.core.util.Constants.Yellow_Cards
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.first_Half
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.core.util.Constants.second_Half
import com.example.sportsprediction.core.util.Constants.shortDateFormat
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.draw
import com.example.sportsprediction.core.util.SuggestionVariables.lose
import com.example.sportsprediction.core.util.SuggestionVariables.win
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Functions {

    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
    val shortDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(shortDateFormat)!!

    fun getEventStatsValues(eventStats: EventStatsEntity, period: String, location: String, marketName: String): String{
        return when(period){
            Full_Time -> {
                when (location) {
                    Home -> {
                        when (marketName) {
                            Match_Goals -> eventStats.fullTimeHomeTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.fullTimeHomeTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.fullTimeHomeTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.fullTimeHomeTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.fullTimeHomeTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.fullTimeHomeTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.fullTimeHomeTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    Away -> {
                        when (marketName) {
                            Match_Goals -> eventStats.fullTimeAwayTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.fullTimeAwayTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.fullTimeAwayTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.fullTimeAwayTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.fullTimeAwayTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.fullTimeAwayTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.fullTimeAwayTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    else -> NotAvailable
                }
            }
            first_Half -> {
                when (location) {
                    Home -> {
                        when (marketName) {
                            Match_Goals -> eventStats.firstHalfHomeTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.firstHalfHomeTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.firstHalfHomeTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.firstHalfHomeTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.firstHalfHomeTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.firstHalfHomeTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.firstHalfHomeTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    Away -> {
                        when (marketName) {
                            Match_Goals -> eventStats.firstHalfAwayTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.firstHalfAwayTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.firstHalfAwayTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.firstHalfAwayTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.firstHalfAwayTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.firstHalfAwayTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.firstHalfAwayTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    else -> NotAvailable
                }
            }
            second_Half -> {
                when (location) {
                    Home -> {
                        when (marketName) {
                            Match_Goals -> eventStats.secondHalfHomeTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.secondHalfHomeTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.secondHalfHomeTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.secondHalfHomeTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.secondHalfHomeTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.secondHalfHomeTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.secondHalfHomeTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    Away -> {
                        when (marketName) {
                            Match_Goals -> eventStats.secondHalfAwayTeamGoalsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Corner_Kicks -> eventStats.secondHalfAwayTeamCornerTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Offsides -> eventStats.secondHalfAwayTeamOffsideTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Yellow_Cards -> eventStats.secondHalfAwayTeamCardTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Total_Shots -> eventStats.secondHalfAwayTeamShotsTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Shots_On_Target -> eventStats.secondHalfAwayTeamShotsOnTargetTotals?.toInt()?.toString()
                                ?: NotAvailable
                            Goalkeeper_Saves -> eventStats.secondHalfAwayTeamGoalKeeperSavesTotals?.toInt()?.toString()
                                ?: NotAvailable
                            else -> NotAvailable
                        }
                    }
                    else -> NotAvailable
                }
            }
            else -> NotAvailable
        }
    }

    fun getResultValue(result: String): Double{
        return when (result){
            win -> WinValue
            lose -> LoseValue
            draw -> DrawValue
            else -> { nullDouble }
        }
    }

    fun getResult(result: Double): String{
        return when (result){
            WinValue -> win
            LoseValue -> lose
            DrawValue -> draw
            else -> { NotAvailable }
        }
    }

    fun getMatchResult(playingLocation: String?, homeScore: Number?, awayScore: Number?): Double {
        val thisPlayingLocation = playingLocation?.lowercase(Locale.ROOT) ?: emptyString
        val home = Home.lowercase(Locale.ROOT)
        val away = Away.lowercase(Locale.ROOT)
        val thisHomeScore = homeScore?.toDouble()
        val thisAwayScore = awayScore?.toDouble()
        return if (thisHomeScore != null && thisAwayScore != null) {
            when(thisPlayingLocation){
                    home-> {
                        if (thisHomeScore > thisAwayScore) {
                            WinValue
                        } else if (thisHomeScore < thisAwayScore) {
                            LoseValue
                        } else DrawValue
                    }
                    away -> {
                        if (thisAwayScore > thisHomeScore) {
                            WinValue
                        } else if (thisAwayScore < thisHomeScore) {
                            LoseValue
                        } else DrawValue
                    }
                    else -> nullDouble
                }
        }
        else nullDouble
    }

    fun getResultBackgroundColor(result: Double): Color {
        return when (result) {
            WinValue -> {
                Color.Green
            }
            LoseValue -> {
                Color.Red
            }
            else -> {
                Color.Gray
            }
        }
    }

    fun findDuplicates(list: List<Any>) : List<Any>{
        val duplicates = mutableListOf<Any>()
        val uniqueValues = mutableSetOf<Any>()
        list.forEach{value->
            if(!uniqueValues.add(value)){
                duplicates.add(value)
            }
        }
        return duplicates
    }

    fun getFormFactor(winningProbability: Double, form: Double): Double{
        val thisForm = form.toInt()
        val thisWinningProbability = winningProbability.toInt()
        when (thisWinningProbability in 100 downTo 0) {
            (thisWinningProbability in 100 downTo 95)->{
                return when (thisForm in -100..100) {
                    (thisForm in -100..-95) -> { -10.0 }
                    (thisForm in -94..-90) -> { -10.0 }
                    (thisForm in -89..-85) -> { -9.0 }
                    (thisForm in -84..-80) -> { -8.0 }
                    (thisForm in -79..-75) -> { -7.0 }
                    (thisForm in -74..-70) -> { -6.0 }
                    (thisForm in -69..-65) -> { -5.0 }
                    (thisForm in -64..-60) -> { -4.0 }
                    (thisForm in -59..-55) -> { -4.0 }
                    (thisForm in -54..-50) -> { -4.0 }
                    (thisForm in -49..-45) -> { -3.0 }
                    (thisForm in -44..-40) -> { -3.0 }
                    (thisForm in -39..-35) -> { -3.0 }
                    (thisForm in -34..-30) -> { -2.0 }
                    (thisForm in -29..-25) -> { -2.0 }
                    (thisForm in -24..-20) -> { -2.0 }
                    (thisForm in -19..-15) -> { -1.0 }
                    (thisForm in -14..-10) -> { -1.0 }
                    (thisForm in -9..-5) -> { 0.0 }
                    (thisForm in -4..0) -> { 0.0 }
                    (thisForm in 1..4) -> { 0.0 }
                    (thisForm in 5..9) -> { 1.0 }
                    (thisForm in 10..14) -> { 1.0 }
                    (thisForm in 15..19) -> { 1.0 }
                    (thisForm in 20..24) -> { 2.0 }
                    (thisForm in 25..29) -> { 2.0 }
                    (thisForm in 30..34) -> { 2.0 }
                    (thisForm in 35..39) -> { 2.0 }
                    (thisForm in 40..44) -> { 3.0 }
                    (thisForm in 45..49) -> { 3.0 }
                    (thisForm in 50..54) -> { 3.0 }
                    (thisForm in 55..59) -> { 3.0 }
                    (thisForm in 60..64) -> { 3.0 }
                    (thisForm in 65..69) -> { 4.0 }
                    (thisForm in 70..74) -> { 4.0 }
                    (thisForm in 75..79) -> { 4.0 }
                    (thisForm in 80..84) -> { 4.0 }
                    (thisForm in 85..89) -> { 4.0 }
                    (thisForm in 90..95) -> { 4.0 }
                    else -> { 4.0 }
                }
            }
            (thisWinningProbability in 94 downTo 90)-> {
                return when (thisForm in -100..100) {
                    (thisForm in -100..-95) -> { -10.0 }
                    (thisForm in -94..-90) -> { -10.0 }
                    (thisForm in -89..-85) -> { -9.0 }
                    (thisForm in -84..-80) -> { -8.0 }
                    (thisForm in -79..-75) -> { -7.0 }
                    (thisForm in -74..-70) -> { -6.0 }
                    (thisForm in -69..-65) -> { -5.0 }
                    (thisForm in -64..-60) -> { -4.0 }
                    (thisForm in -59..-55) -> { -4.0 }
                    (thisForm in -54..-50) -> { -4.0 }
                    (thisForm in -49..-45) -> { -3.0 }
                    (thisForm in -44..-40) -> { -3.0 }
                    (thisForm in -39..-35) -> { -3.0 }
                    (thisForm in -34..-30) -> { -2.0 }
                    (thisForm in -29..-25) -> { -2.0 }
                    (thisForm in -24..-20) -> { -2.0 }
                    (thisForm in -19..-15) -> { -1.0 }
                    (thisForm in -14..-10) -> { -1.0 }
                    (thisForm in -9..-5) -> { 0.0 }
                    (thisForm in -4..0) -> { 0.0 }
                    (thisForm in 1..4) -> { 0.0 }
                    (thisForm in 5..9) -> { 1.0 }
                    (thisForm in 10..14) -> { 1.0 }
                    (thisForm in 15..19) -> { 1.0 }
                    (thisForm in 20..24) -> { 2.0 }
                    (thisForm in 25..29) -> { 2.0 }
                    (thisForm in 30..34) -> { 3.0 }
                    (thisForm in 35..39) -> { 3.0 }
                    (thisForm in 40..44) -> { 4.0 }
                    (thisForm in 45..49) -> { 4.0 }
                    (thisForm in 50..54) -> { 4.0 }
                    (thisForm in 55..59) -> { 5.0 }
                    (thisForm in 60..64) -> { 5.0 }
                    (thisForm in 65..69) -> { 5.0 }
                    (thisForm in 70..74) -> { 6.0 }
                    (thisForm in 75..79) -> { 6.0 }
                    (thisForm in 80..84) -> { 6.0 }
                    (thisForm in 85..89) -> { 7.0 }
                    (thisForm in 90..95) -> { 7.0 }
                    else -> { 7.0 }
                }
            }
            (thisWinningProbability in 89 downTo 85)-> {
                return when (thisForm in -100..100) {
                    (thisForm in -100..-95) -> { -10.0 }
                    (thisForm in -94..-90) -> { -10.0 }
                    (thisForm in -89..-85) -> { -9.0 }
                    (thisForm in -84..-80) -> { -8.0 }
                    (thisForm in -79..-75) -> { -7.0 }
                    (thisForm in -74..-70) -> { -6.0 }
                    (thisForm in -69..-65) -> { -5.0 }
                    (thisForm in -64..-60) -> { -4.0 }
                    (thisForm in -59..-55) -> { -4.0 }
                    (thisForm in -54..-50) -> { -4.0 }
                    (thisForm in -49..-45) -> { -3.0 }
                    (thisForm in -44..-40) -> { -3.0 }
                    (thisForm in -39..-35) -> { -3.0 }
                    (thisForm in -34..-30) -> { -2.0 }
                    (thisForm in -29..-25) -> { -2.0 }
                    (thisForm in -24..-20) -> { -2.0 }
                    (thisForm in -19..-15) -> { -1.0 }
                    (thisForm in -14..-10) -> { -1.0 }
                    (thisForm in -9..-5) -> { 0.0 }
                    (thisForm in -4..0) -> { 0.0 }
                    (thisForm in 1..4) -> { 0.0 }
                    (thisForm in 5..9) -> { 1.0 }
                    (thisForm in 10..14) -> { 1.0 }
                    (thisForm in 15..19) -> { 1.0 }
                    (thisForm in 20..24) -> { 2.0 }
                    (thisForm in 25..29) -> { 2.0 }
                    (thisForm in 30..34) -> { 3.0 }
                    (thisForm in 35..39) -> { 3.0 }
                    (thisForm in 40..44) -> { 4.0 }
                    (thisForm in 45..49) -> { 4.0 }
                    (thisForm in 50..54) -> { 5.0 }
                    (thisForm in 55..59) -> { 5.0 }
                    (thisForm in 60..64) -> { 6.0 }
                    (thisForm in 65..69) -> { 6.0 }
                    (thisForm in 70..74) -> { 7.0 }
                    (thisForm in 75..79) -> { 7.0 }
                    (thisForm in 80..84) -> { 7.0 }
                    (thisForm in 85..89) -> { 8.0 }
                    (thisForm in 90..95) -> { 8.0 }
                    else -> { 8.0 }
                }
            }
            else-> {
                return when (thisForm in -100..100) {
                    (thisForm in -100..-95) -> { -10.0 }
                    (thisForm in -94..-90) -> { -10.0 }
                    (thisForm in -89..-85) -> { -9.0 }
                    (thisForm in -84..-80) -> { -8.0 }
                    (thisForm in -79..-75) -> { -7.0 }
                    (thisForm in -74..-70) -> { -6.0 }
                    (thisForm in -69..-65) -> { -5.0 }
                    (thisForm in -64..-60) -> { -4.0 }
                    (thisForm in -59..-55) -> { -4.0 }
                    (thisForm in -54..-50) -> { -4.0 }
                    (thisForm in -49..-45) -> { -3.0 }
                    (thisForm in -44..-40) -> { -3.0 }
                    (thisForm in -39..-35) -> { -3.0 }
                    (thisForm in -34..-30) -> { -2.0 }
                    (thisForm in -29..-25) -> { -2.0 }
                    (thisForm in -24..-20) -> { -2.0 }
                    (thisForm in -19..-15) -> { -1.0 }
                    (thisForm in -14..-10) -> { -1.0 }
                    (thisForm in -9..-5) -> { 0.0 }
                    (thisForm in -4..0) -> { 0.0 }
                    (thisForm in 1..4) -> { 0.0 }
                    (thisForm in 5..9) -> { 1.0 }
                    (thisForm in 10..14) -> { 1.0 }
                    (thisForm in 15..19) -> { 1.0 }
                    (thisForm in 20..24) -> { 2.0 }
                    (thisForm in 25..29) -> { 3.0 }
                    (thisForm in 30..34) -> { 3.0 }
                    (thisForm in 35..39) -> { 4.0 }
                    (thisForm in 40..44) -> { 5.0 }
                    (thisForm in 45..49) -> { 5.0 }
                    (thisForm in 50..54) -> { 6.0 }
                    (thisForm in 55..59) -> { 6.0 }
                    (thisForm in 60..64) -> { 7.0 }
                    (thisForm in 65..69) -> { 7.0 }
                    (thisForm in 70..74) -> { 8.0 }
                    (thisForm in 75..79) -> { 8.0 }
                    (thisForm in 80..84) -> { 9.0 }
                    (thisForm in 85..89) -> { 9.0 }
                    (thisForm in 90..95) -> { 10.0 }
                    else -> { 10.0 }
                }
            }

        }

    }

    fun getHeadToHeadFactor(winningProbability: Double, headToHeadForm: Double): Double{
        val thisHeadToHeadForm = headToHeadForm.toInt()
        val thisWinningProbability = winningProbability.toInt()
        return when(thisWinningProbability in 100 downTo 0){
            (thisWinningProbability in 100 downTo 95)->{
                when(thisHeadToHeadForm in 100 downTo 0){
                    (thisHeadToHeadForm in 100 downTo 70) -> {3.0}
                    (thisHeadToHeadForm in 69 downTo 50) -> {1.0}
                    (thisHeadToHeadForm in 49 downTo 30) -> {-1.0}
                    else -> {-5.0}
                }
            }
            else -> {
                when(thisHeadToHeadForm in 100 downTo 0){
                    (thisHeadToHeadForm in 100 downTo 70) -> {3.0}
                    (thisHeadToHeadForm in 69 downTo 50) -> {1.0}
                    (thisHeadToHeadForm in 49 downTo 30) -> {-1.0}
                    else -> {-5.0}
                }
            }
        }

    }

    fun getPlayingLocationFactor(winningProbability: Double, location: String): Double{
        val thisLocation = location.lowercase(Locale.ROOT)
        val thisWinningProbability = winningProbability.toInt()
        return when(thisWinningProbability in 100 downTo 0){
            (thisWinningProbability in 100 downTo 95)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-1.0}
                    else -> {3.0}
                }
            }
            (thisWinningProbability in 94 downTo 90)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-1.0}
                    else -> {3.0}
                }
            }
            (thisWinningProbability in 89 downTo 80)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-1.0}
                    else -> {3.0}
                }
            }
            (thisWinningProbability in 79 downTo 75)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-1.0}
                    else -> {3.0}
                }
            }
            (thisWinningProbability in 74 downTo 70)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-1.0}
                    else -> {2.0}
                }
            }
            (thisWinningProbability in 69 downTo 65)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-2.0}
                    else -> {2.0}
                }
            }
            (thisWinningProbability in 64 downTo 55)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-2.0}
                    else -> {1.0}
                }
            }
            (thisWinningProbability in 54 downTo 0)->{
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-3.0}
                    else -> {1.0}
                }
            }
            else -> {
                when(thisLocation){
                    Away.lowercase(Locale.ROOT) -> {-3.0}
                    else -> {1.0}
                }
            }
        }

    }

    fun getTeamOddsMultiplierValue(oddsValue: Double): Double {
        return if (oddsValue >= 0.0 && oddsValue < 1.1) 1.2
        else if (oddsValue >= 1.1 && oddsValue < 1.15) 1.18
        else if (oddsValue >= 1.15 && oddsValue < 1.2) 1.15
        else if (oddsValue >= 1.2 && oddsValue < 1.3) 1.10
        else if (oddsValue >= 1.3 && oddsValue < 1.4) 1.08
        else if (oddsValue >= 1.4 && oddsValue < 1.5) 1.06
        else if (oddsValue >= 1.5 && oddsValue < 1.6) 1.04
        else if (oddsValue >= 1.6 && oddsValue < 1.7) 1.02
        else if (oddsValue >= 1.7 && oddsValue < 1.8) 1.00
        else if (oddsValue >= 1.8 && oddsValue < 2.0) 0.98
        else if (oddsValue >= 2.0 && oddsValue < 2.5) 0.95
        else if (oddsValue >= 2.5 && oddsValue < 3.5) 0.90
        else if (oddsValue >= 3.5 && oddsValue < 4.5) 0.85
        else if (oddsValue >= 4.5 && oddsValue < 6.5) 0.80
        else 0.75
    }

    fun getEventOddsChoiceName(marketName: String): List<String> {
        return when(marketName){
            Full_Time -> { listOf("1", "2") }
            Constants.Double_Chance -> { listOf("1X", "X2") }
            first_Half -> { listOf("1", "2") }
            Constants.Both_Teams_To_Score -> { listOf("Yes", "No") }
            Match_Goals -> { listOf("Over", "Under") }
            Constants.Cards_In_Match -> { listOf("Over", "Under") }
            Constants.Corners_2_Way -> { listOf("Over", "Under") }
            else -> listOf("","")
        }
    }

    fun getEventOddsMarketName(marketName: String): String {
        return when(marketName){
            Full_Time_Match_Result -> {
                Full_Time
            }
            Full_Time_Double_Chance -> {
                Constants.Double_Chance
            }
            First_Half_Match_Result -> {
                first_Half
            }
            Full_Time_Both_Teams_To_Score -> {
                Constants.Both_Teams_To_Score
            }
            Full_Time_Goals_Totals -> {
                Match_Goals
            }
            Full_Time_Yellow_Cards_Totals -> {
                Constants.Cards_In_Match
            }
            Full_Time_Corner_Totals -> {
                Constants.Corners_2_Way
            }
            else -> emptyString
        }
    }

    fun getOpponentForm(formValue: Double): Double {
        val form = formValue.toInt()
        return if (form in 91..100) 0.6
        else if (form in 81 ..90) 0.65
        else if (form in 71 ..80) 0.70
        else if (form in 61 ..70) 0.75
        else if (form in 51 ..60) 0.80
        else if (form in 41 ..50) 0.85
        else if (form in 31 ..40) 0.90
        else if (form in 21 ..30) 0.95
        else if (form in 11 ..20) 0.975
        else 0.99
    }

    fun mapCategoryToScores(category: String, eventStats: EventStatsEntity): MutableList<String>{
        val scores = mutableListOf<String>()
        when(category){
            Both_Halves_Totals -> {
                val firstHalfHomeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val secondHalfHomeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()

                val firstHalfAwayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                val secondHalfAwayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()

                scores.add("$firstHalfHomeScore : $secondHalfHomeScore")
                scores.add("$firstHalfAwayScore : $secondHalfAwayScore")
            }
            Full_Time_Match_Result -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Match_Result -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Match_Result -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }

            Full_Time_MultiGoals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_MultiGoals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_MultiGoals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Handicap -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Handicap -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Handicap -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Goals_Totals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Goals_Totals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Goals_Totals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Double_Chance -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Double_Chance -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Double_Chance -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Main_Team_MultiGoals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Main_Team_MultiGoals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Main_Team_MultiGoals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Main_Team_Clean_Sheets -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Main_Team_Clean_Sheets -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Main_Team_Clean_Sheets -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Both_Teams_To_Score -> {
                val homeScore = if (eventStats.fullTimeHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Both_Teams_To_Score -> {
                val homeScore = if (eventStats.firstHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Both_Teams_To_Score -> {
                val homeScore = if (eventStats.secondHalfHomeTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamGoalsTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamGoalsTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamGoalsTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamGoalsTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }

            Full_Time_Corner_Result -> {
                val homeScore = if (eventStats.fullTimeHomeTeamCornerTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamCornerTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Corner_Result -> {
                val homeScore = if (eventStats.firstHalfHomeTeamCornerTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamCornerTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Corner_Result -> {
                val homeScore = if (eventStats.secondHalfHomeTeamCornerTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamCornerTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Corner_Totals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamCornerTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamCornerTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Corner_Totals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamCornerTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamCornerTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Corner_Totals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamCornerTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamCornerTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamCornerTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamCornerTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamCornerTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamCornerTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Offsides_Result -> {
                val homeScore = if (eventStats.fullTimeHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Offsides_Result -> {
                val homeScore = if (eventStats.firstHalfHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Offsides_Result -> {
                val homeScore = if (eventStats.secondHalfHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Offsides_Totals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Offsides_Totals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Offsides_Totals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamOffsideTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamOffsideTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamOffsideTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamOffsideTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamOffsideTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamOffsideTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }

            Full_Time_Yellow_Cards_Result -> {
                val homeScore = if (eventStats.fullTimeHomeTeamCardTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamCardTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamCardTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamCardTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Yellow_Cards_Result -> {
                val homeScore = if (eventStats.firstHalfHomeTeamCardTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamCardTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamCardTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamCardTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Yellow_Cards_Result -> {
                val homeScore = if (eventStats.secondHalfHomeTeamCardTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamCardTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamCardTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamCardTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Full_Time_Yellow_Cards_Totals -> {
                val homeScore = if (eventStats.fullTimeHomeTeamCardTotals == null) NotAvailable else if (eventStats.fullTimeHomeTeamCardTotals < 0.0) NotAvailable else eventStats.fullTimeHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.fullTimeAwayTeamCardTotals == null) NotAvailable else if (eventStats.fullTimeAwayTeamCardTotals < 0.0) NotAvailable else eventStats.fullTimeAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            First_Half_Yellow_Cards_Totals -> {
                val homeScore = if (eventStats.firstHalfHomeTeamCardTotals == null) NotAvailable else if (eventStats.firstHalfHomeTeamCardTotals < 0.0) NotAvailable else eventStats.firstHalfHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.firstHalfAwayTeamCardTotals == null) NotAvailable else if (eventStats.firstHalfAwayTeamCardTotals < 0.0) NotAvailable else eventStats.firstHalfAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            Second_Half_Yellow_Cards_Totals -> {
                val homeScore = if (eventStats.secondHalfHomeTeamCardTotals == null) NotAvailable else if (eventStats.secondHalfHomeTeamCardTotals < 0.0) NotAvailable else eventStats.secondHalfHomeTeamCardTotals.toInt().toString()
                val awayScore = if (eventStats.secondHalfAwayTeamCardTotals == null) NotAvailable else if (eventStats.secondHalfAwayTeamCardTotals < 0.0) NotAvailable else eventStats.secondHalfAwayTeamCardTotals.toInt().toString()
                scores.add(homeScore)
                scores.add(awayScore)
            }
            else -> {
                scores.add(NotAvailable)
                scores.add(NotAvailable)
            }


        }
        return scores
    }

}