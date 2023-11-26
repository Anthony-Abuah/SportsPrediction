package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.longDateFormatter
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.model.general.LoadStatsParameters
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowSelectedEventsCard(
    selectedEvents: List<EventsEntity>,
    isLoadingStats: Boolean,
    loadingStatsMessage: String,
    getSelectedEvents: (thisSelectedEvents: List<EventsEntity>) -> Unit,
    onClick: (listOfParameters: List<LoadStatsParameters>) -> Unit,
) {
    val context = LocalContext.current
    val sportPredictionPreferences = context.getSharedPreferences(Constants.SportsPredictionPreferences, Context.MODE_PRIVATE)
    val numberOfPastEvents = sportPredictionPreferences.getString(Constants.NumberOfPastEvents, "${Constants.Default_Past_Events_Value}")?.toInt() ?: Constants.Default_Past_Events_Value
    val numberOfPastHeadToHeadEvents = sportPredictionPreferences.getString(Constants.NumberOfHeadToHeadEvents, "${Constants.Default_Past_Head_To_Head_Events_Value}")?.toInt() ?: Constants.Default_Past_Head_To_Head_Events_Value
    val listOfParameters = mutableListOf<LoadStatsParameters>()

    var thisSelectedEvents by remember {
        mutableStateOf(selectedEvents.toMutableList())
    }

    LaunchedEffect(selectedEvents){
        thisSelectedEvents = selectedEvents.toSet().toMutableList()
        listOfParameters.clear()
        selectedEvents.forEach { selectedEvent->
            val homeTeamName = selectedEvent.homeTeamName ?: emptyString
            val awayTeamName = selectedEvent.awayTeamName ?: emptyString
            val homeTeamId = selectedEvent.homeTeamId ?: 0
            val awayTeamId = selectedEvent.awayTeamId ?: 0
            val headToHeadId = selectedEvent.headToHeadId ?: emptyString
            val eventId = selectedEvent.eventId ?: 0
            val date = selectedEvent.date ?: Date()
            listOfParameters.add(LoadStatsParameters(homeTeamName, homeTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfPastHeadToHeadEvents))
            listOfParameters.add(LoadStatsParameters(awayTeamName, awayTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfPastHeadToHeadEvents))
        }
    }

    Card(
        modifier = Modifier
            .heightIn(min = 60.dp, max = 400.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(LocalSpacing.current.noElevation),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            thisSelectedEvents.forEachIndexed { index, selectedEvent ->
                val startTimestamp = selectedEvent.startTimestamp?.toLong() ?: 0.toLong()
                val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(startTimestamp.let { Instant.ofEpochSecond(it) })

                val matchDate = LocalDate.parse(timeStampAsDateTimeString, longDateFormatter)
                val matchTime = LocalTime.parse(timeStampAsDateTimeString, longDateFormatter)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = LocalSpacing.current.extraSmall),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Column(modifier = Modifier.requiredWidth(80.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(LocalSpacing.current.extraSmall)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$matchDate",
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$matchTime",
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.extraSmall)
                        .fillMaxHeight(0.95f)
                        .width(LocalSpacing.current.borderStroke)
                        .background(MaterialTheme.colorScheme.onBackground),
                        contentAlignment = Alignment.Center
                    ){
                        Text(modifier = Modifier.padding(LocalSpacing.current.textSpace),
                            text = "ba.",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Column(modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedEvent.homeTeamName ?: emptyString,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedEvent.awayTeamName ?: emptyString,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Box(modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.small)
                        .fillMaxHeight()
                        .width(LocalSpacing.current.medium)
                        .background(MaterialTheme.colorScheme.errorContainer, CircleShape)
                        .clickable {
                            thisSelectedEvents = selectedEvents.toSet().toMutableList()
                            thisSelectedEvents.remove(selectedEvent)
                            thisSelectedEvents = thisSelectedEvents.toSet().toMutableList()
                            getSelectedEvents(thisSelectedEvents)
                        },
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = emptyString,
                            tint = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }

                }

                if (index != selectedEvents.lastIndex) {
                    Divider(
                        thickness = LocalSpacing.current.thinBorder,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = LocalSpacing.current.buttonHeight)
                    .padding(LocalSpacing.current.noPadding)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.shapes.medium
                    )
                    .clickable(
                        enabled = !isLoadingStats
                    ) {
                        onClick(
                            listOfParameters
                                .toSet()
                                .toList()
                        )
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoadingStats) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(LocalSpacing.current.semiLarge)
                            .padding(LocalSpacing.current.small),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        strokeWidth = LocalSpacing.current.extraSmall
                    )
                }
                val buttonText = if (isLoadingStats){
                    loadingStatsMessage
                } else { "Load Suggestions" }
                Text(
                    modifier = Modifier.padding(LocalSpacing.current.default),
                    text = loadingStatsMessage,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}
