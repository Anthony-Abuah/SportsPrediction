package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.Functions.fromTimeStampToLocalDate
import com.example.sportsprediction.core.util.Functions.fromTimeStampToLocalTime
import com.example.sportsprediction.core.util.Functions.toDateString
import com.example.sportsprediction.core.util.Functions.toNotNull
import com.example.sportsprediction.core.util.Functions.toTimeStampString
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateEventCard1(
    preferredEvent: EventsEntity,
    navigateToPredictionsScreen: (eventId: String, headToHeadId: String, date: String, homeTeamName: String, homeTeamId: String, awayTeamName: String, awayTeamId: String) -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),

        onClick = {
            navigateToPredictionsScreen(
                "${preferredEvent.eventId ?: nullInteger}",
                preferredEvent.headToHeadId ?: emptyString,
                 preferredEvent.date?.toDateString().toNotNull(),
                preferredEvent.homeTeamName ?: emptyString,
                "${preferredEvent.homeTeamId ?: nullInteger}",
                preferredEvent.awayTeamName ?: emptyString,
                "${preferredEvent.awayTeamId ?: nullInteger}"
            )
        },
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        val timeStamp = preferredEvent.startTimestamp.toNotNull()
        val dateString = timeStamp.toTimeStampString().fromTimeStampToLocalDate().toDateString().removePrefix("20")
        val dayOfWeek = timeStamp.toTimeStampString().fromTimeStampToLocalDate()
            .dayOfWeek.toString().take(3)
            .lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        val timeString = timeStamp.toTimeStampString().fromTimeStampToLocalTime().toString()
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier
                .wrapContentWidth()
                .padding(LocalSpacing.current.noPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = dayOfWeek,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = dateString,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(LocalSpacing.current.noPadding),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = timeString,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
            }


            Column(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = LocalSpacing.current.smallMedium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = preferredEvent.homeTeamName.toNotNull(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = preferredEvent.awayTeamName.toNotNull(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}


@Composable
fun Header(
    header: String,
    isExpanded: Boolean,
    onChangeExpanded: ()-> Unit,
){
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LocalSpacing.current.extraSmall)
            .clickable { onChangeExpanded() },
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.small)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Start
                )
            }

            Box(
                modifier = Modifier
                    .requiredWidth(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small)
                    .clickable { onChangeExpanded() },
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier
                        .size(LocalSpacing.current.large)
                        .aspectRatio(1f / 1f),
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = emptyString,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}

