package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
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
    Card(
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = PrimaryThemeColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(LocalSpacing.current.topAppBarSize),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
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
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(LocalSpacing.current.extraSmall),
                        contentDescription = emptyString
                    )
                }

                Box(modifier = Modifier.padding(LocalSpacing.current.extraSmall), contentAlignment = Alignment.Center){
                    TopAppBarText(text = Soccer, fontSize = 20.sp, textColor = Color.White)
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
                    Icon(painter = painterResource(id = R.drawable.filter), contentDescription = emptyString)
                }

                IconButton(
                    modifier = Modifier
                        .padding(LocalSpacing.current.extraSmall)
                        .background(Color.Transparent),
                    onClick = openSearchCard) {
                    Icon(painter = painterResource(id = R.drawable.search), contentDescription = emptyString)
                }

                IconButton(
                    modifier = Modifier
                        .padding(LocalSpacing.current.extraSmall)
                        .background(Color.Transparent),
                    onClick = navigateToUserPreferences
                ) {
                    Icon(painter = painterResource(id = R.drawable.app_settings), contentDescription = emptyString)
                }
            }

        }
    }
}
