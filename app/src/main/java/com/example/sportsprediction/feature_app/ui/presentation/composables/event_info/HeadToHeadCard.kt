package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.NothingToShow
import com.example.sportsprediction.core.util.Constants.ShowLess
import com.example.sportsprediction.core.util.Constants.ShowMore
import com.example.sportsprediction.core.util.Constants.longDateFormat
import com.example.sportsprediction.core.util.Functions
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun HeadToHeadCard(
    teamNameEvents: List<TeamEventEntity>,
    isLoading: Boolean
) {
    var showMore by remember {
        mutableStateOf(0)
    }

    val showTeamEvents = when(showMore) {
        0 -> teamNameEvents.take(6)
        1 -> teamNameEvents.take(12)
        else-> teamNameEvents
    }


    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.noPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(250.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .requiredHeight(50.dp)
                        .requiredWidth(50.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = LocalSpacing.current.small
                )
            }

        }

        else {
            val height = if (showMore == 0) 300.dp else if (showMore == 1) 400.dp else 500.dp
            if (teamNameEvents.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = NothingToShow,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
            else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.small)
                        .heightIn(min = 10.dp, max = 500.dp),
                        //.requiredHeight(height),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    items(showTeamEvents) { teamNameEvent ->
                        val timeStamp = teamNameEvent.startTimestamp?.toLong()
                        val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })
                        val date = LocalDate.parse(timeStampAsDateTimeString, Functions.longDateFormatter)

                        HeadToHeadScoresInfo(
                            date = "$date",
                            homeTeam = teamNameEvent.homeTeamName!!,
                            homeScore = teamNameEvent.homeScore!!.toInt().toString(),
                            awayTeam = teamNameEvent.awayTeamName!!,
                            awayScore = teamNameEvent.awayScore!!.toInt().toString()
                        )
                        Divider(
                            color = MaterialTheme.colorScheme.onBackground,
                            thickness = LocalSpacing.current.thinBorder
                        )
                    }
                }
            }
        }

        //Show More label
        if (showTeamEvents.isNotEmpty()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.small)
                    .clickable {
                        showMore = when (showMore) {
                            0 -> 1
                            1 -> 2
                            else -> 0
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .clickable {
                            showMore = when (showMore) {
                                0 -> 1
                                1 -> 2
                                else -> 0
                            }
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (showMore) {
                            0 -> ShowMore
                            1 -> ShowMore
                            else -> ShowLess
                        },
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(LocalSpacing.current.small))
                    Icon(
                        modifier = Modifier
                            .requiredWidth(LocalSpacing.current.medium)
                            .requiredHeight(LocalSpacing.current.medium),
                        imageVector = when (showMore) {
                            0 -> Icons.Default.KeyboardArrowDown
                            1 -> Icons.Default.KeyboardArrowDown
                            else -> Icons.Default.KeyboardArrowUp
                        },
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = Constants.emptyString
                    )
                }
            }
        }

    }

}