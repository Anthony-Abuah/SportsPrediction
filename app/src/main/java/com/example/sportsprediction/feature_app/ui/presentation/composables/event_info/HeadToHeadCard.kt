package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sportsprediction.core.util.Constants.longDateFormat
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HeadToHeadCard(
    teamNameEvents: List<TeamEventEntity>
) {

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.smallMedium)
    ) {
        teamNameEvents.forEach {teamNameEvent->
            val timeStamp = teamNameEvent.startTimestamp?.toLong()
            val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })

            val longFormat = DateTimeFormatter.ofPattern(longDateFormat)
            val date = LocalDate.parse(timeStampAsDateTimeString, longFormat)

            HeadToHeadScoresInfo(
                date = "$date",
                homeTeam = teamNameEvent.homeTeamName!!,
                homeScore = teamNameEvent.homeScore!!.toInt().toString(),
                awayTeam = teamNameEvent.awayTeamName!!,
                awayScore = teamNameEvent.awayScore!!.toInt().toString()
            )

            Divider(modifier = Modifier.padding(start = LocalSpacing.current.extraSmall, end = LocalSpacing.current.extraSmall))
        }

    }

}