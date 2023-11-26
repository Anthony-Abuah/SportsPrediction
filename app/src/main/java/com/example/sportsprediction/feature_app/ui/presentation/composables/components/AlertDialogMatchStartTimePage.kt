package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.All
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun AlertDialogMatchStartTimePage (
    getStartTimeValue : (startTime: Long) -> Unit
) {

    /*
    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
    val shortDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val dateString = shortDateFormatter.format(dateToLocalDate)
    val localDate = LocalDate.parse(dateString, shortDateFormatter)
    val longDate = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    val newDate = localDate.toDate()
    */


    val now = LocalDateTime.now()
    val nowInSeconds = now.toEpochSecond(ZoneOffset.UTC)
    val dateTime = LocalDateTime.ofEpochSecond(nowInSeconds, 0, ZoneOffset.UTC)

    val test_timestamp: Long = 1499070300

    val triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(test_timestamp), TimeZone.getDefault().toZoneId())


    val localDateTimeNow = LocalDateTime.now()
    val in_1_hour = localDateTimeNow.plusHours(1).toEpochSecond(ZoneOffset.UTC)
    val in_2_hours = localDateTimeNow.plusHours(2).toEpochSecond(ZoneOffset.UTC)
    val in_3_hours = localDateTimeNow.plusHours(3).toEpochSecond(ZoneOffset.UTC)
    val in_6_hours = localDateTimeNow.plusHours(6).toEpochSecond(ZoneOffset.UTC)
    val in_12_hours = localDateTimeNow.plusHours(12).toEpochSecond(ZoneOffset.UTC)
    val today = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    val tomorrow = LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    val yesterday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    val all = (-1).toLong()


    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(all) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = All)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(in_1_hour) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "In 1 hour")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(in_2_hours) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "In 2 hours")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(in_3_hours) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "In 3 hours")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(in_6_hours) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "In 6 hours")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(in_12_hours) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "In 12 hours")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(today) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "Today")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(yesterday) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "Yesterday")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getStartTimeValue(tomorrow) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = "Tomorrow")
            }
        }

    }

}
