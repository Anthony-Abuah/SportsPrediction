package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.sportsprediction.core.util.Constants.NothingToShow
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import kotlinx.coroutines.CoroutineScope
import java.util.*

@Composable
fun DateEventContent(
    groupedEvents: Map<String, List<EventsEntity>>,
    groupedCountryEvents: Map<String, List<EventsEntity>>,
    isLoading: Boolean,
    filterCardIsOpened: Boolean,
    searchCardIsOpened: Boolean,
    selectedDate: Date,
    closeFilterCard: () -> Unit,
    closeSearchCard: () -> Unit,
    getDate: (date: Date) -> Unit,
    getMatchStartTimePreferredEvent: (value: Long) -> Unit,
    getFilteredTournamentsPreferredEvent: (value: Map<String, String>) -> Unit,
    getSearchedPreferredEvent: (value: String) -> Unit,
    getSortedPreferredEvent: (value: String) -> Unit,
    navigateToEventsInfoScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit,
) {
    var leaguesSelectionDialogIsOpened by remember { mutableStateOf(false) }
    var matchStartTimeDialogIsOpened by remember { mutableStateOf(false) }
    var sortEventsDialogIsOpened by remember { mutableStateOf(false) }
    var searchValue by remember { mutableStateOf(emptyString) }
    var tournaments by remember { mutableStateOf(emptyMap<String, String>()) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var newDateIsSelected by remember {
        mutableStateOf(false)
    }

    var date by remember { mutableStateOf(selectedDate) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
    ) {

        DateRow(selectedDate = date){ _selectedDate ->
            newDateIsSelected = true
            date = _selectedDate.toDate()
        }

        if (newDateIsSelected) {
            LaunchedEffect(Unit) {
                getDate(date)
                newDateIsSelected = false
            }
        }

        AnimatedVisibility (searchCardIsOpened) {
            SearchCard(
                thisSearchValue = searchValue,
                coroutineScope = coroutineScope,
                getSearchValue = { _searchValue ->
                    searchValue = _searchValue
                },
                getSearchedPreferredEvent = { _searchValue ->
                    getSearchedPreferredEvent(_searchValue)
                },
                closeSearchCard = closeSearchCard
            )

        }

        AnimatedVisibility (filterCardIsOpened) {
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

        AnimatedVisibility (matchStartTimeDialogIsOpened) {
            MatchStartTimeDialog(
                coroutineScope = coroutineScope,
                getMatchStartTimePreferredEvent = { value->
                    matchStartTimeDialogIsOpened = false
                    getMatchStartTimePreferredEvent (value)
                    matchStartTimeDialogIsOpened = false
                },
            )
        }

        AnimatedVisibility (sortEventsDialogIsOpened) {
            SortEventsDialog(
                coroutineScope = coroutineScope,
                getSortedPreferredEvent = { _sortFilter ->
                    getSortedPreferredEvent(_sortFilter)
                    sortEventsDialogIsOpened = false
                }
            )
        }

        AnimatedVisibility (leaguesSelectionDialogIsOpened) {
            LeaguesSelectionDialog(
                groupedCountryEvents = groupedCountryEvents,
                theSelectedTournaments = tournaments,
                coroutineScope = coroutineScope,
                closeFilterCard = closeFilterCard,
                closeLeaguesSelectionDialog = { leaguesSelectionDialogIsOpened = false },
                getFilteredTournamentsPreferredEvent = { _tournament ->
                    tournaments = _tournament
                    getFilteredTournamentsPreferredEvent(_tournament)
                }
            )
        }

        Box(modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize()
            .clickable {
                closeFilterCard()
                leaguesSelectionDialogIsOpened = false
                sortEventsDialogIsOpened = false
                matchStartTimeDialogIsOpened = false
            },
            contentAlignment = Alignment.Center
        ) {
            if (groupedEvents.isEmpty()){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        closeFilterCard()
                        closeSearchCard()
                    },
                    contentAlignment = Alignment.Center){
                    Text(text = NothingToShow,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(LocalSpacing.current.noPadding)
                        .verticalScroll(state = rememberScrollState(), enabled = true)
                        .clickable { closeFilterCard() }
                ) {
                    groupedEvents.forEach{ groupedEvent->
                        val header = groupedEvent.key
                        val groupedPreferredEvents = groupedEvent.value
                        var isExpanded by remember {
                            mutableStateOf(true)
                        }
                        Header(header = header, isExpanded = isExpanded) {
                            isExpanded = !isExpanded
                        }
                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                groupedPreferredEvents.forEachIndexed { index, preferredEvent ->
                                    DateEventCard1(
                                        preferredEvent = preferredEvent,
                                        navigateToPredictionsScreen = { eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId ->
                                            closeFilterCard()
                                            closeSearchCard()
                                            navigateToEventsInfoScreen(
                                                eventId,
                                                headToHeadId,
                                                date,
                                                homeTeamName,
                                                homeTeamId,
                                                awayTeamName,
                                                awayTeamId
                                            )
                                        }
                                    )
                                    if (index != groupedPreferredEvents.size - 1) {
                                        Divider(modifier = Modifier.padding(LocalSpacing.current.borderStroke))
                                    }
                                }
                            }
                        }
                    }
                    if (groupedEvents.isEmpty()){
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                closeFilterCard()
                                closeSearchCard()
                            },
                            contentAlignment = Alignment.Center){
                            Text(text = NothingToShow,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            if (isLoading)
            {
                Box(modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .clickable {
                        closeFilterCard()
                        leaguesSelectionDialogIsOpened = false
                        sortEventsDialogIsOpened = false
                        matchStartTimeDialogIsOpened = false
                    },
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
        /*
        if (isLoading)
        {
            Box(modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
                .clickable {
                    closeFilterCard()
                    leaguesSelectionDialogIsOpened = false
                    sortEventsDialogIsOpened = false
                    matchStartTimeDialogIsOpened = false
                },
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else {
            if (groupedEvents.isEmpty()){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        closeFilterCard()
                        closeSearchCard()
                    },
                    contentAlignment = Alignment.Center){
                    Text(text = NothingToShow,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(LocalSpacing.current.noPadding)
                        .verticalScroll(state = rememberScrollState(), enabled = true)
                        .clickable { closeFilterCard() }
                ) {
                    groupedEvents.forEach{ groupedEvent->
                        val header = groupedEvent.key
                        val groupedPreferredEvents = groupedEvent.value
                        var isExpanded by remember {
                            mutableStateOf(true)
                        }
                        Header(header = header, isExpanded = isExpanded) {
                            isExpanded = !isExpanded
                        }
                        AnimatedVisibility(visible = isExpanded) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                groupedPreferredEvents.forEachIndexed { index, preferredEvent ->
                                    DateEventCard1(
                                        preferredEvent = preferredEvent,
                                        navigateToPredictionsScreen = { eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId ->
                                            closeFilterCard()
                                            closeSearchCard()
                                            navigateToEventsInfoScreen(
                                                eventId,
                                                headToHeadId,
                                                date,
                                                homeTeamName,
                                                homeTeamId,
                                                awayTeamName,
                                                awayTeamId
                                            )
                                        }
                                    )
                                    if (index != groupedPreferredEvents.size - 1) {
                                        Divider(modifier = Modifier.padding(LocalSpacing.current.borderStroke))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        */
    }
}