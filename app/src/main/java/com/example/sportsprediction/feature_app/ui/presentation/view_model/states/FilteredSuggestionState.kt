package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.ListOfSuggestions


data class FilteredSuggestionState (
    val filteredSuggestions: ListOfSuggestions = emptyList(),
    val isLoading: Boolean = false
)