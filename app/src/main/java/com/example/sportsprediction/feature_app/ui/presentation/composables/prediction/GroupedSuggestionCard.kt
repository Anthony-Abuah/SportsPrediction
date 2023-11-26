package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.No_Suggestions
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun GroupedSuggestionCard(
    group: String,
    suggestions: ListOfSuggestions,
    betSuggestions: List<BetSuggestion>,
    onClickSuggestion: (marketCategory: String) -> Unit,
    addToBuildBet: (suggestion: Suggestion) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(LocalSpacing.current.small),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RectangleShape
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.default),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = group,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        suggestions.forEach { suggestion ->
            SuggestionCard(
                suggestion = suggestion,
                betSuggestions = betSuggestions,
                onClick = { onClickSuggestion(suggestion.marketCategory ?: emptyString) },
                addToBuildBetList = {
                    addToBuildBet(it)
                }
            )
        }


    }

}