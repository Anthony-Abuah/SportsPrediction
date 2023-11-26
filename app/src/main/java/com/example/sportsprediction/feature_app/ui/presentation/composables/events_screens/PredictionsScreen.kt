package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.ArrangementOrder
import com.example.sportsprediction.core.util.Constants.Descending
import com.example.sportsprediction.core.util.Constants.Market_Type
import com.example.sportsprediction.core.util.Constants.SuggestionGroupOption
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.PredictionsScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.prediction.PredictionsContent
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamEventsStatsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamNameEventsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamSuggestionsViewModel
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun PredictionsScreen(
    teamSuggestionsViewModel: TeamSuggestionsViewModel = hiltViewModel(),
    teamNameEventsViewModel: TeamNameEventsViewModel = hiltViewModel(),
    teamEventsStatsViewModel: TeamEventsStatsViewModel = hiltViewModel(),
    mainTeamName: String,
    mainTeamId: String,
    opponentTeamName: String,
    opponentTeamId: String,
    mainTeamPlayingLocation: String,
    headToHeadId: String,
    eventId: String,
    tournamentInfo: String,
    navigateBack: () -> Unit,
){
    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current
    val sportPredictionPreferences = context.getSharedPreferences(Constants.SportsPredictionPreferences, Context.MODE_PRIVATE)

    val suggestionGrouping = sportPredictionPreferences.getString(SuggestionGroupOption, Market_Type) ?: Market_Type
    val suggestionsArrangement = sportPredictionPreferences.getString(ArrangementOrder, Descending) ?: Descending
    val percentageFilterValue = sportPredictionPreferences.getString(Constants.PercentageFilter, "50") ?: "50"

    val loadedSuggestions = teamSuggestionsViewModel.loadTeamSuggestionState.value.suggestions
    val filteredSuggestions = teamSuggestionsViewModel.filteredSuggestions.value.filteredSuggestions

    var percentageFilterValueHasChanged by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        teamSuggestionsViewModel.loadTeamSuggestion(
            teamId = mainTeamId.toInt(),
            teamName = mainTeamName,
            date = Date()
        )

    }

    LaunchedEffect(loadedSuggestions) {
        val percentage = percentageFilterValue.toDouble().div(100.0)
        if (loadedSuggestions.isNotEmpty()) {
            teamSuggestionsViewModel.saveTeamSuggestionEntity(mainTeamId.toInt(), mainTeamName, Date(), loadedSuggestions)
            teamSuggestionsViewModel.groupSuggestions(loadedSuggestions, percentage, suggestionsArrangement, suggestionGrouping)
            teamSuggestionsViewModel.initializeFilteredSuggestions(loadedSuggestions)
        }
        teamEventsStatsViewModel.getTeamEventStats(mainTeamId.toInt())
    }

    LaunchedEffect(filteredSuggestions) {
        val percentage = percentageFilterValue.toDouble().div(100.0)
        if (filteredSuggestions.isNotEmpty()) {
            teamSuggestionsViewModel.groupSuggestions(filteredSuggestions, percentage, suggestionsArrangement, suggestionGrouping)
        }
    }

    when(percentageFilterValueHasChanged){
        true-> {
            val percentage = percentageFilterValue.toDouble().div(100.0)
            teamSuggestionsViewModel.groupSuggestions(filteredSuggestions, percentage, suggestionsArrangement, suggestionGrouping)
            percentageFilterValueHasChanged = !percentageFilterValueHasChanged
        }
        else-> { /* Todo */ }
    }


    /*
    LaunchedEffect(groupedSuggestions){
        val dateToLocalDate = Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = Functions.shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, Functions.shortDateFormatter)
        val newDate = localDate.toDate()

        teamSuggestionsViewModel.getTeamSuggestion(mainTeamId.toInt(), mainTeamName, newDate, suggestions)
    }
    */


    /*
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { TabPage.values().size }
    */


    LaunchedEffect(key1 = true){
        teamSuggestionsViewModel.eventFlow.collectLatest { eventFlow->
            when (eventFlow){
                is UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = eventFlow.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            PredictionsScreenTopBar(
                label = mainTeamName,
                openOrCloseFilterCard = {
                    teamSuggestionsViewModel.onOpenOrCloseFilterCard()
                }
            ) { navigateBack() }
        },
        floatingActionButton = {
        },
        scaffoldState = scaffoldState,
    ) {
        it
        PredictionsContent(
            mainTeamName = mainTeamName,
            opponentTeamName = opponentTeamName,
            opponentTeamId = opponentTeamId,
            eventId = eventId,
            headToHeadId = headToHeadId,
            mainTeamPlayingLocation = mainTeamPlayingLocation,
            tournamentInfo = tournamentInfo,
            closeFilterCard = { teamSuggestionsViewModel.onCloseFilterCard() },
            orderSuggestions = {_arrangementOrder ->
                teamSuggestionsViewModel.orderSuggestions(filteredSuggestions, _arrangementOrder)
            },
            filterSuggestionsByTeam = { _teams ->
                teamSuggestionsViewModel.getFilterSuggestionsByTeams(loadedSuggestions, _teams)
            },
            filterSuggestionsByMatchPeriod = { _matchPeriod ->
                teamSuggestionsViewModel.filterSuggestionsByMatchPeriod(loadedSuggestions, _matchPeriod)
            },
            filterSuggestionsByMarketCategory = { _marketCategories ->
                teamSuggestionsViewModel.filterSuggestionsByMarketCategory(loadedSuggestions, _marketCategories)
            },
            filterSuggestionsByMarketType = {_marketTypes ->
                teamSuggestionsViewModel.filterSuggestionsByMarketType(loadedSuggestions, _marketTypes)
            },
            filterSuggestionsByPercentage = {
                percentageFilterValueHasChanged = true
            },
            groupSuggestionsBy = {_suggestionGrouping ->
                teamSuggestionsViewModel.groupSuggestionsBy(filteredSuggestions, _suggestionGrouping)
            },
            groupedSuggestions = teamSuggestionsViewModel.groupedSuggestions.value.groupedSuggestions,
            openFilterCard = teamSuggestionsViewModel.openFilterCard,
            isLoadingSuggestions = teamSuggestionsViewModel.loadTeamSuggestionState.value.isLoading,
            teamEventsStats = teamEventsStatsViewModel.listOfTeamEventsStatsState.value.listOfAllTeamEventsStats
        )

    /*

        Column(modifier = Modifier) {
            HorizontalScrollPager(
                pagerState, coroutineScope,
                firstPage = {
                    val teamNameEventEntities = teamNameEventsViewModel.listOfTeamEventState.value.teamNameEvents
                    PredictionsContent(
                        teamEventEntity = if(teamNameEventEntities.isNotEmpty()) teamNameEventEntities.first() else null,
                        teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions,
                        addToBetSlip = {marketName,percentage,ratio,text->
                            teamSuggestionsViewModel.addToBetSip(marketName,percentage, ratio, text)
                        },
                        betList = teamSuggestionsViewModel.betSlip.toList(),
                        showBetSlip = teamSuggestionsViewModel.openBetSlip,
                        closeBetSlip = {teamSuggestionsViewModel.onCloseBetSlip()},
                        isLoadingSuggestions = teamSuggestionsViewModel.teamSuggestionsState.value.isLoading,
                        isLoadingTeamNameEventEntity = teamNameEventsViewModel.listOfTeamEventState.value.isLoading
                    )
                },
                secondPage = {
                    MarketTypePredictionsContent(
                        teamEventEntity = teamEventEntity,
                        teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
                    )
                },
                thirdPage = {
                    MarketCategoryPredictionsContent(
                        teamEventEntity = teamEventEntity,
                        teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
                    )
                },
                fourthPage = {
                    MatchPeriodPredictionsContent(
                        teamEventEntity = teamEventEntity,
                        teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
                    )
                },
                fifthPage = {
                    TeamPredictionsContent(
                        teamEventEntity = teamEventEntity,
                        teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
                    )
                }

                */

    /*
                teamEventEntity = teamEventEntity,
                teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
                 *//*

            )
        }
        */

        /*PredictionsContent(
            teamEventEntity = teamEventEntity,
            teamSuggestion = teamSuggestionsViewModel.teamSuggestionsState.value.teamSuggestions
        )*/

    }
}