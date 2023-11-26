package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.domain.repository.TipsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TipsterViewModel @Inject constructor(
    private val tipsterRepository: TipsterRepository
): ViewModel() {

    var error by mutableStateOf(emptyString)
        private set

    var successMessage by mutableStateOf(emptyString)
        private set


    var errorMessage by mutableStateOf(emptyString)
        private set

    var openSearchCard by mutableStateOf(false)
        private set


    fun registerTipster(tipster: TipsterEntity) = viewModelScope.launch{
        tipsterRepository.registerTipster(tipster).onEach { response->
            when(response){
                is Resource.Success ->{
                    successMessage = response.data.toString()
                }
                is Resource.Loading ->{
                }
                is Resource.Error ->{
                    error =  response.data.toString()
                    errorMessage = response.message.toString()
                }
            }
        }.launchIn(this)

    }

}