package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sportsprediction.core.util.Constants.ascendingTime
import com.example.sportsprediction.core.util.Constants.popular
import com.example.sportsprediction.core.util.Constants.descendingTime
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun AlertDialogMatchSortEventsPage (
    getSortValue : (sort: String) -> Unit
) {
    val sortFilters = listOf(popular, ascendingTime, descendingTime)

    Column(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        sortFilters.forEach { sortFilter->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { getSortValue(sortFilter) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth().padding(LocalSpacing.current.smallMedium),
                    contentAlignment = Alignment.CenterStart
                ) {
                    StartTimeFilterText(text = sortFilter)
                }
            }
        }


    }

}
