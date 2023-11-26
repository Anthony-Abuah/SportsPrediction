package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.ListOfSuggestions

data class LoadTeamSuggestionsState (
    val suggestions: ListOfSuggestions = emptyList(),
    val isLoading: Boolean = false
)