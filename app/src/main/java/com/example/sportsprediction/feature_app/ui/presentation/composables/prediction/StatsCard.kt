package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.No_Stats
import com.example.sportsprediction.core.util.Functions.mapCategoryToScores
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.SuggestionPercentageText
import com.example.sportsprediction.feature_app.ui.presentation.composables.event_info.HeadToHeadScoresInfo
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun StatsCard(
    category: String,
    isLoading: Boolean,
    //tournament: String,
    teamEventStats: List<EventStatsEntity>,
    closeStatsCard: () -> Unit
) {
    val homeFiltered = teamEventStats.filter { stat->
        stat.location == Home
    }
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.noPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.small)
    ) {
        val longFormat = DateTimeFormatter.ofPattern(Constants.longDateFormat)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(PrimaryThemeColor)
                .padding(LocalSpacing.current.noPadding)
                .clickable { closeStatsCard() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = Color.White
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(LocalSpacing.current.small),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent, MaterialTheme.shapes.large)
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.Center
            ) {
                SuggestionPercentageText(
                    text = category,
                    fontSize = 18.sp,
                    textColor = PrimaryThemeColor
                )
            }
        }

        val scrollState = rememberScrollState(0)


        if (teamEventStats.isEmpty()){
            Box(modifier = Modifier
                .fillMaxSize(1f)
                .padding(LocalSpacing.current.small), contentAlignment = Alignment.Center){
                BasicText(
                    text = No_Stats,
                    fontSize = 15.sp,
                    textColor = Color.Gray
                )
            }
        }
        else{
            if (isLoading){
                Box(modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(LocalSpacing.current.small), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .verticalScroll(state = scrollState, enabled = true)
                ) {

                    Card(
                        shape = RoundedCornerShape(LocalSpacing.current.noPadding),
                        colors = CardDefaults.cardColors(
                            contentColor = Color.Black,
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpacing.current.small),
                        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.smallMedium)
                    ) {
                        teamEventStats.forEach {eventStats->
                            val timeStamp = eventStats.startTimestamp?.toLong()
                            val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })
                            val date = LocalDate.parse(timeStampAsDateTimeString, longFormat)

                            HeadToHeadScoresInfo(
                                date = "$date",
                                homeTeam = eventStats.homeTeamName!!,
                                homeScore = mapCategoryToScores(category, eventStats).first(),
                                awayTeam = eventStats.awayTeamName!!,
                                awayScore = mapCategoryToScores(category, eventStats).last()
                            )

                            Divider(
                                modifier = Modifier.padding(
                                    start = LocalSpacing.current.extraSmall,
                                    end = LocalSpacing.current.extraSmall
                                )
                            )
                        }

                    }
                }
            }

        }

    }


}