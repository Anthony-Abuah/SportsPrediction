package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.FormGuide
import com.example.sportsprediction.core.util.Functions.getResultBackgroundColor
import com.example.sportsprediction.core.util.Functions.getMatchResult
import com.example.sportsprediction.core.util.Functions.getResult
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormGuideRemarksText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormGuideTeamNameText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormGuideText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormGuideTitleText
import com.example.sportsprediction.feature_app.ui.theme.*
import java.util.*

@Composable
fun FormGuideCard(
    homeTeam: String,
    awayTeam: String,
    homeFormPercentage: Double,
    awayFormPercentage: Double,
    homeTeamEvents: List<TeamEventEntity>,
    awayTeamEvents: List<TeamEventEntity>
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = Color.Black,
            containerColor = MainBackgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(200.dp)
            .padding(LocalSpacing.current.default),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {

        // Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                FormGuideText(text = FormGuide)
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        // Main Content Row
        Row(modifier = Modifier.fillMaxWidth()) {

            // Team Info
            Column(
                modifier = Modifier.weight(1.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                    FormGuideTitleText(text = "Team")
                }

                Column(
                    modifier = Modifier.weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Default Team Emblem
                    Box(
                        modifier = Modifier
                            .background(TeamEmblemColor, MaterialTheme.shapes.large)
                            .requiredWidth(LocalSpacing.current.large)
                            .requiredHeight(LocalSpacing.current.large),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .fillMaxSize(),
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = Constants.emptyString
                        )
                    }
                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                    // Default Team Name
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        FormGuideTeamNameText(text = homeTeam)
                    }
                }

                Column(
                    modifier = Modifier.weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Away Team Emblem
                    Box(
                        modifier = Modifier
                            .background(TeamEmblemColor, MaterialTheme.shapes.large)
                            .requiredWidth(LocalSpacing.current.large)
                            .requiredHeight(LocalSpacing.current.large),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(LocalSpacing.current.small)
                                .fillMaxSize(),
                            painter = painterResource(id = R.drawable.away),
                            contentDescription = Constants.emptyString
                        )
                    }

                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                    // Away Team Name
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        FormGuideTeamNameText(text = awayTeam)
                    }
                }

            }

            // Form Guide
            Column(
                modifier = Modifier.weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                    FormGuideTitleText(text = "Form")
                }

                Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(LocalSpacing.current.large)
                            .background(Color.Transparent, MaterialTheme.shapes.medium)
                            .padding(LocalSpacing.current.noPadding)
                    ) {
                        for (teamNameEvent in homeTeamEvents.take(5)) {
                            val result = getMatchResult(
                                teamNameEvent.mainTeamPlayingLocation,
                                teamNameEvent.homeScore,
                                teamNameEvent.awayScore
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(
                                        start = LocalSpacing.current.borderStroke,
                                        end = LocalSpacing.current.borderStroke
                                    )
                                    .background(
                                        getResultBackgroundColor(result),
                                        MaterialTheme.shapes.medium
                                    )
                                    .weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = LocalSpacing.current.extraSmall,
                                        end = LocalSpacing.current.extraSmall
                                    ),
                                    text = getResult(result).uppercase(Locale.ROOT).take(1),
                                    style = MaterialTheme.typography.h6
                                )
                            }

                        }
                    }
                }

                Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(LocalSpacing.current.large)
                            .background(Color.Transparent, MaterialTheme.shapes.medium)
                            .padding(LocalSpacing.current.noPadding)
                    ) {
                        for (teamNameEvent in awayTeamEvents.take(5)) {
                            if (teamNameEvent.homeScore != null || teamNameEvent.awayScore != null) {

                                val result = getMatchResult(
                                    teamNameEvent.mainTeamPlayingLocation,
                                    teamNameEvent.homeScore,
                                    teamNameEvent.awayScore
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(
                                            start = LocalSpacing.current.borderStroke,
                                            end = LocalSpacing.current.borderStroke
                                        )
                                        .background(
                                            getResultBackgroundColor(result),
                                            MaterialTheme.shapes.medium
                                        )
                                        .weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            start = LocalSpacing.current.extraSmall,
                                            end = LocalSpacing.current.extraSmall
                                        ),
                                        text = getResult(result).take(1).uppercase(Locale.ROOT),
                                        style = MaterialTheme.typography.h6
                                    )
                                }
                            }
                        }
                    }

                }
            }

            // Remarks
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    FormGuideTitleText(text = "Remarks")
                }

                Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .requiredHeight(LocalSpacing.current.large)
                            .background(Color.LightGray, MaterialTheme.shapes.medium)
                            .padding(LocalSpacing.current.noPadding)
                    ) {
                        val form: Double = homeFormPercentage
                        Box(
                            modifier = Modifier.fillMaxHeight().background(
                                color = if (form > 90.0) green1
                                else if (form > 80.0) green2
                                else if (form > 70.0) green3
                                else if (form > 60.0) green4
                                else if (form > 50.0) green5
                                else if (form > 40.0) Color.Gray
                                else Color.Red,
                                shape = MaterialTheme.shapes.medium
                            ).padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            FormGuideRemarksText(text = "${form.toString().take(5)}%")
                        }

                    }
                }

                Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .requiredHeight(LocalSpacing.current.large)
                            .background(Color.LightGray, MaterialTheme.shapes.medium)
                            .padding(LocalSpacing.current.noPadding)
                    ) {
                        val form: Double = awayFormPercentage
                        Box(
                            modifier = Modifier.fillMaxHeight().background(
                                color = if (form > 90.0) green1
                                else if (form > 80.0) green2
                                else if (form > 70.0) green3
                                else if (form > 60.0) green4
                                else if (form > 50.0) green5
                                else if (form > 40.0) Color.Gray
                                else Color.Red,
                                shape = MaterialTheme.shapes.medium
                            ).padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            FormGuideRemarksText(text = "${form.toString().take(5)}%")
                        }

                    }
                }

            }

        }

    }
}