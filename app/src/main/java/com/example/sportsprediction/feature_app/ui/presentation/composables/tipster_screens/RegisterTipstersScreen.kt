package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.PredictionsScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.tipster.TipsterRegistrationContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TipsterViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.UserProfileViewModel

@Composable
fun RegisterTipstersScreen(
    navigateBack: () -> Unit,
    userProfileViewModel: UserProfileViewModel = hiltViewModel(),
    tipsterViewModel: TipsterViewModel = hiltViewModel(),
    navigateToTipsterLoginScreen: ()-> Unit
){
    Scaffold(
        topBar = {
            PredictionsScreenTopBar(label = "Tipster Registration") {
                navigateBack()
            }
        },
    ){it
        TipsterRegistrationContent(
            getUser = {username, password -> userProfileViewModel.getUser(username, password) },
            user = userProfileViewModel.user,
            registerTipster = {tipster-> tipsterViewModel.registerTipster(tipster) },
            navigateToTipsterLoginScreen = navigateToTipsterLoginScreen
        )
    }
}