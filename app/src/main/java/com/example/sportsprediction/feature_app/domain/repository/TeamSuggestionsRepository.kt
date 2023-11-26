package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TeamSuggestionsRepository {
    fun saveTeamSuggestionsEntity(teamId: Int, teamName: String, date: Date, suggestions: ListOfSuggestions): Flow<Resource<TeamSuggestionsEntity>>


    suspend fun getBetorsConfidence(suggestion: Suggestion, betSuggestion: BetSuggestion) :Flow<Resource<Double>>

    suspend fun showBetBuilderSuggestions(listOfEvents: ListOfEvents, date: Date) :Flow<Resource<List<TeamSuggestionsEntity>>>


    fun filterSuggestionsByPercentage(suggestions: ListOfSuggestions, percentage: Double) :Flow<Resource<ListOfSuggestions>>

    fun orderSuggestions(suggestions: ListOfSuggestions, arrangementOrder: String) :Flow<Resource<ListOfSuggestions>>

    fun groupSuggestionsBy(suggestions: ListOfSuggestions, groupSuggestionsBy: String) :Flow<Resource<Map<String?, ListOfSuggestions>>>

    fun filterSuggestionsByMarketCategory(suggestions: ListOfSuggestions, marketCategories: List<String>) :Flow<Resource<ListOfSuggestions>>

    fun filterSuggestionsByMarketType(suggestions: ListOfSuggestions, marketTypes: List<String>) :Flow<Resource<ListOfSuggestions>>

    fun filterSuggestionsByMatchPeriod(suggestions: ListOfSuggestions, matchPeriod: String) :Flow<Resource<ListOfSuggestions>>

    fun filterSuggestionsByTeams(suggestions: ListOfSuggestions, teams: String) :Flow<Resource<ListOfSuggestions>>


}
