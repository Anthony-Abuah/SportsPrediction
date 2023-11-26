package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.ListOfSuggestions


data class GroupedSuggestionState (
    val groupedSuggestions: Map<String?, ListOfSuggestions> = emptyMap(),
    val isLoading: Boolean = false

)