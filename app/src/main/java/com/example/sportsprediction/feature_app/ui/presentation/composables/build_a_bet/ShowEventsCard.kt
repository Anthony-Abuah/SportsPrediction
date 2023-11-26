package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.ListOfTeamEvent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing


@Composable
fun ShowEventsCard(
    teamName: String?,
    pastEvents: ListOfTeamEvent,
    openStats: (eventId: Int, showEventStats: Boolean) -> Unit,
){
    var showPastEvent by remember { mutableStateOf(false) }

    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.noPadding)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = LocalSpacing.current.large,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(LocalSpacing.current.noPadding)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(LocalSpacing.current.topAppBarSize)
                .clickable {
                    showPastEvent = !showPastEvent
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(5f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    BasicText(text = teamName.toString(), fontSize = 16.sp, textColor = Color.Black)
                }


                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            showPastEvent = !showPastEvent
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (showPastEvent) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "", tint = Color.Black
                    )
                }
            }

            AnimatedVisibility(visible = showPastEvent) {
                PastEventsScoresCard(
                    pastEvents = pastEvents
                ) { eventId, openStats ->
                    openStats(eventId, openStats)
                }
            }
        }

    }

}