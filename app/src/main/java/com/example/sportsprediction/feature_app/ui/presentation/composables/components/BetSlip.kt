package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Bet_Suggestions
import com.example.sportsprediction.core.util.Constants.Remove_All
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.getTotalStreakPercentage
import com.example.sportsprediction.core.util.Functions.getTotalStreaks
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing


@Composable
fun BetSlip(
    closeBetSlip: () -> Unit,
    betList: List<BetSuggestion>,
    mainTeamName: String,
    isLoadingConfidenceLevel: Boolean,
    removeBet: (betSuggestion: BetSuggestion) -> Unit,
    removeAllBet: () -> Unit,
    openBetSlip: () -> Unit,
){
    var thisBetList by remember {
        mutableStateOf(betList)
    }

    var betIsRemoved by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(LocalSpacing.current.noElevation),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RectangleShape
    ) {
        val scrollState = rememberScrollState(0)
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(LocalSpacing.current.noPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Column(modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.default),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .requiredWidth(LocalSpacing.current.large)
                        .requiredHeight(LocalSpacing.current.large)
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            shape = CircleShape
                        )
                        .padding(LocalSpacing.current.small)
                        //.wrapContentSize()
                        .aspectRatio(1f / 1f),
                        contentAlignment = Alignment.Center
                    ){
                        val betListSize = "${thisBetList.size}"
                        Text(
                            text = betListSize,
                            fontWeight= FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(modifier = Modifier
                        .weight(Bet_Suggestions.length.toFloat())
                        .padding(LocalSpacing.current.small),
                        contentAlignment = Alignment.Center){
                        Text(
                            text = Bet_Suggestions,
                            fontWeight= FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(modifier = Modifier
                        .weight(Remove_All.length.toFloat())
                        .padding(LocalSpacing.current.default),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Box(modifier = Modifier
                            .clickable { removeAllBet() },
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                modifier = Modifier
                                    .height(LocalSpacing.current.semiLarge)
                                    .width(LocalSpacing.current.semiLarge),
                                imageVector = Icons.Default.Delete,
                                contentDescription = emptyString,
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                        Box(modifier = Modifier
                            //.padding(horizontal = LocalSpacing.current.small)
                            .clickable { removeAllBet() },
                            contentAlignment = Alignment.Center
                        ){
                            Text(
                                text = Remove_All,
                                fontWeight= FontWeight.Normal,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }

            }

            Column(
                modifier = Modifier
                    .heightIn(min = LocalSpacing.current.small, max = 400.dp)
                    .verticalScroll(state = scrollState, enabled = true),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (betIsRemoved){
                    thisBetList = betList
                    betIsRemoved = false
                }
                if (thisBetList.isEmpty()) {
                    closeBetSlip()
                }
                else {
                    thisBetList.forEach { betSuggestion ->
                        BetSlipCard(
                            mainTeamName = betSuggestion.mainTeamName ?: emptyString,
                            opposingTeamName = betSuggestion.opposingTeamName ?: emptyString,
                            playingLocation = betSuggestion.mainTeamPlayingLocation ?: Constants.Home,
                            marketName = betSuggestion.marketName ?: emptyString,
                            numerator = betSuggestion.numerator ?: "0",
                            denominator = betSuggestion.denominator ?: "1",
                            removeBet = {
                                betIsRemoved = true
                                removeBet(betSuggestion)
                            },
                            openBetSlip = openBetSlip,
                            closeBetSlip = closeBetSlip
                        )
                        Divider(
                            thickness = LocalSpacing.current.thinBorder,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

            }


            Column(modifier = Modifier.background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Row(modifier = Modifier
                    .padding(LocalSpacing.current.small)
                    .height(LocalSpacing.current.topAppBarSize)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.small)
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(LocalSpacing.current.semiLarge)
                                .height(LocalSpacing.current.semiLarge),
                            imageVector = Icons.Filled.Info,
                            contentDescription = emptyString,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(LocalSpacing.current.small))
                        val totalNumerator =  getTotalStreaks(betList).first()
                        val totalDenominator =  getTotalStreaks(betList).last()
                        val totalStreakPercentage =  getTotalStreakPercentage(betList)
                        Text(
                            modifier = Modifier
                                .wrapContentWidth(align = Alignment.Start)
                                .padding(LocalSpacing.current.small),
                            text = "Total streak",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(LocalSpacing.current.small),
                            text = "$totalNumerator out of $totalDenominator ($totalStreakPercentage%)",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.End,
                            maxLines = 2
                        )
                    }

                }

                Row(modifier = Modifier
                    .padding(LocalSpacing.current.small)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.shapes.small
                    )
                    .fillMaxWidth()
                    .height(LocalSpacing.current.topAppBarSize),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier
                        .weight(4f)
                        .fillMaxHeight()
                        .padding(start = LocalSpacing.current.default, end = LocalSpacing.current.small),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(modifier = Modifier
                            .width(LocalSpacing.current.semiLarge)
                            .height(LocalSpacing.current.semiLarge),
                            imageVector = Icons.Default.Info,
                            contentDescription = emptyString,
                            tint = Color.Gray)

                        Spacer(modifier = Modifier.width(LocalSpacing.current.small))

                        Text(
                            modifier = Modifier
                                .wrapContentWidth(align = Alignment.Start)
                                .padding(LocalSpacing.current.small),
                            text = "$mainTeamName win probability",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 2
                        )
                        Box(modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.small)
                            .weight(1f)
                            .fillMaxHeight(),
                            contentAlignment = Alignment.CenterEnd){
                            if(isLoadingConfidenceLevel) CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
                            else Text(
                                text = "55.5%",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.End,
                                maxLines = 2
                            )
                        }
                    }


                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                Row(modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth()
                    .height(LocalSpacing.current.topAppBarSize),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        //.background(MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxHeight()
                        .weight(1.5f),
                    contentAlignment = Alignment.Center) {
                        Text(
                            modifier = Modifier,
                            text = "Save Suggestions",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }

                    Row(modifier = Modifier
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .fillMaxHeight()
                        .weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(modifier = Modifier.padding(LocalSpacing.current.small),
                            imageVector = Icons.Default.Share,
                            contentDescription = emptyString,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Box(modifier = Modifier
                            .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                text = "Share",
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}