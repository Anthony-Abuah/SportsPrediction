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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.Soccer
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun DateEventHeader(
    openFilterCard: () -> Unit,
    openSearchCard: () -> Unit,
    navigateToUserPreferences: () -> Unit,
){
    val iconTintColor = MaterialTheme.colorScheme.onBackground
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalSpacing.current.topAppBarSize),
        elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.noElevation)
    ) {
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Row(modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.padding(
                    start = LocalSpacing.current.small),
                    contentAlignment = Alignment.Center){
                    Icon(
                        painter = painterResource(id = R.drawable.football),
                        tint = iconTintColor,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(LocalSpacing.current.extraSmall),
                        contentDescription = emptyString
                    )
                }

                Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                    Text(text = Soccer, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
            }


            Row(modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(LocalSpacing.current.extraSmall)
                        .background(Color.Transparent),
                    onClick = openFilterCard) {
                    Icon(painter = painterResource(id = R.drawable.filter),
                        tint = iconTintColor,
                        contentDescription = emptyString)
                }

                IconButton(
                    modifier = Modifier
                        .padding(LocalSpacing.current.extraSmall)
                        .background(Color.Transparent),
                    onClick = openSearchCard) {
                    Icon(painter = painterResource(id = R.drawable.search),
                        tint = iconTintColor,
                        contentDescription = emptyString)
                }

                IconButton(
                    modifier = Modifier
                        .padding(LocalSpacing.current.extraSmall)
                        .background(Color.Transparent),
                    onClick = navigateToUserPreferences
                ) {
                    Icon(painter = painterResource(id = R.drawable.app_settings),
                        tint = iconTintColor,
                        contentDescription = emptyString)
                }
            }

        }
    }
}
