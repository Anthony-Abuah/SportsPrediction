package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet.BuildBetContent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamEventsStatsViewModel
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@Composable
fun BuildBetScreen(
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    teamEventsStatsViewModel: TeamEventsStatsViewModel = hiltViewModel(),
    navController: NavHostController,
    navigateToShowEventsScreen: (selectedEvents: ListOfEvents) -> Unit,
    navigateBack: () -> Unit
){
    val scaffoldState = rememberScaffoldState()

    var numberOfSelectedEvents by remember { mutableStateOf(0) }
    val localDate = LocalDate.now().atStartOfDay().toLocalDate()
    val theDate = localDate.toDate()

    LaunchedEffect(Unit) {
        dateEventsViewModel.getPreferredDateEvents(theDate)
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
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomBar(navHostController = navController)
        },
        floatingActionButton = {
            /*
            AnimatedVisibility(numberOfSelectedEvents > 0) {
                BuildBetFloatingActionButton1(numberOfEvents = numberOfSelectedEvents.toString()) {}
            }
            */
        },
        /*
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false*/
    ){it
        BuildBetContent(
            preferredEvents = dateEventsViewModel.preferredEventState.value.preferredEvents,
            matchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.preferredEvents,
            searchedEvents = dateEventsViewModel.searchedEventState.value.preferredEvents,
            sortedEvents = dateEventsViewModel.sortEventState.value.preferredEvents,
            filteredTournamentEvents = dateEventsViewModel.filteredTournamentsEventState.value.preferredEvents,
            isLoading = dateEventsViewModel.preferredEventState.value.isLoading,
            isLoadingSearchedEvents = dateEventsViewModel.searchedEventState.value.isLoading,
            isLoadingMatchStartTimeEvents = dateEventsViewModel.matchStartTimeEventState.value.isLoading,
            isLoadingSortedEvents = dateEventsViewModel.sortEventState.value.isLoading,
            isLoadingFilteredTournamentsEvents = dateEventsViewModel.filteredTournamentsEventState.value.isLoading,
            filterCardIsOpened = dateEventsViewModel.openFilterCard,
            searchCardIsOpened = dateEventsViewModel.openSearchCard,
            closeFilterCard = {dateEventsViewModel.onCloseFilterCard()},
            closeSearchCard = {dateEventsViewModel.onCloseSearchCard()},
            isLoadingStats = teamEventsStatsViewModel.isLoadingStats,
            loadingStatsMessage = teamEventsStatsViewModel.statsLoadingMessage,
            loadStats = {statParameters->
                teamEventsStatsViewModel.loadSelectedTeamEventsStats(statParameters)
            },
            getNumberOfSelectedEvents = {_numberOfSelectedEvents->
                numberOfSelectedEvents = _numberOfSelectedEvents
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