package com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing

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
        .fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        elevation = LocalSpacing.current.medium
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = LocalSpacing.current.borderStroke,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navHostController
                    )
                }
            }
        }

    }
}