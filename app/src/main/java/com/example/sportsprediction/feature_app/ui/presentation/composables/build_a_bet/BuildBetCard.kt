package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildBetCard(
    country: String,
    preferredEvents: List<EventsEntity>,
    selectedEvents: MutableList<EventsEntity>,
    expandedEvents:  Map<String, List<EventsEntity>>,
    getIsSelected: (preferredEvents: List<EventsEntity>) -> Unit,
    getIsExpanded: (isExpanded: Boolean, preferredEvents: Map<String,List<EventsEntity>>) -> Unit,
) {

    val theseSelectedEvents by remember { mutableStateOf(selectedEvents) }

    var isSelected by remember { mutableStateOf(selectedEvents.containsAll(preferredEvents)) }
    var isExpanded by remember { mutableStateOf(false) }
    val expandedPreferredEvents by remember { mutableStateOf(expandedEvents.toMutableMap()) }

    val contentColor = Color.Black
    val containerColor = if(isSelected) Color.LightGray else Color.White
    val cardElevation = if (isSelected) LocalSpacing.current.small else LocalSpacing.current.large

    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = containerColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        onClick = {
            isSelected =! isSelected
            if (isSelected) {
                theseSelectedEvents.addAll(preferredEvents)
                getIsSelected(theseSelectedEvents)
            }else{
                theseSelectedEvents.removeAll(preferredEvents)
                getIsSelected(theseSelectedEvents)
            }
        },
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation )
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(modifier = Modifier
                .width(LocalSpacing.current.topAppBarSize)
                .height(LocalSpacing.current.topAppBarSize)
                .padding(LocalSpacing.current.small),
                contentAlignment = Alignment.Center
            ){
                Image(modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.flag),
                    contentDescription = "",
                )
            }

            Box(modifier = Modifier
                .weight(1f)
                .height(LocalSpacing.current.extraLarge)
                .padding(LocalSpacing.current.small),
                contentAlignment = Alignment.CenterStart
            ){
                BasicText(text = country, fontSize = 16.sp, textColor = Color.Black)
            }

            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(LocalSpacing.current.small),
                contentAlignment = Alignment.Center
            ){
                BasicText(text = "${preferredEvents.size}", fontSize = 16.sp, textColor = contentColor)
            }

            if (!isSelected) {
                Box(modifier = Modifier
                    .width(LocalSpacing.current.large)
                    .height(LocalSpacing.current.large)
                    .padding(LocalSpacing.current.small)
                    .clickable(
                        enabled = true,
                        onClick = {
                            isExpanded = !isExpanded

                            expandedPreferredEvents[country] = preferredEvents

                            getIsExpanded(isExpanded, expandedPreferredEvents)

                        }
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(
                                width = LocalSpacing.current.extraSmall,
                                color = PrimaryThemeColor,
                                shape = CircleShape
                            ),
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = PrimaryThemeColor
                    )
                }
            }

            Spacer(modifier = Modifier.width(LocalSpacing.current.small))


        }
    }

}