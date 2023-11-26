package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.Ascending
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
import com.example.sportsprediction.core.util.Constants.Market_Category
import com.example.sportsprediction.core.util.Constants.Match_Period
import com.example.sportsprediction.core.util.Constants.Match_Result
import com.example.sportsprediction.core.util.Constants.Offsides
import com.example.sportsprediction.core.util.Constants.Second_Half
import com.example.sportsprediction.core.util.Constants.Teams
import com.example.sportsprediction.core.util.Constants.WinValue
import com.example.sportsprediction.core.util.Constants.Yellow_Cards
import com.example.sportsprediction.core.util.Functions.getResultValue
import com.example.sportsprediction.core.util.ListOfSuggestions
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
import com.example.sportsprediction.core.util.SuggestionVariables.Draw_Or_Lose
import com.example.sportsprediction.core.util.SuggestionVariables.FirstHalfCorners
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Totals
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
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Unders
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
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.WinOrDraw
import com.example.sportsprediction.core.util.SuggestionVariables.WinOrLose
import com.example.sportsprediction.core.util.SuggestionVariables.Win_Or_Draw
import com.example.sportsprediction.core.util.SuggestionVariables.Win_Or_Lose
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
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.repository.LoadSuggestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class LoadSuggestionsRepositoryImpl(
    private val eventStatsDao: EventStatsDao
) : LoadSuggestionsRepository {
    override fun loadTeamSuggestions(
        teamId: Int,
        teamName: String,
        date: Date
    ): Flow<Resource<ListOfSuggestions>> = flow {
        emit(Resource.Loading())

        val suggestions = mutableListOf<Suggestion>()

        resultsList.forEach{ result ->
            // Full Time Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $teamName ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeMatchResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFullTimeMatchResult(WinValue, teamId)!!.plus(eventStatsDao.getFullTimeMatchResult(LoseValue, teamId)!!).plus(eventStatsDao.getFullTimeMatchResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = Full_Time,
                    team = teamName
                )
            )

            // First Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $teamName ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfMatchResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFirstHalfMatchResult(WinValue, teamId)!!
                        .plus(eventStatsDao.getFirstHalfMatchResult(LoseValue, teamId)!!)
                        .plus(eventStatsDao.getFirstHalfMatchResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Match_Result,
                    marketType = Match_Result,
                    matchPeriod = First_Half,
                    team = teamName
                )
            )

            // Second Half Match Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $teamName ${result}s",
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
                    team = teamName
                )
            )

            // Full time corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $teamName $Corner_Kicks ${result}s",
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
                    team = teamName
                )
            )

            // First half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $teamName $Corner_Kicks ${result}s",
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
                    team = teamName
                )
            )
            // Second half corner kick result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $teamName $Corner_Kicks ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfCornerKicksResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getSecondHalfCornerKicksResult(WinValue, teamId)!!.plus(eventStatsDao.getSecondHalfCornerKicksResult(LoseValue, teamId)!!).plus(eventStatsDao.getSecondHalfCornerKicksResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Corner_Result,
                    marketType = Corner_Result,
                    matchPeriod = Second_Half,
                    team = teamName
                )
            )
            // Full time yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $teamName $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeCardsResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFullTimeCardsResult(WinValue, teamId)!!.plus(eventStatsDao.getFullTimeCardsResult(LoseValue, teamId)!!).plus(eventStatsDao.getFullTimeCardsResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Full_Time,
                    team = teamName
                )
            )
            // First half yellow card result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $teamName $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfCardsResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFirstHalfCardsResult(WinValue, teamId)!!.plus(eventStatsDao.getFirstHalfCardsResult(LoseValue, teamId)!!).plus(eventStatsDao.getFirstHalfCardsResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = First_Half,
                    team = teamName
                )
            )
            // Second half yellow cards result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $teamName $Yellow_Cards ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfCardsResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getSecondHalfCardsResult(WinValue, teamId)!!.plus(eventStatsDao.getSecondHalfCardsResult(LoseValue, teamId)!!).plus(eventStatsDao.getSecondHalfCardsResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Yellow_Cards_Result,
                    marketType = Yellow_Cards_Result,
                    matchPeriod = Second_Half,
                    team = teamName
                )
            )
            // Full time Offsides Results
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Full_Time $teamName $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFullTimeOffsideResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFullTimeOffsideResult(WinValue, teamId)!!.plus(eventStatsDao.getFullTimeOffsideResult(LoseValue, teamId)!!).plus(eventStatsDao.getFullTimeOffsideResult(DrawValue, teamId)!!),
                    marketCategory = Full_Time_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Full_Time,
                    team = teamName
                )
            )
            // First half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$First_Half $teamName $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getFirstHalfOffsideResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getFirstHalfOffsideResult(WinValue, teamId)!!.plus(eventStatsDao.getFirstHalfOffsideResult(LoseValue, teamId)!!).plus(eventStatsDao.getFirstHalfOffsideResult(DrawValue, teamId)!!),
                    marketCategory = First_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = First_Half,
                    team = teamName
                )
            )
            // Second half offsides result
            suggestions.add(
                Suggestion(
                    teamName = teamName,
                    teamId = teamId,
                    market = "$Second_Half $teamName $Offsides ${result}s",
                    value = getResultValue(result).toString(),
                    outcome = eventStatsDao.getSecondHalfOffsideResult(getResultValue(result), teamId)!!,
                    sampleSpace = eventStatsDao.getSecondHalfOffsideResult(WinValue, teamId)!!.plus(eventStatsDao.getSecondHalfOffsideResult(LoseValue, teamId)!!).plus(eventStatsDao.getSecondHalfOffsideResult(DrawValue, teamId)!!),
                    marketCategory = Second_Half_Offsides_Result,
                    marketType = Offsides_Result,
                    matchPeriod = Second_Half,
                    team = teamName
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
                outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, min.toDouble(), 1.0)!!,
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
                outcome = eventStatsDao.getFirstHalfMultiGoals(teamId, min.toDouble(), 1.0)!!,
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
                outcome = eventStatsDao.getSecondHalfMultiGoals(teamId, min.toDouble(), 1.0)!!,
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
                market = "$Full_Time $teamName $Multigoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(teamId, Home, min.toDouble(), 1.0)!!.plus(eventStatsDao.getFullTimeMainTeamAwayMultiGoals(teamId, Away, min.toDouble(), 1.0)!!),
                sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                marketCategory = Full_Time_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Full_Time,
                team = teamName
            )
        )
        // First half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half $teamName $Multigoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(teamId, Home, min.toDouble(), 1.0)!!.plus(eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(teamId, Away, min.toDouble(), 1.0)!!),
                sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                marketCategory = First_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = First_Half,
                team = teamName
            )
        )
        // Second half Main Team Multi goals
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half $teamName $Multigoals ($No_Goal)",
                value = No_Goal,
                outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(teamId, Home, min.toDouble(), 1.0)!!.plus(eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(teamId, Away, min.toDouble(), 1.0)!!),
                sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                marketCategory = Second_Half_Main_Team_MultiGoals,
                marketType = Multigoals,
                matchPeriod = Second_Half,
                team = teamName
            )
        )

        // Full time Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time $teamName $Clean_Sheets",
                value = No_Goal,
                outcome = eventStatsDao.getFullTimeMainTeamHomeCleanSheets(teamId, Home, 0.0)!!.plus(eventStatsDao.getFullTimeMainTeamAwayCleanSheets(teamId, Away, 0.0)!!),
                sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                marketCategory = Full_Time_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Full_Time,
                team = teamName
            )
        )
        // First half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half $teamName $Clean_Sheets",
                value = No_Goal,
                outcome = eventStatsDao.getFirstHalfMainTeamHomeCleanSheets(teamId, Home, 0.0)!!.plus(eventStatsDao.getFirstHalfMainTeamAwayCleanSheets(teamId, Away, 0.0)!!),
                sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                marketCategory = First_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = First_Half,
                team = teamName
            )
        )
        // Second half Main Team Clean Sheets
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half $teamName $Clean_Sheets",
                value = No_Goal,
                outcome = eventStatsDao.getSecondHalfMainTeamHomeCleanSheets(teamId, Home, 0.0)!!.plus(eventStatsDao.getSecondHalfMainTeamAwayCleanSheets(teamId, Away, 0.0)!!),
                sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                marketCategory = Second_Half_Main_Team_Clean_Sheets,
                marketType = Clean_Sheets,
                matchPeriod = Second_Half,
                team = teamName
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
                outcome = (eventStatsDao.getFullTimeTotalBothTeamsToScore(teamId)!! - eventStatsDao.getFullTimeBothTeamsToScore(teamId, 0.0)!!),
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
                outcome = (eventStatsDao.getFirstHalfTotalBothTeamsToScore(teamId)!! - eventStatsDao.getFirstHalfBothTeamsToScore(teamId, 0.0)!!),
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
                outcome = (eventStatsDao.getSecondHalfTotalBothTeamsToScore(teamId)!! - eventStatsDao.getSecondHalfBothTeamsToScore(teamId, 0.0)!!),
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
                market = "$Full_Time $teamName $Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = teamName
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time $teamName $Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, WinValue, LoseValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = teamName
            )
        )
        // Full time Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Full_Time $teamName $Draw_Or_Lose ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getFullTimeDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFullTimeTotalDoubleChance(teamId)!!,
                marketCategory = Full_Time_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Full_Time,
                team = teamName
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half $teamName $Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = teamName
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half $teamName $Win_Or_Lose ($WinOrLose)",
                value = WinOrLose,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, WinValue, LoseValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = teamName
            )
        )
        // First half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$First_Half $teamName $Draw_Or_Lose ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getFirstHalfDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getFirstHalfTotalDoubleChance(teamId)!!,
                marketCategory = First_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = First_Half,
                team = teamName
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half $teamName $Win_Or_Draw ($WinOrDraw)",
                value = WinOrDraw,
                outcome = eventStatsDao.getSecondHalfDoubleChance(teamId, WinValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalDoubleChance(teamId)!!,
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = teamName
            )
        )
        // Second half Double Chance
        suggestions.add(
            Suggestion(
                teamName = teamName,
                teamId = teamId,
                market = "$Second_Half $teamName $Win_Or_Lose ($WinOrLose)",
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
                market = "$Second_Half $teamName $Draw_Or_Lose ($DrawOrLose)",
                value = DrawOrLose,
                outcome = eventStatsDao.getSecondHalfDoubleChance(teamId, LoseValue, DrawValue)!!,
                sampleSpace = eventStatsDao.getSecondHalfTotalDoubleChance(teamId)!!,
                marketCategory = Second_Half_Double_Chance,
                marketType = Double_Chance,
                matchPeriod = Second_Half,
                team = teamName
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
                        outcome = eventStatsDao.getFullTimeGoalsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeGoalsUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getFirstHalfCardsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfCardsUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getSecondHalfCardsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfCardsUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getFirstHalfOffsideUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfOffsideUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getSecondHalfOffsideUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfOffsideUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getFirstHalfGoalsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfGoalsUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getSecondHalfGoalsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfGoalsUnder(min, max, teamId)!!,
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
                        market = "$Full_Time $teamName $Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getFullTimeHomeHandicapResult(teamId, Home, value.toDouble())!!.plus(eventStatsDao.getFullTimeAwayHandicapResult(teamId, Away, value.toDouble())!!),
                        sampleSpace = eventStatsDao.getFullTimeTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time $teamName $Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getFullTimeHomeHandicapResult(teamId, Home, (value.toDouble().times(-1.0)))!!.plus(eventStatsDao.getFullTimeAwayHandicapResult(teamId, Away, value.toDouble().times(-1.0))!!),
                        sampleSpace = eventStatsDao.getFullTimeTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = Full_Time_Handicap,
                        marketType = Handicap,
                        matchPeriod = Full_Time,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half $teamName $Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getFirstHalfHomeHandicapResult(teamId, Home, value.toDouble())!!.plus(eventStatsDao.getFirstHalfAwayHandicapResult(teamId, Away, value.toDouble())!!),
                        sampleSpace = eventStatsDao.getFirstHalfTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half $teamName $Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getFirstHalfHomeHandicapResult(teamId, Home, value.toDouble().times(-1.0))!!.plus(eventStatsDao.getFirstHalfAwayHandicapResult(teamId, Away, value.toDouble().times(-1.0))!!),
                        sampleSpace = eventStatsDao.getFirstHalfTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = First_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = First_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half $teamName $Handicap (0 : $value)",
                        value = "-$value",
                        outcome = eventStatsDao.getSecondHalfHomeHandicapResult(teamId, Home, value.toDouble())!!.plus(eventStatsDao.getSecondHalfAwayHandicapResult(teamId, Away, value.toDouble())!!),
                        sampleSpace = eventStatsDao.getSecondHalfTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half $teamName $Handicap ($value : 0)",
                        value = "+$value",
                        outcome = eventStatsDao.getSecondHalfHomeHandicapResult(teamId, Home, value.toDouble().times(-1.0))!!.plus(eventStatsDao.getSecondHalfAwayHandicapResult(teamId, Away, value.toDouble().times(-1.0))!!),
                        sampleSpace = eventStatsDao.getSecondHalfTotalHandicapResult(teamId, -1.0, 100.0)!!,
                        marketCategory = Second_Half_Handicap,
                        marketType = Handicap,
                        matchPeriod = Second_Half,
                        team = teamName
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
                        outcome = eventStatsDao.getFirstHalfCornersUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFirstHalfCornersUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getSecondHalfCornersUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getSecondHalfCornersUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getFullTimeCardsUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeCardsUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getFullTimeOffsideUnder(min, value, teamId)!!,
                        sampleSpace = eventStatsDao.getFullTimeOffsideUnder(min, max, teamId)!!,
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
                        outcome = eventStatsDao.getBothHalvesOvers(teamId, value.toDouble())!!,
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
                        outcome = eventStatsDao.getBothHalvesTotals(teamId)!!.minus(eventStatsDao.getBothHalvesOvers(teamId, value.toDouble())!!),
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
                        outcome = eventStatsDao.getBothHalvesUnders(teamId, value.toDouble())!!,
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
                        outcome = eventStatsDao.getBothHalvesTotals(teamId)!!.minus(eventStatsDao.getBothHalvesUnders(teamId, value.toDouble())!!),
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, 0.0, value.toDouble().plus(1.0))!!,
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, 1.0, value.toDouble().plus(1.0))!!,
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, 2.0, value.toDouble().plus(1.0))!!,
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, value.minus(1.0), max.toDouble())!!,
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, 3.0, value.toDouble().plus(1.0))!!,
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
                        outcome = eventStatsDao.getFullTimeMatchMultiGoals(teamId, 4.0, value.toDouble().plus(1.0))!!,
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
                        market = "$Full_Time $teamName $Multigoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(teamId, Home, 0.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getFullTimeMainTeamAwayMultiGoals(teamId, Away, 0.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half $teamName $Multigoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(teamId, Home, 0.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(teamId, Away, 0.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half $teamName $Multigoals (1-$value goals)",
                        value = "1-$value goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(teamId, Home, 0.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(teamId, Away, 0.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = teamName
                    )
                )

            }

            if (value == 3) {
                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time $teamName $Multigoals (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(teamId, Home, 1.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getFullTimeMainTeamAwayMultiGoals(teamId, Away, 1.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Full_Time $teamName $Multigoals  (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)}+ goals",
                        outcome = eventStatsDao.getFullTimeMainTeamHomeMultiGoals(teamId, Home, 3.0, max.toDouble())!!.plus(eventStatsDao.getFullTimeMainTeamAwayMultiGoals(teamId, Away, 3.0, max.toDouble())!!),
                        sampleSpace = eventStatsDao.getFullTimeTotalMultiGoals(teamId)!!,
                        marketCategory = Full_Time_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Full_Time,
                        team = teamName
                    )
                )


                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half $teamName $Multigoals  (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(teamId, Home, 1.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(teamId, Away, 1.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$First_Half $teamName $Multigoals  (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)} goals",
                        outcome = eventStatsDao.getFirstHalfMainTeamHomeMultiGoals(teamId, Home, 3.0, max.toDouble())!!.plus(eventStatsDao.getFirstHalfMainTeamAwayMultiGoals(teamId, Away, 3.0, max.toDouble())!!),
                        sampleSpace = eventStatsDao.getFirstHalfTotalMultiGoals(teamId)!!,
                        marketCategory = First_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = First_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half $teamName $Multigoals  (2-$value goals)",
                        value = "2-$value goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(teamId, Home, 1.0, value.toDouble().plus(1.0))!!.plus(eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(teamId, Away, 1.0, value.toDouble().plus(1.0))!!),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = teamName
                    )
                )

                suggestions.add(
                    Suggestion(
                        teamName = teamName,
                        teamId = teamId,
                        market = "$Second_Half $teamName $Multigoals  (${value.plus(1)}+ goals)",
                        value = "1-${value.plus(1)} goals",
                        outcome = eventStatsDao.getSecondHalfMainTeamHomeMultiGoals(teamId, Home, 3.0, max.toDouble())!!.plus(eventStatsDao.getSecondHalfMainTeamAwayMultiGoals(teamId, Away, 3.0, max.toDouble())!!),
                        sampleSpace = eventStatsDao.getSecondHalfTotalMultiGoals(teamId)!!,
                        marketCategory = Second_Half_Main_Team_MultiGoals,
                        marketType = Multigoals,
                        matchPeriod = Second_Half,
                        team = teamName
                    )
                )

            }

        }

        emit(Resource.Success(suggestions))

    }

    override fun groupSuggestions(
        suggestions: ListOfSuggestions,
        percentage: Double,
        arrangementOrder: String,
        suggestionGrouping: String
    ): Flow<Resource<Map<String?, ListOfSuggestions>>> = flow{
        emit(Resource.Loading())
        val groupedSuggestions = when(suggestionGrouping){
            Market_Category-> when(arrangementOrder){
                Ascending->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedBy { it.streakProbability }.groupBy { it.marketCategory }
                else->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedByDescending { it.streakProbability }.groupBy { it.marketCategory }
            }

            Match_Period-> when(arrangementOrder){
                Ascending->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedBy { it.streakProbability }.groupBy { it.matchPeriod }
                else->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedByDescending { it.streakProbability }.groupBy { it.matchPeriod }
            }

            Teams-> when(arrangementOrder){
                Ascending->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedBy { it.streakProbability }.groupBy { it.team }
                else->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedByDescending { it.streakProbability }.groupBy { it.team }
            }

            else-> when(arrangementOrder){
                Ascending->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedBy { it.streakProbability }.groupBy { it.marketType }
                else->suggestions.filter { (it.streakProbability ?: 0.0) >= percentage }.sortedByDescending { it.streakProbability }.groupBy { it.marketType }
            }

        }

        emit(Resource.Success(groupedSuggestions))
    }

}