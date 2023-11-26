package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.theme.*


@Composable
fun BetSlip(
    closeBetSlip: () -> Unit,
    betList: List<BetSuggestion>,
    confidenceLevel: Double,
    isLoadingConfidenceLevel: Boolean,
    removeBet: (betSuggestion: BetSuggestion) -> Unit,
    removeAllBet: () -> Unit,
    openBetSlip: () -> Unit,
){
    androidx.compose.material.Card(
        modifier = Modifier
            //.padding(horizontal = LocalSpacing.current.default)
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = 15.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(LocalSpacing.current.smallMedium)
    ) {
        val scrollState = rememberScrollState(0)
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(LocalSpacing.current.noPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.noPadding)
                    .background(PrimaryThemeColor)
                    .clickable { closeBetSlip() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = emptyString,
                    tint = Color.White
                )
            }

            Column(modifier = Modifier
                .background(MainBackgroundColor)
                .fillMaxWidth()
                .wrapContentHeight()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.default),
                    contentAlignment = Alignment.Center
                    /*,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically*/
                ) {
                    Box(modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart){
                        Box(modifier = Modifier
                            .background(PrimaryThemeColor, shape = CircleShape)
                            .height(LocalSpacing.current.large)
                            .width(LocalSpacing.current.large)
                            .padding(
                                horizontal = LocalSpacing.current.default,
                                vertical = LocalSpacing.current.small,
                            ),
                            contentAlignment = Alignment.Center){
                            SaveBetSlipText(text = "${betList.size}")
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.small),
                        contentAlignment = Alignment.Center){
                        BetSlipTeamText(text = "Bet SuggestionVariables")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpacing.current.default),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier
                        .weight(1f)
                        .padding(LocalSpacing.current.default),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Box(modifier = Modifier
                            .clickable {
                                removeAllBet()
                                closeBetSlip()
                                openBetSlip()
                            },
                            contentAlignment = Alignment.Center){
                            Icon(
                                modifier = Modifier
                                    .height(LocalSpacing.current.semiLarge)
                                    .width(LocalSpacing.current.semiLarge),
                                imageVector = Icons.Default.Delete,
                                contentDescription = emptyString,
                                tint = Color.Red
                            )
                        }
                        Box(modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.small)
                            .clickable {
                                removeAllBet()
                                closeBetSlip()
                                openBetSlip()
                            },
                            contentAlignment = Alignment.Center){
                            BasicText(text = "Remove All", fontSize = 14.sp, textColor = Color.Red)
                        }

                    }

                    Row(modifier = Modifier
                        .weight(1f)
                        .padding(LocalSpacing.current.default),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Box(modifier = Modifier.background(Color.White, shape = CircleShape),
                            contentAlignment = Alignment.Center){
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = emptyString,
                                tint = Color.Black
                            )
                        }
                        Box(modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center){
                            BasicText(text = "Bet Settings", fontSize = 14.sp, textColor = Color.Black)
                        }

                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MainBackgroundColor)
                        .height(LocalSpacing.current.topAppBarSize)
                        .padding(horizontal = LocalSpacing.current.default),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(LocalSpacing.current.small)
                        .background(
                            PrimaryThemeColor,
                            shape = RoundedCornerShape(LocalSpacing.current.small)
                        ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center){
                            BasicText(text = "Single Event", fontSize = 18.sp, textColor = Color.White)
                        }

                    }
                    Row(modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(LocalSpacing.current.small)
                        .background(
                            BetorsConfidenceBackground,
                            shape = RoundedCornerShape(LocalSpacing.current.small)
                        ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center){
                            BasicText(text = "Multi Events", fontSize = 18.sp, textColor = Color.Black)
                        }

                    }

                }


            }



            //Divider()

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .verticalScroll(state = scrollState, enabled = true),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                betList.forEach { betSuggestion ->
                    BetSlipCard(
                        mainTeamName = betSuggestion.mainTeamName ?: emptyString,
                        opposingTeamName = betSuggestion.opposingTeamName ?: emptyString,
                        playingLocation = betSuggestion.mainTeamPlayingLocation ?: Constants.Home,
                        marketName = betSuggestion.marketName ?: emptyString,
                        numerator = betSuggestion.numerator ?: "0",
                        denominator = betSuggestion.denominator ?: "1",
                        removeBet = { removeBet(betSuggestion) },
                        openBetSlip = openBetSlip,
                        closeBetSlip = closeBetSlip
                    )
                    Divider()
                }


                Column(modifier = Modifier.background(MainBackgroundColor),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    var totalNumerator: Double =  0.0
                    var totalDenominator: Double =  0.0
                    betList.forEach { betBuilder ->
                        val numerator = betBuilder.numerator?.toDouble() ?: 0.0
                        val denominator = betBuilder.denominator?.toDouble() ?: 0.0
                        totalNumerator += numerator
                        totalDenominator += denominator
                    }

                    Row(modifier = Modifier
                        //.background(DateEventHeaderBackgroundColor, shape = RoundedCornerShape(LocalSpacing.current.default))
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
                            BasicText(
                                text = "Average streak is ${(totalNumerator.div(totalDenominator)).times(100.0).toString().take(4)}% (${totalNumerator.toInt()} out of ${totalDenominator.toInt()} matches)",
                                fontSize = 15.sp,
                                textColor = Color.Black
                            )
                        }

                    }

                    Row(modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .background(
                            BetorsConfidenceBackground,
                            shape = RoundedCornerShape(LocalSpacing.current.small)
                        )
                        .fillMaxWidth()
                        .height(LocalSpacing.current.topAppBarSize),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(modifier = Modifier
                            .weight(4f)
                            .fillMaxHeight()
                            .padding(horizontal = LocalSpacing.current.default),
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

                            BasicText(
                                text = "Betor's Confidence Probability",
                                fontSize = 16.sp,
                                textColor = Color.Black
                            )
                        }

                        Box(modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.default)
                            .weight(1f)
                            .fillMaxHeight(),
                            contentAlignment = Alignment.CenterEnd){
                            if(isLoadingConfidenceLevel)
                                CircularProgressIndicator(color = MainBackgroundColor)
                            else BasicText(
                                text = "${confidenceLevel.toString().take(5)} %",
                                fontSize = 16.sp,
                                textColor = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                    Row(modifier = Modifier
                        .background(MainBackgroundColor)
                        .fillMaxWidth()
                        .height(LocalSpacing.current.topAppBarSize)
                    ) {
                        Row(modifier = Modifier
                            .background(SelectedTabColor)
                            .fillMaxHeight()
                            //.padding(LocalSpacing.current.small)
                            .weight(1.5f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            SaveBetSlipText(text = "Save SuggestionVariables")
                        }

                        Row(modifier = Modifier
                            .background(TabRowBackgroundColor)
                            .fillMaxHeight()
                            //.padding(LocalSpacing.current.small)
                            .weight(1f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(modifier = Modifier.padding(LocalSpacing.current.small),
                                imageVector = Icons.Default.Share,
                                contentDescription = emptyString,
                                tint = Color.White)
                            SaveBetSlipText(text = "Share")
                        }

                    }
                }

            }

            //Spacer(modifier = Modifier.height(LocalSpacing.current.default))



        }
    }
}