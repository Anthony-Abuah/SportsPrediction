package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomNavScreens

@Composable
fun UserProfilesNavGraph(
    navHostController: NavHostController
){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = UserProfileScreens.RegisterUserScreen.route)
    {
        composable(route = UserProfileScreens.RegisterUserScreen.route){
            RegisterUserScreen(
                navController = navHostController,
                navigateBack = { navController.popBackStack() },
                navigateToCompleteProfileScreen = {
                    navController.navigate(UserProfileScreens.CompleteProfileScreen.route)
                }
            ) {
                navController.navigate(UserProfileScreens.UserLoginScreen.route)
            }
        }

        composable(route = UserProfileScreens.UserProfileScreen.route){
            UserProfileScreen(
                navController = navHostController
            ) { navController.popBackStack() }
        }

        composable(route = UserProfileScreens.CompleteProfileScreen.route
        ){
                    CompleteProfileScreen(
                        navController = navHostController,
                        navigateToHomePage = { navController.navigate(UserProfileScreens.UserProfileScreen.route) }) {
                        navController.popBackStack()
                    }


        }


        composable(route = UserProfileScreens.UserLoginScreen.route ) {
            UserLoginScreen (
                navController = navHostController,
                navigateBack = {navController.popBackStack()}
            ) {
                navHostController.navigate(BottomNavScreens.Events.route)
            }
        }



    }
}