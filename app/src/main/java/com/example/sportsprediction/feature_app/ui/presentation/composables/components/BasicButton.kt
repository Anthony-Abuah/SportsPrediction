package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PredictionsButtonBackgroundColor

@Composable
fun BasicButton(
    buttonName: String,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
    ) {
        Text(
            text = buttonName,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun RegisterTipsterButton(
    buttonName: String,
    isLoading: Boolean?,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = PredictionsButtonBackgroundColor,
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(LocalSpacing.current.smallMedium),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
    ) {
        Text(
            text = buttonName,
            style = androidx.compose.material.MaterialTheme.typography.button
        )
        if (isLoading == true) {
            Spacer(modifier = Modifier.width(LocalSpacing.current.small))
            Box(modifier = Modifier.size(LocalSpacing.current.medium),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.height(LocalSpacing.current.medium),
                    color = Color.White,
                    strokeWidth = LocalSpacing.current.borderStroke
                )
            }

        }
    }
}


@Composable
fun LoadStatsButton(
    buttonName: String,
    isLoadingStats: Boolean,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.buttonHeight)
    ) {
        if (isLoadingStats) {
            Row(modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(LocalSpacing.current.semiLarge)
                        .padding(LocalSpacing.current.small),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    strokeWidth = LocalSpacing.current.extraSmall
                )
                Text(
                    text = "Loading stats...",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }

        }
        else {
            Text(
                text = buttonName,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

