package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Form
import com.example.sportsprediction.core.util.Constants.FormGuide
import com.example.sportsprediction.core.util.Constants.Remarks
import com.example.sportsprediction.core.util.Constants.Team
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Functions.getMatchResult
import com.example.sportsprediction.core.util.Functions.getResult
import com.example.sportsprediction.core.util.Functions.getResultBackgroundColor
import com.example.sportsprediction.core.util.Functions.getResultContentColor
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormCardHeaderText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.FormGuideTeamNameText
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
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(200.dp)
            .padding(LocalSpacing.current.default),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {

        // Title
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = FormGuide,
                fontWeight= FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
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
                    FormCardHeaderText(text = Team)
                }

                Column(
                    modifier = Modifier.weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Default Team Emblem
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                        .requiredWidth(LocalSpacing.current.large)
                        .requiredHeight(LocalSpacing.current.large),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .aspectRatio(1f.div(1f))
                            .fillMaxSize(),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = emptyString
                        )
                    }
                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                    // Default Team Name
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center) {
                        FormGuideTeamNameText(text = homeTeam)
                    }
                }

                Column(
                    modifier = Modifier.weight(3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Away Team Emblem
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.tertiaryContainer, CircleShape)
                        .requiredWidth(LocalSpacing.current.large)
                        .requiredHeight(LocalSpacing.current.large),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .aspectRatio(1f.div(1f))
                            .fillMaxSize(),
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            painter = painterResource(id = R.drawable.away),
                            contentDescription = emptyString
                        )
                    }

                    Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                    // Away Team Name
                    Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center) {
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
                    FormCardHeaderText(text = Form)
                }

                Box(modifier = Modifier.weight(3f), contentAlignment = Alignment.Center) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(LocalSpacing.current.large)
                            .background(Color.Transparent, MaterialTheme.shapes.medium)
                            .padding(LocalSpacing.current.noPadding)
                    ) {
                        homeTeamEvents.take(5).forEach {teamNameEvent->
                            val result = getMatchResult(
                                teamNameEvent.mainTeamPlayingLocation,
                                teamNameEvent.homeScore,
                                teamNameEvent.awayScore
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = LocalSpacing.current.borderStroke)
                                    .background(
                                        getResultBackgroundColor(result, isSystemInDarkTheme()),
                                        MaterialTheme.shapes.medium
                                    )
                                    .weight(1f),
                                colors = CardDefaults.cardColors(
                                    containerColor = getResultBackgroundColor(result, isSystemInDarkTheme()),
                                    contentColor = getResultContentColor(result, isSystemInDarkTheme())
                                ),
                                elevation = CardDefaults.cardElevation(LocalSpacing.current.small)
                            ) {
                                Box(modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center) {
                                    Text(
                                        modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                                        text = getResult(result).uppercase(Locale.ROOT).take(1),
                                        fontWeight = FontWeight.SemiBold,
                                        color = getResultContentColor(
                                            result,
                                            isSystemInDarkTheme()
                                        ),
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center
                                    )
                                }
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
                        awayTeamEvents.take(5).forEach {teamNameEvent->
                            if (teamNameEvent.homeScore != null || teamNameEvent.awayScore != null) {

                                val result = getMatchResult(
                                    teamNameEvent.mainTeamPlayingLocation,
                                    teamNameEvent.homeScore,
                                    teamNameEvent.awayScore
                                )

                                Card(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(horizontal = LocalSpacing.current.borderStroke)
                                        .background(
                                            getResultBackgroundColor(result, isSystemInDarkTheme()),
                                            MaterialTheme.shapes.medium
                                        )
                                        .weight(1f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = getResultBackgroundColor(result, isSystemInDarkTheme()),
                                        contentColor = getResultContentColor(result, isSystemInDarkTheme())
                                    ),
                                    elevation = CardDefaults.cardElevation(LocalSpacing.current.small)
                                ) {
                                    Box(modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center) {
                                        Text(
                                            modifier = Modifier.padding(LocalSpacing.current.extraSmall),
                                            text = getResult(result).uppercase(Locale.ROOT).take(1),
                                            fontWeight = FontWeight.SemiBold,
                                            color = getResultContentColor(
                                                result,
                                                isSystemInDarkTheme()
                                            ),
                                            style = MaterialTheme.typography.bodyMedium,
                                            textAlign = TextAlign.Center
                                        )
                                    }
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
                    FormCardHeaderText(text = Remarks)
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
                        val thisForm: Int = try {
                            form.toInt()
                        }catch (e: java.lang.Exception){
                            0
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(
                                    color = when (thisForm in 0..100) {
                                        (thisForm in 60..100) -> if (isSystemInDarkTheme()) green50 else green40
                                        (thisForm in 30..59) -> if (isSystemInDarkTheme()) Grey40 else Grey40
                                        else -> if (isSystemInDarkTheme()) Red40 else Red60
                                    },
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${form.toString().take(5)}%",
                                fontWeight= FontWeight.SemiBold,
                                color = when (thisForm in 0..100) {
                                    (thisForm in 60..100) -> green90
                                    (thisForm in 30..59) -> Grey90
                                    else -> Red90
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
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
                        val thisForm: Int = try {
                            form.toInt()
                        }catch (e: java.lang.Exception){
                            0
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(
                                    color = when (thisForm in 0..100) {
                                        (thisForm in 60..100) -> if (isSystemInDarkTheme()) green50 else green40
                                        (thisForm in 30..59) -> if (isSystemInDarkTheme()) Grey40 else Grey40
                                        else -> if (isSystemInDarkTheme()) Red40 else Red60
                                    },
                                    shape = MaterialTheme.shapes.medium
                                )
                                .padding(LocalSpacing.current.extraSmall),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${form.toString().take(5)}%",
                                fontWeight= FontWeight.SemiBold,
                                color = when (thisForm in 0..100) {
                                    (thisForm in 60..100) -> green90
                                    (thisForm in 30..59) -> Grey90
                                    else -> Red90
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }

            }

        }

    }
}