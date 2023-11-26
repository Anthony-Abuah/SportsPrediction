package com.example.sportsprediction.feature_app.ui.presentation.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.ListOfTeamEventState
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.domain.repository.TeamEventRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.FormPercentageState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.GetTeamsPastEventsState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.ShowTeamsPastEventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TeamNameEventsViewModel @Inject constructor(
    private val teamNameEventRepository: TeamEventRepository
): ViewModel() {

    private var _awayListOfTeamEventState = mutableStateOf(ListOfTeamEventState())
    val awayListOfTeamEventState: State<ListOfTeamEventState> = _awayListOfTeamEventState

    private var _homeListOfTeamEventState = mutableStateOf(ListOfTeamEventState())
    val homeListOfTeamEventState: State<ListOfTeamEventState> = _homeListOfTeamEventState

    private var _ListOf_teamEventState = mutableStateOf(ListOfTeamEventState())
    val listOfTeamEventState: State<ListOfTeamEventState> = _ListOf_teamEventState

    private var _listOfPastEvents = mutableStateOf(ListOfTeamEventState())
    val listOfPastEvents: State<ListOfTeamEventState> = _listOfPastEvents

    private var _listOfHomeTeamFormEvents = listOf<TeamEventEntity>()
    val listOfHomeTeamFormEvents: List<TeamEventEntity> = _listOfHomeTeamFormEvents

    private var _listOfAwayTeamFormEvents = listOf<TeamEventEntity>()
    val listOfAwayTeamFormEvents: List<TeamEventEntity> = _listOfAwayTeamFormEvents

    private var _getTeamPastEventsMessages = mutableStateOf(GetTeamsPastEventsState())
    val getTeamPastEventsMessages: State<GetTeamsPastEventsState> = _getTeamPastEventsMessages

    private var _showTeamPastEventsMessages = mutableStateOf(ShowTeamsPastEventsState())
    val showTeamPastEventsMessages: State<ShowTeamsPastEventsState> = _showTeamPastEventsMessages

    private var _homeTeamFormPercentage = mutableStateOf(FormPercentageState())
    val homeTeamFormPercentage : State<FormPercentageState> = _homeTeamFormPercentage

    private var _awayTeamFormPercentage = mutableStateOf(FormPercentageState())
    val awayTeamFormPercentage : State<FormPercentageState> = _awayTeamFormPercentage


    private var _listOfTeamNameEvents = listOf<TeamEventEntity>()
    val listOfTeamNameEvents: List<TeamEventEntity> = _listOfTeamNameEvents


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var thereIsError by mutableStateOf(false)
        private set

    var showPastEvents by mutableStateOf(false)
        private set

    var homeFormPercentage by mutableStateOf(0.0)
        private set

    var awayFormPercentage by mutableStateOf(0.0)
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set

    var headToHeadEvents by mutableStateOf(emptyFlow<List<TeamEventEntity>>())
        private set

    var teamEventEntity by mutableStateOf(emptyFlow<TeamEventEntity>())
        private set

    fun getShowPastEvents(){
        showPastEvents = !showPastEvents
    }


    fun getTeamsPastEvents(listOfEvents: ListOfEvents, date: Date) = viewModelScope.launch {
        teamNameEventRepository.getTeamsPastEvents(listOfEvents, date).onEach { response->
            when(response){
                is Resource.Success ->{
                   _listOfPastEvents.value = listOfPastEvents.value.copy(
                       listOfTeamEvent = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfPastEvents.value = listOfPastEvents.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfPastEvents.value = listOfPastEvents.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = true
                    )

                    thereIsError = response.message != null
                    if (thereIsError){
                        errorMessage = response.message.toString()
                    }
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }

    fun showPastEvents(listOfEvents: ListOfEvents, date: Date) = viewModelScope.launch {
        Log.d("TeamNameEventViewModel", "showPastEventsFunction: this piece of code gets called")

        teamNameEventRepository.showTeamPastEvents(listOfEvents, date).onEach { response->
            when(response){
                is Resource.Success ->{
                    Log.d("TeamNameEventViewModel", "showPastEventsSuccess: this piece of code gets called")

                    _showTeamPastEventsMessages.value = showTeamPastEventsMessages.value.copy(
                       showTeamsPastEventsMessage = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    Log.d("TeamNameEventViewModel", "showPastEventsIsLoading: this piece of code gets called")

                    _showTeamPastEventsMessages.value = showTeamPastEventsMessages.value.copy(
                        showTeamsPastEventsMessage = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    Log.d("TeamNameEventViewModel", "showPastEventsError: this piece of code gets called")

                    _showTeamPastEventsMessages.value = showTeamPastEventsMessages.value.copy(
                        showTeamsPastEventsMessage = response.data ?: emptyList(),
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

    fun getRemoteHomeTeamEvents(teamId: Int, teamName: String, date: Date, headToHeadEventId: String) = viewModelScope.launch {
        teamNameEventRepository.getRemoteTeamEvents(teamId, teamName, date, headToHeadEventId).onEach { response->
            when(response){
                is Resource.Success ->{
                   _homeListOfTeamEventState.value = homeListOfTeamEventState.value.copy(
                       listOfTeamEvent = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _homeListOfTeamEventState.value = homeListOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _homeListOfTeamEventState.value = homeListOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
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

    fun getRemoteAwayTeamEvents(teamId: Int, teamName: String, date: Date, headToHeadEventId: String) = viewModelScope.launch {
        teamNameEventRepository.getRemoteTeamEvents(teamId, teamName, date, headToHeadEventId).onEach { response->
            when(response){
                is Resource.Success ->{
                   _awayListOfTeamEventState.value = awayListOfTeamEventState.value.copy(
                       listOfTeamEvent = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _awayListOfTeamEventState.value = awayListOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _awayListOfTeamEventState.value = awayListOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
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

    fun getHeadToHeadEvents(teamId: Int, headToHeadEventId: String) = viewModelScope.launch {
        headToHeadEvents = teamNameEventRepository.getHeadToHeadTeamEvents(teamId, headToHeadEventId) ?: emptyFlow()
    }

    fun getTeamNameEventsList(teamId: Int) = viewModelScope.launch {
        _listOfTeamNameEvents = teamNameEventRepository.getTeamNameEventsList(teamId) ?: emptyList()
    }

    fun getHomeTeamFormPercentage(teamId: Int) = viewModelScope.launch {
        teamNameEventRepository.getTeamFormPercentage(teamId).onEach { response->
            when(response){
                is Resource.Success ->{
                    _homeTeamFormPercentage.value = homeTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _homeTeamFormPercentage.value = homeTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _homeTeamFormPercentage.value = homeTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)

    }

    fun getAwayTeamFormPercentage(teamId: Int) = viewModelScope.launch {
        teamNameEventRepository.getTeamFormPercentage(teamId).onEach { response->
            when(response){
                is Resource.Success ->{
                    _awayTeamFormPercentage.value = awayTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _awayTeamFormPercentage.value = awayTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _awayTeamFormPercentage.value = awayTeamFormPercentage.value.copy(
                        formPercentage = response.data ?: 0.0,
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)

    }

    fun getTeamNameEventEntity(eventId: Int, teamId: Int) = viewModelScope.launch {
        teamNameEventRepository.getTeamNameEventEntity(eventId, teamId).onEach { response->
            when(response){
                is Resource.Success ->{
                    _ListOf_teamEventState.value = listOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _ListOf_teamEventState.value = listOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _ListOf_teamEventState.value = listOfTeamEventState.value.copy(
                        listOfTeamEvent = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)
    }



}