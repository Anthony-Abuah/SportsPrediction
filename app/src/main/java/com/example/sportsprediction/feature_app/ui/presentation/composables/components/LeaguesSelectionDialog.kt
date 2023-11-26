package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LeaguesSelectionDialog(
    groupedCountryEvents: Map<String, List<EventsEntity>>,
    theSelectedTournaments: Map<String, String>,
    /*preferredEvents: List<EventsEntity>,
    sortedEvents: List<EventsEntity>,
    searchedEvents: List<EventsEntity>,
    matchStartTimeEvents: List<EventsEntity>,*/
    coroutineScope: CoroutineScope,
    closeFilterCard: () -> Unit,
    closeLeaguesSelectionDialog: () -> Unit,
    getFilteredTournamentsPreferredEvent: (/*preferredEvents: List<EventsEntity>,*/ tournament: Map<String, String>) -> Unit,
){
    var tournaments by remember {
        mutableStateOf(theSelectedTournaments)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.noPadding)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(LocalSpacing.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RectangleShape
    ) {
        AlertDialogCheckboxPage(
            groupedCountryEvents = groupedCountryEvents,
            theSelectedTournaments = tournaments,
            closeFilter = { closeFilterCard()
                closeLeaguesSelectionDialog()
            }
        ) {
            tournaments = it
            /*if (tournaments.isEmpty()) {
                coroutineScope.launch { getFilteredTournamentsPreferredEvent(preferredEvents, it) }
            }
            else if (matchStartTimeEvents.isNotEmpty()) {
                coroutineScope.launch { getFilteredTournamentsPreferredEvent(matchStartTimeEvents, it) }
            }
            else if (searchedEvents.isNotEmpty()){
                coroutineScope.launch { getFilteredTournamentsPreferredEvent(searchedEvents, it) }
            }
            else if (sortedEvents.isNotEmpty()){
                coroutineScope.launch { getFilteredTournamentsPreferredEvent(sortedEvents, it) }
            }
            else*/ coroutineScope.launch { getFilteredTournamentsPreferredEvent(/*preferredEvents,*/ it) }

        }
    }
}