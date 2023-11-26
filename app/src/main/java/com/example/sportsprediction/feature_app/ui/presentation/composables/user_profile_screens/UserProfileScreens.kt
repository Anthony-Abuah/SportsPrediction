package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

sealed class UserProfileScreens(val route: String){
    object RegisterUserScreen: UserProfileScreens("to_register_user_screen")
    object UserLoginScreen: UserProfileScreens("to_user_login_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}