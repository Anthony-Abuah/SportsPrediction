package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.StatsTab
import com.example.sportsprediction.feature_app.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventStatsPager(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    fullTimePage: @Composable () -> Unit,
    firstHalfPage: @Composable () -> Unit,
    secondHalfPage: @Composable () -> Unit
) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Box(modifier = Modifier.height(LocalSpacing.current.topAppBarSize)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            StatsTab(selectedIndex = pagerState.currentPage,
                coroutineScope = coroutineScope,
                onSelect = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it.ordinal)
                    }
                }
            )
        }

        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(1f),
                state = pagerState,
                pageSpacing = LocalSpacing.current.small,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(0.dp),
                beyondBoundsPageCount = 0,
                pageSize = PageSize.Fill,
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                key = null, //{ index-> },
                pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                    Orientation.Horizontal
                ),
                pageContent = { index ->
                    when (index) {
                        0 -> Box(modifier = Modifier.fillMaxSize(1f)) { fullTimePage() }

                        1 -> Box(modifier = Modifier.fillMaxSize(1f)) { firstHalfPage() }

                        2 -> Box(modifier = Modifier.fillMaxSize(1f)) { secondHalfPage() }
                    }
                }
            )
        }

    }
}