package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sportsprediction.core.util.Constants.emptyString

@Composable
fun EventsNavGraph(
    navHostController: NavHostController
){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = EventsScreens.DateEventsScreen.route)
    {
        composable(route = EventsScreens.DateEventsScreen.route){
            DateEventsScreen (
                navController = navHostController,
                navigateToUserPreferencesScreen = { navController.navigate(EventsScreens.UserPreferencesScreen.route) }
            ) { eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId ->
                navController.navigate(EventsScreens.EventsInfoScreen.withArgs(eventId, headToHeadId, date, homeTeamName, homeTeamId, awayTeamName, awayTeamId))
            }
        }

        composable(route = EventsScreens.UserPreferencesScreen.route){
            UserPreferencesScreen(){
                navController.popBackStack()
            }
        }

        composable(
            route = EventsScreens.PredictionsScreen.route + "/{mainTeamName}/{mainTeamId}/{opponentTeamName}/{opponentTeamId}/{mainTeamPlayingLocation}/{headToHeadId}/{eventId}/{tournamentInfo}",
            arguments = listOf(
                navArgument("mainTeamName"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("mainTeamId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("opponentTeamName"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("opponentTeamId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("mainTeamPlayingLocation"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("headToHeadId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("eventId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("tournamentInfo"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                }
            )
        ){entry ->
            val mainTeamName = entry.arguments?.getString("mainTeamName")
            val mainTeamId = entry.arguments?.getString("mainTeamId")
            val opponentTeamName = entry.arguments?.getString("opponentTeamName")
            val opponentTeamId = entry.arguments?.getString("opponentTeamId")
            val mainTeamPlayingLocation = entry.arguments?.getString("mainTeamPlayingLocation")
            val headToHeadId = entry.arguments?.getString("headToHeadId")
            val eventId = entry.arguments?.getString("eventId")
            val tournamentInfo = entry.arguments?.getString("tournamentInfo")
            if (mainTeamName != null) {
                if (mainTeamId != null) {
                    if (opponentTeamName != null) {
                        if (opponentTeamId != null) {
                            if (mainTeamPlayingLocation != null) {
                                if (headToHeadId != null) {
                                    if (eventId != null) {
                                        if (tournamentInfo != null) {
                                            PredictionsScreen(
                                                mainTeamName = mainTeamName,
                                                mainTeamId = mainTeamId,
                                                opponentTeamName = opponentTeamName,
                                                opponentTeamId = opponentTeamId,
                                                mainTeamPlayingLocation = mainTeamPlayingLocation,
                                                headToHeadId = headToHeadId,
                                                eventId = eventId,
                                                tournamentInfo = tournamentInfo
                                            ) {
                                                navController.popBackStack()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        composable(
            route = EventsScreens.EventsInfoScreen.route + "/{eventId}/{headToHeadId}/{date}/{homeTeamName}/{homeTeamId}/{awayTeamName}/{awayTeamId}",
            arguments = listOf(
                navArgument("eventId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("headToHeadId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("date"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("homeTeamName"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("homeTeamId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("awayTeamName"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                },
                navArgument("awayTeamId"){
                    type = NavType.StringType
                    defaultValue = emptyString
                    nullable = true
                }
            )
        ){entry ->
            val eventId = entry.arguments?.getString("eventId")
            val headToHeadId = entry.arguments?.getString("headToHeadId")
            val date = entry.arguments?.getString("date")
            val homeTeamName = entry.arguments?.getString("homeTeamName")
            val homeTeamId = entry.arguments?.getString("homeTeamId")
            val awayTeamName = entry.arguments?.getString("awayTeamName")
            val awayTeamId = entry.arguments?.getString("awayTeamId")
            if (eventId != null) {
                if (headToHeadId != null) {
                    if (date != null) {
                        if (homeTeamName != null) {
                            if (homeTeamId != null) {
                                if (awayTeamName != null) {
                                    if (awayTeamId != null) {
                                        EventsInfoScreen (
                                            eventId = eventId,
                                            headToHeadId = headToHeadId,
                                            date = date,
                                            homeTeamName = homeTeamName,
                                            homeTeamId = homeTeamId,
                                            awayTeamName = awayTeamName,
                                            awayTeamId = awayTeamId,
                                            navigateToPredictionsScreen = { mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, mainTeamPlayingLocation, thisHeadToHeadId, thisEventId, tournamentInfo ->
                                                navController.navigate(EventsScreens.PredictionsScreen.withArgs(mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, mainTeamPlayingLocation, thisHeadToHeadId, thisEventId, tournamentInfo))
                                            }
                                        ){
                                            navController.popBackStack()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }



    }
}