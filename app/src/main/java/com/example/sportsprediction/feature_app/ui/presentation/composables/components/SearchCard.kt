package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun SearchCard(
    thisSearchValue: String,
    coroutineScope: CoroutineScope,
    getSearchValue: (searchValue: String) -> Unit,
    getSearchedPreferredEvent: (searchValue: String) -> Unit,
    closeSearchCard: () -> Unit,


){
    var searchValue by remember {
        mutableStateOf(thisSearchValue)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.noPadding)
            .height(LocalSpacing.current.topAppBarSize)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(LocalSpacing.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RectangleShape
    ) {
        SearchTextField(
            searchValue = searchValue,
            getValue = {
                //Get the search value
                searchValue = it
                getSearchValue(searchValue)

                // We need to search the filtered events first and return the results of the search.
                // If the filtered events are all empty(or haven't been filtered yet) then we return the result of the search from the entire events
                /*if (filteredTournamentEvents.isNotEmpty()) {
                    coroutineScope.launch { getSearchedPreferredEvent(filteredTournamentEvents, it) }
                }
                else if (matchStartTimeEvents.isNotEmpty()) {
                    coroutineScope.launch { getSearchedPreferredEvent(matchStartTimeEvents, it) }
                }
                else if (sortedEvents.isNotEmpty()){
                    coroutineScope.launch { getSearchedPreferredEvent(sortedEvents, it) }
                }
                else*/ coroutineScope.launch { getSearchedPreferredEvent(/*preferredEvents,*/ it) }

            }) { closeSearchCard() }

    }
}