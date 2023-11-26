package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.first_Half
import com.example.sportsprediction.core.util.Constants.second_Half
import com.example.sportsprediction.core.util.StatsTabs
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventStatsCard(
    eventStats: EventStatsEntity,
    isLoadingEventStats: Boolean,
    closeStats: (closeStats: Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { StatsTabs.values().size }


    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)
            .background(MainBackgroundColor)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.large)
            .background(PrimaryThemeColor)
            .clickable {
                closeStats(false)
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = emptyString, tint = Color.White)
        }

        if (isLoadingEventStats){
            Box(modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        contentAlignment = Alignment.TopCenter) {
            EventStatsPager(
                pagerState = pagerState,
                coroutineScope = coroutineScope,
                fullTimePage = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        StatsContent(eventStats = eventStats, period = Full_Time)
                    }
                },
                firstHalfPage = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        StatsContent(eventStats = eventStats, period = first_Half)
                    }
                },
                secondHalfPage = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        StatsContent(eventStats = eventStats, period = second_Half)
                    }
                }
            )
        }
    }

}
