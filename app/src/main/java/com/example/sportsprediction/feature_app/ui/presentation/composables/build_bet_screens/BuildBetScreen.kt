package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet.BuildBetContent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetFloatingActionButton1
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel
import java.time.LocalDate

@Composable
fun BuildBetScreen(
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    navController: NavHostController,
    navigateToShowEventsScreen: (selectedEvents: ListOfEvents) -> Unit,
    navigateBack: () -> Unit
){
    var selectedEvents by remember { mutableStateOf((emptyList<EventsEntity>())) }
    val localDate = LocalDate.now().atStartOfDay().toLocalDate()
    val theDate = localDate.toDate()

    LaunchedEffect(Unit) {
        dateEventsViewModel.getPreferredDateEvents(theDate)
    }

    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                label = "Build A Bet",
                openSearchCard = {
                    dateEventsViewModel.onOpenOrCloseSearchCard()
                    dateEventsViewModel.onCloseFilterCard()
                },
                openFilterCard = {
                    dateEventsViewModel.onOpenOrCloseFilterCard()
                    dateEventsViewModel.onCloseSearchCard()
                },
                navigateBack = navigateBack
            )
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
        floatingActionButton = {
            BuildBetFloatingActionButton1 {
                navigateToShowEventsScreen(selectedEvents)
            }
        },
    ){it
        BuildBetContent(
            preferredEvents = dateEventsViewModel.preferredEventState.value.preferredEvents,
            matchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.preferredEvents,
            searchedEvents = dateEventsViewModel.searchedEventState.value.preferredEvents,
            sortedEvents = dateEventsViewModel.sortEventState.value.preferredEvents,
            filteredTournamentEvents = dateEventsViewModel.filteredTournamentsEventState.value.preferredEvents,
            isLoadingPreferredEvents = dateEventsViewModel.preferredEventState.value.isLoading,
            isLoadingSearchedEvents = dateEventsViewModel.searchedEventState.value.isLoading,
            isLoadingMatchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.isLoading,
            isLoadingSortedEvents = dateEventsViewModel.sortEventState.value.isLoading,
            isLoadingFilteredTournamentsEvents = dateEventsViewModel.filteredTournamentsEventState.value.isLoading,
            thereIsError = dateEventsViewModel.thereIsError,
            errorMessage = dateEventsViewModel.errorMessage,
            openFilterCard = dateEventsViewModel.openFilterCard,
            openSearchCard = dateEventsViewModel.openSearchCard,
            build_a_bet = dateEventsViewModel.buildBet,
            closeFilterCard = {dateEventsViewModel.onCloseFilterCard()},
            closeSearchCard = {dateEventsViewModel.onCloseSearchCard()},
            getSelectedEventsEntity = {theSelectedEvents ->
                selectedEvents = theSelectedEvents
            },
            getMatchStartTimePreferredEvent = {preferredEvents, value ->
                dateEventsViewModel.getMatchTimePreferredEvents(preferredEvents, value)
            },
            getFilteredTournamentsPreferredEvent = {preferredEvents, value ->
                dateEventsViewModel.getFilteredTournamentsPreferredEvents(preferredEvents, value)
            },
            getSearchedPreferredEvent = {preferredEvents, value ->
                dateEventsViewModel.getSearchedPreferredEvents(preferredEvents, value)
            },
        ) { preferredEvents, value ->
            dateEventsViewModel.getSortedPreferredEvents(preferredEvents, value)
        }

    }
}