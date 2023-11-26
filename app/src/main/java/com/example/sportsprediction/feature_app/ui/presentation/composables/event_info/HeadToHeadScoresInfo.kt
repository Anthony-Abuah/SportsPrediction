package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventScoresTeamText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun HeadToHeadScoresInfo(
    date: String,
    homeTeam: String,
    homeScore: String,
    awayTeam: String,
    awayScore: String
) {
    val thisHomeScore = try {
        homeScore.toDouble()
    } catch (e: java.lang.NumberFormatException) {
        nullDouble
    }
    val thisAwayScore = try {
        awayScore.toDouble()
    } catch (e: java.lang.NumberFormatException) {
        nullDouble
    }

    val homeFontWeight = if((thisHomeScore != nullDouble || thisAwayScore != nullDouble) && (thisHomeScore > thisAwayScore)) FontWeight.SemiBold else FontWeight.Normal
    val awayFontWeight = if((thisHomeScore != nullDouble || thisAwayScore != nullDouble) && (thisAwayScore > thisHomeScore)) FontWeight.SemiBold else FontWeight.Normal


    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(modifier = Modifier.weight(9f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                // Date
                Box(modifier = Modifier.padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.Center){
                    Text(
                        text = date,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                // Vertical Border
                Box(modifier = Modifier
                    .padding(start = LocalSpacing.current.small, end = LocalSpacing.current.small)
                    .fillMaxHeight(0.95f)
                    .width(LocalSpacing.current.borderStroke)
                    .background(MaterialTheme.colorScheme.onBackground),
                    contentAlignment = Alignment.Center
                ){
                    Text(modifier = Modifier.padding(LocalSpacing.current.textSpace),
                        text = homeTeam.take(2),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Column(modifier = Modifier.padding(start = LocalSpacing.current.noPadding)
                ) {
                    //Default Team
                    Row(modifier = Modifier.padding(LocalSpacing.current.noPadding)
                    ) {
                        //Default Team
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.noPadding),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            EventScoresTeamText(text = homeTeam, fontWeight = homeFontWeight)
                        }
                    }

                    //Away Team
                    Row(modifier = Modifier.padding(LocalSpacing.current.noPadding)
                    ){
                        //Away Team
                        Box(modifier = Modifier.padding(LocalSpacing.current.noPadding),
                            contentAlignment = Alignment.CenterStart
                        ){
                            EventScoresTeamText(text = awayTeam, fontWeight = awayFontWeight)
                        }

                    }

                }

            }


            Row(modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(end = LocalSpacing.current.small)
                ) {
                    //Default Team
                    Row(
                        modifier = Modifier.padding(LocalSpacing.current.noPadding)
                    ) {
                        //Home Scores
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.noPadding),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            EventScoresTeamText(text = homeScore, fontWeight = homeFontWeight)
                        }
                    }

                    Row(
                        modifier = Modifier.padding(end = LocalSpacing.current.noPadding)
                    ) {
                        //Away Scores
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.noPadding),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            EventScoresTeamText(text = awayScore, fontWeight = awayFontWeight)
                        }

                    }

                }
            }

        }

    }

}