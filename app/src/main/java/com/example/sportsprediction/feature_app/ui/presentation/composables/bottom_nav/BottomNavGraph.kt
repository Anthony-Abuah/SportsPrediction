package com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens.BuildBetNavGraph
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens.BuildBetScreen
import com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens.EventsNavGraph
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens.TipstersNavGraph
import com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens.UserProfilesNavGraph

@Composable
fun BottomNavGraph (){
    val mainNavController = rememberNavController()
    NavHost(
        navController = mainNavController,
        startDestination = BottomNavScreens.BuildBet.route)
    {
        composable(route = BottomNavScreens.Events.route){
            EventsNavGraph(mainNavController)
        }
        composable(route = BottomNavScreens.BuildBet.route){
            BuildBetNavGraph(navHostController = mainNavController)
        }
        composable(route = BottomNavScreens.Tipsters.route){
            TipstersNavGraph(mainNavController)
        }
        composable(route = BottomNavScreens.Profile.route){
            UserProfilesNavGraph(mainNavController)
        }
    }
}

