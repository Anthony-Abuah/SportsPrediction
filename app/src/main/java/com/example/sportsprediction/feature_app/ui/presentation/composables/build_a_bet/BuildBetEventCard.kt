package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.model.general.LoadStatsParameters
import com.example.sportsprediction.feature_app.ui.theme.*
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildBetEventCard(
    selectedEvents: List<EventsEntity>,
    groupedEvents: Map<String, List<EventsEntity>>,
    getSelectedEvents: (selectedEvents: List<EventsEntity>) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var thisSelectedEvents by remember {
        mutableStateOf(selectedEvents.toMutableList())
    }

    LaunchedEffect(selectedEvents){
        thisSelectedEvents = selectedEvents.toSet().toMutableList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(LocalSpacing.current.noPadding)
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {
        groupedEvents.forEach { groupedEvent ->
            val header = groupedEvent.key
            val groupedEventEntities = groupedEvent.value
            val numberOfMatches = groupedEventEntities.size
            var isExpanded by remember {
                mutableStateOf(true)
            }
            var allGroupedEventsAreSelected by remember {
                mutableStateOf(thisSelectedEvents.containsAll(groupedEventEntities))
            }

            val headingBackgroundColor = if (allGroupedEventsAreSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }

            val contentColor = if (allGroupedEventsAreSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onPrimaryContainer
            }

            Card(
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    contentColor = contentColor,
                    containerColor = headingBackgroundColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LocalSpacing.current.extraSmall)
                    .combinedClickable(
                        enabled = true,
                        onLongClick = {
                            coroutineScope.launch {
                                allGroupedEventsAreSelected = !allGroupedEventsAreSelected
                                if (allGroupedEventsAreSelected) {
                                    thisSelectedEvents.addAll(groupedEventEntities)
                                    thisSelectedEvents = thisSelectedEvents
                                        .toSet()
                                        .toMutableList()
                                    getSelectedEvents(thisSelectedEvents)
                                } else {
                                    thisSelectedEvents = thisSelectedEvents
                                        .toSet()
                                        .toMutableList()
                                    thisSelectedEvents.removeAll(groupedEventEntities)
                                    getSelectedEvents(thisSelectedEvents)
                                }
                            }
                        },
                        onDoubleClick = {
                            allGroupedEventsAreSelected = !allGroupedEventsAreSelected
                            if (allGroupedEventsAreSelected) {
                                thisSelectedEvents.addAll(groupedEventEntities)
                                thisSelectedEvents = thisSelectedEvents
                                    .toSet()
                                    .toMutableList()
                                getSelectedEvents(thisSelectedEvents)
                            } else {
                                thisSelectedEvents = thisSelectedEvents
                                    .toSet()
                                    .toMutableList()
                                thisSelectedEvents.removeAll(groupedEventEntities)
                                getSelectedEvents(thisSelectedEvents)
                            }
                        },
                        onClick = {
                            isExpanded = !isExpanded
                        }
                    )
                    /*.toggleable(value = allGroupedEventsAreSelected,
                        onValueChange = { isToggled ->
                            allGroupedEventsAreSelected = isToggled
                            if (isToggled) {
                                isExpanded = false
                                thisSelectedEvents.addAll(groupedEventEntities)
                                thisSelectedEvents = thisSelectedEvents.toSet().toMutableList()
                                getSelectedEvents(thisSelectedEvents)

                            } else {
                                thisSelectedEvents = thisSelectedEvents.toSet().toMutableList()
                                thisSelectedEvents.removeAll(groupedEventEntities)
                                getSelectedEvents(thisSelectedEvents)
                            }
                        })*/,
                elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.small)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .requiredSize(LocalSpacing.current.large)
                            .background(contentColor, CircleShape)
                            .padding(LocalSpacing.current.noPadding)
                            .clickable { isExpanded = !isExpanded },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(LocalSpacing.current.small),
                            text = "$numberOfMatches",
                            fontWeight = FontWeight.ExtraBold,
                            style = MaterialTheme.typography.bodyLarge,
                            color = headingBackgroundColor,
                            textAlign = TextAlign.Start
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(LocalSpacing.current.default),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            modifier = Modifier,
                            text = header,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyLarge,
                            color = contentColor,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start
                        )
                    }

                    Box(
                        modifier = Modifier
                            .requiredWidth(LocalSpacing.current.large)
                            .padding(LocalSpacing.current.small)
                            .clickable { isExpanded = !isExpanded },
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(LocalSpacing.current.large)
                                .aspectRatio(1f / 1f),
                            imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = Constants.emptyString,
                            tint = contentColor,
                        )
                    }
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    groupedEventEntities.forEach { eventEntity ->
                        BuildBetCard(
                            preferredEvent = eventEntity,
                            selectedEvents = thisSelectedEvents
                        ) { _thisSelectedEvents ->
                            thisSelectedEvents = _thisSelectedEvents
                            getSelectedEvents(thisSelectedEvents)
                            allGroupedEventsAreSelected = false
                        }
                    }
                }
            }
        }
    }
}

