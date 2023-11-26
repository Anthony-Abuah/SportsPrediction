package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor

@Composable
fun TeamCard(
    teamEventEntity: TeamEventEntity?,
    filter: String,
    mainTeamName: String,
    opponentTeamName: String,
    tournamentInfo: String,
    onClickFilter: ()-> Unit
) {

    val teamNameEvent = teamEventEntity?: TeamEventEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)

    val roundName = teamNameEvent.roundInfo?.name ?: emptyString
    val roundNumber = teamNameEvent.roundInfo?.round ?: Constants.nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == Constants.nullInteger){ emptyString }
    else if (roundName.isNotEmpty() && roundNumber == Constants.nullInteger){", $roundName"}
    else if (roundName.isEmpty() && roundNumber != Constants.nullInteger){", Round $roundNumber"}
    else {", $roundName, Round $roundNumber"}

    val tournamentName = if (teamNameEvent.tournamentName.isNullOrBlank()) emptyString else {"${teamNameEvent.tournamentName}"}


    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = MainBackgroundColor,
        ),
        //border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        elevation = CardDefaults.cardElevation(
            defaultElevation = LocalSpacing.current.small
        )
    ) {
        Column(modifier = Modifier.padding(LocalSpacing.current.medium),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                Row {
                    Box(modifier = Modifier.weight(3f)) {
                        BasicText(
                            text = "Main Team : ${if (mainTeamName.length >= 20) "${mainTeamName.take(17)}..." else mainTeamName}",
                            fontSize = 16.sp,
                            textColor = Color.Black
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(horizontal = LocalSpacing.current.small)
                            .weight(1f)
                            .clickable { },
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.padding(LocalSpacing.current.noPadding)
                                    .clickable { onClickFilter() },
                                painter = painterResource(id = R.drawable.filter),
                                tint = Color.Blue,
                                contentDescription = emptyString
                            )
                        }
                    }

                }

            }
/*

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = "Country: ${teamNameEvent.country ?: emptyString}",
                    fontSize = 16.sp,
                    textColor = Color.Black
                )
            }
*/

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = "Opponent: ${if (opponentTeamName.length >= 20) "${opponentTeamName.take(17)}..." else opponentTeamName}",
                    fontSize = 16.sp,
                    textColor = Color.Black
                )
            }

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                BasicText(
                    text = "Tournament: $tournamentInfo",
                    fontSize = 15.sp,
                    textColor = Color.Black
                )
            }

        }
    }

    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically) {

        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                contentColor = Color.Gray,
                containerColor = Color.LightGray,
            ),
            //border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier.padding(LocalSpacing.current.noPadding),
            elevation = CardDefaults.cardElevation(
                defaultElevation = LocalSpacing.current.small
            )
        ) {
            Box(
                modifier = Modifier.padding(LocalSpacing.current.small),
                contentAlignment = Alignment.Center
            ) {
                BasicText(
                    text = filter,
                    fontSize = 14.sp,
                    textColor = Color.Gray
                )
            }
        }
    }


}