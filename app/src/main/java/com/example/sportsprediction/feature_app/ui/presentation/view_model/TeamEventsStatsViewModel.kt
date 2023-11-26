package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.LoadingCompleted
import com.example.sportsprediction.core.util.Constants.UnknownError
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.domain.model.general.LoadStatsParameters
import com.example.sportsprediction.feature_app.domain.repository.TeamEventStatsRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.ListOfTeamEventsStatsState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.TeamEventsStatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TeamEventsStatsViewModel @Inject constructor(
    private val teamEventsStatsRepository: TeamEventStatsRepository
): ViewModel() {

    private val _listOfHomeTeamEventsStatsState = mutableStateOf(ListOfTeamEventsStatsState())
    val listOfHomeTeamEventsStatsState: State<ListOfTeamEventsStatsState> = _listOfHomeTeamEventsStatsState

    private val _listOfAwayTeamEventsStatsState = mutableStateOf(ListOfTeamEventsStatsState())
    val listOfAwayTeamEventsStatsState: State<ListOfTeamEventsStatsState> = _listOfAwayTeamEventsStatsState

    private val _listOfTeamEventsStatsState = mutableStateOf(ListOfTeamEventsStatsState())
    val listOfTeamEventsStatsState: State<ListOfTeamEventsStatsState> = _listOfTeamEventsStatsState

    private val _eventStatsState = mutableStateOf(TeamEventsStatsState())
    val eventStatsState: State<TeamEventsStatsState> = _eventStatsState


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    var statsLoadingMessage by mutableStateOf(emptyString)
        private set

    var isLoadingStats by mutableStateOf(false)
        private set


    fun loadHomeTeamEventsStats(
        mainTeamId: Int,
        headToHeadId: String,
        eventId: Int,
        date: Date,
        numberOfPastEvents: Int,
        numberOfHeadToHeadEvents: Int
        ) = viewModelScope.launch {
        teamEventsStatsRepository.getAllTeamEventStats(mainTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfHeadToHeadEvents).onEach { response->
            when(response){
                is Resource.Success ->{
                    _listOfHomeTeamEventsStatsState.value = listOfHomeTeamEventsStatsState.value.copy(
                       listOfAllTeamEventsStats = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfHomeTeamEventsStatsState.value = listOfHomeTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfHomeTeamEventsStatsState.value = listOfHomeTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }


    fun loadAwayTeamEventsStats(
        mainTeamId: Int,
        headToHeadId: String,
        eventId: Int,
        date: Date,
        numberOfPastEvents: Int,
        numberOfHeadToHeadEvents: Int
        ) = viewModelScope.launch {
        teamEventsStatsRepository.getAllTeamEventStats(mainTeamId, headToHeadId, eventId, date, numberOfPastEvents, numberOfHeadToHeadEvents).onEach { response->
            when(response){
                is Resource.Success ->{
                    _listOfAwayTeamEventsStatsState.value = listOfAwayTeamEventsStatsState.value.copy(
                       listOfAllTeamEventsStats = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfAwayTeamEventsStatsState.value = listOfAwayTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfAwayTeamEventsStatsState.value = listOfAwayTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }

    fun loadSelectedTeamEventsStats(
        parameters: List<LoadStatsParameters>
        ) = viewModelScope.launch {
        teamEventsStatsRepository.loadSelectedTeamEventsStats(parameters).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    isLoadingStats = false
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.data ?: LoadingCompleted))
                }
                is Resource.Loading -> {
                    isLoadingStats = true
                    statsLoadingMessage = response.data?: "Loading selected events stats..."
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.data ?: "Loading selected events stats..."))
                }
                is Resource.Error -> {
                    isLoadingStats = false
                    statsLoadingMessage = response.data?: "Unknown Error"
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: UnknownError))
                }
            }
        }.launchIn(this)

    }


    fun getTeamEventStats(
        mainTeamId: Int
        ) = viewModelScope.launch {
        teamEventsStatsRepository.getTeamEventStats(mainTeamId).onEach { response->
            when(response){
                is Resource.Success ->{
                    _listOfTeamEventsStatsState.value = listOfTeamEventsStatsState.value.copy(
                       listOfAllTeamEventsStats = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfTeamEventsStatsState.value = listOfTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfTeamEventsStatsState.value = listOfTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = response.data ?: emptyList(),
                        isLoading = false
                    )
                    _eventFlow.emit(UIEvent.ShowSnackBar(
                        response.message ?: "Unknown Error"
                    ))
                }
            }
        }.launchIn(this)
    }

    fun getEventStats(
        eventId: Int
        ) = viewModelScope.launch {
        teamEventsStatsRepository.getEventStats(eventId).onEach { response->
            when(response){
                is Resource.Success ->{
                    _eventStatsState.value = eventStatsState.value.copy(
                       teamEventsStats = response.data,
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _eventStatsState.value = eventStatsState.value.copy(
                        teamEventsStats = response.data,
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _eventStatsState.value = eventStatsState.value.copy(
                        teamEventsStats = response.data,
                        isLoading = false
                    )
                    _eventFlow.emit(UIEvent.ShowSnackBar(
                        response.message ?: "Unknown Error"
                    ))
                }
            }
        }.launchIn(this)
    }

}