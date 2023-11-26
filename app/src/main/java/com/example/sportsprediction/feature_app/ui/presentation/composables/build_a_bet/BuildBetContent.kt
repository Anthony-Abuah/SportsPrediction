package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.UnknownCountry
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.model.general.LoadStatsParameters
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.*
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildBetContent(
    preferredEvents: List<EventsEntity>,
    matchStartTimeEvents: List<EventsEntity>,
    searchedEvents: List<EventsEntity>,
    sortedEvents: List<EventsEntity>,
    filteredTournamentEvents: List<EventsEntity>,
    isLoading: Boolean,
    isLoadingSearchedEvents: Boolean,
    isLoadingMatchStartTimeEvents: Boolean,
    isLoadingSortedEvents: Boolean,
    isLoadingStats: Boolean,
    isLoadingFilteredTournamentsEvents: Boolean,
    filterCardIsOpened: Boolean,
    searchCardIsOpened: Boolean,
    loadingStatsMessage: String,
    closeFilterCard: () -> Unit,
    closeSearchCard: () -> Unit,
    getNumberOfSelectedEvents: (numberOfSelectedEvents: Int) -> Unit,
    loadStats: (listOfParameters: List<LoadStatsParameters>) -> Unit,
    getMatchStartTimePreferredEvent: (preferredEvents: List<EventsEntity>, value: Long) -> Unit,
    getFilteredTournamentsPreferredEvent: (preferredEvents: List<EventsEntity>, value: Map<String, String>) -> Unit,
    getSearchedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,
    getSortedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,

    ) {
    val showSelectedEventsSheetState = rememberModalBottomSheetState()

    var leaguesSelectionDialogIsOpened by remember { mutableStateOf(false) }
    var sortFilterValueChanges by remember { mutableStateOf(false) }
    var matchStartTimeValueChanges by remember { mutableStateOf(false) }
    var searchValueChanges by remember { mutableStateOf(false) }
    var filteredTournamentsValueChanges by remember { mutableStateOf(false) }
    var matchStartTimeDialogIsOpened by remember { mutableStateOf(false) }
    var sortEventsDialogIsOpened by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf(emptyString) }
    var tournaments by remember { mutableStateOf(emptyMap<String, String>()) }

    var numberOfSelectedEvents by remember { mutableStateOf(0) }
    var selectedEvents by remember { mutableStateOf(emptyList<EventsEntity>()) }

    var openSelectedEvents by remember { mutableStateOf(false) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val groupedCountryEvents = if (sortedEvents.isNotEmpty()) {
        sortedEvents.groupBy { it.country ?: UnknownCountry }
    }
    else if (matchStartTimeEvents.isNotEmpty()){
        matchStartTimeEvents.groupBy { it.country ?: UnknownCountry }
    }
    else if (searchedEvents.isNotEmpty()){
        searchedEvents.groupBy { it.country ?: UnknownCountry }
    }
    else preferredEvents.groupBy { it.country ?: UnknownCountry }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
    ) {
        /*
        if (searchCardIsOpened) {

            SearchCard(
                thisSearchValue = searchValue,
                preferredEvents = preferredEvents,
                filteredTournamentEvents = filteredTournamentEvents,
                matchStartTimeEvents = matchStartTimeEvents,
                sortedEvents = sortedEvents,
                coroutineScope = coroutineScope,
                getSearchValue = { _searchValue ->
                    searchValue = _searchValue

                    // set the other filter values to false and set search value change to true
                    // this is to let the lazy column select which events to display
                    searchValueChanges = true
                    matchStartTimeValueChanges = false
                    filteredTournamentsValueChanges = false
                    sortFilterValueChanges = false
                },
                getSearchedPreferredEvent = {_preferredEvent, _searchValue ->
                    getSearchedPreferredEvent(_preferredEvent, _searchValue)
                },
                closeSearchCard = closeSearchCard
            )

        }
        */

        if (filterCardIsOpened) {
            FilterCard(
                matchStartTimeDialogIsOpened = matchStartTimeDialogIsOpened,
                leaguesSelectionDialogIsOpened = leaguesSelectionDialogIsOpened,
                sortEventsDialogIsOpened = sortEventsDialogIsOpened,
                onClickMatchStartTime = {
                    // When we click on the match start time to open the dialog, we want all the other filter dialogs to close
                    matchStartTimeDialogIsOpened = !matchStartTimeDialogIsOpened
                    leaguesSelectionDialogIsOpened = false
                    sortEventsDialogIsOpened = false
                },
                onClickLeagues = {
                    // When we click on the league selection to open the dialog, we want all the other filter dialogs to close
                    leaguesSelectionDialogIsOpened = !leaguesSelectionDialogIsOpened
                    matchStartTimeDialogIsOpened = false
                    sortEventsDialogIsOpened = false
                },
                onClickSortOrder = {
                    // When we click on the sort order to open the dialog, we want all the other filter dialogs to close
                    sortEventsDialogIsOpened = !sortEventsDialogIsOpened
                    matchStartTimeDialogIsOpened = false
                    leaguesSelectionDialogIsOpened = false
                }
            )

        }

        /*
        if (matchStartTimeDialogIsOpened) {
            MatchStartTimeDialog(
                preferredEvents = preferredEvents,
                filteredTournamentEvents = filteredTournamentEvents,
                searchedEvents = searchedEvents,
                sortedEvents = sortedEvents,
                coroutineScope = coroutineScope,
                getMatchStartTimePreferredEvent = {preferredEvents, value->
                    getMatchStartTimePreferredEvent (preferredEvents, value)

                    matchStartTimeValueChanges = true
                    filteredTournamentsValueChanges = false
                    sortFilterValueChanges = false
                    searchValueChanges = false


                    matchStartTimeDialogIsOpened = false
                    leaguesSelectionDialogIsOpened = false
                    sortEventsDialogIsOpened = false
                }
            )
        }

        if (sortEventsDialogIsOpened) {

            SortEventsDialog(
                preferredEvents = preferredEvents,
                filteredTournamentEvents = filteredTournamentEvents,
                searchedEvents = searchedEvents,
                matchStartTimeEvents = matchStartTimeEvents,
                coroutineScope = coroutineScope,
                getSortedPreferredEvent = {preferredEvents, _sortFilter ->
                    getSortedPreferredEvent(preferredEvents, _sortFilter)

                    sortFilterValueChanges = true
                    matchStartTimeValueChanges = false
                    filteredTournamentsValueChanges = false
                    searchValueChanges = false

                    sortEventsDialogIsOpened = false
                    matchStartTimeDialogIsOpened = false
                    leaguesSelectionDialogIsOpened = false
                }
            )

        }
        */

        /*
        if (leaguesSelectionDialogIsOpened) {

            LeaguesSelectionDialog(
                groupedCountryEvents = groupedCountryEvents,
                theSelectedTournaments = tournaments,
                preferredEvents = preferredEvents,
                sortedEvents = sortedEvents,
                searchedEvents = searchedEvents,
                matchStartTimeEvents = matchStartTimeEvents,
                coroutineScope = coroutineScope,
                closeFilterCard = closeFilterCard,
                closeLeaguesSelectionDialog = { leaguesSelectionDialogIsOpened = false },
                getFilteredTournamentsPreferredEvent = {preferredEvents, _tournament ->
                    tournaments = _tournament
                    getFilteredTournamentsPreferredEvent(preferredEvents, _tournament)

                    filteredTournamentsValueChanges = true
                    matchStartTimeValueChanges = false
                    sortFilterValueChanges = false
                    searchValueChanges = false
                }
            )

        }
        */

        Box(modifier = Modifier.fillMaxWidth(1f),
            contentAlignment = Alignment.TopCenter
        ) {

            val showPreferredEvents = if(filteredTournamentsValueChanges){
                filteredTournamentEvents
            }
            else if (matchStartTimeValueChanges){
                matchStartTimeEvents
            }
            else if (sortFilterValueChanges){
                sortedEvents
            }
            else if (searchValueChanges){
                searchedEvents
            }
            else preferredEvents

            Spacer(modifier = Modifier.height(LocalSpacing.current.default))

            if (isLoading || leaguesSelectionDialogIsOpened || sortEventsDialogIsOpened || matchStartTimeDialogIsOpened || isLoadingFilteredTournamentsEvents || isLoadingSortedEvents || isLoadingMatchStartTimeEvents || isLoadingSearchedEvents) {
                Box(modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .clickable {
                        closeFilterCard()
                        leaguesSelectionDialogIsOpened = false
                        sortEventsDialogIsOpened = false
                        matchStartTimeDialogIsOpened = false
                    },
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }

            else {
                if (showPreferredEvents.isEmpty()){
                    Column(modifier = Modifier.fillMaxSize()) {
                        AnimatedVisibility(visible = true) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        closeFilterCard()
                                        closeSearchCard()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = Constants.NothingToShow,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                else {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        BuildBetEventCard(
                            selectedEvents = selectedEvents,
                            groupedEvents = showPreferredEvents.groupBy { "${it.country}, ${it.tournamentName}" },
                        ) { _selectedEvents ->
                            selectedEvents = _selectedEvents
                            numberOfSelectedEvents = _selectedEvents.size
                            getNumberOfSelectedEvents(numberOfSelectedEvents)
                        }
                        Column(modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = LocalSpacing.current.small)
                        ){
                            AnimatedVisibility(visible = numberOfSelectedEvents > 0) {
                                Column(
                                    modifier = Modifier
                                        .background(Blue95)
                                        .wrapContentSize()
                                        .clickable { openSelectedEvents = !openSelectedEvents }
                                        .padding(vertical = LocalSpacing.current.small),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(Red60, CircleShape)
                                            .width(LocalSpacing.current.large)
                                            .aspectRatio(1f)
                                            .padding(vertical = LocalSpacing.current.noPadding),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(LocalSpacing.current.small),
                                            text = numberOfSelectedEvents.toString(),
                                            fontWeight = FontWeight.Normal,
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Grey90
                                        )
                                    }

                                    Text(
                                        modifier = Modifier.padding(horizontal = LocalSpacing.current.extraSmall),
                                        text = "Selected",
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Blue30
                                    )
                                    Text(
                                        modifier = Modifier.padding(LocalSpacing.current.noPadding),
                                        text = "Events",
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Blue30
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (openSelectedEvents) {
        ModalBottomSheet(
            modifier = Modifier.padding(LocalSpacing.current.noPadding),
            shape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            sheetState = showSelectedEventsSheetState,
            onDismissRequest = { openSelectedEvents = false },
            dragHandle = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .height(LocalSpacing.current.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.shapes.extraSmall
                        )
                        .width(LocalSpacing.current.large)
                        .height(LocalSpacing.current.small)
                    ){
                        Text(text = "bar")
                    }
                }
            }
        ) {
            ShowSelectedEventsCard(
                selectedEvents = selectedEvents,
                isLoadingStats = isLoadingStats,
                loadingStatsMessage = loadingStatsMessage,
                getSelectedEvents = {_selectedEvents->
                    selectedEvents = _selectedEvents
                }
            ){statsParameters ->
                loadStats(statsParameters)
            }
        }
    }

    LaunchedEffect(selectedEvents){
        numberOfSelectedEvents = selectedEvents.size
    }
}