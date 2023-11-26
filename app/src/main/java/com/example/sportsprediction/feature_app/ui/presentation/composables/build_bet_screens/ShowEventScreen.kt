package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet.ShowEventsContent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetFloatingActionButton
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamEventsStatsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamNameEventsViewModel
import java.time.LocalDate

@Composable
fun ShowEventsScreen(
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    teamNameEventsViewModel: TeamNameEventsViewModel = hiltViewModel(),
    teamEventsStatsViewModel: TeamEventsStatsViewModel = hiltViewModel(),
    listOfEvents: ListOfEvents,
    navigateToSuggestionsScreen: () -> Unit,
    navigateBack: () -> Unit
){
    val localDate = LocalDate.now().atStartOfDay().toLocalDate()
    val theDate = localDate.toDate()

    LaunchedEffect(Unit) {
        teamNameEventsViewModel.getTeamsPastEvents(listOfEvents, theDate)
    }

    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                openFilterCard = {
                    dateEventsViewModel.onOpenOrCloseFilterCard()
                    dateEventsViewModel.onCloseSearchCard()
                },
                openSearchCard = {
                    dateEventsViewModel.onOpenOrCloseSearchCard()
                    dateEventsViewModel.onCloseFilterCard()
                },
                navigateBack = navigateBack
            )
        },
        floatingActionButton = {
            BuildBetFloatingActionButton {
                navigateToSuggestionsScreen()
            }
        },
    ){it
        ShowEventsContent(
            isLoading = teamNameEventsViewModel.listOfPastEvents.value.isLoading,
            allTeamEvent = teamNameEventsViewModel.listOfPastEvents.value.listOfTeamEvent,
            getEventStats = {eventId ->
                teamEventsStatsViewModel.getEventStats(eventId)
            },
            isLoadingEventStats = teamEventsStatsViewModel.eventStatsState.value.isLoading,
            eventStats = teamEventsStatsViewModel.eventStatsState.value.teamEventsStats
        )

        Log.d("ShowEventsScreen", "showPastEvents Boolean: ${teamNameEventsViewModel.showPastEvents}")


        /*
        val teamEvents = teamNameEventsViewModel.showTeamPastEventsMessages.value.showTeamsPastEventsMessage
        Log.d("ShowEventsScreen", "isLoading: ${teamNameEventsViewModel.showTeamPastEventsMessages.value.isLoading}")
        teamEvents.forEach { teamEvent ->
            Log.d("ShowEventsScreen", "this team event: ${teamEvent.mainTeamName} vs ${teamEvent.opponentName}")
        }
        */

    }
}