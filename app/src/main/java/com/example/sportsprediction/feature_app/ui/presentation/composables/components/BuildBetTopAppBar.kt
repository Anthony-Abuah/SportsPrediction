package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

@Composable
fun BuildBetTopAppBar(
    openFilterCard: () -> Unit,
    openSearchCard: () -> Unit
){
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(LocalSpacing.current.topAppBarSize),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier
                .weight(1f)
                .padding(horizontal = LocalSpacing.current.small),
                contentAlignment = Alignment.Center){
                Text(text = "Build A Bet",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(
                modifier = Modifier
                    .width(LocalSpacing.current.topAppBarSize)
                    .height(LocalSpacing.current.topAppBarSize)
                    .padding(horizontal = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent),
                onClick = openFilterCard) {
                Icon(painter = painterResource(id = R.drawable.filter),
                    contentDescription = emptyString,
                    tint = MaterialTheme.colorScheme.onBackground)
            }

            IconButton(
                modifier = Modifier
                    .width(LocalSpacing.current.topAppBarSize)
                    .height(LocalSpacing.current.topAppBarSize)
                    .padding(horizontal = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent),
                onClick = openSearchCard) {
                Icon(painter = painterResource(id = R.drawable.search),
                    contentDescription = emptyString,
                    tint = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}
