package com.example.sportsprediction.feature_app.ui.presentation.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun BuildBetTopAppBar(
    title: String,
    openFilterCard: () -> Unit,
    openSearchCard: () -> Unit
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

            Box(modifier = Modifier
                .weight(1f)
                .padding(horizontal = LocalSpacing.current.small),
                contentAlignment = Alignment.Center){
                TopAppBarText(text = title, fontSize = 20.sp, textColor = Color.White)
            }

            IconButton(
                modifier = Modifier
                    .width(LocalSpacing.current.topAppBarSize)
                    .height(LocalSpacing.current.topAppBarSize)
                    .padding(horizontal = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent),
                onClick = openFilterCard) {
                Icon(painter = painterResource(id = R.drawable.filter), contentDescription = emptyString, tint = Color.White)
            }

            IconButton(
                modifier = Modifier
                    .width(LocalSpacing.current.topAppBarSize)
                    .height(LocalSpacing.current.topAppBarSize)
                    .padding(horizontal = LocalSpacing.current.extraSmall)
                    .background(Color.Transparent),
                onClick = openSearchCard) {
                Icon(painter = painterResource(id = R.drawable.search), contentDescription = emptyString, tint = Color.White)
            }




        }
    }
}
