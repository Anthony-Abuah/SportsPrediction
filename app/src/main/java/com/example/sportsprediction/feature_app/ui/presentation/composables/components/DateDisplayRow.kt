package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.feature_app.ui.theme.DateRowBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.SelectedDateColor
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun DateDisplayRow(
    selectedDate: LocalDate,
    dateSelected: (date: LocalDate) -> Unit
){
    fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
    val shortDateFormatter = DateTimeFormatter.ofPattern(Constants.shortDateFormat)


    val selectedDateString = shortDateFormatter.format(selectedDate)
    val localSelectedDate = LocalDate.parse(selectedDateString, shortDateFormatter)
    val dateTodayString = shortDateFormatter.format(LocalDate.now())
    val today = LocalDate.parse(dateTodayString, shortDateFormatter)

    val dates = listOf(
        today.minusDays(3),
        today.minusDays(2),
        today.minusDays(1),
        today,
        today.plusDays(1),
        today.plusDays(2),
        today.plusDays(3),
        today.plusDays(4),
    )


    var isSelected by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val scrollState = rememberScrollState (0)


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = LocalSpacing.current.medium)
        .horizontalScroll(state = scrollState, enabled = true)) {
        dates.forEach{date->

            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    contentColor = Color.Black,
                    containerColor = if((date == localSelectedDate)) SelectedDateColor else DateRowBackgroundColor),
                modifier = Modifier
                    .height(LocalSpacing.current.topAppBarSize)
                    .padding(LocalSpacing.current.small)
                    .focusable(enabled = true, interactionSource = interactionSource),
                elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.small)
            ){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(LocalSpacing.current.small)
                    .toggleable(value = isSelected,
                        onValueChange = {
                            dateSelected(date)
                            isSelected = !isSelected
                        }),
                    contentAlignment = Alignment.Center
                ){
                    Column(modifier = Modifier.padding(LocalSpacing.current.noPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val day = date.dayOfWeek.toString().uppercase(Locale.getDefault())
                        val monthOfTheYear = if(date.monthValue < 10) "0${date.monthValue}" else "${date.monthValue}"
                        val dayOfTheMonth = if (date.dayOfMonth < 10) "0${date.dayOfMonth}" else "${date.dayOfMonth}"
                        BasicText(text = day.take(3), fontSize = 14.sp, textColor = Color.White)
                        BasicText(text = "$dayOfTheMonth.$monthOfTheYear", fontSize = 12.sp, textColor = Color.White)

                    }
                }
            }
        }
        
    }
    
}