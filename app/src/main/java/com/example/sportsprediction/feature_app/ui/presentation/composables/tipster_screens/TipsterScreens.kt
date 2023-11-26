package com.example.sportsprediction.feature_app.ui.presentation.composables.tipster_screens

sealed class TipsterScreens(val route: String){
    object RegisterTipsterScreen: TipsterScreens("to_register_tipster_screen")
    object TipsterLoginScreen: TipsterScreens("to_tipster_login_screen")
    object TipstersScreen: TipsterScreens("to_tipster_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}