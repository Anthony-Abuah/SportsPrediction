package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun DateEventContent(
    preferredEvents: List<EventsEntity>,
    matchStartTimeEvents: List<EventsEntity>,
    searchedEvents: List<EventsEntity>,
    sortedEvents: List<EventsEntity>,
    filteredTournamentEvents: List<EventsEntity>,
    isLoading: Boolean,
    isLoadingSearchedEvents: Boolean,
    isLoadingMatchStartTimeEvents: Boolean,
    isLoadingSortedEvents: Boolean,
    isLoadingFilteredTournamentsEvents: Boolean,
    thereIsError: Boolean,
    errorMessage: String,
    openFilterCard: Boolean,
    openSearchCard: Boolean,
    openOrCloseFilterCard: () -> Unit,
    openOrCloseSearchCard: () -> Unit,
    closeFilterCard: () -> Unit,
    closeSearchCard: () -> Unit,
    getDate: (date: Date) -> Unit,
    getMatchStartTimePreferredEvent: (preferredEvents: List<EventsEntity>, value: Long) -> Unit,
    getFilteredTournamentsPreferredEvent: (preferredEvents: List<EventsEntity>, value: Map<String, String>) -> Unit,
    getSearchedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,
    getSortedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,
    navigateToUserPreferencesScreen: () -> Unit,
    navigateToEventsInfoScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit,

    ) {
    var openLeaguesSelectionDialog by remember { mutableStateOf(false) }
    var sortFilterValueChanges by remember { mutableStateOf(false) }
    var matchStartTimeValueChanges by remember { mutableStateOf(false) }
    var searchValueChanges by remember { mutableStateOf(false) }
    var filteredTournamentsValueChanges by remember { mutableStateOf(false) }
    var openMatchStartTimeDialog by remember { mutableStateOf(false) }
    var openSortEventsDialog by remember { mutableStateOf(false) }
    var sortFilter by remember { mutableStateOf(emptyString) }
    var searchValue by remember { mutableStateOf(emptyString) }
    var tournaments by remember { mutableStateOf(emptyMap<String, String>()) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val country = preferredEvents.groupBy { it.country ?: "Unknown Country" }

    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

    var newDateIsSelected by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var date by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
    ) {
        DateEventHeader(
            openFilterCard = {
                openOrCloseFilterCard()
                closeSearchCard()
            },
            openSearchCard = {
                openOrCloseSearchCard()
                closeFilterCard()
            },
            navigateToUserPreferences = navigateToUserPreferencesScreen
        )

        DateRow{ selectedDate ->
            newDateIsSelected = true
            date = selectedDate.toDate()
        }

        if (newDateIsSelected) {
            LaunchedEffect(Unit) {
                getDate(date)
                newDateIsSelected = false
            }
        }

        if (openSearchCard) {
            Card(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .height(LocalSpacing.current.topAppBarSize)
                    .fillMaxWidth(),
                elevation = LocalSpacing.current.large,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(LocalSpacing.current.noPadding)
            ) {
                SearchTextField(
                    searchValue = searchValue,
                    getValue = {
                        searchValue = it

                        searchValueChanges = true
                        matchStartTimeValueChanges = false
                        filteredTournamentsValueChanges = false
                        sortFilterValueChanges = false

                        if (filteredTournamentEvents.isNotEmpty()) {
                            coroutineScope.launch { getSearchedPreferredEvent(filteredTournamentEvents, it) }
                        }
                        else if (matchStartTimeEvents.isNotEmpty()) {
                            coroutineScope.launch { getSearchedPreferredEvent(matchStartTimeEvents, it) }
                        }
                        else if (sortedEvents.isNotEmpty()){
                            coroutineScope.launch { getSearchedPreferredEvent(sortedEvents, it) }
                        }
                        else coroutineScope.launch { getSearchedPreferredEvent(preferredEvents, it) }

                }) { closeSearchCard() }

            }
        }

        if (openFilterCard) {
            Card(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                elevation = LocalSpacing.current.large,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(LocalSpacing.current.noPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LocalSpacing.current.topAppBarSize)
                            .background(color = Color.White)
                            .border(0.8.dp, Color.LightGray),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = LocalSpacing.current.small)
                                .background(color = Color.White)
                                .clickable {
                                    openMatchStartTimeDialog = !openMatchStartTimeDialog
                                    openLeaguesSelectionDialog = false
                                    openSortEventsDialog = false
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (openMatchStartTimeDialog) SelectLeagueText(text = "Time")
                                else BasicText(text = "Time", fontSize = 16.sp, textColor = Color.Black)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(LocalSpacing.current.noPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(LocalSpacing.current.large)
                                        .height(LocalSpacing.current.large),
                                    painter = painterResource(
                                        id = if (openMatchStartTimeDialog) R.drawable.drop_up
                                        else R.drawable.drop_down
                                    ),
                                    contentDescription = emptyString,
                                    tint = PrimaryThemeColor
                                )
                            }
                        }

                        Spacer(modifier = Modifier
                            .width(0.8.dp)
                            .background(Color.LightGray)
                            .fillMaxHeight()
                        )

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = LocalSpacing.current.small)
                                .background(color = Color.White)
                                .clickable {
                                    openLeaguesSelectionDialog = !openLeaguesSelectionDialog
                                    openMatchStartTimeDialog = false
                                    openSortEventsDialog = false
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (openLeaguesSelectionDialog) SelectLeagueText(text = "Leagues")
                                else BasicText(text = "Leagues", fontSize = 16.sp, textColor = Color.Black)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(LocalSpacing.current.noPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(LocalSpacing.current.large)
                                        .height(LocalSpacing.current.large),
                                    painter = painterResource(
                                        id = if (openLeaguesSelectionDialog) R.drawable.drop_up
                                            else R.drawable.drop_down
                                    ),
                                    contentDescription = emptyString,
                                    tint = PrimaryThemeColor
                                )
                            }
                        }


                        Spacer(modifier = Modifier
                            .width(0.8.dp)
                            .background(Color.LightGray)
                            .fillMaxHeight()
                        )

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = LocalSpacing.current.small)
                                .background(color = Color.White)
                                .clickable {
                                    openSortEventsDialog = !openSortEventsDialog
                                    openMatchStartTimeDialog = false
                                    openLeaguesSelectionDialog = false
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .padding(horizontal = LocalSpacing.current.small),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (openSortEventsDialog) SelectLeagueText(text = "Sort Order")
                                else BasicText(text = "Sort Order", fontSize = 16.sp, textColor = Color.Black)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(LocalSpacing.current.noPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(LocalSpacing.current.large)
                                        .height(LocalSpacing.current.large),
                                    painter = painterResource(
                                        id = if (openSortEventsDialog) R.drawable.drop_up
                                        else R.drawable.drop_down
                                    ),
                                    contentDescription = emptyString,
                                    tint = PrimaryThemeColor
                                )
                            }
                        }


                    }

                }
            }

        }

        if (openMatchStartTimeDialog) {
            Card(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                elevation = LocalSpacing.current.large,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(LocalSpacing.current.noPadding)
            ) {
                AlertDialogMatchStartTimePage{
                    //matchStartTimeFilterValue = it
                    if (filteredTournamentEvents.isNotEmpty()) {
                        coroutineScope.launch { getMatchStartTimePreferredEvent(filteredTournamentEvents, it) }
                    }
                    else if (searchedEvents.isNotEmpty()){
                        coroutineScope.launch { getMatchStartTimePreferredEvent(searchedEvents, it) }
                    }
                    else if (sortedEvents.isNotEmpty()){
                        coroutineScope.launch { getMatchStartTimePreferredEvent(sortedEvents, it) }
                    }
                    else coroutineScope.launch { getMatchStartTimePreferredEvent(preferredEvents, it) }

                    matchStartTimeValueChanges = true
                    filteredTournamentsValueChanges = false
                    sortFilterValueChanges = false
                    searchValueChanges = false


                    openMatchStartTimeDialog = false
                    openSortEventsDialog = false
                }
            }
        }

        if (openSortEventsDialog) {
            Card(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                elevation = LocalSpacing.current.large,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(LocalSpacing.current.noPadding)
            ) {
                AlertDialogMatchSortEventsPage{
                    sortFilter = it

                    if (filteredTournamentEvents.isNotEmpty()) {
                        coroutineScope.launch { getSortedPreferredEvent(filteredTournamentEvents, it) }
                    }
                    else if (matchStartTimeEvents.isNotEmpty()) {
                        coroutineScope.launch { getSortedPreferredEvent(matchStartTimeEvents, it) }
                    }
                    else if (searchedEvents.isNotEmpty()){
                        coroutineScope.launch { getSortedPreferredEvent(searchedEvents, it) }
                    }
                    else coroutineScope.launch { getSortedPreferredEvent(preferredEvents, it) }

                    sortFilterValueChanges = true
                    matchStartTimeValueChanges = false
                    filteredTournamentsValueChanges = false
                    searchValueChanges = false

                    openSortEventsDialog = false
                }
            }
        }

        if (openLeaguesSelectionDialog) {
            Card(
                modifier = Modifier
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                elevation = LocalSpacing.current.large,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(LocalSpacing.current.noPadding)
            ) {
                AlertDialogCheckboxPage(
                    countries = country,
                    theSelectedTournaments = tournaments,
                    closeFilter = { closeFilterCard()
                        openLeaguesSelectionDialog = false
                    }
                ) {
                    tournaments = it
                    if (matchStartTimeEvents.isNotEmpty()) {
                        coroutineScope.launch { getFilteredTournamentsPreferredEvent(matchStartTimeEvents, it) }
                    }
                    else if (searchedEvents.isNotEmpty()){
                        coroutineScope.launch { getFilteredTournamentsPreferredEvent(searchedEvents, it) }
                    }
                    else if (sortedEvents.isNotEmpty()){
                        coroutineScope.launch { getFilteredTournamentsPreferredEvent(sortedEvents, it) }
                    }
                    else coroutineScope.launch { getFilteredTournamentsPreferredEvent(preferredEvents, it) }

                    filteredTournamentsValueChanges = true
                    matchStartTimeValueChanges = false
                    sortFilterValueChanges = false
                    searchValueChanges = false
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth(1f),
        contentAlignment = Alignment.TopCenter) {

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

            Spacer(modifier = Modifier.height(15.dp))

            if (isLoading || openLeaguesSelectionDialog || openSortEventsDialog || openMatchStartTimeDialog || isLoadingFilteredTournamentsEvents || isLoadingSortedEvents || isLoadingMatchStartTimeEvents || isLoadingSearchedEvents) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { closeFilterCard() }
                        .padding(LocalSpacing.current.small)
                ) {
                    items(
                        items = showPreferredEvents
                    ) { preferredEvent ->
                        DateEventCard(
                            preferredEvent = preferredEvent,
                            navigateToPredictionsScreen = { eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId ->
                                closeFilterCard()
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
                    }
                }

                Box(modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxSize()
                    .clickable {
                        closeFilterCard()
                        openLeaguesSelectionDialog = false
                        openSortEventsDialog = false
                        openMatchStartTimeDialog = false
                    },
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            else {
                if (showPreferredEvents.isEmpty()){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .clickable { closeFilterCard() }, contentAlignment = Alignment.Center){
                        BasicText(
                            text = "Nothing to show",
                            fontSize = 16.sp,
                            textColor = Color.Black
                        )
                    }
                }

                else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { closeFilterCard() }
                            .padding(LocalSpacing.current.small)
                    ) {
                        items(
                            items = showPreferredEvents
                        ) { preferredEvent ->
                            DateEventCard(
                                preferredEvent = preferredEvent,
                                navigateToPredictionsScreen = { eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId ->
                                    closeFilterCard()
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
                        }
                    }
                }

            }

            if (thereIsError) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

}