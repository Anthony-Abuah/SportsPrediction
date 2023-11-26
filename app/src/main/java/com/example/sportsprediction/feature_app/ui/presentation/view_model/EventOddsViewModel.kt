package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.ListOfEventOddsState
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.core.util.UIEvent
import com.example.sportsprediction.feature_app.domain.repository.EventOddsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EventOddsViewModel @Inject constructor(
    private val eventOddsRepository: EventOddsRepository
): ViewModel() {


    private val _listOfEventOddsState = mutableStateOf(ListOfEventOddsState())
    val listOfEventOddsStateState: State<ListOfEventOddsState> = _listOfEventOddsState


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var thereIsError by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set


    fun getRemoteHomeTeamEvents(homeTeamId: Int, awayTeamId: Int, date: Date, eventId: Int) = viewModelScope.launch {
        eventOddsRepository.getRemoteEventOdds(homeTeamId, awayTeamId, date, eventId).onEach { response->
            when(response){
                is Resource.Success ->{
                   _listOfEventOddsState.value = listOfEventOddsStateState.value.copy(
                       listOfAllEventOdds = response.data ?: emptyList(),
                       isLoading = false
                   )
                }
                is Resource.Loading ->{
                    _listOfEventOddsState.value = listOfEventOddsStateState.value.copy(
                        listOfAllEventOdds = response.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error ->{
                    _listOfEventOddsState.value = listOfEventOddsStateState.value.copy(
                        listOfAllEventOdds = response.data ?: emptyList(),
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