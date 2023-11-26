package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav.BottomBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TipstersScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster.TipsterListContent

@Composable
fun TipstersScreen(
    navController: NavHostController,
    navigateBack: ()-> Unit,
    navigateToRegisterTipsterScreen: ()-> Unit,
    ){
    Scaffold(
        topBar = {
            TipstersScreenTopBar(label = "Tipsters",
                navigateToRegisterTipster = navigateToRegisterTipsterScreen
            ) {
                navigateBack()
            }
        },
        bottomBar = {
            BottomBar(navHostController = navController)
        },
    ){it
        TipsterListContent()
    }
}