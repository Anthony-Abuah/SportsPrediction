package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Functions.getMatchResult
import com.example.sportsprediction.core.util.Functions.longDateFormatter
import com.example.sportsprediction.core.util.Functions.mapCategoryToScores
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.event_info.EventScoresInfo
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun StatsCard(
    category: String,
    playingLocation: String,
    teamEventStats: List<EventStatsEntity>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 16.dp, max = 500.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(LocalSpacing.current.noPadding)
            .verticalScroll(state = ScrollState(0), enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        Row(modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = category,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        teamEventStats.forEach { eventStats ->
            val timeStamp = eventStats.startTimestamp?.toLong()
            val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })
            val date = LocalDate.parse(timeStampAsDateTimeString, longDateFormatter)
            val categoryScores = mapCategoryToScores(category, eventStats)
            val homeScore = categoryScores.first()
            val awayScore = categoryScores.last()
            val result = if (categoryScores.isNotEmpty()) {
                getMatchResult(
                    playingLocation,
                    try {
                        homeScore.toInt()
                    }catch (e:Exception){0},
                    try {
                        awayScore.toInt()
                    }catch (e:Exception){0}
                )
            } else 0.0
            EventScoresInfo(
                date = "$date",
                homeTeam = eventStats.homeTeamName!!,
                homeScore = homeScore,
                awayTeam = eventStats.awayTeamName!!,
                awayScore = awayScore,
                result = result
            )

            Divider(
                color = MaterialTheme.colorScheme.onBackground,
                thickness = LocalSpacing.current.thinBorder,
                modifier = Modifier.padding(
                    start = LocalSpacing.current.extraSmall,
                    end = LocalSpacing.current.extraSmall
                )
            )
        }

    }


}