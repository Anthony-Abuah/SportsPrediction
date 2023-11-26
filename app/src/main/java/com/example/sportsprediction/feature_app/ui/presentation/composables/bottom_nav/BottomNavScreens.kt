package com.example.sportsprediction.feature_app.ui.presentation.composables.bottom_nav

import com.example.sportsprediction.R


sealed class BottomNavScreens(
    val name: String,
    val route: String,
    val focused_icon: Int,
    val unfocused_icon: Int,
){
    object Events: BottomNavScreens(
        name = "Events",
        route = "events",
        focused_icon = R.drawable.focused_event,
        unfocused_icon = R.drawable.unfocused_event
    )
    object BuildBet: BottomNavScreens(
        name = "Build bet",
        route = "build_bet",
        focused_icon = R.drawable.focused_add_bet,
        unfocused_icon = R.drawable.unfocused_add_bet
    )
    object Tipsters: BottomNavScreens(
        name = "Tipsters",
        route = "tipsters",
        focused_icon = R.drawable.focused_tipster,
        unfocused_icon = R.drawable.unfocused_tipster
    )
    object Profile: BottomNavScreens(
        name = "Profile",
        route = "profile",
        focused_icon = R.drawable.focused_person,
        unfocused_icon = R.drawable.unfocused_person
    )
}