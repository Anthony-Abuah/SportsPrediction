package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

sealed class EventsScreens(val route: String){
    object DateEventsScreen: EventsScreens("to_date_events_screen")
    object PredictionsScreen: EventsScreens("to_predictions_screen")
    object EventsInfoScreen: EventsScreens("to_events_info_screen")
    object UserPreferencesScreen: EventsScreens("to_user_preferences_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}