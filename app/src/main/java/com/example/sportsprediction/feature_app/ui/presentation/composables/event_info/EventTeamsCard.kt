package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamDateText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.EventTeamTimeText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TeamEmblemText
import com.example.sportsprediction.feature_app.ui.theme.*

@Composable
fun EventTeamsCard(
    day: String,
    date: String,
    time: String,
    homeTeam: String,
    awayTeam: String
) {
    val backgroundColor = Blue10
    val contentColor = BlueGrey90

    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = backgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.noPadding),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
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
                    .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape)
                    .requiredWidth(LocalSpacing.current.large)
                    .requiredHeight(LocalSpacing.current.large),
                    contentAlignment = Alignment.Center
                ){
                    Icon(modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .aspectRatio(1f.div(1f))
                        .fillMaxSize(),
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = emptyString)
                }


            }

            Column(modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = day,
                        fontWeight= FontWeight.SemiBold,
                        color = contentColor,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }

                Box(modifier = Modifier,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = date,
                        fontWeight= FontWeight.SemiBold,
                        color = contentColor,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }

                Box(modifier = Modifier.padding(bottom = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                    text = time,
                    fontWeight= FontWeight.Normal,
                    color = contentColor,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
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
                    .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape)
                    .requiredWidth(LocalSpacing.current.large)
                    .requiredHeight(LocalSpacing.current.large)
                    .clip(CircleShape)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Icon(modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .aspectRatio(1f.div(1f))
                        .fillMaxSize(),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        painter = painterResource(id = R.drawable.away),
                        contentDescription = emptyString)
                }
            }

        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.default))
    }

}