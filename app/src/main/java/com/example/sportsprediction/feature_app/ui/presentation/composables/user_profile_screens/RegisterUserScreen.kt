package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile.RegisterUserContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.UserProfileViewModel

@Composable
fun RegisterUserScreen(
    navController: NavHostController,
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToUserLoginScreen: (userName: String, password: String) -> Unit
){

    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                label = "Profile",
                openSearchCard = {
                },
                openFilterCard = {
                },
                navigateBack = navigateBack
            )
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
    ){it
        RegisterUserContent(
            registerUser = {user ->
                userProfileViewModel.registerUser(user)
            },
            navigateToTipsterLoginScreen = {username, password ->
                navigateToUserLoginScreen(username, password)
            }
        )
    }
}