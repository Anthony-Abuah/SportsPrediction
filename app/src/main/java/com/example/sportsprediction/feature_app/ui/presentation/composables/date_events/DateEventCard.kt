package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.DateEventTeamNameText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamTournamentText
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
    val dateToLocalDate = preferredEvent.date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val dateString = shortDateFormatter.format(dateToLocalDate)


    val roundName = preferredEvent.roundInfo?.name ?: emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == nullInteger){ emptyString }
    else if (roundName.isNotEmpty() && roundNumber == nullInteger){", $roundName"}
    else if (roundName.isEmpty() && roundNumber != nullInteger){", Round $roundNumber"}
    else {", $roundName, Round $roundNumber"}

    val sport = if(preferredEvent.tournament?.sport.isNullOrBlank()){emptyString} else  preferredEvent.tournament?.sport
    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournamentName}"}
    val country = if(preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournament?.categoryName}"}


    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),

        onClick = {navigateToPredictionsScreen("${preferredEvent.eventId ?: nullInteger}",
            preferredEvent.headToHeadId ?: emptyString,
            dateString ?: emptyString,
            preferredEvent.homeTeamName ?: emptyString,
            "${preferredEvent.homeTeamId ?: nullInteger}",
            preferredEvent.awayTeamName ?: emptyString,
            "${preferredEvent.awayTeamId ?: nullInteger}"
        ) },
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.smallMedium)
    ) {

        Row(modifier = Modifier.padding(LocalSpacing.current.extraSmall),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){

            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                Icon(painter = painterResource(id = R.drawable.football), contentDescription = emptyString)
            }
            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                EventTeamTournamentText(text = "$sport$country$tournamentName$roundInfo")
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall)) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                DateEventTeamNameText(text = preferredEvent.homeTeamName ?: emptyString)
            }
            Spacer(modifier = Modifier.weight(1.25f))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                DateEventTeamNameText(text = preferredEvent.awayTeamName ?: emptyString)
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = LocalSpacing.current.small)) {
            Box(modifier = Modifier
                .weight(1f)
                .background(Color.Transparent), contentAlignment = Alignment.Center){
                Icon(modifier = Modifier.padding(top = LocalSpacing.current.extraSmall)
                    .requiredHeight(LocalSpacing.current.large)
                    .requiredWidth(LocalSpacing.current.large)
                    .background(TeamEmblemColor, shape = MaterialTheme.shapes.medium),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = emptyString)
            }
            Box(modifier = Modifier
                .weight(1.25f)
                .background(Color.Transparent), contentAlignment = Alignment.Center){
                preferredEvent.date?.let {
                    preferredEvent.startTimestamp?.let { startTimestamp ->
                        DateTimeView(
                            startTimestamp = startTimestamp.toLong()
                        )
                    }
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .background(Color.Transparent, shape = MaterialTheme.shapes.large), contentAlignment = Alignment.Center){
                Icon(modifier = Modifier.padding(top = LocalSpacing.current.extraSmall)
                    .requiredHeight(LocalSpacing.current.large)
                    .requiredWidth(LocalSpacing.current.large)
                    .background(TeamEmblemColor, shape = MaterialTheme.shapes.medium),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.away),
                    contentDescription = emptyString)
            }
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildBetEventCard(
    preferredEvent: EventsEntity,
    selectedPreferredEvents: MutableList<EventsEntity>,
    getPreferredEvent: (MutableList<EventsEntity>) -> Unit
) {

    val shortDateFormatter = DateTimeFormatter.ofPattern(Constants.shortDateFormat)
    val dateToLocalDate = preferredEvent.date?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val dateString = shortDateFormatter.format(dateToLocalDate)


    var isSelected by mutableStateOf(selectedPreferredEvents.contains(preferredEvent))
    val thisSelectedPreferredEvents by mutableStateOf(selectedPreferredEvents)


    val roundName = preferredEvent.roundInfo?.name ?: emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == nullInteger){ emptyString }
    else if (roundName.isNotEmpty() && roundNumber == nullInteger){", $roundName"}
    else if (roundName.isEmpty() && roundNumber != nullInteger){", Round $roundNumber"}
    else {", $roundName, Round $roundNumber"}

    val sport = if(preferredEvent.tournament?.sport.isNullOrBlank()){emptyString} else  preferredEvent.tournament?.sport
    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournamentName}"}
    val country = if(preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournament?.categoryName}"}

    val backgroundColor = if(isSelected) Color.LightGray else Color.White
    val cardElevation = if(isSelected)  LocalSpacing.current.medium else  LocalSpacing.current.small

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),

        onClick = {
            isSelected =! isSelected
            if (isSelected) {
                thisSelectedPreferredEvents.add(preferredEvent)
                getPreferredEvent(thisSelectedPreferredEvents)
            }
            else{
                thisSelectedPreferredEvents.remove(preferredEvent)
                getPreferredEvent(thisSelectedPreferredEvents)
            }
        },
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {

        Row(modifier = Modifier.padding(LocalSpacing.current.extraSmall),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){

            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                Icon(painter = painterResource(id = R.drawable.football), contentDescription = emptyString)
            }
            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                EventTeamTournamentText(text = "$sport$country$tournamentName$roundInfo")
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall)) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                DateEventTeamNameText(text = preferredEvent.homeTeamName ?: emptyString)
            }
            Spacer(modifier = Modifier.weight(1.25f))
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                DateEventTeamNameText(text = preferredEvent.awayTeamName ?: emptyString)
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = LocalSpacing.current.small)) {
            Box(modifier = Modifier
                .weight(1f)
                .background(Color.Transparent), contentAlignment = Alignment.Center){
                Icon(modifier = Modifier.padding(top = LocalSpacing.current.extraSmall)
                    .requiredHeight(LocalSpacing.current.large)
                    .requiredWidth(LocalSpacing.current.large)
                    .background(TeamEmblemColor, shape = MaterialTheme.shapes.medium),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = emptyString)
            }
            Box(modifier = Modifier
                .weight(1.25f)
                .background(Color.Transparent), contentAlignment = Alignment.Center){
                preferredEvent.date?.let {
                    preferredEvent.startTimestamp?.let { startTimestamp ->
                        DateTimeView(
                            startTimestamp = startTimestamp.toLong()
                        )
                    }
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .background(Color.Transparent, shape = MaterialTheme.shapes.large), contentAlignment = Alignment.Center){
                Icon(modifier = Modifier.padding(top = LocalSpacing.current.extraSmall)
                    .requiredHeight(LocalSpacing.current.large)
                    .requiredWidth(LocalSpacing.current.large)
                    .background(TeamEmblemColor, shape = MaterialTheme.shapes.medium),
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.away),
                    contentDescription = emptyString)
            }
        }

    }


}