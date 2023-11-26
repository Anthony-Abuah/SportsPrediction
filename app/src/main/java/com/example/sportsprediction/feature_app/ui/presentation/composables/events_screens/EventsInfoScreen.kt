package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventsInfoScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.event_info.EventsInfoContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.EventOddsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.PreferredEventsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamEventsStatsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamNameEventsViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

@Composable
fun EventsInfoScreen(
    teamNameEventsViewModel: TeamNameEventsViewModel = hiltViewModel(),
    teamEventsStatsViewModel: TeamEventsStatsViewModel = hiltViewModel(),
    eventOddsViewModel: EventOddsViewModel = hiltViewModel(),
    preferredEventsViewModel: PreferredEventsViewModel = hiltViewModel(),
    eventId: String,
    headToHeadId: String,
    date: String,
    homeTeamName: String,
    homeTeamId: String,
    awayTeamName: String,
    awayTeamId: String,
    navigateToPredictionsScreen: (mainTeamName: String, mainTeamId: String, opponentTeamName: String, opponentTeamId: String, mainTeamPlayingLocation: String, headToHeadId: String, eventId: String, tournamentInfo: String) -> Unit,
    navigateBack: ()-> Unit
    ){

    val coroutineScope = rememberCoroutineScope()

    val headToHeadEvents by teamNameEventsViewModel.headToHeadEvents.collectAsState(
        initial = emptyList()
    )

    val thePreferredEvent by preferredEventsViewModel.preferredEventEntity.collectAsState(
        initial = EventsEntity(null, Date(), 0,
            emptyString, null,
            emptyString, 0,
            emptyString,  0,  0, 0, null, null, null, null, null, null, null, null, null, null, null, null)
    )

    val localDate = LocalDate.parse(date, shortDateFormatter)
    val newDate = localDate.toDate()


    LaunchedEffect(Unit) {
        teamNameEventsViewModel.getRemoteHomeTeamEvents(
            teamId = homeTeamId.toInt(),
            teamName = homeTeamName,
            date = newDate,
            headToHeadEventId = headToHeadId
        )
        teamNameEventsViewModel.getRemoteAwayTeamEvents(
            teamId = awayTeamId.toInt(),
            teamName = awayTeamName,
            date = newDate,
            headToHeadEventId = headToHeadId
        )
        teamNameEventsViewModel.getHeadToHeadEvents(
            teamId = homeTeamId.toInt(),
            headToHeadEventId = headToHeadId
        )
        /*
        teamNameEventsViewModel.getHomeTeamFormPercentage(
            teamId = homeTeamId.toInt()
        )

        teamNameEventsViewModel.getAwayTeamFormPercentage(
            teamId = awayTeamId.toInt()
        )
        */

        eventOddsViewModel.getRemoteHomeTeamEvents(
            homeTeamId = homeTeamId.toInt(),
            awayTeamId = awayTeamId.toInt(),
            date = newDate,
            eventId = eventId.toInt()
        )

        preferredEventsViewModel.getPreferredEvent(
            eventId = eventId.toInt()
        )
    }

    LaunchedEffect(teamNameEventsViewModel.homeListOfTeamEventState.value.listOfTeamEvent){
        teamNameEventsViewModel.getHomeTeamFormPercentage(
            teamId = homeTeamId.toInt()
        )
    }

    LaunchedEffect(teamNameEventsViewModel.awayListOfTeamEventState.value.listOfTeamEvent){
        teamNameEventsViewModel.getAwayTeamFormPercentage(
            teamId = awayTeamId.toInt()
        )
    }

    Scaffold(
        topBar = {
            EventsInfoScreenTopBar {
                navigateBack()
            }
        },
        content = { it
                EventsInfoContent(
                    homeTeam = homeTeamName,
                    homeTeamId = homeTeamId,
                    awayTeam = awayTeamName,
                    awayTeamId = awayTeamId,
                    eventId = eventId,
                    headToHeadId = headToHeadId,
                    preferredEvent = thePreferredEvent,
                    isLoadingHomeTeamStats = teamEventsStatsViewModel.listOfHomeTeamEventsStatsState.value.isLoading,
                    homeTeamStatsHaveBeenLoaded = teamEventsStatsViewModel.listOfHomeTeamEventsStatsState.value.listOfAllTeamEventsStats.isNotEmpty(),
                    loadHomeTeamStats = { mainTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfHeadToHeadEvents->
                        coroutineScope.launch {
                            teamEventsStatsViewModel.loadHomeTeamEventsStats(
                                mainTeamId,
                                headToHeadId,
                                eventId,
                                date,
                                numberOfPastEvents,
                                numberOfHeadToHeadEvents
                            )
                        }
                    },
                    isLoadingAwayTeamStats = teamEventsStatsViewModel.listOfAwayTeamEventsStatsState.value.isLoading,
                    awayTeamStatsHaveBeenLoaded = teamEventsStatsViewModel.listOfAwayTeamEventsStatsState.value.listOfAllTeamEventsStats.isNotEmpty(),
                    loadAwayTeamStats = { mainTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfHeadToHeadEvents->
                        coroutineScope.launch {
                            teamEventsStatsViewModel.loadAwayTeamEventsStats(
                                mainTeamId,
                                headToHeadId,
                                eventId,
                                date,
                                numberOfPastEvents,
                                numberOfHeadToHeadEvents
                            )
                        }
                    },
                    date = newDate,
                    navigateToPredictionsScreen = navigateToPredictionsScreen,
                    loadingHomeTeamEvents = teamNameEventsViewModel.homeListOfTeamEventState.value.isLoading,
                    headToHeadEvents = headToHeadEvents,
                    homeFormPercentage = teamNameEventsViewModel.homeTeamFormPercentage.value.formPercentage,
                    awayFormPercentage = teamNameEventsViewModel.awayTeamFormPercentage.value.formPercentage,
                    homeTeamNameEvents = teamNameEventsViewModel.homeListOfTeamEventState.value.listOfTeamEvent,
                    loadingAwayTeamEvents = teamNameEventsViewModel.awayListOfTeamEventState.value.isLoading,
                    awayTeamNameEvents = teamNameEventsViewModel.awayListOfTeamEventState.value.listOfTeamEvent,
                    eventOdds = eventOddsViewModel.listOfEventOddsStateState.value.listOfAllEventOdds
                )

        }
    )
}