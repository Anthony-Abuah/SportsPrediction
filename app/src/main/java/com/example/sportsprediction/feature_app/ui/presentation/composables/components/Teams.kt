package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.feature_app.ui.theme.*


@Composable
fun HomeTeam(
    homeTeam: String,
    homeOdds: String
) {
    Box(
        modifier = Modifier.padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.requiredHeight(125.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(2f))

            Box(modifier = Modifier.weight(3f)){
                Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.home), contentDescription = "home team")
            }

            Box(modifier = Modifier.weight(4f), contentAlignment = Alignment.Center){
                BasicText(text = homeTeam, fontSize = 12.sp, textColor = TeamTextColor)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    contentColor = DisplayOddsCardContentColor,
                    containerColor = DisplayOddsCardBackgroundColor,
                ),
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TitleText(
                        title = homeOdds,
                        fontSize = 15.sp,
                        textColor = DisplayOddsCardContentColor
                    )
                }
            }
        }
    }

}

@Composable
fun AwayTeam(
    awayTeam: String,
    awayOdds: String
) {
    Box(
        modifier = Modifier.padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.requiredHeight(125.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(2f))

            Box(modifier = Modifier.weight(3f)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.away),
                    contentDescription = "away team"
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Box(modifier = Modifier.weight(4f), contentAlignment = Alignment.Center){
                BasicText(text = awayTeam, fontSize = 12.sp, textColor = TeamTextColor)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    contentColor = DisplayOddsCardContentColor,
                    containerColor = DisplayOddsCardBackgroundColor,
                ),
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TitleText(
                        title = awayOdds,
                        fontSize = 15.sp,
                        textColor = DisplayOddsCardContentColor
                    )
                }

            }
        }

    }
}

@Composable
fun Draw(
    market: String,
    time: String,
    date: String,
    drawOdds: String
) {
    Box(
        modifier = Modifier.padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.requiredHeight(125.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier.weight(2f)){
                BasicText(text = date, fontSize = 14.sp, textColor = DateEventCardContentColor)
            }

            Box(modifier = Modifier.weight(2.5f)){
                TitleText(title = time, fontSize = 14.sp, textColor = DateEventCardContentColor)
            }

            Spacer(modifier = Modifier.width(4.dp))

            
            Box(modifier = Modifier.weight(2f)) {
                BasicText(text = market, fontSize = 13.sp, textColor = MarketTextColor)
            }
            Spacer(modifier = Modifier.height(4.dp))

            Card(
                shape = MaterialTheme.shapes.small,
                colors = CardDefaults.cardColors(
                    contentColor = DisplayOddsCardContentColor,
                    containerColor = DisplayOddsCardBackgroundColor,
                ),
                modifier = Modifier
                    .requiredHeight(40.dp)
                    .fillMaxWidth()
                    .padding(2.dp)
                    .align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TitleText(
                        title = drawOdds,
                        fontSize = 15.sp,
                        textColor = DisplayOddsCardContentColor
                    )
                }
            }
        }
    }
}


