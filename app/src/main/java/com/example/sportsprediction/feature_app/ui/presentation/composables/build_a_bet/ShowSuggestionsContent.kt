package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing


@Composable
fun ShowSuggestionsContent(
    isLoading: Boolean,
    teamSuggestions: List<TeamSuggestionsEntity>,
) {
    val scrollState = rememberScrollState(0)

    Log.d("ShowSuggestionContent", "teamSuggestions size: ${teamSuggestions.size}")
    Log.d("ShowSuggestionContent", "isLoading: $isLoading")

    if (isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)
            .verticalScroll(scrollState)
        ) {
            val everyTeamSuggestion = mutableListOf<Suggestion>()
            teamSuggestions.forEach { teamSuggestion->
                everyTeamSuggestion.addAll(teamSuggestion.suggestions)
            }
            val suggestions = everyTeamSuggestion.filter { (it.streakProbability ?: nullDouble) >= 0.0 }
            suggestions.forEach { suggestion ->
                ShowSuggestionsCard(
                    suggestion = suggestion,
                    onAddToBetSlip = { _, _, _, _, _, _, _, _, _ -> }
                ) {}
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
                Divider()
                Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
            }
        }
    }
}








