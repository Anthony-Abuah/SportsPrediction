package com.example.sportsprediction.feature_app.ui.presentation.composables.events_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sportsprediction.core.util.TabPage
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamSuggestionsViewModel
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BetSuggestionsFloatingActionButton
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.PredictionsScreenTopBar
import com.example.sportsprediction.feature_app.ui.presentation.composables.prediction.*
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamEventsStatsViewModel
import com.example.sportsprediction.feature_app.ui.presentation.view_model.TeamNameEventsViewModel
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
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
    navigateBack: () -> Unit
){
    val teamEventEntity by teamNameEventsViewModel.teamEventEntity.collectAsState(
        initial = TeamEventEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))


    LaunchedEffect(Unit) {

        teamNameEventsViewModel.getTeamNameEventEntity(eventId.toInt(), mainTeamId.toInt())

        teamEventsStatsViewModel.getTeamEventStats(mainTeamId.toInt())

        teamSuggestionsViewModel.getSuggestions(
            teamId = mainTeamId.toInt(),
            teamName = mainTeamName,
            date = Date()
        )
        if (teamSuggestionsViewModel.calculateBetConfidenceLevel){
            teamSuggestionsViewModel.getBetorsConfidence(teamSuggestionsViewModel.betSlip.toList().toSet().toList())
        }
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { TabPage.values().size }




    Scaffold(
        topBar = {
            PredictionsScreenTopBar(label = mainTeamName) { navigateBack() }
        },
        floatingActionButton = {
            if (!teamSuggestionsViewModel.openFilterCard && !teamSuggestionsViewModel.openBetSlip) {
                BetSuggestionsFloatingActionButton {
                    teamSuggestionsViewModel.onOpenAndCloseBetSlip()
                }
            }
        }
    ) {
        it
        val teamNameEventEntities = teamNameEventsViewModel.listOfTeamEventState.value.listOfTeamEvent
        PredictionsContent(
            mainTeamName = mainTeamName,
            mainTeamId = mainTeamId,
            opponentTeamName = opponentTeamName,
            opponentTeamId = opponentTeamId,
            mainTeamPlayingLocation = mainTeamPlayingLocation,
            eventId = eventId,
            headToHeadId = headToHeadId,
            tournamentInfo = tournamentInfo,
            teamEventEntity = if(teamNameEventEntities.isNotEmpty()) teamNameEventEntities.first() else null,
            teamSuggestion = teamSuggestionsViewModel.suggestionsState.value.teamSuggestions,
            addToBetSlip = {mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, eventId, headToHeadId, mainTeamPlayingLocation,marketName, numerator, denominator, percentageText, marketCategory, marketType, matchPeriod, team->
                teamSuggestionsViewModel.addToBetSip(mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, eventId, headToHeadId, mainTeamPlayingLocation,marketName, numerator, denominator, percentageText, marketCategory, marketType, matchPeriod, team)
            },
            betList = teamSuggestionsViewModel.betSlip.toList().toSet().toList(),
            confidenceLevel = teamSuggestionsViewModel.confidenceLevelState.value.confidenceLevel,
            showBetSlip = teamSuggestionsViewModel.openBetSlip,
            openFilterCard = teamSuggestionsViewModel.openFilterCard,
            openOrCloseFilterCard = {teamSuggestionsViewModel.onOpenOrCloseFilterCard()},
            calculateBetConfidenceLevel = {suggestion, betBuilder -> teamSuggestionsViewModel.getBetorsConfidence(suggestion,betBuilder) },
            isLoadingConfidenceLevel = teamSuggestionsViewModel.confidenceLevelState.value.isLoading,
            closeBetSlip = {teamSuggestionsViewModel.onCloseBetSlip()},
            openBetSlip = {teamSuggestionsViewModel.onOpenBetSlip()},
            isRefreshing = teamSuggestionsViewModel.suggestionsState.value.isLoading,
            getSuggestions = {teamSuggestionsViewModel.getSuggestions(mainTeamId.toInt(), mainTeamName, Date())},
            isLoadingSuggestions = teamSuggestionsViewModel.suggestionsState.value.isLoading,
            isLoadingTeamNameEventEntity = teamNameEventsViewModel.listOfTeamEventState.value.isLoading,
            removeAllBet = { teamSuggestionsViewModel.removeEverythingFromBetSip() },
            removeBet = {betBuilder -> teamSuggestionsViewModel.removeFromBetSip(betBuilder) },
            teamEventsStats = teamEventsStatsViewModel.listOfTeamEventsStatsState.value.listOfAllTeamEventsStats,
            isLoadingTeamEventStats = teamEventsStatsViewModel.listOfTeamEventsStatsState.value.isLoading,
            openStatsCard = teamEventsStatsViewModel.openStatsCard,
            onOpenStatsCard = {teamEventsStatsViewModel.onOpenStatsCard()},
            onCloseStatsCard = {teamEventsStatsViewModel.onCloseStatsCard()},
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