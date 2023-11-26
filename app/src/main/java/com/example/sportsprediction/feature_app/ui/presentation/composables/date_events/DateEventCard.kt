package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateEventCard(
    preferredEvent: EventsEntity,
    navigateToPredictionsScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit
) {

    val shortDateFormatter = DateTimeFormatter.ofPattern(Constants.shortDateFormat)
    val dateToLocalDate =
        preferredEvent.date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val dateString = shortDateFormatter.format(dateToLocalDate)


    val roundName = preferredEvent.roundInfo?.name ?: emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == nullInteger) {
        emptyString
    } else if (roundName.isNotEmpty() && roundNumber == nullInteger) {
        ", $roundName"
    } else if (roundName.isEmpty() && roundNumber != nullInteger) {
        ", Round $roundNumber"
    } else {
        ", $roundName, Round $roundNumber"
    }

    val sport = if (preferredEvent.tournament?.sport.isNullOrBlank()) {
        emptyString
    } else preferredEvent.tournament?.sport
    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) emptyString else {
        ", ${preferredEvent.tournamentName}"
    }
    val country = if (preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {
        ", ${preferredEvent.tournament?.categoryName}"
    }


    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),

        onClick = {
            navigateToPredictionsScreen(
                "${preferredEvent.eventId ?: nullInteger}",
                preferredEvent.headToHeadId ?: emptyString,
                dateString ?: emptyString,
                preferredEvent.homeTeamName ?: emptyString,
                "${preferredEvent.homeTeamId ?: nullInteger}",
                preferredEvent.awayTeamName ?: emptyString,
                "${preferredEvent.awayTeamId ?: nullInteger}"
            )
        },
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.smallMedium)
    ) {

        Row(
            modifier = Modifier.padding(LocalSpacing.current.extraSmall),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.football),
                    contentDescription = emptyString,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$sport$country$tournamentName$roundInfo",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LocalSpacing.current.small)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = LocalSpacing.current.noPadding)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(LocalSpacing.current.extraSmall)
                            .requiredHeight(LocalSpacing.current.large)
                            .requiredWidth(LocalSpacing.current.large)
                            .aspectRatio(1f.div(1f))
                            .background(
                                MaterialTheme.colorScheme.primary,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(CircleShape),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = emptyString
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = preferredEvent.homeTeamName ?: emptyString,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }

            }

            Box(
                modifier = Modifier
                    .weight(1f), contentAlignment = Alignment.Center
            ) {
                preferredEvent.date?.let {
                    preferredEvent.startTimestamp?.let { startTimestamp ->
                        DateTimeView(
                            startTimestamp = startTimestamp.toLong()
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = LocalSpacing.current.noPadding)
                        .background(Color.Transparent, shape = MaterialTheme.shapes.large),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(LocalSpacing.current.extraSmall)
                            .requiredHeight(LocalSpacing.current.large)
                            .requiredWidth(LocalSpacing.current.large)
                            .aspectRatio(1f.div(1f))
                            .background(
                                MaterialTheme.colorScheme.tertiary,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clip(CircleShape),
                        tint = MaterialTheme.colorScheme.onTertiary,
                        painter = painterResource(id = R.drawable.away),
                        contentDescription = emptyString
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = preferredEvent.awayTeamName ?: emptyString,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }

    }


}

