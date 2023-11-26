package com.example.sportsprediction.feature_app.data.repository

import android.util.Log
import com.example.sportsprediction.core.util.Constants.Ascending
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.First_Half
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.Market_Category
import com.example.sportsprediction.core.util.Constants.Match_Period
import com.example.sportsprediction.core.util.Constants.Second_Half
import com.example.sportsprediction.core.util.Constants.Teams
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.Functions.getEventOddsMarketName
import com.example.sportsprediction.core.util.Functions.getFormFactor
import com.example.sportsprediction.core.util.Functions.getHeadToHeadFactor
import com.example.sportsprediction.core.util.Functions.getPlayingLocationFactor
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Main_Team
import com.example.sportsprediction.core.util.SuggestionVariables.Opponent
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

    override fun saveTeamSuggestionsEntity(
        teamId: Int,
        teamName: String,
        date: Date,
        suggestions: ListOfSuggestions
    ): Flow<Resource<TeamSuggestionsEntity>> = flow {
        emit(Resource.Loading())

        val teamPrediction = teamSuggestionsDao.getThisTeamSuggestions(teamId)
        emit(Resource.Loading(data = teamPrediction))

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()

        val teamSuggestion = TeamSuggestionsEntity(null, teamName, date, teamId, suggestions)

        teamSuggestionsDao.deleteTeamSuggestions(teamId)
        teamSuggestionsDao.insertTeamSuggestions(teamSuggestion)

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

    override fun filterSuggestionsByPercentage(
        suggestions: ListOfSuggestions,
        percentage: Double
    ): Flow<Resource<ListOfSuggestions>> = flow{
        emit(Resource.Loading(suggestions))
        val filteredSuggestions = suggestions.filter { suggestion->
            ((suggestion.streakProbability ?: 0.0) >= percentage)
        }
        emit(Resource.Success(filteredSuggestions))
    }

    override fun orderSuggestions(
        suggestions: ListOfSuggestions,
        arrangementOrder: String
    ): Flow<Resource<ListOfSuggestions>> =flow{
        emit(Resource.Loading(suggestions))
        val filteredSuggestions = when(arrangementOrder){
            Ascending -> suggestions.sortedBy { suggestion-> suggestion.streakProbability }
            else -> suggestions.sortedByDescending { suggestion-> suggestion.streakProbability }
        }
        emit(Resource.Success(filteredSuggestions))
    }

    override fun groupSuggestionsBy(
        suggestions: ListOfSuggestions,
        groupSuggestionsBy: String
    ): Flow<Resource<Map<String?,ListOfSuggestions>>> = flow{
        emit(Resource.Loading())
        val groupedSuggestions = when(groupSuggestionsBy) {
            Market_Category -> suggestions.groupBy { suggestion -> suggestion.marketCategory }
            Match_Period -> suggestions.groupBy { suggestion -> suggestion.matchPeriod }
            Teams -> suggestions.groupBy { suggestion -> suggestion.team }
            else -> suggestions.groupBy { suggestion -> suggestion.marketType }
        }
        emit(Resource.Success(groupedSuggestions))
    }

    override fun filterSuggestionsByMarketCategory(
        suggestions: ListOfSuggestions,
        marketCategories: List<String>
    ): Flow<Resource<ListOfSuggestions>> = flow{
        emit(Resource.Loading(suggestions))
        var filteredSuggestions = suggestions
        marketCategories.forEach { marketCategory->
            filteredSuggestions = filteredSuggestions.filter {it.marketCategory == marketCategory  }
        }
        emit(Resource.Success(filteredSuggestions))
    }

    override fun filterSuggestionsByMarketType(
        suggestions: ListOfSuggestions,
        marketTypes: List<String>
    ): Flow<Resource<ListOfSuggestions>> = flow{
        emit(Resource.Loading(suggestions))
        val filteredSuggestions = mutableListOf<Suggestion>()
        marketTypes.forEach { marketType->
            filteredSuggestions.addAll(suggestions.filter {it.marketType == marketType  })
        }
        emit(Resource.Success(filteredSuggestions))
    }

    override fun filterSuggestionsByMatchPeriod(
        suggestions: ListOfSuggestions,
        matchPeriod: String
    ): Flow<Resource<ListOfSuggestions>> = flow{
        emit(Resource.Loading(suggestions))
        val filteredSuggestions = when(matchPeriod) {
            Full_Time -> suggestions.filter { suggestion -> ((suggestion.matchPeriod) == Full_Time) }
            First_Half -> suggestions.filter { suggestion -> ((suggestion.matchPeriod ) == First_Half) }
            Second_Half -> suggestions.filter { suggestion -> ((suggestion.matchPeriod ) == Second_Half) }
            else -> suggestions
        }
        emit(Resource.Success(filteredSuggestions))
    }

    override fun filterSuggestionsByTeams(
        suggestions: ListOfSuggestions,
        teams: String
    ): Flow<Resource<ListOfSuggestions>> = flow{
        emit(Resource.Loading(suggestions))
        val filteredSuggestions = when(teams) {
            Main_Team -> suggestions.filter { suggestion -> ((suggestion.team) == Main_Team) }
            Opponent -> suggestions.filter { suggestion -> ((suggestion.team ) == Opponent) }
            else -> suggestions
        }
        emit(Resource.Success(filteredSuggestions))
    }

}