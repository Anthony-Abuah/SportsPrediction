package com.example.sportsprediction.feature_app.ui.presentation.composables.user_profile_screens

sealed class UserProfileScreens(val route: String){
    object RegisterUserScreen: UserProfileScreens("to_register_user_screen")
    object CompleteProfileScreen: UserProfileScreens("to_complete_profile_screen")
    object UserLoginScreen: UserProfileScreens("to_user_login_screen")
    object UserProfileScreen: UserProfileScreens("to_user_profile_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}