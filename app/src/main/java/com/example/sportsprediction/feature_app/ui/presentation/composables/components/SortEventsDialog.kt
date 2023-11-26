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
fun SortEventsDialog(
    //preferredEvents: List<EventsEntity>,
    //filteredTournamentEvents: List<EventsEntity>,
    //searchedEvents: List<EventsEntity>,
    //matchStartTimeEvents: List<EventsEntity>,
    coroutineScope: CoroutineScope,
    getSortedPreferredEvent: (/*preferredEvents: List<EventsEntity>,*/ sortFilter: String) -> Unit,
){
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
        AlertDialogMatchSortEventsPage{
            /*if (filteredTournamentEvents.isNotEmpty()) {
                coroutineScope.launch { getSortedPreferredEvent(filteredTournamentEvents, it) }
            }
            else if (matchStartTimeEvents.isNotEmpty()) {
                coroutineScope.launch { getSortedPreferredEvent(matchStartTimeEvents, it) }
            }
            else if (searchedEvents.isNotEmpty()){
                coroutineScope.launch { getSortedPreferredEvent(searchedEvents, it) }
            }
            else */coroutineScope.launch { getSortedPreferredEvent(/*preferredEvents,*/ it) }

        }
    }

}