package com.example.sportsprediction.feature_app.ui.presentation.composables.date_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.DateDisplayRow
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.ShowDateDialog
import com.example.sportsprediction.feature_app.ui.theme.DateRowBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate


@Composable
fun DateRow(
    dateSelected: (date: LocalDate) -> Unit
){

    val dateDialogState = rememberMaterialDialogState()
    var date by remember { mutableStateOf(LocalDate.now()) }

    Row(modifier = Modifier.fillMaxWidth()
        .background(DateRowBackgroundColor)
    ) {
        Box(modifier = Modifier.weight(9f), contentAlignment = Alignment.Center){
            DateDisplayRow(selectedDate = date,
                dateSelected = {selectedToggleDate->
                    date = selectedToggleDate
                    dateSelected(selectedToggleDate)
                }
            )
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
            IconButton(
                modifier = Modifier
                    .padding(LocalSpacing.current.extraSmall)
                    .background(Color.Transparent),
                onClick = { dateDialogState.show() }
            ) {
                Icon(painter = painterResource(id = R.drawable.calendar),
                    tint = Color.White,
                    contentDescription = Constants.emptyString)
            }
        }

    }

    ShowDateDialog(
        selectedDate = { theSelectedDate->
            date = theSelectedDate
            dateSelected(theSelectedDate)
        },
        initialDate = LocalDate.now(),
        dateDialogState = dateDialogState
    )


}