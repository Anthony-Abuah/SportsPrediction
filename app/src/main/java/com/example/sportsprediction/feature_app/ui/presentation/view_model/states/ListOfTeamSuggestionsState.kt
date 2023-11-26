package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity


data class ListOfTeamSuggestionsState (
    val listOfTeamSuggestions: List<TeamSuggestionsEntity> = emptyList(),
    val isLoading: Boolean = false
)