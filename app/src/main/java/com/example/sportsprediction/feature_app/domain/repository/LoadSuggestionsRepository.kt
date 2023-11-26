package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.core.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LoadSuggestionsRepository {

    fun loadTeamSuggestions(teamId: Int, teamName: String, date: Date): Flow<Resource<ListOfSuggestions>>

    fun groupSuggestions(suggestions: ListOfSuggestions, percentage: Double, arrangementOrder: String, suggestionGrouping: String): Flow<Resource<Map<String?, ListOfSuggestions>>>


}
