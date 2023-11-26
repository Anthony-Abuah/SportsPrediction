package com.example.sportsprediction.feature_app.data.repository

import android.util.Log
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Both_Teams_To_Score
import com.example.sportsprediction.core.util.Constants.Corner_Kicks
import com.example.sportsprediction.core.util.Constants.Double_Chance
import com.example.sportsprediction.core.util.Constants.DrawValue
import com.example.sportsprediction.core.util.Constants.First_Half
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.Handicap
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.LoseValue
import com.example.sportsprediction.core.util.Constants.Match_Result
import com.example.sportsprediction.core.util.Constants.Offsides
import com.example.sportsprediction.core.util.Constants.Second_Half
import com.example.sportsprediction.core.util.Constants.WinValue
import com.example.sportsprediction.core.util.Constants.Yellow_Cards
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.Functions.getEventOddsMarketName
import com.example.sportsprediction.core.util.Functions.getFormFactor
import com.example.sportsprediction.core.util.Functions.getHeadToHeadFactor
import com.example.sportsprediction.core.util.Functions.getPlayingLocationFactor
import com.example.sportsprediction.core.util.Functions.getResultValue
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.SuggestionVariables.BothTeams
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Corners_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Corners_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.DrawOrLose
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCorners
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Lose_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Win_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Win_Or_Lose
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.FullTimeCorners
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Goals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Lose_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Win_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Win_Or_Lose
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Main_Team
import com.example.sportsprediction.core.util.SuggestionVariables.Multigoals
import com.example.sportsprediction.core.util.SuggestionVariables.No
import com.example.sportsprediction.core.util.SuggestionVariables.No_Goal
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.SecondHalfCorners
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Goals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Lose_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Win_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Win_Or_Lose
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.WinOrDraw
import com.example.sportsprediction.core.util.SuggestionVariables.WinOrLose
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Yes
import com.example.sportsprediction.core.util.SuggestionVariables.max
import com.example.sportsprediction.core.util.SuggestionVariables.min
import com.example.sportsprediction.core.util.SuggestionVariables.over
import com.example.sportsprediction.core.util.SuggestionVariables.pointFive
import com.example.sportsprediction.core.util.SuggestionVariables.resultsList
import com.example.sportsprediction.core.util.SuggestionVariables.under
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsDao
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventDao
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.WinProbability
import com.example.sportsprediction.feature_app.domain.repository.TeamSuggestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class TeamSuggestionsRepositoryImpl(
    private val teamSuggestionsDao: TeamSuggestionsDao,
    private val eventStatsDao: EventStatsDao,
    private val eventOddsDao: EventOddsDao,
    private val teamEventDao: TeamEventDao
) : TeamSuggestionsRepository {

    override suspend fun getBetorsConfidence(
        suggestion: Suggestion,
        betSuggestion: BetSuggestion
    ): Flow<Resource<Double>> = flow {
        var confidenceLevel = 0.0
        emit(Resource.Loading(confidenceLevel))
        try {
            val localDateTwoYearsAgo = LocalDate.now().minusYears(2)
            val longDateTwoYearsAgo = localDateTwoYearsAgo.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond

            val eventId = betSuggestion.eventId ?: "0"
            val headToHeadId = betSuggestion.headToHeadId ?: emptyString
            val numerator = betSuggestion.numerator ?: "0.0"
            val denominator = betSuggestion.denominator ?: "0.0"
            val mainTeamName = betSuggestion.mainTeamName ?: emptyString
            val opposingTeamName = betSuggestion.mainTeamName ?: emptyString
            val mainTeamId = betSuggestion.mainTeamId ?: "0"
            val opposingTeamId = betSuggestion.opposingTeamId ?: "0"
            val playingLocation = betSuggestion.mainTeamPlayingLocation ?: Home
            val opposingTeamPlayingLocation =
                if (playingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) Home else Away
            val homeTeamId =
                if (playingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) opposingTeamId else mainTeamId
            val awayTeamId =
                if (playingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) opposingTeamId else mainTeamId
            val homeTeamName =
                if (playingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) opposingTeamName else mainTeamName
            val awayTeamName =
                if (playingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) opposingTeamName else mainTeamName
            val marketCategory = betSuggestion.marketCategory ?: emptyString

            val mainTeamHeadToHeadEvents =
                teamEventDao.getHeadToHeadEventsList(mainTeamId.toInt(), headToHeadId)
                    ?: emptyList()
            val opposingTeamHeadToHeadEvents =
                teamEventDao.getHeadToHeadEventsList(opposingTeamId.toInt(), headToHeadId)
                    ?: emptyList()
            val previousMainTeamEvents =
                teamEventDao.getListOfTeamEventsForThisParticularTeam(mainTeamId.toInt())
                    ?: emptyList()
            val previousOpposingTeamEvents =
                teamEventDao.getListOfTeamEventsForThisParticularTeam(opposingTeamId.toInt())
                    ?: emptyList()

            val mainTeamFormEvents = mutableListOf<TeamEventEntity>()
            mainTeamFormEvents.clear()
            val opposingTeamFormEvents = mutableListOf<TeamEventEntity>()
            opposingTeamFormEvents.clear()

            mainTeamFormEvents.addAll(previousMainTeamEvents.toSet().take(6))
            mainTeamFormEvents.addAll(mainTeamHeadToHeadEvents.toSet().take(4))
            opposingTeamFormEvents.addAll(previousOpposingTeamEvents.toSet().take(6))
            opposingTeamFormEvents.addAll(opposingTeamHeadToHeadEvents.toSet().take(4))

            var mainTeamFormPercentageValue = 0.0
            var totalMainTeamFormValues = 0.0

            var opposingTeamFormPercentageValue = 0.0
            var totalOpposingTeamFormValues = 0.0


            mainTeamFormEvents.forEach { formEvent ->
                val mainTeamPlayingLocation = formEvent.mainTeamPlayingLocation ?: Home
                val mainTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger
                val opponentTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger

                val isNotNull =
                    !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
                val totalValue = if (isNotNull) 1.0 else 0.0
                val resultValue = if (isNotNull && (mainTeamScores > opponentTeamScores)) {
                    1.0
                } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                    0.0
                } else if (isNotNull) {
                    0.5
                } else {
                    0.0
                }

                totalMainTeamFormValues += totalValue
                mainTeamFormPercentageValue += resultValue

                Log.d("TeamSuggestionsRepository", "totalValue: $totalValue")
                Log.d(
                    "TeamSuggestionsRepository",
                    "totalMainTeamFormValues: $totalMainTeamFormValues"
                )

                Log.d("TeamSuggestionsRepository", "resultValue: $resultValue")
                Log.d(
                    "TeamSuggestionsRepository",
                    "mainTeamFormPercentageValue: $mainTeamFormPercentageValue"
                )

            }
            val mainTeamFormPercentage = try {
                (mainTeamFormPercentageValue.div(totalMainTeamFormValues)).times(100.0)
            } catch (e: ArithmeticException) {
                0.0
            }
            Log.d("TeamSuggestionsRepository", "mainTeamFormPercentage: $mainTeamFormPercentage")


            opposingTeamFormEvents.forEach { formEvent ->
                val opposingTeamLocation = formEvent.mainTeamPlayingLocation ?: Home
                val opposingTeamScores =
                    if (opposingTeamLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger
                val opponentTeamScores =
                    if (opposingTeamLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger

                val isNotNull =
                    !(opposingTeamScores == nullInteger || opponentTeamScores == nullInteger)
                val totalValue = if (isNotNull) 1.0 else 0.0
                val resultValue = if (isNotNull && (opposingTeamScores > opponentTeamScores)) {
                    1.0
                } else if (isNotNull && (opposingTeamScores < opponentTeamScores)) {
                    0.0
                } else if (isNotNull) {
                    0.5
                } else {
                    0.0
                }
                totalOpposingTeamFormValues += totalValue
                opposingTeamFormPercentageValue += resultValue
            }
            val opposingTeamFormPercentage = try {
                (opposingTeamFormPercentageValue.div(totalOpposingTeamFormValues)).times(100.0)
            } catch (e: ArithmeticException) {
                0.0
            }


            val mainTeamHeadToHeadFormEvent = mutableListOf<TeamEventEntity>()
            mainTeamHeadToHeadFormEvent.clear()
            mainTeamHeadToHeadEvents.toSet().toList().forEach { event ->
                val longStartDate = event.startTimestamp?.toLong() ?: 0.toLong()
                if (longStartDate > longDateTwoYearsAgo) {
                    mainTeamHeadToHeadFormEvent.add(event)
                    Log.d(
                        "TeamSuggestionsRepository",
                        "mainTeamHeadToHeadFormEvent(2years): ${event.eventId}"
                    )

                }
            }
            var totalMainTeamHeadToHeadFormValue = 0.0
            var mainTeamHeadToHeadFormPercentageValue = 0.0
            mainTeamHeadToHeadFormEvent.forEach { formEvent ->
                val mainTeamPlayingLocation = formEvent.mainTeamPlayingLocation ?: Home
                val mainTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger
                val opponentTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger

                val isNotNull =
                    !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
                val totalValue = if (isNotNull) 1.0 else 0.0
                val resultValue = if (isNotNull && (mainTeamScores > opponentTeamScores)) {
                    1.0
                } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                    0.0
                } else if (isNotNull) {
                    0.5
                } else {
                    0.0
                }
                totalMainTeamHeadToHeadFormValue += totalValue
                mainTeamHeadToHeadFormPercentageValue += resultValue
            }


            val mainTeamHeadToHeadFormPercentage = try {
                (mainTeamHeadToHeadFormPercentageValue.div(totalMainTeamHeadToHeadFormValue)).times(
                    100.0
                )
            } catch (e: ArithmeticException) {
                0.0
            }

            val opposingTeamHeadToHeadFormEvent = mutableListOf<TeamEventEntity>()
            opposingTeamHeadToHeadFormEvent.clear()
            opposingTeamHeadToHeadEvents.toSet().toList().forEach { event ->
                val longStartDate = event.startTimestamp?.toLong() ?: 0.toLong()
                if (longStartDate > longDateTwoYearsAgo) {
                    opposingTeamHeadToHeadFormEvent.add(event)
                    Log.d(
                        "TeamSuggestionsRepository",
                        "opposingTeamHeadToHeadFormEvent(2years): ${event.eventId}"
                    )
                }

            }
            var totalOpposingTeamHeadToHeadFormValue = 0.0
            var opposingTeamHeadToHeadFormPercentageValue = 0.0
            opposingTeamHeadToHeadFormEvent.forEach { formEvent ->
                val mainTeamPlayingLocation = formEvent.mainTeamPlayingLocation ?: Home
                val mainTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger
                val opponentTeamScores =
                    if (mainTeamPlayingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) formEvent.awayScore
                        ?: nullInteger else formEvent.homeScore ?: nullInteger

                val isNotNull =
                    !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
                val totalValue = if (isNotNull) 1.0 else 0.0
                val resultValue = if (isNotNull && (mainTeamScores > opponentTeamScores)) {
                    1.0
                } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                    0.0
                } else if (isNotNull) {
                    0.5
                } else {
                    0.0
                }
                totalOpposingTeamHeadToHeadFormValue += totalValue
                opposingTeamHeadToHeadFormPercentageValue += resultValue
            }

            val opposingTeamHeadToHeadFormPercentage = try {
                (opposingTeamHeadToHeadFormPercentageValue.div(totalOpposingTeamHeadToHeadFormValue)).times(
                    100.0
                )
            } catch (e: ArithmeticException) {
                0.0
            }


            val oddsList = eventOddsDao.getListOfTeamEventOdds(
                homeTeamId.toInt(),
                awayTeamId.toInt(),
                eventId.toInt()
            ) ?: emptyList()

            val marketName = betSuggestion.marketName ?: emptyString
            var homeTeamOdds = 1.2
            var drawOdds = 8.0
            var awayTeamOdds = 5.0

            var winningProbability = 0.0

            var winProbability =
                WinProbability(0, emptyString, emptyString, 0, emptyString, 0, 0.0, 0.0, 0.0)

            if (marketCategory == Full_Time_Match_Result) {
                val eventOddsMarketName = getEventOddsMarketName(marketName)
                oddsList.forEach { eventOddsEntity ->
                    if (eventOddsEntity.marketName?.lowercase(Locale.ROOT) == Full_Time.lowercase(
                            Locale.ROOT
                        )
                    ) {
                        eventOddsEntity.choices?.forEach { choice ->
                            if (choice.name?.lowercase(Locale.ROOT) == "1".lowercase(Locale.ROOT)) {
                                homeTeamOdds = choice.fractionalValue ?: 0.0
                            }
                            if (choice.name?.lowercase(Locale.ROOT) == "X".lowercase(Locale.ROOT)) {
                                drawOdds = choice.fractionalValue ?: 0.0
                            }
                            if (choice.name?.lowercase(Locale.ROOT) == "2".lowercase(Locale.ROOT)) {
                                awayTeamOdds = choice.fractionalValue ?: 0.0
                            }
                        }
                    }
                }

                val homeWin = homeTeamOdds <= awayTeamOdds
                val awayWin = homeTeamOdds > awayTeamOdds
                val isPlayingAtHome =
                    playingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)
                val isPlayingAway =
                    playingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)

                val winningOddsProbability =
                    if (awayWin) (1.0.div(awayTeamOdds) * 100.0) else (1.0.div(homeTeamOdds) * 100.0)
                val losingOddsProbability =
                    if (homeWin) (1.0.div(awayTeamOdds) * 100.0) else (1.0.div(homeTeamOdds) * 100.0)
                val winningSide =
                    if ((homeWin && isPlayingAway) || (awayWin && isPlayingAtHome)) opposingTeamName else mainTeamName


                Log.d("TeamSuggestionsRepository", "MainTeamOdds: $homeTeamOdds")
                Log.d("TeamSuggestionsRepository", "OpposingTeamOdds: $awayTeamOdds")


                winningProbability = if (winningSide == opposingTeamName) {
                    winningOddsProbability.plus(
                        getFormFactor(
                            winningOddsProbability,
                            opposingTeamFormPercentage.minus(mainTeamFormPercentage)
                        )
                    )
                } else {
                    winningOddsProbability.plus(
                        getFormFactor(
                            winningOddsProbability,
                            mainTeamFormPercentage.minus(opposingTeamFormPercentage)
                        )
                    )
                }
                Log.d("TeamSuggestionsRepository", "WinningSide: $winningSide")
                Log.d("TeamSuggestionsRepository", "MainTeamForm: $mainTeamFormPercentage")
                Log.d("TeamSuggestionsRepository", "OpposingTeamForm: $opposingTeamFormPercentage")


                winningProbability += if (winningSide == opposingTeamName) {
                    getPlayingLocationFactor(winningProbability, opposingTeamPlayingLocation)
                } else {
                    getPlayingLocationFactor(winningProbability, playingLocation)
                }
                Log.d("TeamSuggestionsRepository", "Main Team Playing Location: $playingLocation")
                Log.d(
                    "TeamSuggestionsRepository",
                    "Opposing Team Playing Location: $opposingTeamPlayingLocation"
                )

                winningProbability += if (winningSide == opposingTeamName) {
                    getHeadToHeadFactor(winningProbability, opposingTeamHeadToHeadFormPercentage)
                } else {
                    getHeadToHeadFactor(winningProbability, mainTeamHeadToHeadFormPercentage)
                }
                Log.d(
                    "TeamSuggestionsRepository",
                    "MainTeamH2HForm: $mainTeamHeadToHeadFormPercentage"
                )
                Log.d(
                    "TeamSuggestionsRepository",
                    "OpposingTeamH2HForm: $opposingTeamHeadToHeadFormPercentage"
                )

                // Save into the winning probability entity(database)
                if (awayWin) {
                    val changeInWinProbability = winningOddsProbability - winningProbability
                    val loseOrDrawProbability = 100.0.minus(winningProbability)
                    val loseDrawRatio =
                        (1.0.div(homeTeamOdds) * 100.0).div(1.0.div(drawOdds) * 100.0)
                    val losingProbability =
                        loseOrDrawProbability.times(loseDrawRatio.div((loseDrawRatio.plus(1.0))))
                    val adjustedLosingProbability =
                        losingProbability.plus(changeInWinProbability.times(loseDrawRatio))
                    val adjustedDrawProbability =
                        100.0.minus(winningProbability).minus(adjustedLosingProbability)

                    winProbability = WinProbability(
                        eventId = eventId.toInt(),
                        headToHeadId = headToHeadId,
                        homeTeamName = homeTeamName,
                        awayTeamName = awayTeamName,
                        homeTeamId = homeTeamId.toInt(),
                        awayTeamId = awayTeamId.toInt(),
                        homeWinProbability = if (opposingTeamPlayingLocation.lowercase(Locale.ROOT) == Home.lowercase(
                                Locale.ROOT
                            )
                        ) winningProbability else adjustedLosingProbability,
                        drawProbability = adjustedDrawProbability,
                        awayWinProbability = if (opposingTeamPlayingLocation.lowercase(Locale.ROOT) == Away.lowercase(
                                Locale.ROOT
                            )
                        ) winningProbability else adjustedLosingProbability,
                    )
                } else {
                    val changeInWinProbability = winningOddsProbability - winningProbability
                    val loseOrDrawProbability = 100.0.minus(winningProbability)
                    val loseDrawRatio =
                        (1.0.div(awayTeamOdds) * 100.0).div(1.0.div(drawOdds) * 100.0)
                    val losingProbability =
                        loseOrDrawProbability.times(loseDrawRatio.div((loseDrawRatio.plus(1.0))))
                    val adjustedLosingProbability =
                        losingProbability.plus(changeInWinProbability.times(loseDrawRatio))
                    val adjustedDrawProbability =
                        100.0.minus(winningProbability).minus(adjustedLosingProbability)

                    winProbability = WinProbability(
                        eventId = eventId.toInt(),
                        headToHeadId = headToHeadId,
                        homeTeamName = homeTeamName,
                        awayTeamName = awayTeamName,
                        homeTeamId = homeTeamId.toInt(),
                        awayTeamId = awayTeamId.toInt(),
                        homeWinProbability = if (opposingTeamPlayingLocation.lowercase(Locale.ROOT) == Home.lowercase(
                                Locale.ROOT
                            )
                        ) winningProbability else adjustedLosingProbability,
                        drawProbability = adjustedDrawProbability,
                        awayWinProbability = if (opposingTeamPlayingLocation.lowercase(Locale.ROOT) == Away.lowercase(
                                Locale.ROOT
                            )
                        ) winningProbability else adjustedLosingProbability,
                    )
                }


            }

            confidenceLevel = winningProbability

        } catch (e: ArithmeticException) {
            emit(
                Resource.Error(
                    message = "Cannot Do this",
                    data = confidenceLevel
                )
            )
        } catch (e: NoSuchElementException) {
            emit(
                Resource.Error(
                    message = "Cannot Do this",
                    data = confidenceLevel
                )
            )
        }

        emit(Resource.Success(confidenceLevel))
    }

    override fun getTeamSuggestions(
        teamId: Int,
        teamName: String,
        date: Date
    ): Flow<Resource<TeamSuggestionsEntity>> = flow {
        emit(Resource.Loading())

        val teamPrediction = teamSuggestionsDao.getThisTeamSuggestions(teamId)
        emit(Resource.Loading(data = teamPrediction))

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()


        //for (numbers in resultsList){


        val suggestions = mutableListOf<Suggestion>()

        resultsList.forEach { result ->
            // Full Time Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeMatchResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFullTimeMatchResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFullTimeMatchResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFullTimeMatchResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )


            /*
                    Log.d("TeamSuggestionsRepository", "result List Result: $result")
                    Log.d("TeamSuggestionsRepository", "result List Result Value: ${getResultValue(result)}")
                    Log.d("TeamSuggestionsRepository", "result List Outcome: ${eventStatsDao.getFullTimeMatchResult(getResultValue(result), teamId)!!}")
                    Log.d("TeamSuggestionsRepository", "result List Sample Space: ${eventStatsDao.getFullTimeMatchResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFullTimeMatchResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFullTimeMatchResult(DrawValue, teamId)!!)}")
                */


            // First Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfMatchResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFirstHalfMatchResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFirstHalfMatchResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFirstHalfMatchResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )

            // Second Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfMatchResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getSecondHalfMatchResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getSecondHalfMatchResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getSecondHalfMatchResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )

            // Full time corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeCornerKicksResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFullTimeCornerKicksResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFullTimeCornerKicksResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFullTimeCornerKicksResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )

            // First half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfCornerKicksResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFirstHalfCornerKicksResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFirstHalfCornerKicksResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFirstHalfCornerKicksResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )
            // Second half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfCornerKicksResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getSecondHalfCornerKicksResult(
                        WinValue,
                        teamId
                    )!!
                        .plus(eventStatsDao.getSecondHalfCornerKicksResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getSecondHalfCornerKicksResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
            // Full time yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeCardsResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFullTimeCardsResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFullTimeCardsResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFullTimeCardsResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )
            // First half yellow card result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfCardsResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFirstHalfCardsResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFirstHalfCardsResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFirstHalfCardsResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )
            // Second half yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfCardsResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getSecondHalfCardsResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getSecondHalfCardsResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getSecondHalfCardsResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
            // Full time Offsides Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeOffsideResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFullTimeOffsideResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFullTimeOffsideResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFullTimeOffsideResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )
            // First half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfOffsideResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getFirstHalfOffsideResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFirstHalfOffsideResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFirstHalfOffsideResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )
            // Second half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfOffsideResult(
                        getResultValue(result),
                        teamId
                    )!!,
                    sampleSpace = eventStatsDao.getSecondHalfOffsideResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getSecondHalfOffsideResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getSecondHalfOffsideResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
        }

        // Full time Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                    teamId,
                    min.toDouble(),
                    1.0
                )!!,
                sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                marketCategory = Full_Time_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First Half Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFirstHalfMultiGoals(
                    teamId,
                    min.toDouble(),
                    1.0
                )!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                marketCategory = First_Half_MultiGoals,
                marketType = Multigoals,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second Half Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getSecondHalfMultiGoals(
                    teamId,
                    min.toDouble(),
                    1.0
                )!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                marketCategory = Second_Half_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Full time Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(
                    teamId,
                    Home,
                    min.toDouble(),
                    1.0
                )!!
                    .plus(
                        eventStatsDao.getFullTimeMainTeamAwayMultiGoals(
                            teamId,
                            Away,
                            min.toDouble(),
                            1.0
                        )!!
                    ),
                sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                marketCategory = Full_Time_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Full_Time,
                team = Main_Team
            )
        )
        // First half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(
                    teamId,
                    Home,
                    min.toDouble(),
                    1.0
                )!!
                    .plus(
                        eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(
                            teamId,
                            Away,
                            min.toDouble(),
                            1.0
                        )!!
                    ),
                sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                marketCategory = First_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = First_Half,
                team = Main_Team
            )
        )
        // Second half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(
                    teamId,
                    Home,
                    min.toDouble(),
                    1.0
                )!!
                    .plus(
                        eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(
                            teamId,
                            Away,
                            min.toDouble(),
                            1.0
                        )!!
                    ),
                sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                marketCategory = Second_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Second_Half,
                team = Main_Team
            )
        )

        // Full time Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = Full_Time_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = eventStatsDao.getFullTimeMainTeamHomeCleanSheets(teamId, Home,
                    0.0)!!.plus(eventStatsDao.getFullTimeMainTeamAwayCleanSheets(teamId, Away, 0.0)!!),
                sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                marketCategory = Full_Time_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Full_Time,
                team = Main_Team
            )
        )
        // First half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = First_Half_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = eventStatsDao.getFirstHalfMainTeamHomeCleanSheets(
                    teamId,
                    Home,
                    0.0
                )!!
                    .plus(
                        eventStatsDao.getFirstHalfMainTeamAwayCleanSheets(
                            teamId,
                            Away,
                            0.0
                        )!!
                    ),
                sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                marketCategory = First_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = First_Half,
                team = Main_Team
            )
        )
        // Second half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = Second_Half_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = eventStatsDao.getSecondHalfMainTeamHomeCleanSheets(teamId, Home, 0.0)!!
                    .plus(eventStatsDao.getSecondHalfMainTeamAwayCleanSheets(teamId, Away, 0.0)!!),
                sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                marketCategory = Second_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Second_Half,
                team = Main_Team
            )
        )


        // Full time Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = eventStatsDao.getFullTimeBothTeamsToScore(teamId, 0.0)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalBothTeamsToScore(teamId)!!,
                marketCategory = Full_Time_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Both_Teams_To_Score ($No)",
                value = No,
                outcome = (eventStatsDao.getFullTimeTotalBothTeamsToScore(teamId)!! - eventStatsDao.getFullTimeBothTeamsToScore(
                    teamId,
                    0.0
                )!!),
                sampleSpace = eventStatsDao.getFullTimeTotalBothTeamsToScore(teamId)!!,
                marketCategory = Full_Time_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = eventStatsDao.getFirstHalfBothTeamsToScore(teamId, 0.0)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalBothTeamsToScore(teamId)!!,
                marketCategory = First_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Both_Teams_To_Score ($No)",
                value = No,
                outcome = (eventStatsDao.getFirstHalfTotalBothTeamsToScore(teamId)!! - eventStatsDao.getFirstHalfBothTeamsToScore(
                    teamId,
                    0.0
                )!!),
                sampleSpace = eventStatsDao.getFirstHalfTotalBothTeamsToScore(teamId)!!,
                marketCategory = First_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = eventStatsDao.getSecondHalfBothTeamsToScore(teamId, 0.0)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalBothTeamsToScore(teamId)!!,
                marketCategory = Second_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Both_Teams_To_Score ($No)",
                value = No,
                outcome = (eventStatsDao.getSecondHalfTotalBothTeamsToScore(teamId)!! - eventStatsDao.getSecondHalfBothTeamsToScore(
                    teamId,
                    0.0
                )!!),
                sampleSpace = eventStatsDao.getSecondHalfTotalBothTeamsToScore(teamId)!!,
                marketCategory = Second_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )

        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, WinValue, LoseValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, WinValue, LoseValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getSecondHalfDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalDoubleChance(teamId)!!,
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = eventStatsDao.getSecondHalfDoubleChance(teamId, WinValue, LoseValue)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalDoubleChance(teamId)!!,
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getSecondHalfDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalDoubleChance(teamId)!!,
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )

        for (value in 0 until 20) {

            // Full time Goals and Cards Totals
            if (value < 6) {
                // Goals
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeGoalsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeGoalsOver(min, teamId)!!,
                        marketCategory = Full_Time_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeGoalsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeGoalsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Full_Time_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Cards
                // First Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfCardsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfCardsOver(min, teamId)!!,
                        marketCategory = First_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfCardsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFirstHalfCardsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = First_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                // Second Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfCardsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfCardsOver(min, teamId)!!,
                        marketCategory = Second_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfCardsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getSecondHalfCardsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Second_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )


                // Offsides
                // First Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfOffsideOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfOffsideOver(min, teamId)!!,
                        marketCategory = First_Half_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfOffsideUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFirstHalfOffsideUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = First_Half_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                // Second Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfOffsideOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfOffsideOver(min, teamId)!!,
                        marketCategory = Second_Half_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfOffsideUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getSecondHalfOffsideUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Second_Half_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
            }

            if (value < 4) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfGoalsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfGoalsOver(min, teamId)!!,
                        marketCategory = First_Half_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfGoalsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFirstHalfGoalsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = First_Half_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfGoalsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfGoalsOver(min, teamId)!!,
                        marketCategory = Second_Half_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfGoalsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getSecondHalfGoalsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Second_Half_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
            }

            // Handicap
            if (value in 1..4) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getFullTimeHomeHandicapResult(
                            teamId,
                            Home,
                            value.toDouble()
                        )!!.plus(
                            eventStatsDao.getFullTimeAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble()
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getFullTimeTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getFullTimeHomeHandicapResult(
                            teamId,
                            Home,
                            (value.toDouble().times(-1.0))
                        )!!.plus(
                            eventStatsDao.getFullTimeAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble().times(-1.0)
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getFullTimeTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getFirstHalfHomeHandicapResult(
                            teamId,
                            Home,
                            value.toDouble()
                        )!!.plus(
                            eventStatsDao.getFirstHalfAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble()
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getFirstHalfTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getFirstHalfHomeHandicapResult(
                            teamId,
                            Home,
                            value.toDouble().times(-1.0)
                        )!!.plus(
                            eventStatsDao.getFirstHalfAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble().times(-1.0)
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getFirstHalfTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getSecondHalfHomeHandicapResult(
                            teamId,
                            Home,
                            value.toDouble()
                        )!!.plus(
                            eventStatsDao.getSecondHalfAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble()
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getSecondHalfTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getSecondHalfHomeHandicapResult(
                            teamId,
                            Home,
                            value.toDouble().times(-1.0)
                        )!!.plus(
                            eventStatsDao.getSecondHalfAwayHandicapResult(
                                teamId,
                                Away,
                                value.toDouble().times(-1.0)
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getSecondHalfTotalHandicapResult(
                            teamId,
                            -1.0,
                            100.0
                        )!!,
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

            }

            // Full Time Corners
            if (value in 5..13) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FullTimeCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeCornersOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeCornersOver(min, teamId)!!,
                        marketCategory = Full_Time_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FullTimeCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeCornersUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeCornersUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Full_Time_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            // First & Second Half Corners
            if (value in 4..8) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FirstHalfCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfCornersOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfCornersOver(min, teamId)!!,
                        marketCategory = First_Half_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FirstHalfCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFirstHalfCornersUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFirstHalfCornersUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = First_Half_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$SecondHalfCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfCornersOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfCornersOver(min, teamId)!!,
                        marketCategory = Second_Half_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$SecondHalfCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getSecondHalfCornersUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getSecondHalfCornersUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Second_Half_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

            }

            if (value <= 8) {
                // Cards
                // Full Time
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeCardsOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeCardsOver(min, teamId)!!,
                        marketCategory = Full_Time_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeCardsUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeCardsUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Full_Time_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )


                // Offsides
                // Full Time
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeOffsideOver(value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeOffsideOver(min, teamId)!!,
                        marketCategory = Full_Time_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = eventStatsDao.getFullTimeOffsideUnder(
                            min,
                            value,
                            teamId
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeOffsideUnder(
                            min,
                            max,
                            teamId
                        )!!,
                        marketCategory = Full_Time_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            // Both Halves Totals
            if (value in 0..2) {
                // Both Halves Overs
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $over $value$pointFive ($Yes)",
                        value = Yes,
                        outcome = eventStatsDao.getBothHalvesOvers(
                            teamId,
                            value.toDouble()
                        )!!,
                        sampleSpace = eventStatsDao.getBothHalvesTotals(teamId)!!,
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Overs
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $over $value$pointFive ($No)",
                        value = No,
                        outcome = eventStatsDao.getBothHalvesTotals(teamId)!!.minus(
                            eventStatsDao.getBothHalvesOvers(
                                teamId,
                                value.toDouble()
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getBothHalvesTotals(teamId)!!,
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Unders
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $under $value$pointFive ($Yes)",
                        value = Yes,
                        outcome = eventStatsDao.getBothHalvesUnders(
                            teamId,
                            value.toDouble()
                        )!!,
                        sampleSpace = eventStatsDao.getBothHalvesTotals(teamId)!!,
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Unders
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $under $value$pointFive ($No)",
                        value = No,
                        outcome = eventStatsDao.getBothHalvesTotals(teamId)!!.minus(
                            eventStatsDao.getBothHalvesUnders(
                                teamId,
                                value.toDouble()
                            )!!
                        ),
                        sampleSpace = eventStatsDao.getBothHalvesTotals(teamId)!!,
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            if (value in 2..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            0.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }


            if (value in 3..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            1.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }


            if (value in 4..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (3-$value goals)",
                        value = "3-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            2.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals ($value+ goals)",
                        value = "$value+ goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            value.minus(1.0),
                            max.toDouble()
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )


            }


            if (value in 5..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (4-$value goals)",
                        value = "4-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            3.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }


            if (value in 6..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (5-$value goals)",
                        value = "5-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            4.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            if (value in 2..3) {

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            0.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getFullTimeMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    0.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            0.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    0.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            0.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    0.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

            }


            if (value == 3) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            1.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getFullTimeMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    1.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)}+ goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            3.0,
                            max.toDouble()
                        )!!
                            .plus(
                                eventStatsDao.getFullTimeMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    3.0,
                                    max.toDouble()
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )


                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            1.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    1.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)} goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            3.0,
                            max.toDouble()
                        )!!
                            .plus(
                                eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    3.0,
                                    max.toDouble()
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            1.0,
                            value.toDouble().plus(1.0)
                        )!!
                            .plus(
                                eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    1.0,
                                    value.toDouble().plus(1.0)
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )


                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)} goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(
                            teamId,
                            Home,
                            3.0,
                            max.toDouble()
                        )!!
                            .plus(
                                eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(
                                    teamId,
                                    Away,
                                    3.0,
                                    max.toDouble()
                                )!!
                            ),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )


            }


        }

        val teamSuggestion = TeamSuggestionsEntity(null, teamName, date, teamId, suggestions)

        teamSuggestionsDao.deleteTeamSuggestions(teamId)
        teamSuggestionsDao.insertTeamSuggestions(teamSuggestion)

        //}

        val newTeamSuggestionsEntity =
            teamSuggestionsDao.getThisTeamSuggestions(teamId) ?: TeamSuggestionsEntity(
                null,
                emptyString,
                newDate,
                0,
                emptyList()
            )

        emit(Resource.Success(newTeamSuggestionsEntity))

    }

    override fun getSuggestions(
        teamId: Int,
        teamName: String,
        date: Date,
        // teamStats: ListOfEventStats
    ): Flow<Resource<TeamSuggestionsEntity>> = flow {
        emit(Resource.Loading())

        val teamPrediction = teamSuggestionsDao.getThisTeamSuggestions(teamId)
        emit(Resource.Loading(data = teamPrediction))

        val teamStats = eventStatsDao.getAllOfThisTeamStats(teamId) ?: emptyList()

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()


        //for (numbers in resultsList){


        val suggestions = mutableListOf<Suggestion>()

        resultsList.forEach { result ->
            // Full Time Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.fullTimeMatchResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Full_Time_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )



            Log.d("TeamSuggestionsRepository", "result List Result: $result")
            Log.d(
                "TeamSuggestionsRepository",
                "result List Result Value: ${getResultValue(result)}"
            )
            Log.d(
                "TeamSuggestionsRepository",
                "result List Outcome: ${
                    teamStats.count {
                        it.fullTimeMatchResult == getResultValue(result)
                    }.toDouble()
                }"
            )
            Log.d(
                "TeamSuggestionsRepository",
                "result List Sample Space: ${
                    teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                        .toDouble()
                }"
            )


            // First Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.firstHalfMatchResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                        .toDouble(),
                    marketCategory = First_Half_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )

            // Second Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.secondHalfMatchResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Second_Half_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )

            // Full time corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.fullTimeCornerResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.fullTimeCornerResult == WinValue) || (it.fullTimeCornerResult == LoseValue) || (it.fullTimeCornerResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Full_Time_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )

            // First half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.firstHalfCornerResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.firstHalfCornerResult == WinValue) || (it.firstHalfCornerResult == LoseValue) || (it.firstHalfCornerResult == DrawValue) }
                        .toDouble(),
                    marketCategory = First_Half_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )
            // Second half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.secondHalfCornerResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.secondHalfCornerResult == WinValue) || (it.secondHalfCornerResult == LoseValue) || (it.secondHalfCornerResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Second_Half_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
            // Full time yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.fullTimeCardResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.fullTimeCardResult == WinValue) || (it.fullTimeCardResult == LoseValue) || (it.fullTimeCardResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Full_Time_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )
            // First half yellow card result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.firstHalfCardResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.firstHalfCardResult == WinValue) || (it.firstHalfCardResult == LoseValue) || (it.firstHalfCardResult == DrawValue) }
                        .toDouble(),
                    marketCategory = First_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )
            // Second half yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.secondHalfCardResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.secondHalfCardResult == WinValue) || (it.secondHalfCardResult == LoseValue) || (it.secondHalfCardResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Second_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
            // Full time Offsides Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.fullTimeOffsideResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.fullTimeOffsideResult == WinValue) || (it.fullTimeOffsideResult == LoseValue) || (it.fullTimeOffsideResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Full_Time_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Full_Time,
                    team = BothTeams
                )
            )
            // First half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.firstHalfOffsideResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.firstHalfOffsideResult == WinValue) || (it.firstHalfOffsideResult == LoseValue) || (it.firstHalfOffsideResult == DrawValue) }
                        .toDouble(),
                    marketCategory = First_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = First_Half,
                    team = BothTeams
                )
            )

            // Second half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = teamStats.count { it.secondHalfOffsideResult == getResultValue(result) }
                        .toDouble(),
                    sampleSpace = teamStats.count { (it.secondHalfOffsideResult == WinValue) || (it.secondHalfOffsideResult == LoseValue) || (it.secondHalfOffsideResult == DrawValue) }
                        .toDouble(),
                    marketCategory = Second_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Second_Half,
                    team = BothTeams
                )
            )
        }

        // Full time Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { it.fullTimeGoalsTotals == 0.0 }.toDouble(),
                sampleSpace = teamStats.count { (it.fullTimeGoalsTotals ?: -10.0) >= 0.0 }
                    .toDouble(),
                marketCategory = Full_Time_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First Half Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { it.firstHalfGoalsTotals == 0.0 }.toDouble(),
                sampleSpace = teamStats.count { (it.firstHalfGoalsTotals ?: -10.0) >= 0.0 }
                    .toDouble(),
                marketCategory = First_Half_MultiGoals,
                marketType = Multigoals,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second Half Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { it.secondHalfGoalsTotals == 0.0 }.toDouble(),
                sampleSpace = teamStats.count { (it.secondHalfGoalsTotals ?: -10.0) >= 0.0 }
                    .toDouble(),
                marketCategory = Second_Half_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Full time Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.fullTimeHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.fullTimeAwayTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Full_Time_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Full_Time,
                team = Main_Team
            )
        )
        // First half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.firstHalfHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.firstHalfAwayTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = First_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = First_Half,
                team = Main_Team
            )
        )
        // Second half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Main_Team_MultiGoals ($No_Goal)",
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.secondHalfHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.secondHalfAwayTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Second_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Second_Half,
                team = Main_Team
            )
        )

        // Full time Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = Full_Time_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.fullTimeAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.fullTimeHomeTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Full_Time_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Full_Time,
                team = Main_Team
            )
        )
        // First half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = First_Half_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.firstHalfAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.firstHalfHomeTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = First_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = First_Half,
                team = Main_Team
            )
        )
        // Second half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = Second_Half_Main_Team_Clean_Sheets,
                value = No_Goal,
                outcome = teamStats.count { ((it.location == Home) && (it.secondHalfAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.secondHalfHomeTeamGoalsTotals == 0.0)) }
                    .toDouble(),
                sampleSpace = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Second_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Second_Half,
                team = Main_Team
            )
        )


        // Full time Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) > 0.0) && ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) > 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Full_Time_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Both_Teams_To_Score ($No)",
                value = No,
                outcome = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) == 0.0) || ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) == 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.fullTimeHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Full_Time_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) > 0.0) && ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) > 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = First_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Both_Teams_To_Score ($No)",
                value = No,
                outcome = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) == 0.0) || ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) == 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.firstHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = First_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Both_Teams_To_Score ($Yes)",
                value = Yes,
                outcome = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) > 0.0) && ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) > 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Second_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Both Teams to Score
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Both_Teams_To_Score ($No)",
                value = No,
                outcome = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) == 0.0) || ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) == 0.0)
                }.toDouble(),
                sampleSpace = teamStats.count {
                    ((it.secondHalfHomeTeamGoalsTotals
                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals ?: -10.0) >= 0.0)
                }.toDouble(),
                marketCategory = Second_Half_Both_Teams_To_Score,
                marketType = Both_Teams_To_Score,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )

        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = teamStats.count { (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = teamStats.count { (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                    .toDouble(),
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { it.secondHalfMatchResult == WinValue }
                    .plus(teamStats.count { it.secondHalfMatchResult == LoseValue })
                    .plus(teamStats.count { it.secondHalfMatchResult == DrawValue }).toDouble(),
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half_Lose_Or_Draw ($DrawOrLose)",
                value = DrawOrLose,
                outcome = teamStats.count { (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                    .toDouble(),
                sampleSpace = teamStats.count { it.secondHalfMatchResult == WinValue }
                    .plus(teamStats.count { it.secondHalfMatchResult == LoseValue })
                    .plus(teamStats.count { it.secondHalfMatchResult == DrawValue }).toDouble(),
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = BothTeams
            )
        )

        for (value in 0 until 20) {

            // Full time Goals and Cards Totals
            if (value < 6) {
                // Goals
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.fullTimeGoalsTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0) && ((it.fullTimeGoalsTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Cards
                // First Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.firstHalfCardTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.firstHalfCardTotals ?: -10.0) >= 0.0) && ((it.firstHalfCardTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                // Second Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.secondHalfCardTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.secondHalfCardTotals ?: -10.0) >= 0.0) && ((it.secondHalfCardTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )


                // Offsides
                // First Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.firstHalfOffsideTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            (it.firstHalfOffsideTotals ?: -10.0) >= 0.0
                        }.toDouble(),
                        marketCategory = First_Half_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.firstHalfOffsideTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfOffsideTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            (it.firstHalfOffsideTotals ?: -10.0) >= 0.0
                        }.toDouble(),
                        marketCategory = First_Half_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                // Second Half
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.secondHalfOffsideTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            (it.secondHalfOffsideTotals ?: -10.0) >= 0.0
                        }.toDouble(),
                        marketCategory = Second_Half_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.secondHalfOffsideTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfOffsideTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            (it.secondHalfOffsideTotals ?: -10.0) >= 0.0
                        }.toDouble(),
                        marketCategory = Second_Half_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
            }

            if (value < 4) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.firstHalfGoalsTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.firstHalfGoalsTotals ?: -10.0) >= 0.0) && ((it.firstHalfGoalsTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Goals $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.secondHalfGoalsTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_Goals_Totals,
                        marketType = Goals_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Goals $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.secondHalfGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_Goals_Totals,
                        marketType = Goals_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
            }

            // Handicap
            if (value in 1..4) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0).minus(
                                (it.fullTimeAwayTeamGoalsTotals ?: -10.0)
                            ) > value.toDouble())) ||
                                    ((it.location == Away) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.fullTimeHomeTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble()))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0).plus(value.toDouble()) > (it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0))) ||
                                    ((it.location == Away) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0)))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0).minus(
                                (it.firstHalfAwayTeamGoalsTotals ?: -10.0)
                            ) > value.toDouble())) ||
                                    ((it.location == Away) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.firstHalfHomeTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble()))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0).plus(value.toDouble()) > (it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0))) ||
                                    ((it.location == Away) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0)))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Handicap (0 : $value)",
                        value = "-$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0).minus(
                                (it.secondHalfAwayTeamGoalsTotals ?: -10.0)
                            ) > value.toDouble())) ||
                                    ((it.location == Away) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.secondHalfHomeTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble()))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Handicap ($value : 0)",
                        value = "+$value",
                        outcome = teamStats.count {
                            ((it.location == Home) && ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0).plus(value.toDouble()) > (it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0))) ||
                                    ((it.location == Away) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0)))
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

            }

            // Full Time Corners
            if (value in 5..13) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FullTimeCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.fullTimeCornerTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeCornerTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FullTimeCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.fullTimeCornerTotals ?: -10.0) >= 0.0) && ((it.fullTimeCornerTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeCornerTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            // First & Second Half Corners
            if (value in 4..8) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FirstHalfCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.firstHalfCornerTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfCornerTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$FirstHalfCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.firstHalfCornerTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfCornerTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfCornerTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$SecondHalfCorners $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.firstHalfCornerTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfCornerTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_Corner_Totals,
                        marketType = Corners_Overs,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$SecondHalfCorners $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.secondHalfCornerTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfCornerTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            (it.secondHalfCornerTotals ?: -10.0) >= 0.0
                        }.toDouble(),
                        marketCategory = Second_Half_Corner_Totals,
                        marketType = Corners_Unders,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

            }

            if (value <= 8) {
                // Cards
                // Full Time
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Yellow_Cards $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.fullTimeCardTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Yellow_Cards $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.fullTimeCardTotals ?: -10.0) >= 0.0) && ((it.fullTimeCardTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeCardTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Yellow_Cards_Totals,
                        marketType = Yellow_Cards_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )


                // Offsides
                // Full Time
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Offsides $over $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            (it.fullTimeOffsideTotals ?: -10.0) > value.toDouble()
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeOffsideTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Offsides_Totals,
                        marketType = Offsides_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Offsides $under $value$pointFive",
                        value = "$value$pointFive",
                        outcome = teamStats.count {
                            ((it.fullTimeOffsideTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeOffsideTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeOffsideTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_Offsides_Totals,
                        marketType = Offsides_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            // Both Halves Totals
            if (value in 0..2) {
                // Both Halves Overs
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $over $value$pointFive ($Yes)",
                        value = Yes,
                        outcome = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) > value.toDouble()) && ((it.secondHalfGoalsTotals
                                ?: -10.0) > value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Overs
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $over $value$pointFive ($No)",
                        value = No,
                        outcome = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) <= value.toDouble()) || ((it.secondHalfGoalsTotals
                                ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Overs,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Unders
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $under $value$pointFive ($Yes)",
                        value = Yes,
                        outcome = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) < value.toDouble()) && ((it.secondHalfGoalsTotals
                                ?: -10.0) < value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

                // Both Halves Unders
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Both_Halves $under $value$pointFive ($No)",
                        value = No,
                        outcome = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) >= value.toDouble()) || ((it.secondHalfGoalsTotals
                                ?: -10.0) >= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Both_Halves_Totals,
                        marketType = Both_Halves_Unders,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            if (value in 2..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = teamStats.count {
                            ((it.fullTimeGoalsTotals ?: -10.0) >= 1.0) && ((it.fullTimeGoalsTotals
                                ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0) }
                            .toDouble(),
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }


            if (value in 3..7) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = teamStats.count {
                            ((it.fullTimeGoalsTotals ?: -10.0) >= 2.0) && ((it.fullTimeGoalsTotals
                                ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0) }
                            .toDouble(),
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }


            if (value in 4..7) {

                /*
                suggestions.add(
                    Suggestion(
                            teamName = teamName,
                            teamId = teamId,
                                market = "$Full_Time_MultiGoals (3-$value goals)",
                        value = "3-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            2.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                */

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals ($value+ goals)",
                        value = "$value+ goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.fullTimeGoalsTotals
                                ?: -10.0) >= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count { ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0) }
                            .toDouble(),
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )

            }


            /*
            if (value in 5..7) {
                suggestions.add(
                    Suggestion(
                            teamName = teamName,
                            teamId = teamId,
                                market = "$Full_Time_MultiGoals (4-$value goals)",
                        value = "4-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            3.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }

            if (value in 6..7) {
                suggestions.add(
                    Suggestion(
                            teamName = teamName,
                            teamId = teamId,
                                market = "$Full_Time_MultiGoals (5-$value goals)",
                        value = "5-$value goals",
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(
                            teamId,
                            4.0,
                            value.toDouble().plus(1.0)
                        )!!,
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
            }
            */


            if (value in 2..3) {

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 1.0 && (it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 1.0 && (it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 1.0 && (it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

            }


            if (value == 3) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 2.0 && (it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "${value.plus(1)}+ goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 4.0) ||
                                    (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 4.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )


                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 2.0 && (it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "${value.plus(1)} goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 4.0) ||
                                    (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 4.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 2.0 && (it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) <= value.toDouble()) ||
                                    (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )


                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)} goals",
                        outcome = teamStats.count {
                            (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 4.0) ||
                                    (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 4.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )


            }


        }

        val teamSuggestion = TeamSuggestionsEntity(null, teamName, date, teamId, suggestions)

        teamSuggestionsDao.deleteTeamSuggestions(teamId)
        teamSuggestionsDao.insertTeamSuggestions(teamSuggestion)

        //}

        val newTeamSuggestionsEntity =
            teamSuggestionsDao.getThisTeamSuggestions(teamId) ?: TeamSuggestionsEntity(
                null,
                emptyString,
                newDate,
                0,
                emptyList()
            )

        emit(Resource.Success(newTeamSuggestionsEntity))

    }

    override suspend fun getBetBuilderSuggestions(
        listOfEvents: ListOfEvents,
        date: Date
    ): Flow<Resource<List<TeamSuggestionsEntity>>> = flow {

        emit(Resource.Loading())

        Log.d("TeamSuggestionsRepository", "getBuilderSuggestions function is called")

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()

        val listOfTeamSuggestions = mutableListOf<TeamSuggestionsEntity>()
        listOfEvents.forEach { eventsEntity ->
            val homeTeamName = eventsEntity.homeTeamName ?: emptyString
            val awayTeamName = eventsEntity.awayTeamName ?: emptyString
            val homeTeamId = eventsEntity.homeTeamId ?: nullInteger
            val awayTeamId = eventsEntity.awayTeamId ?: nullInteger

            Log.d(
                "TeamSuggestionsRepository",
                "getBuilderSuggestions: List Of Events function is called"
            )


            val homeTeamPrediction = teamSuggestionsDao.getThisTeamSuggestions(homeTeamId, date)
            val awayTeamPrediction = teamSuggestionsDao.getThisTeamSuggestions(awayTeamId, date)

            Log.d(
                "TeamSuggestionsRepository",
                "homeTeamPrediction: ${homeTeamPrediction?.teamName ?: "null"}"
            )
            Log.d(
                "TeamSuggestionsRepository",
                "awayTeamPrediction: ${awayTeamPrediction?.teamName ?: "null"}"
            )

            val bothTeams = mutableMapOf<Int, String>()

            if (homeTeamPrediction == null) {
                bothTeams[homeTeamId] = homeTeamName
            } else {
                listOfTeamSuggestions.add(homeTeamPrediction)
            }

            if (awayTeamPrediction == null) {
                bothTeams[awayTeamId] = awayTeamName
            } else {
                listOfTeamSuggestions.add(awayTeamPrediction)
            }

            Log.d(
                "TeamSuggestionsRepository",
                "both Teams Keys: ${bothTeams.keys}      both Teams Keys: ${bothTeams.values}"
            )
            Log.d("TeamSuggestionsRepository", "List of team suggestion: $listOfTeamSuggestions")


            bothTeams.keys.forEach { teamId ->
                val teamStats = eventStatsDao.getAllOfThisTeamStats(teamId) ?: emptyList()
                val suggestions = mutableListOf<Suggestion>()

                teamStats.forEachIndexed { index, teamStat ->
                    Log.d(
                        "TeamSuggestionsRepository",
                        "${teamStat.mainTeamName} Stat Event ${index.plus(1)} : ${teamStat.homeTeamName} vs ${teamStat.awayTeamName} }"
                    )
                }

                resultsList.forEach { result ->
                    // Full Time Match Results
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Full_Time ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.fullTimeMatchResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Full_Time_Match_Result,
                            marketType = Match_Result,
                            matchPeriod = Full_Time,
                            team = BothTeams
                        )
                    )



                    Log.d("TeamSuggestionsRepository", "result List Result: $result")
                    Log.d(
                        "TeamSuggestionsRepository",
                        "result List Result Value: ${getResultValue(result)}"
                    )
                    Log.d(
                        "TeamSuggestionsRepository",
                        "result List Outcome: ${
                            teamStats.count {
                                it.fullTimeMatchResult == getResultValue(result)
                            }.toDouble()
                        }"
                    )
                    Log.d(
                        "TeamSuggestionsRepository",
                        "result List Sample Space: ${
                            teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                                .toDouble()
                        }"
                    )


                    // First Half Match Results
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$First_Half ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.firstHalfMatchResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                                .toDouble(),
                            marketCategory = First_Half_Match_Result,
                            marketType = Match_Result,
                            matchPeriod = First_Half,
                            team = BothTeams
                        )
                    )

                    // Second Half Match Results
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Second_Half ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.secondHalfMatchResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Second_Half_Match_Result,
                            marketType = Match_Result,
                            matchPeriod = Second_Half,
                            team = BothTeams
                        )
                    )

                    // Full time corner kick result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Full_Time $Corner_Kicks ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.fullTimeCornerResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.fullTimeCornerResult == WinValue) || (it.fullTimeCornerResult == LoseValue) || (it.fullTimeCornerResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Full_Time_Corner_Result,
                            marketType = Corner_Result,
                            matchPeriod = Full_Time,
                            team = BothTeams
                        )
                    )

                    // First half corner kick result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$First_Half $Corner_Kicks ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.firstHalfCornerResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.firstHalfCornerResult == WinValue) || (it.firstHalfCornerResult == LoseValue) || (it.firstHalfCornerResult == DrawValue) }
                                .toDouble(),
                            marketCategory = First_Half_Corner_Result,
                            marketType = Corner_Result,
                            matchPeriod = First_Half,
                            team = BothTeams
                        )
                    )
                    // Second half corner kick result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Second_Half $Corner_Kicks ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.secondHalfCornerResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.secondHalfCornerResult == WinValue) || (it.secondHalfCornerResult == LoseValue) || (it.secondHalfCornerResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Second_Half_Corner_Result,
                            marketType = Corner_Result,
                            matchPeriod = Second_Half,
                            team = BothTeams
                        )
                    )
                    // Full time yellow cards result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Full_Time $Yellow_Cards ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.fullTimeCardResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.fullTimeCardResult == WinValue) || (it.fullTimeCardResult == LoseValue) || (it.fullTimeCardResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Full_Time_Yellow_Cards_Result,
                            marketType = Yellow_Cards_Result,
                            matchPeriod = Full_Time,
                            team = BothTeams
                        )
                    )
                    // First half yellow card result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$First_Half $Yellow_Cards ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.firstHalfCardResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.firstHalfCardResult == WinValue) || (it.firstHalfCardResult == LoseValue) || (it.firstHalfCardResult == DrawValue) }
                                .toDouble(),
                            marketCategory = First_Half_Yellow_Cards_Result,
                            marketType = Yellow_Cards_Result,
                            matchPeriod = First_Half,
                            team = BothTeams
                        )
                    )
                    // Second half yellow cards result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Second_Half $Yellow_Cards ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.secondHalfCardResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.secondHalfCardResult == WinValue) || (it.secondHalfCardResult == LoseValue) || (it.secondHalfCardResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Second_Half_Yellow_Cards_Result,
                            marketType = Yellow_Cards_Result,
                            matchPeriod = Second_Half,
                            team = BothTeams
                        )
                    )
                    // Full time Offsides Results
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Full_Time $Offsides ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.fullTimeOffsideResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.fullTimeOffsideResult == WinValue) || (it.fullTimeOffsideResult == LoseValue) || (it.fullTimeOffsideResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Full_Time_Offsides_Result,
                            marketType = Offsides_Result,
                            matchPeriod = Full_Time,
                            team = BothTeams
                        )
                    )
                    // First half offsides result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$First_Half $Offsides ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.firstHalfOffsideResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.firstHalfOffsideResult == WinValue) || (it.firstHalfOffsideResult == LoseValue) || (it.firstHalfOffsideResult == DrawValue) }
                                .toDouble(),
                            marketCategory = First_Half_Offsides_Result,
                            marketType = Offsides_Result,
                            matchPeriod = First_Half,
                            team = BothTeams
                        )
                    )

                    // Second half offsides result
                    suggestions.add(
                        Suggestion(
                            teamName = bothTeams[teamId],
                            teamId = teamId,
                            market = "$Second_Half $Offsides ${result}s",
                            value = getResultValue(result).toString(),
                            outcome = teamStats.count {
                                it.secondHalfOffsideResult == getResultValue(
                                    result
                                )
                            }.toDouble(),
                            sampleSpace = teamStats.count { (it.secondHalfOffsideResult == WinValue) || (it.secondHalfOffsideResult == LoseValue) || (it.secondHalfOffsideResult == DrawValue) }
                                .toDouble(),
                            marketCategory = Second_Half_Offsides_Result,
                            marketType = Offsides_Result,
                            matchPeriod = Second_Half,
                            team = BothTeams
                        )
                    )
                }

                // Full time Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { it.fullTimeGoalsTotals == 0.0 }.toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Full_Time_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // First Half Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { it.firstHalfGoalsTotals == 0.0 }.toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = First_Half_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // Second Half Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { it.secondHalfGoalsTotals == 0.0 }.toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfGoalsTotals ?: -10.0) >= 0.0 }
                            .toDouble(),
                        marketCategory = Second_Half_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
                // Full time Main Team Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Main_Team_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.fullTimeHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.fullTimeAwayTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )
                // First half Main Team Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Main_Team_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.firstHalfHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.firstHalfAwayTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )
                // Second half Main Team Multi goals
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Main_Team_MultiGoals ($No_Goal)",
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.secondHalfHomeTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.secondHalfAwayTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )

                // Full time Main Team Clean Sheets
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = Full_Time_Main_Team_Clean_Sheets,
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.fullTimeAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.fullTimeHomeTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Main_Team_Clean_Sheets,
                        marketType = Clean_Sheets,
                        matchPeriod = Full_Time,
                        team = Main_Team
                    )
                )
                // First half Main Team Clean Sheets
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = First_Half_Main_Team_Clean_Sheets,
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.firstHalfAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.firstHalfHomeTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Main_Team_Clean_Sheets,
                        marketType = Clean_Sheets,
                        matchPeriod = First_Half,
                        team = Main_Team
                    )
                )
                // Second half Main Team Clean Sheets
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = Second_Half_Main_Team_Clean_Sheets,
                        value = No_Goal,
                        outcome = teamStats.count { ((it.location == Home) && (it.secondHalfAwayTeamGoalsTotals == 0.0)) || ((it.location == Away) && (it.secondHalfHomeTeamGoalsTotals == 0.0)) }
                            .toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Main_Team_Clean_Sheets,
                        marketType = Clean_Sheets,
                        matchPeriod = Second_Half,
                        team = Main_Team
                    )
                )


                // Full time Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Both_Teams_To_Score ($Yes)",
                        value = Yes,
                        outcome = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) > 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) > 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // Full time Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Both_Teams_To_Score ($No)",
                        value = No,
                        outcome = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) == 0.0) || ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) == 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.fullTimeHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Full_Time_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // First half Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Both_Teams_To_Score ($Yes)",
                        value = Yes,
                        outcome = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) > 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) > 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // First half Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Both_Teams_To_Score ($No)",
                        value = No,
                        outcome = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) == 0.0) || ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) == 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.firstHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = First_Half_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // Second half Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Both_Teams_To_Score ($Yes)",
                        value = Yes,
                        outcome = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) > 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) > 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
                // Second half Both Teams to Score
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Both_Teams_To_Score ($No)",
                        value = No,
                        outcome = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) == 0.0) || ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) == 0.0)
                        }.toDouble(),
                        sampleSpace = teamStats.count {
                            ((it.secondHalfHomeTeamGoalsTotals
                                ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                ?: -10.0) >= 0.0)
                        }.toDouble(),
                        marketCategory = Second_Half_Both_Teams_To_Score,
                        marketType = Both_Teams_To_Score,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                // Full time Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Win_Or_Draw ($WinOrDraw)",
                        value = WinOrDraw,
                        outcome = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = Full_Time_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // Full time Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Win_Or_Lose ($WinOrLose)",
                        value = WinOrLose,
                        outcome = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = Full_Time_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // Full time Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Full_Time_Lose_Or_Draw ($DrawOrLose)",
                        value = DrawOrLose,
                        outcome = teamStats.count { (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.fullTimeMatchResult == WinValue) || (it.fullTimeMatchResult == LoseValue) || (it.fullTimeMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = Full_Time_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Full_Time,
                        team = BothTeams
                    )
                )
                // First half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Win_Or_Draw ($WinOrDraw)",
                        value = WinOrDraw,
                        outcome = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = First_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // First half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Win_Or_Lose ($WinOrLose)",
                        value = WinOrLose,
                        outcome = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = First_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // First half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$First_Half_Lose_Or_Draw ($DrawOrLose)",
                        value = DrawOrLose,
                        outcome = teamStats.count { (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.firstHalfMatchResult == WinValue) || (it.firstHalfMatchResult == LoseValue) || (it.firstHalfMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = First_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = First_Half,
                        team = BothTeams
                    )
                )
                // Second half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Win_Or_Draw ($WinOrDraw)",
                        value = WinOrDraw,
                        outcome = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                            .toDouble(),
                        marketCategory = Second_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
                // Second half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Win_Or_Lose ($WinOrLose)",
                        value = WinOrLose,
                        outcome = teamStats.count { (it.secondHalfMatchResult == WinValue) || (it.secondHalfMatchResult == LoseValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { it.secondHalfMatchResult == WinValue }
                            .plus(teamStats.count { it.secondHalfMatchResult == LoseValue })
                            .plus(teamStats.count { it.secondHalfMatchResult == DrawValue })
                            .toDouble(),
                        marketCategory = Second_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )
                // Second half Double Chance
                suggestions.add(
                    Suggestion(
                        teamName = bothTeams[teamId],
                        teamId = teamId,
                        market = "$Second_Half_Lose_Or_Draw ($DrawOrLose)",
                        value = DrawOrLose,
                        outcome = teamStats.count { (it.secondHalfMatchResult == LoseValue) || (it.secondHalfMatchResult == DrawValue) }
                            .toDouble(),
                        sampleSpace = teamStats.count { it.secondHalfMatchResult == WinValue }
                            .plus(teamStats.count { it.secondHalfMatchResult == LoseValue })
                            .plus(teamStats.count { it.secondHalfMatchResult == DrawValue })
                            .toDouble(),
                        marketCategory = Second_Half_Double_Chance,
                        marketType = Double_Chance,
                        matchPeriod = Second_Half,
                        team = BothTeams
                    )
                )

                for (value in 0 until 20) {

                    // Full time Goals and Cards Totals
                    if (value < 6) {
                        // Goals
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Goals $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.fullTimeGoalsTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Goals_Totals,
                                marketType = Goals_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Goals $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.fullTimeGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeGoalsTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Goals_Totals,
                                marketType = Goals_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        // Cards
                        // First Half
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Yellow_Cards $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.firstHalfCardTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Overs,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Yellow_Cards $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.firstHalfCardTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfCardTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Unders,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        // Second Half
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Yellow_Cards $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.secondHalfCardTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Overs,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Yellow_Cards $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.secondHalfCardTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfCardTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Unders,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )


                        // Offsides
                        // First Half
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Offsides $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.firstHalfOffsideTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Offsides_Totals,
                                marketType = Offsides_Overs,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Offsides $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.firstHalfOffsideTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfOffsideTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Offsides_Totals,
                                marketType = Offsides_Unders,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        // Second Half
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Offsides $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.secondHalfOffsideTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Offsides_Totals,
                                marketType = Offsides_Overs,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Offsides $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.secondHalfOffsideTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfOffsideTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Offsides_Totals,
                                marketType = Offsides_Unders,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )
                    }

                    if (value < 4) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Goals $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.firstHalfGoalsTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Goals_Totals,
                                marketType = Goals_Overs,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Goals $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfGoalsTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Goals_Totals,
                                marketType = Goals_Unders,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Goals $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.secondHalfGoalsTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Goals_Totals,
                                marketType = Goals_Overs,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Goals $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfGoalsTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Goals_Totals,
                                marketType = Goals_Unders,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )
                    }

                    // Handicap
                    if (value in 1..4) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Handicap (0 : $value)",
                                value = "-$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.fullTimeAwayTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble())) ||
                                            ((it.location == Away) && ((it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0).minus(
                                                (it.fullTimeHomeTeamGoalsTotals ?: -10.0)
                                            ) > value.toDouble()))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_Handicap,
                                marketType = Handicap,
                                matchPeriod = Full_Time,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Handicap ($value : 0)",
                                value = "+$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0))) ||
                                            ((it.location == Away) && ((it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0).plus(value.toDouble()) > (it.fullTimeHomeTeamGoalsTotals
                                                ?: -10.0)))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_Handicap,
                                marketType = Handicap,
                                matchPeriod = Full_Time,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Handicap (0 : $value)",
                                value = "-$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.firstHalfAwayTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble())) ||
                                            ((it.location == Away) && ((it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0).minus(
                                                (it.firstHalfHomeTeamGoalsTotals ?: -10.0)
                                            ) > value.toDouble()))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = First_Half_Handicap,
                                marketType = Handicap,
                                matchPeriod = First_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Handicap ($value : 0)",
                                value = "+$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0))) ||
                                            ((it.location == Away) && ((it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0).plus(value.toDouble()) > (it.firstHalfHomeTeamGoalsTotals
                                                ?: -10.0)))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = First_Half_Handicap,
                                marketType = Handicap,
                                matchPeriod = First_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Handicap (0 : $value)",
                                value = "-$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0).minus(
                                        (it.secondHalfAwayTeamGoalsTotals ?: -10.0)
                                    ) > value.toDouble())) ||
                                            ((it.location == Away) && ((it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0).minus(
                                                (it.secondHalfHomeTeamGoalsTotals ?: -10.0)
                                            ) > value.toDouble()))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Second_Half_Handicap,
                                marketType = Handicap,
                                matchPeriod = Second_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Handicap ($value : 0)",
                                value = "+$value",
                                outcome = teamStats.count {
                                    ((it.location == Home) && ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0).plus(value.toDouble()) > (it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0))) ||
                                            ((it.location == Away) && ((it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0).plus(value.toDouble()) > (it.secondHalfHomeTeamGoalsTotals
                                                ?: -10.0)))
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Second_Half_Handicap,
                                marketType = Handicap,
                                matchPeriod = Second_Half,
                                team = Main_Team
                            )
                        )

                    }

                    // Full Time Corners
                    if (value in 5..13) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$FullTimeCorners $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.fullTimeCornerTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Corner_Totals,
                                marketType = Corners_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$FullTimeCorners $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.fullTimeCornerTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeCornerTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Corner_Totals,
                                marketType = Corners_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )
                    }

                    // First & Second Half Corners
                    if (value in 4..8) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$FirstHalfCorners $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.firstHalfCornerTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Corner_Totals,
                                marketType = Corners_Overs,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$FirstHalfCorners $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.firstHalfCornerTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfCornerTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = First_Half_Corner_Totals,
                                marketType = Corners_Unders,
                                matchPeriod = First_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$SecondHalfCorners $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.firstHalfCornerTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.firstHalfCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Corner_Totals,
                                marketType = Corners_Overs,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$SecondHalfCorners $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.secondHalfCornerTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfCornerTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.secondHalfCornerTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Second_Half_Corner_Totals,
                                marketType = Corners_Unders,
                                matchPeriod = Second_Half,
                                team = BothTeams
                            )
                        )

                    }

                    if (value <= 8) {
                        // Cards
                        // Full Time
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Yellow_Cards $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.fullTimeCardTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Yellow_Cards $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.fullTimeCardTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeCardTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeCardTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Yellow_Cards_Totals,
                                marketType = Yellow_Cards_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )


                        // Offsides
                        // Full Time
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Offsides $over $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    (it.fullTimeOffsideTotals ?: -10.0) > value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Offsides_Totals,
                                marketType = Offsides_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Offsides $under $value$pointFive",
                                value = "$value$pointFive",
                                outcome = teamStats.count {
                                    ((it.fullTimeOffsideTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeOffsideTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    (it.fullTimeOffsideTotals ?: -10.0) >= 0.0
                                }.toDouble(),
                                marketCategory = Full_Time_Offsides_Totals,
                                marketType = Offsides_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )
                    }

                    // Both Halves Totals
                    if (value in 0..2) {
                        // Both Halves Overs
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Both_Halves $over $value$pointFive ($Yes)",
                                value = Yes,
                                outcome = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) > value.toDouble()) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) > value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Both_Halves_Totals,
                                marketType = Both_Halves_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        // Both Halves Overs
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Both_Halves $over $value$pointFive ($No)",
                                value = No,
                                outcome = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) <= value.toDouble()) || ((it.secondHalfGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Both_Halves_Totals,
                                marketType = Both_Halves_Overs,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        // Both Halves Unders
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Both_Halves $under $value$pointFive ($Yes)",
                                value = Yes,
                                outcome = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) < value.toDouble()) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) < value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Both_Halves_Totals,
                                marketType = Both_Halves_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                        // Both Halves Unders
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Both_Halves $under $value$pointFive ($No)",
                                value = No,
                                outcome = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= value.toDouble()) || ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Both_Halves_Totals,
                                marketType = Both_Halves_Unders,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )
                    }

                    if (value in 2..7) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_MultiGoals (1-$value goals)",
                                value = "1-$value goals",
                                outcome = teamStats.count {
                                    ((it.fullTimeGoalsTotals
                                        ?: -10.0) >= 1.0) && ((it.fullTimeGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )
                    }


                    if (value in 3..7) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_MultiGoals (2-$value goals)",
                                value = "2-$value goals",
                                outcome = teamStats.count {
                                    ((it.fullTimeGoalsTotals
                                        ?: -10.0) >= 2.0) && ((it.fullTimeGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )
                    }


                    if (value in 4..7) {


                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_MultiGoals (3-$value goals)",
                                value = "3-$value goals",
                                outcome = teamStats.count {
                                    ((it.fullTimeGoalsTotals
                                        ?: -10.0) >= 3) && ((it.fullTimeGoalsTotals
                                        ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )


                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_MultiGoals ($value+ goals)",
                                value = "$value+ goals",
                                outcome = teamStats.count {
                                    (it.fullTimeGoalsTotals ?: -10.0) >= value.toDouble()
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeGoalsTotals ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = BothTeams
                            )
                        )

                    }

                    if (value in 2..3) {

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Main_Team_MultiGoals (1-$value goals)",
                                value = "1-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0) >= 1.0 && (it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Main_Team_MultiGoals (1-$value goals)",
                                value = "1-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 1.0 && (it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = First_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = First_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Main_Team_MultiGoals (1-$value goals)",
                                value = "1-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 1.0 && (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 1.0 && (it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Second_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Second_Half,
                                team = Main_Team
                            )
                        )

                    }


                    if (value == 3) {
                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Main_Team_MultiGoals (2-$value goals)",
                                value = "2-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0) >= 2.0 && (it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Full_Time_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                                value = "${value.plus(1)}+ goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 4.0) ||
                                            (it.location == Away && (it.fullTimeAwayTeamGoalsTotals
                                                ?: -10.0) >= 4.0)
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.fullTimeHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.fullTimeAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Full_Time_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Full_Time,
                                team = Main_Team
                            )
                        )


                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Main_Team_MultiGoals (2-$value goals)",
                                value = "2-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 2.0 && (it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = First_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = First_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$First_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                                value = "${value.plus(1)} goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 4.0) ||
                                            (it.location == Away && (it.firstHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 4.0)
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.firstHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.firstHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = First_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = First_Half,
                                team = Main_Team
                            )
                        )

                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Main_Team_MultiGoals (2-$value goals)",
                                value = "2-$value goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 2.0 && (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) <= value.toDouble()) ||
                                            (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 2.0 && (it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0) <= value.toDouble())
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Second_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Second_Half,
                                team = Main_Team
                            )
                        )


                        suggestions.add(
                            Suggestion(
                                teamName = bothTeams[teamId],
                                teamId = teamId,
                                market = "$Second_Half_Main_Team_MultiGoals (${value.plus(1)}+ goals)",
                                value = "1-${value.plus(1)} goals",
                                outcome = teamStats.count {
                                    (it.location == Home && (it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 4.0) ||
                                            (it.location == Away && (it.secondHalfAwayTeamGoalsTotals
                                                ?: -10.0) >= 4.0)
                                }.toDouble(),
                                sampleSpace = teamStats.count {
                                    ((it.secondHalfHomeTeamGoalsTotals
                                        ?: -10.0) >= 0.0) && ((it.secondHalfAwayTeamGoalsTotals
                                        ?: -10.0) >= 0.0)
                                }.toDouble(),
                                marketCategory = Second_Half_Main_Team_MultiGoals,
                                marketType = Multigoals,
                                matchPeriod = Second_Half,
                                team = Main_Team
                            )
                        )


                    }

                }

                val teamName = bothTeams[teamId]
                val teamSuggestion =
                    TeamSuggestionsEntity(null, teamName, newDate, teamId, suggestions)

                teamSuggestionsDao.deleteTeamSuggestions(teamId)
                teamSuggestionsDao.insertTeamSuggestions(teamSuggestion)

                listOfTeamSuggestions.add(teamSuggestion)
            }
        }
        emit(Resource.Success(listOfTeamSuggestions))

    }

    override suspend fun showBetBuilderSuggestions(
        listOfEvents: ListOfEvents,
        date: Date
    ): Flow<Resource<List<TeamSuggestionsEntity>>> = flow {
        emit(Resource.Loading())

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()


        val allSuggestions = mutableListOf<TeamSuggestionsEntity>()
        listOfEvents.forEach { eventsEntity ->
            val homeTeamId = eventsEntity.homeTeamId ?: nullInteger
            val awayTeamId = eventsEntity.awayTeamId ?: nullInteger

            val homeTeamSuggestions =
                teamSuggestionsDao.getThisTeamSuggestions(homeTeamId) ?: TeamSuggestionsEntity(
                    null,
                    null,
                    newDate,
                    null,
                    emptyList()
                )
            val awayTeamSuggestions =
                teamSuggestionsDao.getThisTeamSuggestions(awayTeamId) ?: TeamSuggestionsEntity(
                    null,
                    null,
                    newDate,
                    null,
                    emptyList()
                )
            allSuggestions.add(homeTeamSuggestions)
            allSuggestions.add(awayTeamSuggestions)

        }

        Log.d(
            "ShowSuggestionsRepositoryImpl",
            "allSuggestions size: ${allSuggestions.size} or is empty"
        )
        allSuggestions.forEach { sugg ->
            Log.d(
                "ShowSuggestionsRepositoryImpl",
                "${sugg.teamName}'s suggestion: ${sugg.suggestions.size} or is empty"
            )
        }

        emit(Resource.Success(allSuggestions))

    }


}