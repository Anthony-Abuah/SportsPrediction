package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.Football
import com.example.sportsprediction.core.util.Functions.orUnknownCountry
import com.example.sportsprediction.core.util.Functions.toEventsEntityList
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.repository.DateEventsRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.GroupedEventsState
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.PreferredEventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DateEventsViewModel @Inject constructor(
    private val dateEventRepository: DateEventsRepository
): ViewModel() {

    private val _preferredEventState = mutableStateOf(PreferredEventsState())
    val preferredEventState: State<PreferredEventsState> = _preferredEventState

    private val _groupedEventState = mutableStateOf(GroupedEventsState())
    val groupedEventState: State<GroupedEventsState> = _groupedEventState

    private val _filteredTournamentGroupedEventState = mutableStateOf(GroupedEventsState())
    val filteredTournamentGroupedEventState: State<GroupedEventsState> = _filteredTournamentGroupedEventState

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

    var theDate by mutableStateOf(Date())
        private set

    var groupedCountryEvents by mutableStateOf(emptyMap<String, List<EventsEntity>>())
        private set

    private var searchJob: Job? = null

    fun changeToGroupedCountryEvents(){
        groupedCountryEvents = groupedEventState.value.groupedEvents.toEventsEntityList().groupBy { it.country.orUnknownCountry() }
    }
    fun resetEvents(){
        _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
            groupedEvents = groupedEventState.value.groupedEvents,
            isLoading = false
        )
    }

    fun selectedDate(date: Date){
        theDate = date
    }

    var openFilterCard by mutableStateOf(false)
        private set

    fun onCloseFilterCard(){
        openFilterCard = false
    }
    fun onOpenOrCloseFilterCard(){
        openFilterCard =! openFilterCard
    }

    var openSearchCard by mutableStateOf(false)
        private set

    fun onCloseSearchCard(){
        openSearchCard = false
    }

    fun onOpenOrCloseSearchCard(){
        openSearchCard =! openSearchCard
    }


    fun getSearchedPreferredEvents(listOfEvents: ListOfEvents, searchValue: String) = viewModelScope.launch(Dispatchers.IO){
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
                    _eventFlow.emit(UIEvent.ShowSnackBar( response.message ?: "Unknown Error" ))
                }
            }
        }.launchIn(this)
    }

    fun getSearchedPreferredEvents(searchValue: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500L)
            dateEventRepository.getSearchedEvents(
                groupedEventState.value.groupedEvents,
                searchValue
            ).onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        _filteredTournamentGroupedEventState.value =
                            filteredTournamentGroupedEventState.value.copy(
                                groupedEvents = response.data ?: emptyMap(),
                                isLoading = false
                            )
                    }
                    is Resource.Loading -> {
                        _filteredTournamentGroupedEventState.value =
                            filteredTournamentGroupedEventState.value.copy(
                                groupedEvents = response.data ?: emptyMap(),
                                isLoading = true
                            )
                    }
                    is Resource.Error -> {
                        _filteredTournamentGroupedEventState.value =
                            filteredTournamentGroupedEventState.value.copy(
                                groupedEvents = response.data ?: emptyMap(),
                                isLoading = false
                            )
                    }
                }
            }.launchIn(this)
        }
    }

    fun getSortedPreferredEvents(listOfEvents: ListOfEvents, sortValue: String) = viewModelScope.launch(Dispatchers.IO){
        dateEventRepository.getSortedEvents(listOfEvents,  sortValue).onEach { response->
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
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }

    fun getSortedPreferredEvents(sortValue: String) = viewModelScope.launch(Dispatchers.IO){
        dateEventRepository.getSortedEvents(filteredTournamentGroupedEventState.value.groupedEvents,  sortValue).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
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
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }

    fun getMatchTimePreferredEvents(value: Long) = viewModelScope.launch(Dispatchers.IO){
        dateEventRepository.getMatchStartTimeEvents(groupedEventState.value.groupedEvents, value).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
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
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }

    fun getFilteredTournamentsPreferredEvents(value: Map<String, String>) = viewModelScope.launch(Dispatchers.IO){
        dateEventRepository.getFilteredTournaments(groupedEventState.value.groupedEvents,  value).onEach { response->
            when(response){
                is Resource.Success ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
                is Resource.Loading ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                        groupedEvents = response.data ?: emptyMap(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(this)

    }

    fun getPreferredDateEvents(date: Date) = viewModelScope.launch {
        dateEventRepository.getRemoteDateEvents(date, Football).onEach { response->
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
                    _eventFlow.emit(UIEvent.ShowSnackBar(response.message ?: "Unknown Error"))
                }
            }
        }.launchIn(this)

    }


    fun getRemoteGroupedDateEvents(date: Date) = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.IO){
            dateEventRepository.getRemoteGroupedDateEvents(date, Football).onEach { response->
                when(response){
                    is Resource.Success ->{
                        _groupedEventState.value = groupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = false
                        )
                        _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = false
                        )

                    }
                    is Resource.Loading ->{
                        _groupedEventState.value = groupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = true
                        )
                        _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = true
                        )
                    }
                    is Resource.Error ->{
                        _groupedEventState.value = groupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = false
                        )
                        _filteredTournamentGroupedEventState.value = filteredTournamentGroupedEventState.value.copy(
                            groupedEvents = response.data ?: emptyMap(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }


}