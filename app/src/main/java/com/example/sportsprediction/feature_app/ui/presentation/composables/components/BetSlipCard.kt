package com.example.sportsprediction.feature_app.ui.presentation.composables.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.util.*

@Composable
fun BetSlipCard(
    mainTeamName: String,
    opposingTeamName: String,
    playingLocation: String,
    marketName: String,
    numerator: String,
    denominator: String,
    removeBet: ()-> Unit,
    openBetSlip: ()-> Unit,
    closeBetSlip: ()-> Unit,
) {
    val homeTeam = if (playingLocation.lowercase(Locale.ROOT) == Away.lowercase(Locale.ROOT)) opposingTeamName else mainTeamName
    val awayTeam = if (playingLocation.lowercase(Locale.ROOT) == Home.lowercase(Locale.ROOT)) opposingTeamName else mainTeamName


    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Box(modifier = Modifier
            .padding(LocalSpacing.current.small),
        contentAlignment = Alignment.Center
        ){
            Icon(
                modifier = Modifier
                    .height(LocalSpacing.current.medium)
                    .width(LocalSpacing.current.medium)
                    .background(MaterialTheme.colorScheme.errorContainer, shape = CircleShape)
                    .clickable {
                        removeBet()
                        closeBetSlip()
                        openBetSlip()
                    },
                imageVector = Icons.Default.Close,
                contentDescription = emptyString,
                tint = MaterialTheme.colorScheme.onErrorContainer
            )
        }

        Column(modifier = Modifier
            .padding(LocalSpacing.current.small),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.extraSmall),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier, contentAlignment = Alignment.Center
                ){
                    Text(
                        text = marketName,
                        fontWeight= FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.extraSmall),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier, contentAlignment = Alignment.Center){
                    Text(
                        text = "$homeTeam vs $awayTeam",
                        fontWeight= FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
                    )
                    //BetSlipSuggestionText(text = "$homeTeam vs $awayTeam")
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.extraSmall),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically){
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    BetSlipLightStreakText(text = "streak is ")
                    BetSlipBoldStreakText(text = numerator.toDouble().toInt().toString())
                    BetSlipSuggestionText(text = " out of ")
                    BetSlipBoldStreakText(text = denominator.toDouble().toInt().toString())
                    BetSlipLightStreakText(text = " matches")
                }
            }

        }

    }



}