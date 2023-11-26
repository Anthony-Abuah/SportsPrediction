package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfSuggestions
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.domain.model.build_a_bet.BetSuggestion
import com.example.sportsprediction.feature_app.domain.repository.LoadSuggestionsRepository
import com.example.sportsprediction.feature_app.domain.repository.TeamEventStatsRepository
import com.example.sportsprediction.feature_app.domain.repository.TeamEventRepository
import com.example.sportsprediction.feature_app.domain.repository.TeamSuggestionsRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TeamSuggestionsViewModel @Inject constructor(
    private val teamSuggestionsRepository: TeamSuggestionsRepository,
    private val loadSuggestionsRepository: LoadSuggestionsRepository,
    private val teamNameEventRepository: TeamEventRepository,
    private val teamEventsStatsRepository: TeamEventStatsRepository
): ViewModel() {

    private val _teamSuggestionsState = mutableStateOf(TeamSuggestionsState())
    val teamSuggestionsState: State<TeamSuggestionsState> = _teamSuggestionsState

    private val _loadTeamSuggestionState = mutableStateOf(LoadTeamSuggestionsState())
    val loadTeamSuggestionState: State<LoadTeamSuggestionsState> = _loadTeamSuggestionState

    private val _listOfTeamSuggestionsState = mutableStateOf(ListOfTeamSuggestionsState())
    val listOfTeamSuggestionsState: State<ListOfTeamSuggestionsState> = _listOfTeamSuggestionsState

    private val _suggestionsState = mutableStateOf(TeamSuggestionsState())
    val suggestionsState: State<TeamSuggestionsState> = _suggestionsState

    private val _confidenceLevelState = mutableStateOf(ConfidenceLevelState())
    val confidenceLevelState: State<ConfidenceLevelState> = _confidenceLevelState

    private val _ListOf_teamEventState = mutableStateOf(ListOfTeamEventState())
    val listOfTeamEventState: State<ListOfTeamEventState> = _ListOf_teamEventState

    private val _teamEventsStatsState = mutableStateOf(TeamEventsStatsState())
    val teamEventsStatsState: State<TeamEventsStatsState> = _teamEventsStatsState

    private val _filteredSuggestions = mutableStateOf(FilteredSuggestionState())
    val filteredSuggestions: State<FilteredSuggestionState> = _filteredSuggestions


    private val _groupedSuggestions = mutableStateOf(GroupedSuggestionState())
    val groupedSuggestions: State<GroupedSuggestionState> = _groupedSuggestions



    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var thereIsError by mutableStateOf(false)
        private set

    var calculateBetConfidenceLevel by mutableStateOf(false)
        private set

    var openBetSlip by mutableStateOf(false)
        private set

    var isLoadingBetBuildersSuggestions by mutableStateOf(false)
        private set

    var openFilterCard by mutableStateOf(false)
        private set

    var betBuildersSuggestionMessage by mutableStateOf(emptyString)
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set

    var confidenceLevel by mutableStateOf(0.0)
        private set

    var betSlip by mutableStateOf( mutableListOf<BetSuggestion>())
        private set

    fun addToBetSip(mainTeamName: String, mainTeamId: String, opposingTeamName: String, opposingTeamId: String, eventId: String, headToHeadId: String, mainTeamPlayingLocation: String, marketName: String, numerator: String, denominator: String, percentageText: String, marketCategory: String, marketType: String, matchPeriod: String, team: String){
        val betSuggestion = BetSuggestion(mainTeamName, mainTeamId, opposingTeamName, opposingTeamId, eventId, headToHeadId, mainTeamPlayingLocation, marketName, numerator, denominator, percentageText, marketCategory, marketType, matchPeriod, team)
        if (!betSlip.contains(betSuggestion)) { betSlip.add(betSuggestion) }
    }


    fun removeFromBetSip(betSuggestion: BetSuggestion){
        if (betSlip.contains(betSuggestion)) {
            betSlip = betSlip.minus(betSuggestion).toMutableList()
        }
    }


    fun removeEverythingFromBetSip(){
        betSlip.clear()
    }

    fun onCalculateBetConfidenceLevel(){
        calculateBetConfidenceLevel = true
    }

    fun onCloseBetSlip(){
        openBetSlip = false
    }
    fun onOpenBetSlip(){
        openBetSlip = true
    }
    fun onOpenAndCloseBetSlip(){
        openBetSlip =!openBetSlip
    }

    fun onCloseFilterCard(){
        openFilterCard = false
    }
    fun onOpenFilterCard(){
        openFilterCard = true
    }
    fun onOpenOrCloseFilterCard(){
        openFilterCard =!openFilterCard
    }


    fun initializeFilteredSuggestions(suggestions: ListOfSuggestions){
        _filteredSuggestions.value = filteredSuggestions.value.copy(
            filteredSuggestions = suggestions,
            isLoading = false
        )
    }


    fun loadTeamSuggestion(teamId: Int, teamName: String, date: Date) = viewModelScope.launch {
        loadSuggestionsRepository.loadTeamSuggestions(teamId, teamName, date).onEach { response->
            when(response){
                is Resource.Success ->{
                   _loadTeamSuggestionState.value = loadTeamSuggestionState.value.copy(
                       suggestions = response.data ?: emptyList(),
                       isLoading = false
                   )
                    _eventFlow.emit(UIEvent.ShowSnackBar("Suggestions loaded successfully"))
                }
                is Resource.Loading ->{
                    _loadTeamSuggestionState.value = loadTeamSuggestionState.value.copy(
                        suggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                    _eventFlow.emit(UIEvent.ShowSnackBar("Loading suggestions..."))

                }
                is Resource.Error ->{
                    _loadTeamSuggestionState.value = loadTeamSuggestionState.value.copy(
                        suggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }


    fun groupSuggestions(suggestions: ListOfSuggestions, percentage: Double, arrangementOrder: String, suggestionGrouping: String ) = viewModelScope.launch {
        loadSuggestionsRepository.groupSuggestions(suggestions, percentage, arrangementOrder, suggestionGrouping).onEach { response->
            when(response){
                is Resource.Success ->{
                   _groupedSuggestions.value = groupedSuggestions.value.copy(
                       groupedSuggestions = response.data ?: emptyMap(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _groupedSuggestions.value = groupedSuggestions.value.copy(
                        groupedSuggestions = response.data ?: emptyMap(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _groupedSuggestions.value = groupedSuggestions.value.copy(
                        groupedSuggestions = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)

    }


    fun saveTeamSuggestionEntity(teamId: Int, teamName: String, date: Date, suggestions: ListOfSuggestions) = viewModelScope.launch {
        teamSuggestionsRepository.saveTeamSuggestionsEntity(teamId, teamName, date, suggestions).onEach { response->
            when(response){
                is Resource.Success ->{
                   _teamSuggestionsState.value = teamSuggestionsState.value.copy(
                       teamSuggestions = response.data ?: TeamSuggestionsEntity(null, emptyString, Date(), 0, emptyList()),
                       isLoading = false
                   )
                    _eventFlow.emit(UIEvent.ShowSnackBar("Suggestions are saved into database"))
                }
                is Resource.Loading ->{
                    _teamSuggestionsState.value = teamSuggestionsState.value.copy(
                        teamSuggestions = response.data ?: TeamSuggestionsEntity(null, emptyString, Date(), 0, emptyList()),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _teamSuggestionsState.value = teamSuggestionsState.value.copy(
                        teamSuggestions = response.data ?: TeamSuggestionsEntity(null, emptyString, Date(), 0, emptyList()),
                        isLoading = false
                    )
                    thereIsError = response.message != null
                    if (thereIsError){
                        errorMessage = response.message.toString()
                    }
                    _eventFlow.emit(UIEvent.ShowSnackBar(
                        response.message ?: "Unknown Error"
                    ))
                }
            }
        }.launchIn(this)

    }


    fun getFilterSuggestionsByPercentage(suggestions: ListOfSuggestions, percentage: Double) = viewModelScope.launch {
        teamSuggestionsRepository.filterSuggestionsByPercentage(suggestions, percentage).onEach { response->
            when(response){
                is Resource.Success ->{
                   _filteredSuggestions.value = filteredSuggestions.value.copy(
                       filteredSuggestions = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }


    fun orderSuggestions(suggestions: ListOfSuggestions, arrangementOrder: String) = viewModelScope.launch {
        teamSuggestionsRepository.orderSuggestions(suggestions, arrangementOrder).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }

    fun groupSuggestionsBy(suggestions: ListOfSuggestions, suggestionGrouping: String) = viewModelScope.launch {
        teamSuggestionsRepository.groupSuggestionsBy(suggestions, suggestionGrouping).onEach { response->
            when(response){
                is Resource.Success ->{
                    _groupedSuggestions.value = groupedSuggestions.value.copy(
                        groupedSuggestions = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _groupedSuggestions.value = groupedSuggestions.value.copy(
                        groupedSuggestions = response.data ?: emptyMap(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _groupedSuggestions.value = groupedSuggestions.value.copy(
                        groupedSuggestions = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }

    fun filterSuggestionsByMarketCategory(suggestions: ListOfSuggestions, marketCategories: List<String>) = viewModelScope.launch {
        teamSuggestionsRepository.filterSuggestionsByMarketCategory(suggestions, marketCategories).onEach { response->
            when(response){
                is Resource.Success ->{
                   _filteredSuggestions.value = filteredSuggestions.value.copy(
                       filteredSuggestions = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }


    fun filterSuggestionsByMarketType(suggestions: ListOfSuggestions, marketTypes: List<String>) = viewModelScope.launch {
        teamSuggestionsRepository.filterSuggestionsByMarketType(suggestions, marketTypes).onEach { response->
            when(response){
                is Resource.Success ->{
                   _filteredSuggestions.value = filteredSuggestions.value.copy(
                       filteredSuggestions = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }


    fun filterSuggestionsByMatchPeriod(suggestions: ListOfSuggestions, matchPeriod: String) = viewModelScope.launch {
        teamSuggestionsRepository.filterSuggestionsByMatchPeriod(suggestions, matchPeriod).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }

    fun getFilterSuggestionsByTeams(suggestions: ListOfSuggestions, teams: String) = viewModelScope.launch {
        teamSuggestionsRepository.filterSuggestionsByTeams(suggestions, teams).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredSuggestions.value = filteredSuggestions.value.copy(
                        filteredSuggestions = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }



    fun getBetorsConfidence(suggestion: Suggestion, betSuggestion: BetSuggestion) = viewModelScope.launch {
        /*
        teamSuggestionsRepository.getBetorsConfidence(suggestion, betSuggestion).onEach { response->
            when(response){
                is Resource.Success ->{
                   _confidenceLevelState.value = confidenceLevelState.value.copy(
                       confidenceLevel = response.data ?: 0.0,
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _confidenceLevelState.value = confidenceLevelState.value.copy(
                        confidenceLevel = response.data ?: 0.0,
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _confidenceLevelState.value = confidenceLevelState.value.copy(
                        confidenceLevel = response.data ?: 0.0,
                        isLoading = false
                    )
                    thereIsError = response.message != null
                    if (thereIsError){
                        errorMessage = response.message.toString()
                    }
                    _eventFlow.emit(UIEvent.ShowSnackBar(
                        response.message ?: "Unknown Error"
                    ))
                }
            }
        }.launchIn(this)
        */
    }




}