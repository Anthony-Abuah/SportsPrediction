package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.repository.PreferredEventRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.PreferredEventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PreferredEventsViewModel @Inject constructor(
    private val preferredEventRepository: PreferredEventRepository
): ViewModel() {


    var preferredEvent by mutableStateOf(EventsEntity(null, Date(), 0, emptyString, null,  emptyString, 0, emptyString,  0,  0, 0, null, null, null, null, null, null, null, null, null, null, null, null))
        private set

    var preferredEventEntity by mutableStateOf(emptyFlow<EventsEntity>())
        private set

    private val _preferredEventsState = mutableStateOf(PreferredEventsState())
    val preferredEventsState: State<PreferredEventsState> = _preferredEventsState

    lateinit var listOfEventsByDate: ListOfEvents
    private set

    lateinit var teamEventsByDate: ListOfEvents
    private set


    var openBetSlip by mutableStateOf(false)
        private set

    fun onOpenBetSlip(){
        openBetSlip = true
    }
    fun onOpenAndCloseBetSlip(){
        openBetSlip =!openBetSlip
    }


    fun getTeamEvents(date: Date, teamId: Int) = viewModelScope.launch {
        teamEventsByDate = preferredEventRepository.getTeamEvents(date, teamId) ?: emptyList()
        _preferredEventsState.value = preferredEventsState.value.copy(
            preferredEvents = preferredEventRepository.getTeamEvents(date, teamId) ?: emptyList()
        )
    }

    fun getPreferredEvents(date: Date) = viewModelScope.launch {
        listOfEventsByDate = preferredEventRepository.getPreferredEvents(date) ?: emptyList()
        _preferredEventsState.value = preferredEventsState.value.copy(
            preferredEvents = preferredEventRepository.getPreferredEvents(date) ?: emptyList()
        )
    }

    fun getPreferredEvent(eventId: Int) = viewModelScope.launch {
        preferredEventEntity = preferredEventRepository.getPreferredEvent(eventId) ?: preferredEventEntity
    }

}