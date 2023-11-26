package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MatchStartTimeDialog(
    coroutineScope: CoroutineScope,
    getMatchStartTimePreferredEvent: (value: Long) -> Unit,
){
    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.noPadding)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(LocalSpacing.current.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = RectangleShape
    ) {
        AlertDialogMatchStartTimePage{
            coroutineScope.launch { getMatchStartTimePreferredEvent(it) }
        }
    }
}


