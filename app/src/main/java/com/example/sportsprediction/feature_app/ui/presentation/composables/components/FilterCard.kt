package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Time
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun FilterCard(
    matchStartTimeDialogIsOpened: Boolean,
    leaguesSelectionDialogIsOpened: Boolean,
    sortEventsDialogIsOpened: Boolean,
    onClickMatchStartTime: () -> Unit,
    onClickLeagues: () -> Unit,
    onClickSortOrder: () -> Unit,

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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(LocalSpacing.current.topAppBarSize)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        LocalSpacing.current.borderStroke,
                        MaterialTheme.colorScheme.background
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = LocalSpacing.current.small)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            onClickMatchStartTime()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .padding(horizontal = LocalSpacing.current.small),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (matchStartTimeDialogIsOpened)
                            Text(
                                text = Time,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        else
                            Text(
                                text = Time,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(LocalSpacing.current.noPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Icon(
                            modifier = Modifier
                                .width(LocalSpacing.current.large)
                                .height(LocalSpacing.current.large),
                            painter = painterResource(
                                id = if (matchStartTimeDialogIsOpened) R.drawable.drop_up
                                else R.drawable.drop_down
                            ),
                            contentDescription = Constants.emptyString,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .width(LocalSpacing.current.borderStroke)
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxHeight()
                )

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = LocalSpacing.current.small)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            onClickLeagues()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .padding(horizontal = LocalSpacing.current.small),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (leaguesSelectionDialogIsOpened)
                            Text(
                                text = Constants.Leagues,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        else
                            Text(
                                text = Constants.Leagues,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(LocalSpacing.current.noPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Icon(
                            modifier = Modifier
                                .width(LocalSpacing.current.large)
                                .height(LocalSpacing.current.large),
                            painter = painterResource(
                                id = if (leaguesSelectionDialogIsOpened) R.drawable.drop_up
                                else R.drawable.drop_down
                            ),
                            contentDescription = Constants.emptyString,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }


                Spacer(
                    modifier = Modifier
                        .width(LocalSpacing.current.borderStroke)
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxHeight()
                )

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = LocalSpacing.current.small)
                        .background(color = MaterialTheme.colorScheme.surfaceVariant)
                        .clickable {
                            onClickSortOrder()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .padding(horizontal = LocalSpacing.current.small),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (sortEventsDialogIsOpened)
                            Text(
                                text = Constants.SortOrder,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        else
                            Text(
                                text = Constants.SortOrder,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(LocalSpacing.current.noPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.Icon(
                            modifier = Modifier
                                .width(LocalSpacing.current.large)
                                .height(LocalSpacing.current.large),
                            painter = painterResource(
                                id = if (sortEventsDialogIsOpened) R.drawable.drop_up
                                else R.drawable.drop_down
                            ),
                            contentDescription = Constants.emptyString,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }


            }

        }
    }
}