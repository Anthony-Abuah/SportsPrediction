package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile.UserProfileContent

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    navigateBack: () -> Unit
){

    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                openFilterCard = {
                },
                openSearchCard = {
                },
                navigateBack = navigateBack
            )
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
    ){it
        UserProfileContent(username = "Dedee")
    }
}