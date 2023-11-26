package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sportsprediction.core.util.Constants.emptyString
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
            RegisterUserScreen (
                navController = navHostController,
                navigateBack = {navController.popBackStack()}
            ) {username, password->
                navController.navigate(UserProfileScreens.UserLoginScreen.withArgs(username,password))
            }
        }
        composable(route = UserProfileScreens.UserLoginScreen.route + "/{username}/{password}",
            arguments = listOf(
                navArgument("username"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("password"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                }
            ),
        ){entry ->
            val username = entry.arguments?.getString("username")
            val password = entry.arguments?.getString("password")
            if (username != null) {
                if (password != null) {
                    UserLoginScreen (
                        username = username,
                        password = password,
                        navController = navHostController,
                        navigateBack = {navController.popBackStack()}
                    ) {
                        navHostController.navigate(BottomNavScreens.Events.route)
                    }
                }
            }
        }



    }
}