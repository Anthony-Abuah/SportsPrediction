package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.DateDisplayRow
import java.time.LocalDate
import java.util.*


@Composable
fun DateRow(
    selectedDate: Date,
    getDateSelected: (date: LocalDate) -> Unit
){

    var date by remember { mutableStateOf(LocalDate.now()) }

    Row(modifier = Modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            DateDisplayRow(selectedDate = selectedDate,
                getSelectedDate = { selectedToggleDate->
                    date = selectedToggleDate
                    getDateSelected(selectedToggleDate)
                }
            )
        }

    }
}