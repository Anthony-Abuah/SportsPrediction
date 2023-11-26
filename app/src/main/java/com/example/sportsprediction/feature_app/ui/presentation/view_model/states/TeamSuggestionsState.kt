package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import java.util.*


data class TeamSuggestionsState (
    val teamSuggestions: TeamSuggestionsEntity = TeamSuggestionsEntity(null, emptyString, Date(), 0, emptyList()),
    val isLoading: Boolean = false
)