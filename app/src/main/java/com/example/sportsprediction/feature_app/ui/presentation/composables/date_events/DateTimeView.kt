package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants.AFewDaysAgo
import com.example.sportsprediction.core.util.Constants.InAFewDays
import com.example.sportsprediction.core.util.Constants.Today
import com.example.sportsprediction.core.util.Constants.Tomorrow
import com.example.sportsprediction.core.util.Constants.Yesterday
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.longDateFormatter
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun DateTimeView(
    startTimestamp: Long
) {
    val currentDate = LocalDate.now()

    val todayAbsoluteDate = currentDate.toDate()
    val todayAbsoluteLocalDate = todayAbsoluteDate.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val todayAbsoluteLongDate = todayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond

    val yesterdayAbsoluteLocalDate = todayAbsoluteLocalDate?.minusDays(1)
    val tomorrowAbsoluteLocalDate = todayAbsoluteLocalDate?.plusDays(1)
    val beforeYesterdayAbsoluteLocalDate = todayAbsoluteLocalDate?.minusDays(2)
    val afterTomorrowAbsoluteLocalDate = todayAbsoluteLocalDate?.plusDays(2)


    val yesterdayAbsoluteLongDate = yesterdayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val beforeYesterdayAbsoluteLongDate = beforeYesterdayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val tomorrowAbsoluteLongDate = tomorrowAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val afterTomorrowAbsoluteLongDate = afterTomorrowAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond

    val durationToMatch = if (todayAbsoluteLongDate != null && tomorrowAbsoluteLongDate != null && (startTimestamp >= todayAbsoluteLongDate) && (startTimestamp < tomorrowAbsoluteLongDate)){
        Today
    }
    else if (afterTomorrowAbsoluteLongDate != null && tomorrowAbsoluteLongDate != null && (startTimestamp >= tomorrowAbsoluteLongDate) && (startTimestamp < afterTomorrowAbsoluteLongDate)){
        Tomorrow
    }
    else if (todayAbsoluteLongDate != null && yesterdayAbsoluteLongDate != null && (startTimestamp >= yesterdayAbsoluteLongDate) && (startTimestamp < todayAbsoluteLongDate)){
        Yesterday
    }
    else if (tomorrowAbsoluteLongDate != null && (startTimestamp > tomorrowAbsoluteLongDate)){
        InAFewDays
    }
    else if (yesterdayAbsoluteLongDate != null && (startTimestamp < yesterdayAbsoluteLongDate)){
        AFewDaysAgo
    }
    else{ emptyString }


    val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(startTimestamp.let { Instant.ofEpochSecond(it) })

    val matchDate = LocalDate.parse(timeStampAsDateTimeString, longDateFormatter)
    val matchTime = LocalTime.parse(timeStampAsDateTimeString, longDateFormatter)


    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
            Text(
                text = durationToMatch,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
            Text(
                text = "$matchDate",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
            Text(
                text = "$matchTime",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }

    }




}