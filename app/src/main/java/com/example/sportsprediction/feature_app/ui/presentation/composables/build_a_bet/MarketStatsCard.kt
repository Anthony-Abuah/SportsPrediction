package com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText

@Composable
fun MarketStatsCard(
    homeScore: String,
    awayScore: String,
    statName: String                    
){
    val thisHomeScore = try {
        homeScore.toDouble()
    } catch (e: java.lang.NumberFormatException) { 0.0 }
    catch (e: IllegalArgumentException) {0.0}

    val thisAwayScore = try {
        awayScore.toDouble()
    } catch (e: java.lang.NumberFormatException) { 0.0 }
    catch (e: IllegalArgumentException) {0.0}

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart){
                BasicText(text = homeScore, fontSize = 16.sp, textColor = Color.Black)
            }

            Box(modifier = Modifier.weight(8f),
            contentAlignment = Alignment.Center){
                BasicText(text = statName, fontSize = 16.sp, textColor = Color.Black)
            }

            Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd){
                BasicText(text = awayScore, fontSize = 16.sp, textColor = Color.Black)
            }
        }
        StatsBar(homeValue = thisHomeScore, awayValue = thisAwayScore)
    }

}