package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity

@Composable
fun BuildBetNavGraph(
    navHostController: NavHostController
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BuildBetScreens.NestedNavGraph.route)
    {
        navigation(
            route = BuildBetScreens.NestedNavGraph.route,
            startDestination = BuildBetScreens.BuildBetScreen.route
        ) {
            var thisListOfEvents = emptyList<EventsEntity>()
            composable(route = BuildBetScreens.BuildBetScreen.route) {
                BuildBetScreen(
                    navController = navHostController,
                    navigateToShowEventsScreen = { _listOfEvents ->
                        thisListOfEvents = _listOfEvents
                        navController.navigate(BuildBetScreens.ShowEventsScreen.route)
                    }
                ) {
                    navHostController.popBackStack()
                }
            }

            composable(route = BuildBetScreens.ShowEventsScreen.route) {
                ShowEventsScreen(listOfEvents = thisListOfEvents,
                    navigateToSuggestionsScreen = { navController.navigate(BuildBetScreens.ShowSuggestionsScreen.route)}
                ) {
                    navController.popBackStack()
                }
            }

            composable(route = BuildBetScreens.ShowSuggestionsScreen.route) {
                ShowSuggestionsScreen(listOfEvents = thisListOfEvents) {
                    navController.popBackStack()
                }
            }


        }
    }

}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(
    navHostController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(key1 = this) {
        navHostController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}

