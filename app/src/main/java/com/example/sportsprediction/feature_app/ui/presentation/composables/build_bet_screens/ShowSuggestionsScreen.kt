package com.example.sportsprediction.feature_app.ui.presentation.composables.build_bet_screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.ui.presentation.composables.build_a_bet.ShowSuggestionsContent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BuildBetScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.view_model.DateEventsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamSuggestionsViewModel
import java.time.LocalDate

@Composable
fun ShowSuggestionsScreen(
    dateEventsViewModel: DateEventsViewModel = hiltViewModel(),
    listOfEvents: ListOfEvents,
    teamSuggestionsViewModel: TeamSuggestionsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
){
    val localDate = LocalDate.now().atStartOfDay().toLocalDate()
    val theDate = localDate.toDate()



    LaunchedEffect(Unit) {
        //teamSuggestionsViewModel.getAndDisplayBetBuildersSuggestions(listOfEvents, theDate)
    }


    Scaffold(
        topBar = {
            BuildBetScreenTopBar(
                openFilterCard = {
                    dateEventsViewModel.onOpenOrCloseFilterCard()
                    dateEventsViewModel.onCloseSearchCard()
                },
                openSearchCard = {
                    dateEventsViewModel.onOpenOrCloseSearchCard()
                    dateEventsViewModel.onCloseFilterCard()
                },
                navigateBack = navigateBack
            )
        },
        floatingActionButton = {
            //BuildBetFloatingActionButton {}
        },
    ){it

        ShowSuggestionsContent(
            isLoading = teamSuggestionsViewModel.listOfTeamSuggestionsState.value.isLoading,
            teamSuggestions = teamSuggestionsViewModel.listOfTeamSuggestionsState.value.listOfTeamSuggestions
        )

        /*
        Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
            BasicText(text = "Suggestions appear here", fontSize = 20.sp, textColor = Color.Black)
        }*/

        //Log.d("ShowEventsScreen", "showPastEvents Boolean: ${teamNameEventsViewModel.showPastEvents}")

        /*
        val teamEvents = teamNameEventsViewModel.showTeamPastEventsMessages.value.showTeamsPastEventsMessage
        Log.d("ShowEventsScreen", "isLoading: ${teamNameEventsViewModel.showTeamPastEventsMessages.value.isLoading}")
        teamEvents.forEach { teamEvent ->
            Log.d("ShowEventsScreen", "this team event: ${teamEvent.mainTeamName} vs ${teamEvent.opponentName}")
        }
        */

    }
}