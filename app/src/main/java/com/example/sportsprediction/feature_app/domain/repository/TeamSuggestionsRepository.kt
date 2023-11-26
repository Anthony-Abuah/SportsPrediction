package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TeamSuggestionsRepository {
    fun getTeamSuggestions(teamId: Int, teamName: String, date: Date): Flow<Resource<TeamSuggestionsEntity>>

    fun getSuggestions(teamId: Int, teamName: String, date: Date /*, teamStats: ListOfEventStats*/ ): Flow<Resource<TeamSuggestionsEntity>>

    suspend fun getBetorsConfidence(suggestion: Suggestion, betSuggestion: BetSuggestion) :Flow<Resource<Double>>

    suspend fun getBetBuilderSuggestions(listOfEvents: ListOfEvents, date: Date): Flow<Resource<List<TeamSuggestionsEntity>>>

    suspend fun showBetBuilderSuggestions(listOfEvents: ListOfEvents, date: Date) :Flow<Resource<List<TeamSuggestionsEntity>>>

}
