package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Corner_Kicks
import com.example.sportsprediction.core.util.Constants.Goalkeeper_Saves
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.Match_Goals
import com.example.sportsprediction.core.util.Constants.Match_Result
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.Offsides
import com.example.sportsprediction.core.util.Constants.Shots_On_Target
import com.example.sportsprediction.core.util.Constants.Total_Shots
import com.example.sportsprediction.core.util.Constants.Yellow_Cards
import com.example.sportsprediction.core.util.Functions.getEventStatsValues
import com.example.sportsprediction.core.util.SuggestionVariables.Goalkeeper_Saves_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Result
import com.example.sportsprediction.core.util.SuggestionVariables.Shots_On_Target_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Shots_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Result
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventScoresTeamText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor


@Composable
fun StatsContent(
    eventStats: EventStatsEntity,
    period: String,
) {
    val homeTeamName = eventStats.homeTeamName ?: NotAvailable
    val awayTeamName = eventStats.awayTeamName ?: NotAvailable
    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(LocalSpacing.current.noPadding)
            .background(MainBackgroundColor)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Row(modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                BasicText(text = homeTeamName, fontSize = 16.sp, textColor = Color.Black)
            }

            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                BasicText(text = awayTeamName, fontSize = 16.sp, textColor = Color.Black)
            }

        }

        // Goals
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
        contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Match_Goals),
                awayScore = getEventStatsValues(eventStats, period, Away, Match_Goals),
                statName = Match_Result
            )
        }
        // Corner
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Corner_Kicks),
                awayScore = getEventStatsValues(eventStats, period, Away, Corner_Kicks),
                statName = Corner_Kicks
            )
        }
        // Yellow Cards
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Yellow_Cards),
                awayScore = getEventStatsValues(eventStats, period, Away, Yellow_Cards),
                statName = Yellow_Cards
            )
        }
        // Total Shots
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Total_Shots),
                awayScore = getEventStatsValues(eventStats, period, Away, Total_Shots),
                statName = Total_Shots
            )
        }
        // Shots on Target
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Shots_On_Target),
                awayScore = getEventStatsValues(eventStats, period, Away, Shots_On_Target),
                statName = Shots_On_Target
            )
        }
        // Offsides
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Offsides),
                awayScore = getEventStatsValues(eventStats, period, Away, Offsides),
                statName = Offsides
            )
        }
        // Goalkeeper Saves
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(LocalSpacing.current.smallMedium),
            contentAlignment = Alignment.Center) {
            MarketStatsCard(
                homeScore = getEventStatsValues(eventStats, period, Home, Goalkeeper_Saves),
                awayScore = getEventStatsValues(eventStats, period, Away, Goalkeeper_Saves),
                statName = Goalkeeper_Saves
            )
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.smallMedium))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(LocalSpacing.current.noPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Match_Goals).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Match_Goals).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(text = "$Result : ", fontSize = 16.sp, textColor = Color.Black)
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Corner_Kicks).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Corner_Kicks).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(text = "$Corner_Kicks : ", fontSize = 16.sp, textColor = Color.Black)
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Yellow_Cards).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Yellow_Cards).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(
                    text = "$Yellow_Cards_Result : ",
                    fontSize = 16.sp,
                    textColor = Color.Black
                )
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Total_Shots).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Total_Shots).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(text = "$Shots_Result : ", fontSize = 16.sp, textColor = Color.Black)
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Shots_On_Target).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Shots_On_Target).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(
                    text = "$Shots_On_Target_Result : ",
                    fontSize = 16.sp,
                    textColor = Color.Black
                )
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Offsides).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Offsides).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(text = "$Offsides_Result : ", fontSize = 16.sp, textColor = Color.Black)
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeValue = try {
                    getEventStatsValues(eventStats, period, Home, Goalkeeper_Saves).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val awayValue = try {
                    getEventStatsValues(eventStats, period, Away, Goalkeeper_Saves).toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                } catch (e: java.lang.IllegalStateException) {
                    0
                }
                val result =
                    if (homeValue > awayValue) "${eventStats.homeTeamName}" else if (homeValue < awayValue) "${eventStats.awayTeamName}" else "Draw"
                BasicText(
                    text = "$Goalkeeper_Saves_Result : ",
                    fontSize = 16.sp,
                    textColor = Color.Black
                )
                EventScoresTeamText(
                    text = result,
                    fontWeight = FontWeight.Bold
                )
            }


        }


    }

}