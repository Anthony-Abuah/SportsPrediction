package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun SuggestionCard(
    suggestion: Suggestion,
    betSuggestions: List<BetSuggestion>,
    onClick: () -> Unit,
    addToBuildBetList: (suggestion: Suggestion) -> Unit,
){
    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.noPadding)
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable{ onClick() },
        elevation = CardDefaults.cardElevation(LocalSpacing.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val market = suggestion.market ?: emptyString
            val outcome = suggestion.outcome?.toInt() ?: 0
            val sampleSpace = suggestion.sampleSpace?.toInt() ?: 0
            val streak = "${outcome}/${sampleSpace}"
            Row(modifier = Modifier.fillMaxWidth()
                .padding(LocalSpacing.current.small),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = LocalSpacing.current.small),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = market,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Box(modifier = Modifier.wrapContentWidth()
                    .padding(LocalSpacing.current.small)
                    .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.small)
                    .border(width = LocalSpacing.current.thinBorder,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = MaterialTheme.shapes.small)
                    .clickable{
                        addToBuildBetList(suggestion)
                    },
                    contentAlignment = Alignment.CenterEnd
                ){
                    val marketNames = betSuggestions.map { it.marketName }
                    Text(
                        modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                        text = if (marketNames.contains(suggestion.market)) "REMOVE" else "ADD" ,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                    /*
                    Icon(
                        modifier = Modifier
                            .aspectRatio(1f/1f)
                            .fillMaxSize(),
                        imageVector = Icons.Default.Add,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = emptyString
                    )
                    */
                }

                Box(modifier = Modifier.fillMaxWidth(1f)//.requiredWidth(LocalSpacing.current.extraLarge)
                    .padding(LocalSpacing.current.small),
                contentAlignment = Alignment.CenterEnd
                ){
                    Text(
                        text = streak,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Divider(
                thickness = LocalSpacing.current.thinBorder,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}