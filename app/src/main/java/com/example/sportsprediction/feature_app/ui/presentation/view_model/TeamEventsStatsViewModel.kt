package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.domain.repository.TeamEventStatsRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.ListOfTeamEventsStatsState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.TeamEventsStatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TeamEventsStatsViewModel @Inject constructor(
    private val teamEventsStatsRepository: TeamEventStatsRepository
): ViewModel() {

    private val _teamEventsStatsState = mutableStateOf(TeamEventsStatsState())
    val teamEventsStatsState: State<TeamEventsStatsState> = _teamEventsStatsState


    private val _listOfAllTeamEventsStatsState = mutableStateOf(ListOfTeamEventsStatsState())
    val listOfAllTeamEventsStatsState: State<ListOfTeamEventsStatsState> = _listOfAllTeamEventsStatsState

    private val _listOfTeamEventsStatsState = mutableStateOf(ListOfTeamEventsStatsState())
    val listOfTeamEventsStatsState: State<ListOfTeamEventsStatsState> = _listOfTeamEventsStatsState

    private val _eventStatsState = mutableStateOf(TeamEventsStatsState())
    val eventStatsState: State<TeamEventsStatsState> = _eventStatsState


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    var openStatsCard by mutableStateOf(false)
        private set

    var isLoadingStats by mutableStateOf(false)
        private set

    var thereIsError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set

    var loadingStatsMessage by mutableStateOf(emptyString)
        private set

    var resourceSuccess by mutableStateOf(emptyString)
        private set

    var resourceFailed by mutableStateOf(emptyString)
        private set


    fun onOpenStatsCard(){
        openStatsCard = true
    }

    fun onCloseStatsCard(){
        openStatsCard = false
    }


    fun getAllTeamEventStats(
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
                    _listOfAllTeamEventsStatsState.value = listOfAllTeamEventsStatsState.value.copy(
                       listOfAllTeamEventsStats = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfAllTeamEventsStatsState.value = listOfAllTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfAllTeamEventsStatsState.value = listOfAllTeamEventsStatsState.value.copy(
                        listOfAllTeamEventsStats = response.data ?: emptyList(),
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


    fun getTeamsPastEventStats(listOfEvents: ListOfEvents, date: Date, numberOfPastEvents: Int, numberOfPastHeadToHeadEvents: Int
        ) = viewModelScope.launch {
        teamEventsStatsRepository.getListOfTeamsPastEventsStats(listOfEvents, date, numberOfPastEvents, numberOfPastHeadToHeadEvents).onEach { response->
            when(response){
                is Resource.Success ->{
                    loadingStatsMessage = response.message ?: "null success message"
                    isLoadingStats = false
                }
                is Resource.Loading ->{
                    loadingStatsMessage = response.message ?: "null loading message"
                    isLoadingStats = true
                }
                is Resource.Error ->{
                    loadingStatsMessage = response.message ?: "null error message"
                    isLoadingStats = false

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



}