package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.longDateFormat
import com.example.sportsprediction.core.util.Functions.getMatchResult
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun PreviousEventCard(
    isLoading: Boolean,
    backgroundColor: Color,
    teamNameEvents: List<TeamEventEntity>
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = backgroundColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.smallMedium)
    ) {
        if (isLoading){
            Box(modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(250.dp),
            contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(modifier = Modifier
                    .requiredHeight(50.dp)
                    .requiredWidth(50.dp))
            }
        }
        else {
            teamNameEvents.forEach { teamNameEvent->
                val timeStamp = teamNameEvent.startTimestamp?.toLong()
                val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })

                val longFormat = DateTimeFormatter.ofPattern(longDateFormat)
                val date = LocalDate.parse(timeStampAsDateTimeString, longFormat)

                EventScoresInfo(
                    date = "$date",
                    homeTeam = teamNameEvent.homeTeamName ?: NotAvailable.lowercase(Locale.getDefault()),
                    homeScore = "${teamNameEvent.homeScore ?: NotAvailable.lowercase(Locale.getDefault())}",
                    awayTeam = teamNameEvent.awayTeamName ?: NotAvailable.lowercase(Locale.getDefault()),
                    awayScore = "${teamNameEvent.awayScore ?: NotAvailable.lowercase(Locale.getDefault())}",
                    result = getMatchResult(teamNameEvent.mainTeamPlayingLocation, teamNameEvent.homeScore, teamNameEvent.awayScore)
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