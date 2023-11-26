package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.DateMatchDurationText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun DateTimeView(
    startTimestamp: Long
) {
    val currentDate = LocalDate.now()
    val currentTime = LocalTime.now()

    val longFormat = DateTimeFormatter.ofPattern(Constants.longDateFormat)
    val shortFormat = DateTimeFormatter.ofPattern(Constants.shortDateFormat)



    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
    /*val dateToLocalDate = date.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val dateString = shortFormat.format(dateToLocalDate)
    val longDate = dateToLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
*/

    val todayAbsoluteDate = currentDate.toDate()
    val todayAbsoluteLocalDate = todayAbsoluteDate.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()
    val todayAbsoluteLongDate = todayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond

    val yesterdayAbsoluteLocalDate = todayAbsoluteLocalDate?.minusDays(1)
    val tomorrowAbsoluteLocalDate = todayAbsoluteLocalDate?.plusDays(1)
    val beforeYesterdayAbsoluteLocalDate = todayAbsoluteLocalDate?.minusDays(2)
    val afterTomorrowAbsoluteLocalDate = todayAbsoluteLocalDate?.plusDays(2)

    val yesterdayAbsoluteDate = yesterdayAbsoluteLocalDate?.toDate()
    val beforeYesterdayAbsoluteDate = beforeYesterdayAbsoluteLocalDate?.toDate()
    val tomorrowAbsoluteDate = tomorrowAbsoluteLocalDate?.toDate()
    val afterTomorrowAbsoluteDate = afterTomorrowAbsoluteLocalDate?.toDate()

    val yesterdayAbsoluteLongDate = yesterdayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val beforeYesterdayAbsoluteLongDate = beforeYesterdayAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val tomorrowAbsoluteLongDate = tomorrowAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond
    val afterTomorrowAbsoluteLongDate = afterTomorrowAbsoluteLocalDate?.atStartOfDay(ZoneId.systemDefault())?.toInstant()?.epochSecond

    val durationToMatch = if (todayAbsoluteLongDate != null && tomorrowAbsoluteLongDate != null && (startTimestamp >= todayAbsoluteLongDate) && (startTimestamp < tomorrowAbsoluteLongDate)){
        "Today"
    }
    else if (afterTomorrowAbsoluteLongDate != null && tomorrowAbsoluteLongDate != null && (startTimestamp >= tomorrowAbsoluteLongDate) && (startTimestamp < afterTomorrowAbsoluteLongDate)){
        "Tomorrow"
    }
    else if (todayAbsoluteLongDate != null && yesterdayAbsoluteLongDate != null && (startTimestamp >= yesterdayAbsoluteLongDate) && (startTimestamp < todayAbsoluteLongDate)){
        "Yesterday"
    }
    else if (afterTomorrowAbsoluteLongDate != null && (startTimestamp >= afterTomorrowAbsoluteLongDate)){
        "In a few days"
    }
    else if (beforeYesterdayAbsoluteLongDate != null && (startTimestamp <= beforeYesterdayAbsoluteLongDate)){
        "A few days ago"
    }
    else{ emptyString }



    val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(startTimestamp?.let { Instant.ofEpochSecond(it) })

    val matchDate = LocalDate.parse(timeStampAsDateTimeString, longFormat)
    val matchTime = LocalTime.parse(timeStampAsDateTimeString, longFormat)
    val matchDay = matchDate.dayOfWeek.toString().lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }



    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.borderStroke), contentAlignment = Alignment.Center){
            DateMatchDurationText(text = durationToMatch)
        }
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.borderStroke), contentAlignment = Alignment.Center){
            Text(modifier = Modifier, text = "$matchDate", style = MaterialTheme.typography.overline)
        }
        Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.borderStroke), contentAlignment = Alignment.Center){
            Text(modifier = Modifier, text = "$matchTime", style = MaterialTheme.typography.overline)
        }

    }




}