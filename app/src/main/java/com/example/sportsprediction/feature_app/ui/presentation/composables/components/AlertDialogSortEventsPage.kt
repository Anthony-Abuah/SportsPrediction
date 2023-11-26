package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.*
import java.util.*

@Composable
fun AlertDialogMatchSortEventsPage (
    getSortValue : (sort: String) -> Unit
) {
    val default = "Default"
    val ascendingTime = "Ascending Start Time"
    val descendingTime = "Descending Start Time"

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getSortValue(default) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = default)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getSortValue(ascendingTime) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = ascendingTime)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { getSortValue(descendingTime) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterStart
            ) {
                StartTimeFilterText(text = descendingTime)
            }
        }


    }

}
