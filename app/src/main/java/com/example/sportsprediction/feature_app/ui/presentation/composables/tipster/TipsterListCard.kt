package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun TipsterListCard(
    navigateToTipsterScreen: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.small)
            .height(200.dp)
            .fillMaxWidth(),
        elevation = LocalSpacing.current.medium,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(LocalSpacing.current.medium)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.small)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    elevation = LocalSpacing.current.small,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(LocalSpacing.current.medium)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.Person,
                        contentDescription = ""
                    )
                }

                Column(modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(100.dp)
                    .padding(LocalSpacing.current.smallMedium)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicText(
                            text = "Anthony Bet Tips",
                            fontSize = 18.sp,
                            textColor = Color.Black
                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicText(
                            text = "Subscribed",
                            fontSize = 14.sp,
                            textColor = Color.Gray
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f)
                        .padding(vertical = LocalSpacing.current.noPadding),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            Icon(
                                modifier = Modifier
                                    .height(LocalSpacing.current.medium.plus(LocalSpacing.current.small))
                                    .width(LocalSpacing.current.medium.plus(LocalSpacing.current.small)),
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = "",
                                tint = PrimaryThemeColor
                            )
                        }

                        Box(
                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            BasicText(
                                text = "Subscribers: 19.2K",
                                fontSize = 16.sp,
                                textColor = Color.Black
                            )
                        }

                    }

                }
            }


            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)
                    .padding(top = LocalSpacing.current.smallMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicText(
                            text = "User Rating",
                            fontSize = 16.sp,
                            textColor = Color.Black
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicText(
                                text = "7.2",
                                fontSize = 14.sp,
                                textColor = Color.Gray
                            )
                        }

                        Box(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.small, start = LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                modifier = Modifier.width(LocalSpacing.current.medium)
                                    .height(LocalSpacing.current.medium),
                                imageVector = Icons.Default.Star,
                                contentDescription = "",
                                tint = PrimaryThemeColor
                            )
                        }


                    }

                }

                Spacer(modifier = Modifier.padding(LocalSpacing.current.small)
                    .width(LocalSpacing.current.borderStroke)
                    .height(LocalSpacing.current.topAppBarSize)
                    .background(Color.LightGray)
                )

                Column(modifier = Modifier.weight(1f)
                    .padding(top = LocalSpacing.current.smallMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicText(
                            text = "Oct Rating",
                            fontSize = 16.sp,
                            textColor = Color.Black
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            BasicText(
                                text = "8.2",
                                fontSize = 14.sp,
                                textColor = Color.Gray
                            )
                        }

                        Box(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.small, start = LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                modifier = Modifier.width(LocalSpacing.current.medium)
                                    .height(LocalSpacing.current.medium),
                                imageVector = Icons.Default.Star,
                                contentDescription = "",
                                tint = PrimaryThemeColor
                            )
                        }


                    }

                }

                Spacer(modifier = Modifier.padding(LocalSpacing.current.small)
                    .width(LocalSpacing.current.borderStroke)
                    .height(LocalSpacing.current.topAppBarSize)
                    .background(Color.LightGray)
                )

                Column(modifier = Modifier.weight(1f)
                    .padding(top = LocalSpacing.current.smallMedium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ){
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BasicText(
                            text = "Price Range",
                            fontSize = 16.sp,
                            textColor = Color.Black
                        )
                    }

                    Row(modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        Box(
                            modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                            contentAlignment = Alignment.Center
                        ) {
                            BasicText(
                                text = "GHS 200.00",
                                fontSize = 14.sp,
                                textColor = Color.Gray
                            )
                        }

                        /*
                        Box(
                            modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = ""
                            )
                        }
                        */

                    }

                }

            }

        }

    }
}