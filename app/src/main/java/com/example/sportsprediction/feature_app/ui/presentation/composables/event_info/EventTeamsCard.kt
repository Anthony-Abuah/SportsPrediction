package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamDateText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamTimeText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamTournamentText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TeamEmblemText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.TeamEmblemColor

@Composable
fun EventTeamsCard(
    date: String,
    time: String,
    competition: String,
    homeTeam: String,
    awayTeam: String
) {

    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.noPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Top){
            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                Icon(painter = painterResource(id = R.drawable.football), contentDescription = emptyString)
            }
            Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                EventTeamTournamentText(text = competition)
            }
        }
        
        
        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier
                .weight(1f)
                .padding(LocalSpacing.current.small),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center
                ){
                    TeamEmblemText(text = homeTeam)
                }
                Spacer(modifier = Modifier.height(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .background(TeamEmblemColor, MaterialTheme.shapes.large)
                    .requiredWidth(LocalSpacing.current.extraLarge)
                    .requiredHeight(LocalSpacing.current.extraLarge),
                    contentAlignment = Alignment.Center
                ){
                    Image(modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .fillMaxSize(),
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = emptyString)
                }


            }

            Column(modifier = Modifier.weight(1.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(LocalSpacing.current.large))

                Box(modifier = Modifier,
                    contentAlignment = Alignment.Center
                ){
                    EventTeamDateText(text = date)
                }
                Box(modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ){
                    EventTeamTimeText(text = time)
                }


            }

            //Away
            Column(modifier = Modifier
                .weight(1f)
                .padding(LocalSpacing.current.small),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center
                ){
                    TeamEmblemText(text = awayTeam)
                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.smallMedium))

                Box(modifier = Modifier
                    .background(TeamEmblemColor, MaterialTheme.shapes.large)
                    .requiredWidth(LocalSpacing.current.extraLarge)
                    .requiredHeight(LocalSpacing.current.extraLarge),
                    contentAlignment = Alignment.Center
                ){
                    Image(modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .fillMaxSize(),
                        painter = painterResource(id = R.drawable.away),
                        contentDescription = emptyString)
                }
            }

            
        }
    }

}