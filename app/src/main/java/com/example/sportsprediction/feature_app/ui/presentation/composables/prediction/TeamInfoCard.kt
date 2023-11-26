package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import com.example.sportsprediction.core.util.Constants.MainTeam
import com.example.sportsprediction.core.util.Constants.NextOpponent
import com.example.sportsprediction.core.util.Constants.PlayingLocation
import com.example.sportsprediction.core.util.Constants.TeamInformation
import com.example.sportsprediction.core.util.Constants.TournamentInfo
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TeamInformationText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.util.*

@Composable
fun TeamInfoCard(
    mainTeamName: String,
    opponentTeamName: String,
    playingLocation: String,
    tournamentInfo: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(LocalSpacing.current.noPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Team Info
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.noPadding),
            elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.default)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.default),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = TeamInformation,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Team Info content
        Card(
            shape = RectangleShape,
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.noPadding),
            elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(modifier = Modifier.weight(MainTeam.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = MainTeam,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal
                    )
                }

                Box(modifier = Modifier.weight(mainTeamName.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterEnd
                ){
                    TeamInformationText(text = mainTeamName)
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(modifier = Modifier.weight(NextOpponent.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = NextOpponent,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal
                    )
                }

                Box(modifier = Modifier.weight(opponentTeamName.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterEnd
                ){
                    TeamInformationText(text = opponentTeamName)
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(modifier = Modifier.weight(PlayingLocation.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = PlayingLocation,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal
                    )
                }

                Box(modifier = Modifier.weight(playingLocation.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterEnd
                ){
                    TeamInformationText(text = playingLocation.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
                }

            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(modifier = Modifier.weight(TournamentInfo.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = TournamentInfo,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal
                    )
                }

                Box(modifier = Modifier.weight(tournamentInfo.length.toFloat())
                    .padding(horizontal = LocalSpacing.current.small),
                    contentAlignment = Alignment.CenterEnd
                ){
                    TeamInformationText(text = tournamentInfo)
                }

            }

        }


    }

}