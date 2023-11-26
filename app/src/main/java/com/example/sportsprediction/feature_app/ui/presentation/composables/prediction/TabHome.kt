package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import com.example.sportsprediction.core.util.TabPage
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.SelectedTabText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.UnselectedTabText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.SelectedTabColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabHome(
    selectedIndex: Int,
    onSelect: (tabPage: TabPage) -> Unit,
    coroutineScope: CoroutineScope
) {
    val scrollState = rememberScrollState(0)
    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedIndex
    ) {
        /*
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(
                    items = TabPage.values()
                ) { tabPage ->
                    Tab(selected = tabPage.ordinal == selectedIndex,
                        modifier = Modifier.background(
                            color = if (tabPage.ordinal == selectedIndex) Color.LightGray else Color.Transparent),
                        onClick = { onSelect(tabPage) },
                        text = { Text(text = tabPage.title, style = MaterialTheme.typography.h2) }
                    )

                }
            }

        }
        */

        /*
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(
                items = TabPage.values()
            ) { tabPage ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(LocalSpacing.current.noSpacing)
                        .toggleable(value = tabPage.ordinal == selectedIndex,
                            onValueChange = {
                                onSelect(tabPage)
                                tabPage.ordinal != selectedIndex
                            })
                        .background(
                            color = if (tabPage.ordinal == selectedIndex) Color.LightGray else Color.Transparent
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.padding(LocalSpacing.current.small),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = tabPage.title, style = MaterialTheme.typography.h2)
                            Divider(modifier = Modifier.fillMaxWidth()
                                .height(LocalSpacing.current.extraSmall)
                                .background(
                                    Color.Black
                                    //color = if (index == selectedIndex) Color.Black else Color.Transparent
                                ),
                                thickness = LocalSpacing.current.extraSmall,
                                color = Color.Black
                            )
                        }
                    }
            }
        }
        */

        val bringIntoViewRequester = remember { BringIntoViewRequester() }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(
                    state = scrollState,
                    enabled = true,
                    flingBehavior = flingBehavior
                ).onConsumedWindowInsetsChanged {  }
        ) {
            TabPage.values().forEachIndexed { index, tabPage ->

                /*Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = LocalSpacing.current.medium)
                    .toggleable(value = index == selectedIndex,
                        onValueChange = {
                            onSelect(tabPage)
                            index != selectedIndex
                        })
                    .background(
                        color = if (index == selectedIndex) Color.LightGray else Color.Transparent
                    ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(modifier = Modifier.padding(horizontal = LocalSpacing.current.medium)
                            .background(Color.White),
                            text = tabPage.title,
                            style = MaterialTheme.typography.h2)
                    }
                }*/

                Box(
                    modifier = Modifier
                        .padding(bottom = LocalSpacing.current.noPadding)
                        .background(
                            color = // Color.LightGray),
                            if (index == selectedIndex) Color.White else SelectedTabColor
                        )

                    ,
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = (LocalSpacing.current.small))
                            .background(
                                color = //if (index == selectedIndex) Color.LightGray else Color.Transparent
                                SelectedTabColor
                            )
                    ) {
                        Tab(selected = index == selectedIndex,
                            modifier = Modifier.focusable(index ==selectedIndex)
                                .bringIntoViewRequester(bringIntoViewRequester)
                                .onFocusEvent {
                                if (it.isFocused) {
                                    coroutineScope.launch {
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                }
                            }
                                .padding(LocalSpacing.current.noPadding)
                                .background(color = Color.Transparent),
                            //if (index == selectedIndex) Color.LightGray else Color.Transparent),
                            onClick = { onSelect(tabPage) },
                            text = {
                                if (index == selectedIndex) SelectedTabText(text = tabPage.title)
                                    else UnselectedTabText(text = tabPage.title)
                            }
                        )
                        //  }

                    }
                }
            }
        }

    }

}



