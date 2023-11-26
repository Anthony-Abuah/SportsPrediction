package com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor

@Composable
fun BottomBar(
    navHostController: NavHostController
){
    val screens = listOf(
        BottomNavScreens.Events,
        BottomNavScreens.BuildBet,
        BottomNavScreens.Tipsters,
        BottomNavScreens.Profile,
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = Modifier
        .height(LocalSpacing.current.bottomNavBarSize)
        .background(Color.White)
        .fillMaxWidth(),
        backgroundColor = PrimaryThemeColor,
        elevation = LocalSpacing.current.medium
    ) {
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach{screen->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }

    }
}