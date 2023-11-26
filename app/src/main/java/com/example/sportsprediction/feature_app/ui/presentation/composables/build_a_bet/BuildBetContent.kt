package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.presentation.composables.date_events.BuildBetEventCard
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@Composable
fun BuildBetContent(
    preferredEvents: List<EventsEntity>,
    matchStartTimeEvents: List<EventsEntity>,
    searchedEvents: List<EventsEntity>,
    sortedEvents: List<EventsEntity>,
    filteredTournamentEvents: List<EventsEntity>,
    isLoadingPreferredEvents: Boolean,
    isLoadingSearchedEvents: Boolean,
    isLoadingMatchStartTimeEvents: Boolean,
    isLoadingSortedEvents: Boolean,
    isLoadingFilteredTournamentsEvents: Boolean,
    thereIsError: Boolean,
    errorMessage: String,
    openFilterCard: Boolean,
    openSearchCard: Boolean,
    build_a_bet: Boolean,
    closeFilterCard: () -> Unit,
    closeSearchCard: () -> Unit,
    getSelectedEventsEntity: (selectedEvents: ListOfEvents)-> Unit,
    getMatchStartTimePreferredEvent: (preferredEvents: List<EventsEntity>, value: Long) -> Unit,
    getFilteredTournamentsPreferredEvent: (preferredEvents: List<EventsEntity>, value: Map<String, String>) -> Unit,
    getSearchedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,
    getSortedPreferredEvent: (preferredEvents: List<EventsEntity>, value: String) -> Unit,

    ) {

    var openLeaguesSelectionDialog by remember { mutableStateOf(false) }
    var sortFilterValueChanges by remember { mutableStateOf(false) }
    var matchStartTimeValueChanges by remember { mutableStateOf(false) }
    var searchValueChanges by remember { mutableStateOf(false) }
    var filteredTournamentsValueChanges by remember { mutableStateOf(false) }
    var openMatchStartTimeDialog by remember { mutableStateOf(false) }
    var openSortEventsDialog by remember { mutableStateOf(false) }
    var tournamentIsExpanded by remember { mutableStateOf(false) }
    var tournamentIsSelected by remember { mutableStateOf(false) }
    var selectedPreferredEvents by remember { mutableStateOf((emptyList<EventsEntity>().toMutableList())) }
    var expandedPreferredEventsMap by remember { mutableStateOf((emptyMap<String, ListOfEvents>())) }
    var sortFilter by remember { mutableStateOf(Constants.emptyString) }
    var searchValue by remember { mutableStateOf(Constants.emptyString) }
    var tournaments by remember { mutableStateOf(emptyMap<String, String>()) }

    var theseSelectedEvents by remember { mutableStateOf(emptyList<EventsEntity>()) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val country = preferredEvents.groupBy { it.country ?: "Unknown Country" }

    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
            .padding(bottom = LocalSpacing.current.topAppBarSize)
    ) {

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
                                    contentDescription = Constants.emptyString,
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
                                    contentDescription = Constants.emptyString,
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
                                    contentDescription = Constants.emptyString,
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


            if (isLoadingPreferredEvents || openLeaguesSelectionDialog || openSortEventsDialog || openMatchStartTimeDialog || isLoadingFilteredTournamentsEvents || isLoadingSortedEvents || isLoadingMatchStartTimeEvents || isLoadingSearchedEvents) {

                val groupedPreferredEvents = showPreferredEvents.groupBy { "${it.country}, ${it.tournamentName}" }
                val tournamentNames = groupedPreferredEvents.keys.toList().toSet().toList()
                val scrollState = rememberScrollState(0)


                Column(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .clickable { }
                        .padding(LocalSpacing.current.small)
                        .verticalScroll(enabled = true, state = scrollState)
                ) {
                    tournamentNames.forEach {tournamentName->
                        BuildBetCard(
                            country = tournamentName,
                            preferredEvents = groupedPreferredEvents[tournamentName]?.toSet()?.toList() ?: emptyList(),
                            selectedEvents = selectedPreferredEvents,
                            expandedEvents = expandedPreferredEventsMap,
                            getIsSelected = { preferredEvents ->
                                selectedPreferredEvents =  preferredEvents.toMutableList()
                                getSelectedEventsEntity(selectedPreferredEvents)

                                if (selectedPreferredEvents.isNotEmpty()) {
                                    selectedPreferredEvents.forEach { selectedEvent ->
                                        Log.d(
                                            "BuildBetContent",
                                            "selectedPreferredEvent: ${selectedEvent.homeTeamName} vs ${selectedEvent.awayTeamName}"
                                        )
                                    }
                                } else {
                                    Log.d("BuildBetContent", "selectedPreferredEvent is empty")
                                }

                                if (selectedPreferredEvents.size > 10){
                                    Toast.makeText(context, "Cannot select more than 10 events", Toast.LENGTH_LONG).show()
                                }


                            },
                        ) { isExpanded, preferredEventsMap ->
                            tournamentIsExpanded = isExpanded
                            expandedPreferredEventsMap = preferredEventsMap
                        }

                        val notNullExpandedPreferredEvents = expandedPreferredEventsMap[tournamentName]?.toSet()?.toList() ?: emptyList()
                        if (notNullExpandedPreferredEvents.isNotEmpty() && tournamentIsExpanded){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(LocalSpacing.current.smallMedium)
                                .background(Color.Transparent),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                notNullExpandedPreferredEvents.forEach { preferredEvent ->
                                    BuildBetEventCard(
                                        preferredEvent = preferredEvent,
                                        selectedPreferredEvents = selectedPreferredEvents,
                                        getPreferredEvent = { preferredEvents ->
                                            selectedPreferredEvents = preferredEvents
                                            getSelectedEventsEntity(selectedPreferredEvents)

                                            if (selectedPreferredEvents.isNotEmpty()) {
                                                selectedPreferredEvents.forEach { selectedEvent ->
                                                    Log.d(
                                                        "BuildBetContent",
                                                        "selectedPreferredEvent: ${selectedEvent.homeTeamName} vs ${selectedEvent.awayTeamName}"
                                                    )
                                                }
                                            }else {
                                                Log.d("BuildBetContent", "selectedPreferredEvent is empty")
                                            }

                                            if (selectedPreferredEvents.size > 10){
                                                Toast.makeText(context, "Cannot select more than 10 events", Toast.LENGTH_LONG).show()
                                            }

                                        }
                                    )
                                }
                            }
                        }
                    }

                }


                Box(modifier = Modifier
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
                    val groupedPreferredEvents = showPreferredEvents.groupBy { "${it.country}, ${it.tournamentName}" }
                    val tournamentNames = groupedPreferredEvents.keys.toList().toSet().toList()
                    val scrollState = rememberScrollState(0)

                    Column(
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .clickable { }
                            .padding(LocalSpacing.current.small)
                            .verticalScroll(enabled = true, state = scrollState)
                    ) {
                        tournamentNames.forEach {tournamentName->
                            BuildBetCard(
                                country = tournamentName,
                                preferredEvents = groupedPreferredEvents[tournamentName]?.toSet()?.toList() ?: emptyList(),
                                selectedEvents = selectedPreferredEvents,
                                expandedEvents = expandedPreferredEventsMap,
                                getIsSelected = { preferredEvents ->
                                    selectedPreferredEvents =  preferredEvents.toMutableList()
                                    getSelectedEventsEntity(selectedPreferredEvents)

                                    if (selectedPreferredEvents.isNotEmpty()) {
                                        selectedPreferredEvents.forEach { selectedEvent ->
                                            Log.d(
                                                "BuildBetContent",
                                                "selectedPreferredEvent: ${selectedEvent.homeTeamName} vs ${selectedEvent.awayTeamName}"
                                            )
                                        }
                                    }else {
                                        Log.d("BuildBetContent", "selectedPreferredEvent is empty")
                                    }


                                    if (selectedPreferredEvents.size > 10){
                                        Toast.makeText(context, "Cannot select more than 10 events", Toast.LENGTH_LONG).show()
                                    }

                                },
                            ) { isExpanded, preferredEventsMap ->
                                tournamentIsExpanded = isExpanded
                                expandedPreferredEventsMap = preferredEventsMap
                            }
                            val notNullExpandedPreferredEvents = expandedPreferredEventsMap[tournamentName] ?: emptyList()
                            if (notNullExpandedPreferredEvents.toSet().toList().isNotEmpty() && tournamentIsExpanded){
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(LocalSpacing.current.smallMedium)
                                    .background(Color.Transparent),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    notNullExpandedPreferredEvents.toSet().toList().forEach { preferredEvent ->
                                        BuildBetEventCard(
                                            preferredEvent = preferredEvent,
                                            selectedPreferredEvents = selectedPreferredEvents,
                                            getPreferredEvent = { preferredEvents ->
                                                selectedPreferredEvents = preferredEvents
                                                getSelectedEventsEntity(selectedPreferredEvents)

                                                if (selectedPreferredEvents.isNotEmpty()) {
                                                    selectedPreferredEvents.forEach { selectedEvent ->
                                                        Log.d(
                                                            "BuildBetContent",
                                                            "selectedPreferredEvent: ${selectedEvent.homeTeamName} vs ${selectedEvent.awayTeamName}"
                                                        )
                                                    }
                                                }else {
                                                    Log.d("BuildBetContent", "selectedPreferredEvent is empty")
                                                }


                                                if (selectedPreferredEvents.size > 10){
                                                    Toast.makeText(context, "Cannot select more than 10 events", Toast.LENGTH_LONG).show()
                                                }

                                            }
                                        )
                                    }
                                }
                            }
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