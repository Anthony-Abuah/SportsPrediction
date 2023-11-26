package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.ListOfTeamEvent
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun ShowEventsContent(
    isLoading: Boolean,
    allTeamEvent: ListOfTeamEvent,
    getEventStats: (eventId: Int)-> Unit,
    eventStats: EventStatsEntity?,
    isLoadingEventStats: Boolean
){
    val eachTeamEvent = allTeamEvent.groupBy { it.mainTeamName }
    val scrollState = rememberScrollState(0)

    val thisEventStats = eventStats ?: EventStatsEntity(
        nullInteger, null, nullInteger, null, null, null, null, null, null, null, null, null, null, null, null, null, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble, nullDouble)

    var showEventStats by remember {
        mutableStateOf(false)
    }
    var eventId by remember {
        mutableStateOf(nullInteger)
    }

    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.small)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                eachTeamEvent.keys.forEach { teamName ->
                    val pastEvents = eachTeamEvent[teamName]

                    pastEvents?.forEach { pastEvent ->
                        Log.d(
                            "Show Events Content",
                            "past Events: $teamName : ${pastEvent.mainTeamName} vs ${pastEvent.opponentName} "
                        )
                    } ?: Log.d("Show Events Content", "past Events: Is null")

                    if (pastEvents != null && pastEvents.isEmpty())
                        Log.d("Show Events Content", "past Events: Is empty ")

                    ShowEventsCard(
                        teamName = teamName,
                        pastEvents = pastEvents ?: emptyList(),
                        openStats = { _eventId, _showEventStats ->
                            showEventStats = _showEventStats
                            eventId = _eventId
                            getEventStats(eventId)
                        }
                    )

                }
            }
        }

        AnimatedVisibility(visible = showEventStats) {
            Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.BottomCenter) {
                EventStatsCard(
                    eventStats = thisEventStats,
                    isLoadingEventStats = isLoadingEventStats,
                    closeStats = { _showEventStats ->
                        showEventStats = _showEventStats
                    }
                )
            }
        }
    }
}
