package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.feature_app.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalScrollPager(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    firstPage: @Composable (() -> Unit),
    secondPage: @Composable (() -> Unit),
    thirdPage: @Composable (() -> Unit),
    fourthPage: @Composable (() -> Unit),
    fifthPage: @Composable (() -> Unit),

    ) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Box(modifier = Modifier.height(LocalSpacing.current.topAppBarSize), contentAlignment = Alignment.Center) {
            TabHome(selectedIndex = pagerState.currentPage,
                coroutineScope = coroutineScope,
                onSelect = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(it.ordinal)
                    }
                }
            )
        }

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
                pageSpacing = 0.dp,
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
                        0 -> Box(modifier = Modifier) { firstPage() }

                        1 -> Box(modifier = Modifier) { secondPage() }

                        2 -> Box(modifier = Modifier) { thirdPage() }

                        3 -> Box(modifier = Modifier) { fourthPage() }

                        4 -> Box(modifier = Modifier) { fifthPage() }

                    }
                }
            )
        }

    }
}