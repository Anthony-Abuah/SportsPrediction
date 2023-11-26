package com.example.sportsprediction.feature_app.ui.presentation.composables.prediction

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.sportsprediction.core.util.Constants.All
import com.example.sportsprediction.core.util.Constants.ArrangementOrder
import com.example.sportsprediction.core.util.Constants.ArrangementOrderList
import com.example.sportsprediction.core.util.Constants.Both_Teams
import com.example.sportsprediction.core.util.Constants.Descending
import com.example.sportsprediction.core.util.Constants.GroupSuggestionBy
import com.example.sportsprediction.core.util.Constants.MarketCategoryList
import com.example.sportsprediction.core.util.Constants.MarketTypeList
import com.example.sportsprediction.core.util.Constants.Market_Category
import com.example.sportsprediction.core.util.Constants.Market_Type
import com.example.sportsprediction.core.util.Constants.MatchPeriodList
import com.example.sportsprediction.core.util.Constants.Match_Period
import com.example.sportsprediction.core.util.Constants.Order_Suggestions_By
import com.example.sportsprediction.core.util.Constants.Percentage
import com.example.sportsprediction.core.util.Constants.PercentageFilter
import com.example.sportsprediction.core.util.Constants.SportsPredictionPreferences
import com.example.sportsprediction.core.util.Constants.SuggestionGroupOption
import com.example.sportsprediction.core.util.Constants.SuggestionGroupingList
import com.example.sportsprediction.core.util.Constants.Teams
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.*
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionsContent(
    mainTeamName: String,
    opponentTeamName: String,
    opponentTeamId: String,
    eventId: String,
    headToHeadId: String,
    mainTeamPlayingLocation: String,
    tournamentInfo: String,
    closeFilterCard: () -> Unit,
    orderSuggestions: (arrangementOrder: String) -> Unit,
    filterSuggestionsByTeam: (team: String) -> Unit,
    filterSuggestionsByMatchPeriod: (matchPeriod: String) -> Unit,
    filterSuggestionsByMarketCategory: (marketCategory: List<String>) -> Unit,
    filterSuggestionsByMarketType: (marketCategory: List<String>) -> Unit,
    filterSuggestionsByPercentage: (percentage: Double) -> Unit,
    groupSuggestionsBy: (suggestionGrouping: String) -> Unit,
    groupedSuggestions: Map<String?, ListOfSuggestions>,
    openFilterCard: Boolean,
    isLoadingSuggestions: Boolean,
    teamEventsStats: List<EventStatsEntity>,
    ) {

    val context = LocalContext.current
    val sportPredictionPreferences = context.getSharedPreferences(SportsPredictionPreferences, Context.MODE_PRIVATE)
    val editor = sportPredictionPreferences?.edit()

    val suggestionsArrangement = sportPredictionPreferences.getString(ArrangementOrder, Descending) ?: Descending
    val suggestionGrouping = sportPredictionPreferences.getString(SuggestionGroupOption, Market_Type) ?: Market_Type
    val percentageFilterValue = sportPredictionPreferences.getString(PercentageFilter, "50") ?: "50"

    var selectedSuggestionGrouping by remember { mutableStateOf(suggestionGrouping) }
    var typedInPercentageValue by remember { mutableStateOf(percentageFilterValue) }
    var percentageSelected by remember { mutableStateOf(percentageFilterValue) }
    var selectedArrangementOrder by remember { mutableStateOf(suggestionsArrangement) }
    var marketCategory by remember { mutableStateOf(emptyString) }
    var openStatsSheet by remember { mutableStateOf(false) }
    var openBetSlip by remember { mutableStateOf(false) }
    var selectedMarketCategory = listOf<String>()
    var selectedMarketType = listOf<String>()
    var selectedMatchPeriod by remember { mutableStateOf(All) }
    var selectedTeam by remember { mutableStateOf(Both_Teams) }
    val betSlipSheetState = rememberModalBottomSheetState()
    val filterSheetState = rememberModalBottomSheetState()
    val statsSheetState = rememberModalBottomSheetState()

    var openPercentageAlertDialog by remember { mutableStateOf(false) }
    var openArrangementOrderAlertDialog by remember { mutableStateOf(false) }
    var openSuggestionGroupingAlertDialog by remember { mutableStateOf(false) }
    var openMarketCategoryAlertDialog by remember { mutableStateOf(false) }
    var openMarketTypeAlertDialog by remember { mutableStateOf(false) }
    var openMatchPeriodAlertDialog by remember { mutableStateOf(false) }
    var openTeamAlertDialog by remember { mutableStateOf(false) }
    val betSuggestionList by remember { mutableStateOf(mutableListOf<BetSuggestion>()) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            TeamInfoCard(
                mainTeamName = mainTeamName,
                opponentTeamName = opponentTeamName,
                playingLocation = mainTeamPlayingLocation,
                tournamentInfo = tournamentInfo
            )
            Divider(color = MaterialTheme.colorScheme.onBackground)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                Card(
                    shape = MaterialTheme.shapes.small,
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(LocalSpacing.current.extraSmall),
                    elevation = CardDefaults.cardElevation(defaultElevation = LocalSpacing.current.default)
                ) {
                    Box(modifier = Modifier.padding(LocalSpacing.current.small)) {
                        Text(
                            text = "Grouped by $selectedSuggestionGrouping",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoadingSuggestions) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    BasicScreenColumn {
                        groupedSuggestions.forEach { groupedSuggestion ->
                            GroupedSuggestionCard(
                                group = groupedSuggestion.key ?: emptyString,
                                suggestions = groupedSuggestion.value,
                                betSuggestions = betSuggestionList,
                                onClickSuggestion = {_marketCategory->
                                    openStatsSheet = true
                                    marketCategory = _marketCategory
                                },
                                addToBuildBet = {_suggestion->
                                    val betSuggestion = _suggestion.toBetSuggestion(
                                        opponentTeamName = opponentTeamName,
                                        opponentTeamId = opponentTeamId,
                                        eventId = eventId,
                                        headToHeadId = headToHeadId,
                                        playingLoacation = mainTeamPlayingLocation
                                    )
                                    if (betSuggestionList.contains(betSuggestion)){
                                        betSuggestionList.remove(betSuggestion)
                                    }
                                    else betSuggestionList.add(betSuggestion)
                                    openBetSlip = true
                                }
                            )
                        }
                    }
                }


            }
        }

    }


    if (openFilterCard) {
        ModalBottomSheet(
            modifier = Modifier.padding(LocalSpacing.current.noPadding),
            shape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            sheetState = filterSheetState,
            onDismissRequest = { closeFilterCard() },
            dragHandle = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .height(LocalSpacing.current.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.shapes.extraSmall
                        )
                        .width(LocalSpacing.current.large)
                        .height(LocalSpacing.current.small)
                    ){
                        Text(text = "bar")
                    }
                }
            }
        ) {
            SuggestionsFilterCard(
                percentageFilterText = "$percentageSelected %",
                openPercentageFilterAlertDialog = { openPercentageAlertDialog = true },
                groupSuggestionBy = selectedSuggestionGrouping,
                openSuggestionGroupingDialog = { openSuggestionGroupingAlertDialog = true },
                arrangeSuggestionBy = selectedArrangementOrder,
                openSuggestionArrangementOrderAlertDialog = { openArrangementOrderAlertDialog = true },
                selectedMarketCategory = if(selectedMarketCategory.isNotEmpty()) selectedMarketCategory.random() else All,
                openMarketCategoryAlertDialog = { openMarketCategoryAlertDialog = true },
                selectedMarketType = if(selectedMarketType.isNotEmpty()) selectedMarketType.random() else All,
                openMarketTypeAlertDialog = { openMarketTypeAlertDialog = true },
                selectedMatchPeriod = selectedMatchPeriod,
                openMatchPeriodAlertDialog = { openMatchPeriodAlertDialog = true },
                selectedTeams = selectedTeam,
                openTeamsAlertDialog = { openTeamAlertDialog = true },
            )
        }
    }

    if (openBetSlip) {
        ModalBottomSheet(
            modifier = Modifier.padding(LocalSpacing.current.noPadding),
            shape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            sheetState = betSlipSheetState,
            onDismissRequest = { openBetSlip = false },
            dragHandle = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.shapes.extraSmall
                        )
                    ){
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            contentDescription = emptyString)
                    }
                }
            }
        ) {
            BetSlip(
                closeBetSlip = { openBetSlip = false },
                betList = betSuggestionList,
                mainTeamName = mainTeamName,
                isLoadingConfidenceLevel = false,
                removeBet = {_betSuggestion->
                    betSuggestionList.remove(_betSuggestion)
                },
                removeAllBet = {
                    betSuggestionList.clear()
                    openBetSlip = false
                }
            ) { openBetSlip = true }
        }
    }

    if (openStatsSheet) {
        ModalBottomSheet(
            modifier = Modifier.padding(LocalSpacing.current.noPadding),
            shape = MaterialTheme.shapes.large,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            sheetState = statsSheetState,
            onDismissRequest = { openStatsSheet = false },
            dragHandle = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .height(LocalSpacing.current.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.shapes.extraSmall
                        )
                        .width(LocalSpacing.current.large)
                        .height(LocalSpacing.current.small)
                    ){
                        Text(text = "bar")
                    }
                }
            }
        ) {
            StatsCard(category = marketCategory, playingLocation = mainTeamPlayingLocation, teamEventStats = teamEventsStats)
        }
    }

    CustomAlertDialog(
        openDialog = openPercentageAlertDialog,
        alertDialogTitleText = Percentage,
        closeDialog = {
            openPercentageAlertDialog = false
            percentageSelected = try {
                if (typedInPercentageValue.toDouble().isNaN()) "50"
                else if (typedInPercentageValue.toDouble() < 1.0) typedInPercentageValue.toDouble()
                    .times(100.0).toString()
                else if (typedInPercentageValue.toDouble() > 100.0) "100"
                else typedInPercentageValue
            } catch (e: NumberFormatException) {
                "50"
            }
            editor?.apply {
                putString(PercentageFilter, percentageSelected)
                apply()
            }
            val percentage = percentageSelected.toDouble().div(100.0)
            filterSuggestionsByPercentage(percentage)
        }
    ) {
        AlertDialogTextField(
            oldValue = percentageSelected,
            keyboardType = KeyboardType.Decimal,
            getNewValue = { value ->
                typedInPercentageValue = value
            },
        )
    }


    CustomAlertDialog(
        openDialog = openSuggestionGroupingAlertDialog,
        alertDialogTitleText = GroupSuggestionBy,
        closeDialog = {
            openSuggestionGroupingAlertDialog = false
            editor?.apply {
                putString(SuggestionGroupOption, selectedSuggestionGrouping)
                apply()
            }
            groupSuggestionsBy(selectedSuggestionGrouping)
        }
    ) {
        SimpleRadioButtonComponent(
            radioOptions = SuggestionGroupingList,
            oldSelectedOption = selectedSuggestionGrouping,
            getOptionSelected = { _selectedOption ->
                selectedSuggestionGrouping = _selectedOption
            }
        )
    }

    CustomAlertDialog(
        openDialog = openTeamAlertDialog,
        alertDialogTitleText = Teams,
        closeDialog = {
            openTeamAlertDialog = false
            filterSuggestionsByTeam(selectedTeam)

        }
    ) {
        val teamsList = listOf(Both_Teams, mainTeamName )
        SimpleRadioButtonComponent(
            radioOptions = teamsList,
            oldSelectedOption = selectedTeam,
            getOptionSelected = { _selectedOption ->
                selectedTeam = _selectedOption
            }
        )
    }


    CustomAlertDialog(
        openDialog = openArrangementOrderAlertDialog,
        alertDialogTitleText = Order_Suggestions_By,
        closeDialog = {
            openArrangementOrderAlertDialog = false
            editor?.apply {
                putString(ArrangementOrder, selectedArrangementOrder)
                apply()
                orderSuggestions(selectedArrangementOrder)

            }
        }
    ) {
        SimpleRadioButtonComponent(
            radioOptions = ArrangementOrderList,
            oldSelectedOption = selectedArrangementOrder,
            getOptionSelected = { _selectedOption ->
                selectedArrangementOrder = _selectedOption
            }
        )
    }


    CustomAlertDialog(
        openDialog = openMatchPeriodAlertDialog,
        alertDialogTitleText = Match_Period,
        closeDialog = {
            openMatchPeriodAlertDialog = false
            filterSuggestionsByMatchPeriod(selectedMatchPeriod)
        }
    ) {
        SimpleRadioButtonComponent(
            radioOptions = MatchPeriodList,
            oldSelectedOption = selectedMatchPeriod,
            getOptionSelected = { _selectedOption ->
                selectedMatchPeriod = _selectedOption
            }
        )
    }


    CustomAlertDialog(
        openDialog = openMarketCategoryAlertDialog,
        alertDialogTitleText = Market_Category,
        closeDialog = {
            openMarketCategoryAlertDialog = false
            filterSuggestionsByMarketCategory(selectedMarketCategory)
        }
    ) {
        ScrollableChecklistComponent(
            checkListOptions = MarketCategoryList,
            checkedItems = selectedMarketCategory,
            getCheckedItems = {_selectedMarketCategory ->
                selectedMarketCategory = if (_selectedMarketCategory.contains(All)) { MarketCategoryList.minus(All) } else { _selectedMarketCategory }

            }
        )
    }


    CustomAlertDialog(
        openDialog = openMarketTypeAlertDialog,
        alertDialogTitleText = Market_Type,
        closeDialog = {
            openMarketTypeAlertDialog = false
            filterSuggestionsByMarketType(selectedMarketType)
        }
    ) {
        ScrollableChecklistComponent(
            checkListOptions = MarketTypeList,
            checkedItems = selectedMarketType,
            getCheckedItems = {_selectedMarketType ->
                selectedMarketType = if (_selectedMarketType.contains(All)) { MarketTypeList.minus(All) } else { _selectedMarketType }
            }
        )

    }
}








