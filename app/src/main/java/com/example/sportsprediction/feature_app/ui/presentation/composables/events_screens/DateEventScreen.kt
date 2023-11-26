package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.DateEventHeader
import com.example.sportsprediction.feature_app.ui.presentation.composables.date_events.DateEventContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DateEventsScreen(
    navController: NavHostController,
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    navigateToUserPreferencesScreen: () -> Unit,
    navigateToEventsInfoScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit
){
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        val theDate = dateEventsViewModel.theDate
        dateEventsViewModel.getRemoteGroupedDateEvents(theDate)
    }

    LaunchedEffect(key1 = true){
        dateEventsViewModel.eventFlow.collectLatest { eventFlow->
            when (eventFlow){
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = eventFlow.message
                    )
                }
            }
        }
    }

    Scaffold(

        bottomBar = {
            BottomBar(navHostController = navController)
        },
        scaffoldState = scaffoldState,
        topBar = {
             DateEventHeader(openFilterCard = {
                 dateEventsViewModel.onOpenOrCloseFilterCard()
                 dateEventsViewModel.onCloseSearchCard()
             }, openSearchCard = {
                 dateEventsViewModel.onOpenOrCloseSearchCard()
                 dateEventsViewModel.onCloseFilterCard()
             }) {
                 navigateToUserPreferencesScreen()
             }
        },
        content = { it
            LaunchedEffect(dateEventsViewModel.groupedEventState.value.groupedEvents ){
                dateEventsViewModel.changeToGroupedCountryEvents()
            }
            DateEventContent(
                groupedEvents = dateEventsViewModel.filteredTournamentGroupedEventState.value.groupedEvents,
                groupedCountryEvents = dateEventsViewModel.groupedCountryEvents,
                isLoading = dateEventsViewModel.filteredTournamentGroupedEventState.value.isLoading,
                filterCardIsOpened = dateEventsViewModel.openFilterCard,
                searchCardIsOpened = dateEventsViewModel.openSearchCard,
                selectedDate = dateEventsViewModel.theDate,
                closeFilterCard = {dateEventsViewModel.onCloseFilterCard()},
                closeSearchCard = {dateEventsViewModel.onCloseSearchCard()},
                getDate = {date ->
                    dateEventsViewModel.getRemoteGroupedDateEvents(date)
                    dateEventsViewModel.selectedDate(date)
                },
                getMatchStartTimePreferredEvent = {value ->
                    dateEventsViewModel.getMatchTimePreferredEvents(value)
                },
                getFilteredTournamentsPreferredEvent = {value ->
                    dateEventsViewModel.getFilteredTournamentsPreferredEvents(value)
                },
                getSearchedPreferredEvent = {value ->
                    dateEventsViewModel.getSearchedPreferredEvents(value)
                },
                getSortedPreferredEvent = {value ->
                    dateEventsViewModel.getSortedPreferredEvents(value)
                },
                navigateToEventsInfoScreen = navigateToEventsInfoScreen
            )
        }

    )
}