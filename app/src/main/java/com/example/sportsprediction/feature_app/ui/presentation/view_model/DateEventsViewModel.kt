package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.Football
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents
import com.example.sportsprediction.feature_app.domain.repository.DateEventsRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.DateEventState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.PreferredEventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DateEventsViewModel @Inject constructor(
    private val dateEventRepository: DateEventsRepository
): ViewModel() {


    private val _dateEventState = mutableStateOf(DateEventState())
    val dateEventState: State<DateEventState> = _dateEventState

    private val _preferredEventState = mutableStateOf(PreferredEventsState())
    val preferredEventState: State<PreferredEventsState> = _preferredEventState

    private val _matchStartTimeEventState = mutableStateOf(PreferredEventsState())
    val matchStartTimeEventState: State<PreferredEventsState> = _matchStartTimeEventState

    private val _searchedEventState = mutableStateOf(PreferredEventsState())
    val searchedEventState: State<PreferredEventsState> = _searchedEventState

    private val _sortEventState = mutableStateOf(PreferredEventsState())
    val sortEventState: State<PreferredEventsState> = _sortEventState

    private val _filteredTournamentsEventState = mutableStateOf(PreferredEventsState())
    val filteredTournamentsEventState: State<PreferredEventsState> = _filteredTournamentsEventState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    var dateEvent by mutableStateOf(DateEventsEntity(null, Date(), DateEvents(emptyList())))
        private set

    var thereIsError by mutableStateOf(false)
        private set

    var theDate by mutableStateOf(Date())
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set

    var searchedEvents by mutableStateOf(emptyList<EventsEntity>())
        private set

    fun selectedDate(date: Date){
        theDate = date
    }



    var buildBet by mutableStateOf(false)
        private set

    fun onOpenBuildBet(){
        buildBet = true
    }
    fun onCloseBuildBet(){
        buildBet = false
    }
    fun onOpenOrCloseBuildBet(){
        buildBet =! buildBet
    }


    var openFilterCard by mutableStateOf(false)
        private set

    fun onOpenFilterCard(){
        openFilterCard = true
    }
    fun onCloseFilterCard(){
        openFilterCard = false
    }
    fun onOpenOrCloseFilterCard(){
        openFilterCard =! openFilterCard
    }

    var openSearchCard by mutableStateOf(false)
        private set

    fun onOpenSearchCard(){
        openSearchCard = true
    }

    fun onCloseSearchCard(){
        openSearchCard = false
    }

    fun onOpenOrCloseSearchCard(){
        openSearchCard =! openSearchCard
    }

    fun getSearchedPreferredEvents(listOfEvents: ListOfEvents, searchValue: String) = viewModelScope.launch{
        dateEventRepository.getSearchedEvents(listOfEvents,  searchValue).onEach { response->
            when(response){
                is Resource.Success ->{
                    _searchedEventState.value = searchedEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _searchedEventState.value = searchedEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _searchedEventState.value = searchedEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
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

    fun getSortedPreferredEvents(listOfEvents: ListOfEvents, searchValue: String) = viewModelScope.launch{
        dateEventRepository.getSortedEvents(listOfEvents,  searchValue).onEach { response->
            when(response){
                is Resource.Success ->{
                    _sortEventState.value = sortEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _sortEventState.value = sortEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _sortEventState.value = sortEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
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

    fun getMatchTimePreferredEvents(listOfEvents: ListOfEvents, value: Long) = viewModelScope.launch{
        dateEventRepository.getMatchStartTimeEvents(listOfEvents,  value).onEach { response->
            when(response){
                is Resource.Success ->{
                    _matchStartTimeEventState.value = matchStartTimeEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _matchStartTimeEventState.value = matchStartTimeEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _matchStartTimeEventState.value = matchStartTimeEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
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

    fun getFilteredTournamentsPreferredEvents(listOfEvents: ListOfEvents, value: Map<String, String>) = viewModelScope.launch{
        dateEventRepository.getFilteredTournaments(listOfEvents,  value).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredTournamentsEventState.value = filteredTournamentsEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredTournamentsEventState.value = filteredTournamentsEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredTournamentsEventState.value = filteredTournamentsEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
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

    fun getPreferredDateEvents(date: Date) = viewModelScope.launch {
        dateEventRepository.getRemoteDateEvents(date,  Football).onEach { response->
            when(response){
                is Resource.Success ->{
                   _preferredEventState.value = preferredEventState.value.copy(
                       preferredEvents = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _preferredEventState.value = preferredEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _preferredEventState.value = preferredEventState.value.copy(
                        preferredEvents = response.data ?: emptyList(),
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


}