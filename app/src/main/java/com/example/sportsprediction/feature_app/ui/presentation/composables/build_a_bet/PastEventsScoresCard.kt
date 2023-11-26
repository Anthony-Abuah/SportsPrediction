package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants.NotAvailable
import com.example.sportsprediction.core.util.Constants.longDateFormat
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.ListOfTeamEvent
import com.example.sportsprediction.feature_app.ui.presentation.composables.event_info.HeadToHeadScoresInfo
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun PastEventsScoresCard(
    pastEvents: ListOfTeamEvent,
    getOpenStats: (eventId: Int, openStats: Boolean) -> Unit
) {
    val scrollState = rememberScrollState(0)
    var openStats  by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(LocalSpacing.current.small)
            .background(MainBackgroundColor)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        pastEvents.take(10).forEach { event ->
            val eventId = event.eventId ?: nullInteger
            val longFormat = DateTimeFormatter.ofPattern(longDateFormat)
            val timeStamp = event.startTimestamp?.toLong()
            val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp?.let { Instant.ofEpochSecond(it) })
            val date = LocalDate.parse(timeStampAsDateTimeString, longFormat)

            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    openStats = !openStats
                    getOpenStats(eventId, openStats)
                },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
                HeadToHeadScoresInfo(
                    date = "$date",
                    homeTeam = event.homeTeamName ?: NotAvailable,
                    homeScore = event.homeScore?.toString() ?: NotAvailable,
                    awayTeam = event.awayTeamName ?: NotAvailable,
                    awayScore = event.awayScore?.toString() ?: NotAvailable
                )
            }
        }
    }

}