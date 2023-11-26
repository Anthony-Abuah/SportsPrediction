package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.All
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.NothingToShow
import com.example.sportsprediction.core.util.Constants.ShowLess
import com.example.sportsprediction.core.util.Constants.ShowMore
import com.example.sportsprediction.core.util.Constants.ThisTournament
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.longDateFormat
import com.example.sportsprediction.core.util.Functions.getMatchResult
import com.example.sportsprediction.core.util.Functions.longDateFormatter
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.domain.model.general.Tournament
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun PreviousEventCard(
    isLoading: Boolean,
    tournamentName: String,
    teamNameEvents: List<TeamEventEntity>
) {
    var showMore by remember {
        mutableStateOf(0)
    }
    var selectedOption by remember {
        mutableStateOf(All)
    }

    val showTeamEvents = when(showMore){
        0-> when(selectedOption){
            Home -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Home.lowercase(Locale.getDefault())
            }.take(6)
            Away -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Away.lowercase(Locale.getDefault())
            }.take(6)
            ThisTournament -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.tournamentName?.lowercase(Locale.getDefault()) ?: emptyString) == tournamentName.lowercase(Locale.getDefault())
            }.take(6)
            else -> teamNameEvents.take(6)

        }
        1-> when(selectedOption){
            Home -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Home.lowercase(Locale.getDefault())
            }.take(10)
            Away -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Away.lowercase(Locale.getDefault())
            }.take(10)
            ThisTournament -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.tournamentName?.lowercase(Locale.getDefault()) ?: emptyString) == tournamentName.lowercase(Locale.getDefault())
            }.take(10)
            else -> teamNameEvents.take(10)

        }
        else-> when(selectedOption){
            Home -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Home.lowercase(Locale.getDefault())
            }.take(20)
            Away -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.mainTeamPlayingLocation?.lowercase(Locale.getDefault()) ?: emptyString) == Away.lowercase(Locale.getDefault())
            }.take(20)
            ThisTournament -> teamNameEvents.filter { teamEventEntity ->
                (teamEventEntity.tournamentName?.lowercase(Locale.getDefault()) ?: emptyString) == tournamentName.lowercase(Locale.getDefault())
            }.take(20)
            else -> teamNameEvents.take(30)

        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
        .padding(LocalSpacing.current.default),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val options = listOf(All, Home, Away, ThisTournament)

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = LocalSpacing.current.small,
                vertical = LocalSpacing.current.default,
            )
        ) {
            options.forEach { option ->
                val isSelected = selectedOption == option
                Card(
                    shape = MaterialTheme.shapes.small,
                    border = BorderStroke(LocalSpacing.current.borderStroke,
                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    ),
                    colors = CardDefaults.cardColors(
                        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier
                        .weight(option.length.toFloat())
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.extraSmall)
                        .clickable { selectedOption = option },
                    elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.default)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = LocalSpacing.current.default),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                            color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
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
                            .padding(LocalSpacing.current.noPadding)
                            .heightIn(min = 10.dp, max = 500.dp),
                            //.requiredHeight(height),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        items(showTeamEvents) { teamNameEvent ->
                            val timeStamp = teamNameEvent.startTimestamp?.toLong()
                            val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })
                            val date = LocalDate.parse(timeStampAsDateTimeString, longDateFormatter)

                            EventScoresInfo(
                                date = "$date",
                                homeTeam = teamNameEvent.homeTeamName
                                    ?: NotAvailable.lowercase(Locale.getDefault()),
                                homeScore = "${
                                    teamNameEvent.homeScore ?: NotAvailable.lowercase(
                                        Locale.getDefault()
                                    )
                                }",
                                awayTeam = teamNameEvent.awayTeamName
                                    ?: NotAvailable.lowercase(Locale.getDefault()),
                                awayScore = "${
                                    teamNameEvent.awayScore ?: NotAvailable.lowercase(
                                        Locale.getDefault()
                                    )
                                }",
                                result = getMatchResult(
                                    teamNameEvent.mainTeamPlayingLocation,
                                    teamNameEvent.homeScore,
                                    teamNameEvent.awayScore
                                )
                            )
                            Divider(
                                color = MaterialTheme.colorScheme.onSurface,
                                thickness = LocalSpacing.current.thinBorder
                            )
                        }
                    }
                }
            }

            //Show More label
            if (showTeamEvents.isNotEmpty()) {
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
                            contentDescription = emptyString
                        )
                    }
                }
            }

        }

    }
}