package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

sealed class BuildBetScreens(val route: String){
    object NestedNavGraph: BuildBetScreens("to_nested_nav_graph")
    object BuildBetScreen: BuildBetScreens("to_build_bet_screen")
    object ShowEventsScreen: BuildBetScreens("to_show_events_screen")
    object ShowSuggestionsScreen: BuildBetScreens("to_show_suggestions_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}