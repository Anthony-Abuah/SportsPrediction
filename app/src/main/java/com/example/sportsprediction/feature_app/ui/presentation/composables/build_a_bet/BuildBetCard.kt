package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
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
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.date_events.DateTimeView
import com.example.sportsprediction.feature_app.ui.theme.*





@Composable
fun BuildBetCard(
    preferredEvent: EventsEntity,
    selectedEvents: MutableList<EventsEntity>,
    getSelectedEvents: (selectedEvents: MutableList<EventsEntity>) -> Unit,
) {

    var thisEventIsSelected by remember {
        mutableStateOf(selectedEvents.contains(preferredEvent))
    }
    var thisSelectedEvents by remember {
        mutableStateOf(selectedEvents)
    }
    LaunchedEffect(selectedEvents){
        thisEventIsSelected = selectedEvents.contains(preferredEvent)
        thisSelectedEvents = selectedEvents
    }


    val roundName = preferredEvent.roundInfo?.name ?: Constants.emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: Constants.nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == Constants.nullInteger) {
        Constants.emptyString
    } else if (roundName.isNotEmpty() && roundNumber == Constants.nullInteger) {
        ", $roundName"
    } else if (roundName.isEmpty() && roundNumber != Constants.nullInteger) {
        ", Round $roundNumber"
    } else {
        ", $roundName, Round $roundNumber"
    }

    val sport = if (preferredEvent.tournament?.sport.isNullOrBlank()) {
        Constants.emptyString
    } else preferredEvent.tournament?.sport
    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) Constants.emptyString else {
        ", ${preferredEvent.tournamentName}"
    }
    val country = if (preferredEvent.tournament?.categoryName.isNullOrBlank()) Constants.emptyString else {
        ", ${preferredEvent.tournament?.categoryName}"
    }

    val backgroundColor = if (thisEventIsSelected){
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    val contentColor = if (thisEventIsSelected){
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }


    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small)
            .toggleable(value = thisEventIsSelected,
                onValueChange = { isSelected ->
                    thisSelectedEvents = thisSelectedEvents.toSet().toMutableList()
                    if (thisSelectedEvents.contains(preferredEvent)){
                        thisSelectedEvents.remove(preferredEvent)
                    }
                    else { thisSelectedEvents.add(preferredEvent) }
                    thisSelectedEvents = thisSelectedEvents.toSet().toMutableList()
                    getSelectedEvents(thisSelectedEvents)
                    thisEventIsSelected = thisSelectedEvents.contains(preferredEvent)
                }
            ),
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
                    contentDescription = Constants.emptyString,
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
                    color = contentColor,
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
                        contentDescription = Constants.emptyString
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = preferredEvent.homeTeamName ?: Constants.emptyString,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor,
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
                        contentDescription = Constants.emptyString
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = preferredEvent.awayTeamName ?: Constants.emptyString,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = contentColor,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }

            }

        }

    }

}








