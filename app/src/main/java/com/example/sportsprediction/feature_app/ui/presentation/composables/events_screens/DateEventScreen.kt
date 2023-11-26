package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.date_events.DateEventContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel

@Composable
fun DateEventsScreen(
    navController: NavHostController,
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    navigateToUserPreferencesScreen: () -> Unit,
    navigateToEventsInfoScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit
){
    LaunchedEffect(Unit) {
        val theDate = dateEventsViewModel.theDate
        dateEventsViewModel.getPreferredDateEvents(theDate)
    }


    Scaffold(

        bottomBar = {
            BottomBar(navHostController = navController)
        },
        content = { it
                DateEventContent(
                    preferredEvents = dateEventsViewModel.preferredEventState.value.preferredEvents,
                    searchedEvents = dateEventsViewModel.searchedEventState.value.preferredEvents,
                    matchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.preferredEvents,
                    filteredTournamentEvents = dateEventsViewModel.filteredTournamentsEventState.value.preferredEvents,
                    sortedEvents = dateEventsViewModel.sortEventState.value.preferredEvents,
                    isLoading = dateEventsViewModel.preferredEventState.value.isLoading,
                    isLoadingFilteredTournamentsEvents = dateEventsViewModel.filteredTournamentsEventState.value.isLoading,
                    isLoadingSearchedEvents = dateEventsViewModel.searchedEventState.value.isLoading,
                    isLoadingMatchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.isLoading,
                    isLoadingSortedEvents = dateEventsViewModel.sortEventState.value.isLoading,
                    thereIsError = dateEventsViewModel.thereIsError,
                    errorMessage = dateEventsViewModel.errorMessage,
                    openFilterCard = dateEventsViewModel.openFilterCard,
                    openSearchCard = dateEventsViewModel.openSearchCard,
                    openOrCloseFilterCard = {dateEventsViewModel.onOpenOrCloseFilterCard()},
                    openOrCloseSearchCard = {dateEventsViewModel.onOpenOrCloseSearchCard()},
                    closeFilterCard = {dateEventsViewModel.onCloseFilterCard()},
                    closeSearchCard = {dateEventsViewModel.onCloseSearchCard()},
                    getSearchedPreferredEvent = {preferredEvents, value ->
                        dateEventsViewModel.getSearchedPreferredEvents(preferredEvents, value)
                    },
                    getSortedPreferredEvent = {preferredEvents, value ->
                        dateEventsViewModel.getSortedPreferredEvents(preferredEvents, value)
                    },
                    getMatchStartTimePreferredEvent = {preferredEvents, value ->
                        dateEventsViewModel.getMatchTimePreferredEvents(preferredEvents, value)
                    },
                    getFilteredTournamentsPreferredEvent = {preferredEvents, value ->
                        dateEventsViewModel.getFilteredTournamentsPreferredEvents(preferredEvents, value)
                    },
                    getDate = {date ->
                        dateEventsViewModel.getPreferredDateEvents(date)
                        dateEventsViewModel.selectedDate(date)
                    },
                    navigateToEventsInfoScreen = navigateToEventsInfoScreen,
                    navigateToUserPreferencesScreen = navigateToUserPreferencesScreen
                )


        }

    )
}