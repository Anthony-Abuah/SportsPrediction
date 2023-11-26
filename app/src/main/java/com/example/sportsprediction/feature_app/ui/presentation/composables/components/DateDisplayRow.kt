package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Composable
fun DateDisplayRow(
    selectedDate: Date,
    getSelectedDate: (date: LocalDate) -> Unit
){
    val selectedDateToLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val selectedDateString = shortDateFormatter.format(selectedDateToLocalDate)
    val selectedLocalDate = LocalDate.parse(selectedDateString, shortDateFormatter)

    val dateTodayString = shortDateFormatter.format(LocalDate.now())
    val today = LocalDate.parse(dateTodayString, shortDateFormatter)

    val dates = listOf(
        today.minusDays(8),
        today.minusDays(7),
        today.minusDays(6),
        today.minusDays(5),
        today.minusDays(4),
        today.minusDays(3),
        today.minusDays(2),
        today.minusDays(1),
        today,
        today.plusDays(1),
        today.plusDays(2),
        today.plusDays(3),
        today.plusDays(4),
        today.plusDays(5),
        today.plusDays(6),
        today.plusDays(7),
        today.plusDays(8),
    )

    val scrollState = rememberScrollState (initial = 0)

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = LocalSpacing.current.small)
        .horizontalScroll(state = scrollState, enabled = true)) {
        dates.forEach{date->
            val day = date.dayOfWeek.toString().uppercase(Locale.getDefault())
            val monthOfTheYear = if(date.monthValue < 10) "0${date.monthValue}" else "${date.monthValue}"
            val dayOfTheMonth = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else "${date.dayOfMonth}"

            DateChip(
                currentDate = date,
                selectedLocalDate = selectedLocalDate,
                day = day.take(3),
                date = "$dayOfTheMonth.$monthOfTheYear",
                getToggledValue = { thisChipIsSelected->
                    if (thisChipIsSelected){
                        getSelectedDate(date)
                    }
                }
            )

        }
        
    }
    
}