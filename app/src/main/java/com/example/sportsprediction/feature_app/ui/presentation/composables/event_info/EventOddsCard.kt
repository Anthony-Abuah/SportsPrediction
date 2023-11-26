package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.core.util.Constants.FullTime
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.gambleResponsibly
import com.example.sportsprediction.core.util.Constants.moreOdds
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.*

@Composable
fun EventOddsCard(
    odds1: String?,
    oddsX: String?,
    odds2: String?,
    contentColor: Color,
    backgroundColor: Color
) {



    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.default),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Column(modifier = Modifier){

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = LocalSpacing.current.medium, top = LocalSpacing.current.medium), contentAlignment = Alignment.CenterStart){
                Box(modifier = Modifier.fillMaxWidth()){
                    BasicSemiBoldText(text = Full_Time, fontSize = 12.sp, textColor = Color.Black)
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.smallMedium)) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            LocalSpacing.current.borderStroke,
                            Color.LightGray,
                            MaterialTheme.shapes.small
                        )
                        .background(Color.White)
                        .padding(LocalSpacing.current.extraSmall),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsNameText(text = "1")
                    }
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsDisplayText(text = odds1 ?: emptyString)
                    }
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            LocalSpacing.current.borderStroke,
                            Color.LightGray,
                            MaterialTheme.shapes.small
                        )
                        .background(Color.White)
                        .padding(LocalSpacing.current.extraSmall),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsNameText(text = "X")
                    }
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsDisplayText(text = oddsX ?: emptyString)
                    }
                }

                Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            LocalSpacing.current.borderStroke,
                            Color.LightGray,
                            MaterialTheme.shapes.small
                        )
                        .background(Color.White)
                        .padding(LocalSpacing.current.extraSmall),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsNameText(text = "2")
                    }
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                        OddsDisplayText(text = odds2 ?: emptyString)
                    }
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.smallMedium)) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart){
                    BasicText(text = gambleResponsibly, fontSize = 12.sp, textColor = Color.Gray)
                }
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(end = LocalSpacing.current.smallMedium), contentAlignment = Alignment.CenterEnd){
                    BasicSemiBoldText(text = moreOdds, fontSize = 13.sp, textColor = Color.Blue)
                }
            }

        }
    }

}