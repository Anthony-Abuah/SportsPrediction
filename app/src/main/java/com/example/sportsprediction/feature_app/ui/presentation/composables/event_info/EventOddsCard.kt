package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.gambleResponsibly
import com.example.sportsprediction.core.util.Constants.moreOdds
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.OddsDisplayText
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.OddsNameText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun EventOddsCard(
    odds1: String?,
    oddsX: String?,
    odds2: String?
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.default),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = LocalSpacing.current.medium, top = LocalSpacing.current.medium),
            contentAlignment = Alignment.CenterStart){
            Box(modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = Full_Time,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.smallMedium)
        ) {

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(LocalSpacing.current.small),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = BorderStroke(LocalSpacing.current.borderStroke, MaterialTheme.colorScheme.onBackground),
                elevation = CardDefaults.cardElevation(LocalSpacing.current.default)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsNameText(text = "1")
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsDisplayText(text = odds1 ?: emptyString)
                }
            }

            Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(LocalSpacing.current.small),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = BorderStroke(LocalSpacing.current.borderStroke, MaterialTheme.colorScheme.onBackground),
                elevation = CardDefaults.cardElevation(LocalSpacing.current.default)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsNameText(text = "X")
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsDisplayText(text = oddsX ?: emptyString)
                }
            }

            Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))

            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(LocalSpacing.current.small),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = BorderStroke(LocalSpacing.current.borderStroke, MaterialTheme.colorScheme.onBackground),
                elevation = CardDefaults.cardElevation(LocalSpacing.current.default)
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsNameText(text = "2")
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalSpacing.current.extraSmall),
                    contentAlignment = Alignment.Center){
                    OddsDisplayText(text = odds2 ?: emptyString)
                }
            }

        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.smallMedium)
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart){
                Text(
                    text = gambleResponsibly,
                    fontWeight= FontWeight.Normal,
                    color = MaterialTheme.colorScheme.outline,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
            }
            Box(modifier = Modifier
                .weight(1f)
                .padding(end = LocalSpacing.current.smallMedium),
                contentAlignment = Alignment.CenterEnd){
                Text(
                    text = moreOdds,
                    fontWeight= FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.End
                )
            }
        }


    }

}