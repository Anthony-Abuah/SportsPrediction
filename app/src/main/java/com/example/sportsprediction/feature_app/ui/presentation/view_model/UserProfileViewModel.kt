package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.domain.repository.UserProfileRepository
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.UserProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
): ViewModel() {


    private val _userProfileState = mutableStateOf(UserProfileState())
    val userProfileState: State<UserProfileState> = _userProfileState


    var user by mutableStateOf(UserEntity( null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
        private set

    var error by mutableStateOf(emptyString)
        private set

    var errorMessage by mutableStateOf(emptyString)
        private set

    var openSearchCard by mutableStateOf(false)
        private set


    fun insertUser(user: UserEntity) = viewModelScope.launch{
        userProfileRepository.insertUser(user).onEach { response->
            when(response){
                is Resource.Success ->{
                    _userProfileState.value = userProfileState.value.copy(
                        success = response.data,
                        isSaving = false,
                        error = null
                    )
                }
                is Resource.Loading ->{
                    _userProfileState.value= userProfileState.value.copy(
                        success = null,
                        isSaving = true,
                        error = null
                    )
                }
                is Resource.Error ->{
                    _userProfileState.value= userProfileState.value.copy(
                        success = null,
                        isSaving = false,
                        error = "Unable to insert User"
                    )
                }
            }
        }.launchIn(this)

    }

    fun getUser(username: String, password: String) = viewModelScope.launch{
        user = userProfileRepository.getUser(username, password) ?: UserEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

}