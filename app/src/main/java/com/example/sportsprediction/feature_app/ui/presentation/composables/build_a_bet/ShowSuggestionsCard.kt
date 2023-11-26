package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BetSlipBoldStreakText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BetSlipLightStreakText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BetSlipTeamText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun ShowSuggestionsCard(
    suggestion: Suggestion,
    onAddToBetSlip: (marketName: String, numerator: String, denominator: String, value: String?, percentageText: String, marketCategory: String, marketType: String, matchPeriod: String, team: String) -> Unit,
    onOpenStats: (category: String) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = MainBackgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.smallMedium),
        elevation = CardDefaults.cardElevation(
            defaultElevation = LocalSpacing.current.small
        )
    ) {
        Column(modifier = Modifier.padding(LocalSpacing.current.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val percentage = suggestion.streakProbability?.times(100.0)
            val marketName = suggestion.market ?: emptyString
            val numerator = suggestion.outcome?.toInt().toString().take(5)
            val denominator = suggestion.sampleSpace?.toInt().toString().take(5)
            val value = suggestion.value
            val percentageText = "${percentage.toString().take(5)}%"
            val marketCategory = suggestion.marketCategory ?: emptyString
            val marketType = suggestion.marketType ?: emptyString
            val matchPeriod = suggestion.matchPeriod ?: emptyString
            val team = suggestion.team ?: emptyString


            Row(modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onOpenStats(marketCategory)
                    },
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipTeamText(
                                text = marketName
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BasicText(
                                text = suggestion.teamName ?: NotAvailable,
                                fontSize = 15.sp,
                                textColor = Color.Black
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipLightStreakText(
                                text = "Streak is "
                            )
                        }
                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipBoldStreakText(
                                text = "$numerator "
                            )
                        }

                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipLightStreakText(
                                text = "out of "
                            )
                        }

                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipBoldStreakText(
                                text = "$denominator "
                            )
                        }

                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BetSlipLightStreakText(
                                text = "matches"
                            )
                        }

                    }

                }

                    Box(
                        modifier = Modifier
                            .height(LocalSpacing.current.teamLogo)
                            .width(LocalSpacing.current.teamLogo)
                            .background(
                                PrimaryThemeColor,
                                RoundedCornerShape(LocalSpacing.current.small)
                            )
                            .clickable { onAddToBetSlip(marketName, numerator, denominator, value, percentageText, marketCategory, marketType, matchPeriod, team) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                //}

            }

        }

    }

}