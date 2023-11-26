package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sportsprediction.R
import com.example.sportsprediction.core.util.Constants.All
import com.example.sportsprediction.core.util.Constants.ArrangementOrder
import com.example.sportsprediction.core.util.Constants.ArrangementOrderList
import com.example.sportsprediction.core.util.Constants.Descending
import com.example.sportsprediction.core.util.Constants.MarketCategoryList
import com.example.sportsprediction.core.util.Constants.MarketTypeList
import com.example.sportsprediction.core.util.Constants.Market_Category
import com.example.sportsprediction.core.util.Constants.Market_Type
import com.example.sportsprediction.core.util.Constants.MatchPeriodList
import com.example.sportsprediction.core.util.Constants.Match_Period
import com.example.sportsprediction.core.util.Constants.Percentage
import com.example.sportsprediction.core.util.Constants.PercentageFilter
import com.example.sportsprediction.core.util.Constants.SportsPredictionPreferences
import com.example.sportsprediction.core.util.Constants.SuggestionGroupOption
import com.example.sportsprediction.core.util.Constants.SuggestionGroupingList
import com.example.sportsprediction.core.util.Constants.Suggestions_Arrangement_Order
import com.example.sportsprediction.core.util.Constants.Suggestions_Grouping
import com.example.sportsprediction.core.util.Constants.TeamList
import com.example.sportsprediction.core.util.Constants.Teams
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import com.example.sportsprediction.feature_app.ui.theme.PrimaryThemeColor
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PredictionsContent(
    mainTeamName: String,
    mainTeamId: String,
    opponentTeamName: String,
    opponentTeamId: String,
    mainTeamPlayingLocation: String,
    headToHeadId: String,
    eventId: String,
    tournamentInfo: String,
    teamEventEntity: TeamEventEntity?,
    teamEventsStats: List<EventStatsEntity>,
    teamSuggestion: TeamSuggestionsEntity,
    addToBetSlip: (mainTeamName: String, mainTeamId: String, opposingTeamName: String, opposingTeamId: String, eventId: String, headToHeadId: String, mainTeamPlayingLocation: String, marketName: String, numerator: String, denominator: String, percentageText: String, marketCategory: String, marketType: String, matchPeriod: String, team: String) -> Unit,
    betList: List<BetSuggestion>,
    confidenceLevel: Double,
    isLoadingConfidenceLevel: Boolean,
    openOrCloseFilterCard: () -> Unit,
    closeBetSlip: () -> Unit,
    openBetSlip: () -> Unit,
    onOpenStatsCard: () -> Unit,
    onCloseStatsCard: () -> Unit,
    calculateBetConfidenceLevel: (suggestion: Suggestion, betSuggestion: BetSuggestion) -> Unit,
    removeBet: (betSuggestion: BetSuggestion) -> Unit,
    removeAllBet: () -> Unit,
    isRefreshing: Boolean,
    getSuggestions: () -> Unit,
    showBetSlip: Boolean,
    openFilterCard: Boolean,
    openStatsCard: Boolean,
    isLoadingTeamNameEventEntity: Boolean,
    isLoadingTeamEventStats: Boolean,
    isLoadingSuggestions: Boolean
) {

    val context = LocalContext.current
    val sportPredictionPreferences = context.getSharedPreferences(SportsPredictionPreferences, Context.MODE_PRIVATE)

    val suggestionsArrangement = sportPredictionPreferences.getString(ArrangementOrder, Descending) ?: Descending
    val suggestionGrouping = sportPredictionPreferences.getString(SuggestionGroupOption, Market_Type) ?: Market_Type
    val percentageFilterValue = sportPredictionPreferences.getString(PercentageFilter, "50") ?: "50"

    var category by remember { mutableStateOf(emptyString) }
    var selectedSuggestionGrouping by remember { mutableStateOf(suggestionGrouping) }
    var newPercentageSelected by remember { mutableStateOf(percentageFilterValue) }
    var percentageSelected by remember { mutableStateOf(percentageFilterValue) }
    var selectedArrangementOrder by remember { mutableStateOf(suggestionsArrangement) }
    var selectedMarketCategory by remember { mutableStateOf(All) }
    var selectedMarketType by remember { mutableStateOf(All) }
    var selectedMatchPeriod by remember { mutableStateOf(All) }
    var selectedTeam by remember { mutableStateOf(All) }

    var openPercentageAlertDialog by remember { mutableStateOf(false) }
    var openArrangementOrderAlertDialog by remember { mutableStateOf(false) }
    var openSuggestionGroupingAlertDialog by remember { mutableStateOf(false) }
    var openMarketCategoryAlertDialog by remember { mutableStateOf(false) }
    var openMarketTypeAlertDialog by remember { mutableStateOf(false) }
    var openMatchPeriodAlertDialog by remember { mutableStateOf(false) }
    var openTeamAlertDialog by remember { mutableStateOf(false) }
    //var openFilterCard by remember { mutableStateOf(false) }

    var topSuggestions = teamSuggestion.suggestions.groupBy { suggestions->
        suggestions.marketType
    }

    if(selectedSuggestionGrouping.lowercase(Locale.getDefault()) == Market_Type.lowercase(Locale.getDefault())){
        topSuggestions = teamSuggestion.suggestions.groupBy { suggestions -> suggestions.marketType }
        if(selectedMarketType != All){
            topSuggestions = topSuggestions.filter {topPrediction->  topPrediction.key == selectedMarketType }
        }
    }else if(selectedSuggestionGrouping.lowercase(Locale.getDefault()) == Market_Category.lowercase(Locale.getDefault())){
        topSuggestions = teamSuggestion.suggestions.groupBy { suggestions -> suggestions.marketCategory }
    }else if(selectedSuggestionGrouping.lowercase(Locale.getDefault()) == Match_Period.lowercase(Locale.getDefault())){
        topSuggestions = teamSuggestion.suggestions.groupBy { suggestions -> suggestions.matchPeriod }
    }else if(selectedSuggestionGrouping.lowercase(Locale.getDefault()) == Teams.lowercase(Locale.getDefault())){
        topSuggestions = teamSuggestion.suggestions.groupBy { suggestions -> suggestions.team }

    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {

        if (isLoadingTeamNameEventEntity){
            Box(modifier = Modifier
                .padding(LocalSpacing.current.small)
                .fillMaxWidth()
                .height(LocalSpacing.current.dialogBox)
                .background(MainBackgroundColor),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        else {
            TeamCard(teamEventEntity = teamEventEntity, mainTeamName = mainTeamName, opponentTeamName = opponentTeamName, filter = "Grouped by $selectedSuggestionGrouping", tournamentInfo = tournamentInfo) {
                openOrCloseFilterCard()
                closeBetSlip()
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.default))

        Box(modifier = Modifier.fillMaxSize(1f),
        contentAlignment = Alignment.BottomCenter
        ){
            val mainVerticalScrollState = rememberScrollState(0)
            val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = { getSuggestions() })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(LocalSpacing.current.noPadding)
                    .pullRefresh(pullRefreshState)
                    .verticalScroll(state = mainVerticalScrollState, enabled = true),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                if (isLoadingSuggestions){
                    Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center){
                        //CircularProgressIndicator()
                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullRefreshState,
                            modifier = Modifier.align(Alignment.TopCenter)
                        )
                    }

                }

                else {
                    topSuggestions.forEach{topSuggestion->

                        if (selectedMarketType != All) {
                            topSuggestions = topSuggestions.filter { topPrediction -> topPrediction.key == selectedMarketType }
                        }
                        if (!openPercentageAlertDialog){
                            percentageSelected = newPercentageSelected
                        }

                        val percentageSelectedValue = try {
                            if (percentageSelected.toDouble().isNaN()) "50"
                            else if (percentageSelected.toDouble() < 1.0) percentageSelected.toDouble().times(100.0).toString()
                            else if (percentageSelected.toDouble() > 100.0) "100"
                            else percentageSelected
                        } catch (e: NumberFormatException) { "50" }

                        val newSuggestion = topSuggestion.value.filter { suggestion ->
                            if (selectedMarketCategory != All){suggestion.marketCategory == selectedMarketCategory}
                            else suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                        }.filter { suggestion ->
                            if (selectedMarketType != All){suggestion.marketType == selectedMarketType}
                            else suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                        }.filter { suggestion ->
                            if (selectedMatchPeriod != All){suggestion.matchPeriod == selectedMatchPeriod}
                            else suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                        }.filter { suggestion ->
                            if (selectedTeam != All){suggestion.team == selectedTeam}
                            else suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                        }

                        PredictionsCard(
                            suggestions =
                            if (selectedArrangementOrder.lowercase(Locale.getDefault()) == Descending.lowercase(Locale.ROOT))
                                newSuggestion.filter { suggestion -> suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                                }.sortedByDescending { suggestion -> suggestion.streakProbability }
                            else newSuggestion.filter { suggestion ->
                                suggestion.streakProbability!! > percentageSelectedValue.toDouble().div(100.0)
                            }.sortedByDescending { suggestion -> suggestion.streakProbability }.reversed(),

                            onAddToBetSlip = { marketName, numerator, denominator, value, percentageText, marketCategory, marketType, matchPeriod, team ->
                                val betSuggestion = Suggestion(
                                    teamName = mainTeamName,
                                    teamId = mainTeamId.toInt(),
                                    market = marketName,
                                    outcome = numerator.toDouble(),
                                    sampleSpace = denominator.toDouble(),
                                    value = value,
                                    marketCategory = marketCategory,
                                    marketType = marketType,
                                    matchPeriod = matchPeriod,
                                    team = team
                                )
                                val betBuilder = BetSuggestion(mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, eventId, headToHeadId, mainTeamPlayingLocation, marketName, numerator, denominator, percentageText, marketCategory, marketType, matchPeriod, team)
                                addToBetSlip(mainTeamName, mainTeamId, opponentTeamName, opponentTeamId, eventId, headToHeadId, mainTeamPlayingLocation, marketName, numerator, denominator, percentageText, marketCategory, marketType, matchPeriod, team)
                                calculateBetConfidenceLevel(betSuggestion, betBuilder)
                                closeBetSlip()
                                openBetSlip()

                            },
                            onOpenStats = {thisCategory->
                                category = thisCategory
                                onOpenStatsCard()
                            },
                            teamName = mainTeamName
                        )
                        Spacer(modifier = Modifier.height(LocalSpacing.current.default))

                    }
                }

            }

            if(openFilterCard) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.noPadding)
                        .fillMaxHeight(0.6f)
                        .fillMaxWidth(),
                    elevation = 15.dp,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(LocalSpacing.current.smallMedium)
                ) {
                    val scrollState = rememberScrollState(0)
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = PrimaryThemeColor)
                                .clickable { openOrCloseFilterCard() },
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier.padding(LocalSpacing.current.noPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(LocalSpacing.current.large)
                                        .height(LocalSpacing.current.large),
                                    painter = painterResource(
                                        id = R.drawable.drop_down
                                    ),
                                    contentDescription = emptyString,
                                    tint = Color.White
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = scrollState, enabled = true),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            // Percentage Row
                            if (!openMarketCategoryAlertDialog && !openMarketTypeAlertDialog && !openMatchPeriodAlertDialog && !openTeamAlertDialog && !openArrangementOrderAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openPercentageAlertDialog = !openPercentageAlertDialog
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.percentage),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.smallMedium),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Percentage)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.smallMedium),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.smallMedium),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = "$percentageSelected %")
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openPercentageAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                CustomAlertDialog(openDialog = openPercentageAlertDialog,
                                    title = "Select Percentage",
                                    closeDialog = { openPercentageAlertDialog =! openPercentageAlertDialog }) {
                                    AlertDialogTextField(
                                        oldValue = percentageSelected,
                                        keyboardType = KeyboardType.Decimal,
                                        getNewValue = {percentage->
                                            newPercentageSelected = percentage
                                        }
                                    )
                                }
                            }

                            // SuggestionVariables Grouping Row
                            if (!openMarketTypeAlertDialog && !openMatchPeriodAlertDialog && !openTeamAlertDialog && !openMarketCategoryAlertDialog && !openArrangementOrderAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openSuggestionGroupingAlertDialog =
                                                !openSuggestionGroupingAlertDialog
                                            openArrangementOrderAlertDialog = false
                                            openMarketCategoryAlertDialog = false
                                            openMarketTypeAlertDialog = false
                                            openMatchPeriodAlertDialog = false
                                            openTeamAlertDialog = false
                                            openPercentageAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Suggestions_Grouping)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedSuggestionGrouping)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openSuggestionGroupingAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openSuggestionGroupingAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedSuggestionGrouping,
                                        listItems = SuggestionGroupingList
                                    ) { theSelectedSuggestionGrouping ->
                                        selectedSuggestionGrouping = theSelectedSuggestionGrouping
                                    }

                                }
                            }

                            // Arrangement Order Row
                            if (!openMarketTypeAlertDialog && !openMatchPeriodAlertDialog && !openTeamAlertDialog && !openMarketCategoryAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openArrangementOrderAlertDialog =
                                                !openArrangementOrderAlertDialog
                                            openMarketCategoryAlertDialog = false
                                            openMarketTypeAlertDialog = false
                                            openMatchPeriodAlertDialog = false
                                            openTeamAlertDialog = false
                                            openPercentageAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Suggestions_Arrangement_Order)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedArrangementOrder)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openArrangementOrderAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openArrangementOrderAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedArrangementOrder,
                                        listItems = ArrangementOrderList
                                    ) { theSelectedArrangementOrder ->
                                        selectedArrangementOrder =
                                            theSelectedArrangementOrder
                                    }

                                }
                            }

                            // Market Category Row
                            if (!openMarketTypeAlertDialog && !openMatchPeriodAlertDialog && !openTeamAlertDialog && !openArrangementOrderAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openMarketCategoryAlertDialog =
                                                !openMarketCategoryAlertDialog
                                            openArrangementOrderAlertDialog = false
                                            openMarketTypeAlertDialog = false
                                            openMatchPeriodAlertDialog = false
                                            openTeamAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Market_Category)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedMarketCategory)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openMarketCategoryAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openMarketCategoryAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedMarketCategory,
                                        listItems = MarketCategoryList
                                    ) { theSelectedMarket ->
                                        selectedMarketCategory = theSelectedMarket
                                    }

                                }
                            }

                            // Market Type Row
                            if (!openMarketCategoryAlertDialog && !openMatchPeriodAlertDialog && !openTeamAlertDialog && !openArrangementOrderAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openMarketTypeAlertDialog =
                                                !openMarketTypeAlertDialog
                                            openArrangementOrderAlertDialog = false
                                            openMatchPeriodAlertDialog = false
                                            openMarketCategoryAlertDialog = false
                                            openTeamAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Market_Type)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedMarketType)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openMarketTypeAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openMarketTypeAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedMarketType,
                                        listItems = MarketTypeList
                                    ) { theSelectedMarketType ->
                                        selectedMarketType = theSelectedMarketType
                                    }

                                }
                            }

                            // Match Period Row
                            if (!openMarketCategoryAlertDialog && !openMarketTypeAlertDialog && !openTeamAlertDialog && !openArrangementOrderAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openMatchPeriodAlertDialog =
                                                !openMatchPeriodAlertDialog
                                            openArrangementOrderAlertDialog = false
                                            openMarketTypeAlertDialog = false
                                            openMarketCategoryAlertDialog = false
                                            openTeamAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Match_Period)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedMatchPeriod)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openMatchPeriodAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openMatchPeriodAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedMatchPeriod,
                                        listItems = MatchPeriodList
                                    ) { theSelectedMatchPeriod ->
                                        selectedMatchPeriod = theSelectedMatchPeriod
                                    }

                                }
                            }

                            // Team Row
                            if (!openMarketCategoryAlertDialog && !openMarketTypeAlertDialog && !openMatchPeriodAlertDialog && !openArrangementOrderAlertDialog && !openSuggestionGroupingAlertDialog) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            openTeamAlertDialog = !openTeamAlertDialog
                                            openArrangementOrderAlertDialog = false
                                            openMatchPeriodAlertDialog = false
                                            openMarketTypeAlertDialog = false
                                            openMarketCategoryAlertDialog = false
                                        }
                                        .padding(vertical = LocalSpacing.current.smallMedium),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(LocalSpacing.current.large)
                                            .width(LocalSpacing.current.large)
                                            .padding(horizontal = LocalSpacing.current.small),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Icon(
                                            modifier = Modifier.fillMaxSize(),
                                            painter = painterResource(id = R.drawable.market_category),
                                            contentDescription = emptyString,
                                            tint = PrimaryThemeColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                                    Row(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(4f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText(text = Teams)
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .height(LocalSpacing.current.medium)
                                                    .width(LocalSpacing.current.medium)
                                                    .padding(horizontal = LocalSpacing.current.small),
                                                painter = painterResource(id = R.drawable.dot),
                                                contentDescription = emptyString,
                                                tint = PrimaryThemeColor
                                            )
                                        }
                                        Box(
                                            modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            FilterDisplayText1(text = selectedTeam)
                                        }

                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = LocalSpacing.current.small)
                                            .weight(0.5f),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            modifier = Modifier.background(
                                                color = PrimaryThemeColor,
                                                shape = CircleShape
                                            ),
                                            imageVector = if (openTeamAlertDialog) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = emptyString,
                                            tint = Color.White
                                        )
                                    }

                                }

                                Divider(
                                    modifier = Modifier.padding(horizontal = LocalSpacing.current.small),
                                    color = Color.LightGray
                                )

                                AnimatedVisibility(visible = openTeamAlertDialog) {
                                    AlertAutoCompleteTextField(
                                        placeholderText = selectedTeam,
                                        listItems = TeamList
                                    ) { theSelectedTeam ->
                                        selectedTeam = theSelectedTeam
                                    }

                                }
                            }

                        }
                    }

                }
            }

            if(showBetSlip) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.noPadding)
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    elevation = 15.dp,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(LocalSpacing.current.smallMedium)
                ) {
                    BetSlip(
                        openBetSlip = openBetSlip,
                        closeBetSlip =  closeBetSlip,
                        betList = betList,
                        confidenceLevel = confidenceLevel,
                        isLoadingConfidenceLevel = isLoadingConfidenceLevel,
                        removeBet = {betSuggestion ->
                            removeBet(betSuggestion)
                        },
                        removeAllBet = removeAllBet
                    )
                }
            }

            if(openStatsCard){
                Card(
                    modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.noPadding)
                        .fillMaxHeight(1f)
                        .fillMaxWidth(),
                    elevation = 15.dp,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(LocalSpacing.current.small)
                ){
                    StatsCard(
                        category = category,
                        isLoading = isLoadingTeamEventStats,
                        teamEventStats = teamEventsStats,
                        closeStatsCard = onCloseStatsCard
                    )
                }
            }

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )

        }


    }

}








