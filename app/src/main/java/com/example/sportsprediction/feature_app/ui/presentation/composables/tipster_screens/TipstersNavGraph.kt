package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster.TipsterLoginContent

@Composable
fun TipstersNavGraph(
    navHostController: NavHostController
){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TipsterScreens.TipstersScreen.route)
    {
        composable(route = TipsterScreens.TipstersScreen.route){
            TipstersScreen (
                navController = navHostController,
                navigateBack = {navController.popBackStack()}
            ) {
                navController.navigate(TipsterScreens.RegisterTipsterScreen.route)
            }
        }

        composable(route = TipsterScreens.RegisterTipsterScreen.route){
            RegisterTipstersScreen(
                navigateBack = {navController.popBackStack()}

            ) {
                navController.navigate(TipsterScreens.TipsterLoginScreen.route)
            }
        }

        composable(route = TipsterScreens.TipsterLoginScreen.route){
            TipsterLoginScreen(
                navigateBack = {
                navController.popBackStack()
            }
            ){
                navController.navigate(TipsterScreens.RegisterTipsterScreen.route)
            }
        }

    }
}