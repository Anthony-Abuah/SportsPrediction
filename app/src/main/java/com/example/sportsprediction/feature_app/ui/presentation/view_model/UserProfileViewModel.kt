package com.example.sportsprediction.feature_app.ui.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.domain.repository.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository
): ViewModel() {

    var user by mutableStateOf(UserEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))
        private set

    var error by mutableStateOf(emptyString)
        private set

    var successMessage by mutableStateOf(emptyString)
        private set

    var RegisterMessages by mutableStateOf(emptyList<String>())
        private set

    var LoginMessages by mutableStateOf(emptyList<String>())
        private set


    var errorMessage by mutableStateOf(emptyString)
        private set

    var openSearchCard by mutableStateOf(false)
        private set


    fun registerUser(user: UserEntity) = viewModelScope.launch{
        userProfileRepository.registerUser(user).onEach { response->
            when(response){
                is Resource.Success ->{
                    successMessage = response.data.toString()
                    RegisterMessages = listOf(successMessage)
                }
                is Resource.Loading ->{
                }
                is Resource.Error ->{
                    error =  response.data.toString()
                    errorMessage = response.message.toString()
                    RegisterMessages = listOf(errorMessage, error)
                }
            }
        }.launchIn(this)

    }

    fun loginUser(username: String, password: String) = viewModelScope.launch{
        userProfileRepository.loginUser(username, password).onEach { response->
            when(response){
                is Resource.Success ->{
                    successMessage = response.data.toString()
                    LoginMessages = listOf(successMessage)
                }
                is Resource.Loading ->{
                }
                is Resource.Error ->{
                    error =  response.data.toString()
                    errorMessage = response.message.toString()
                    LoginMessages = listOf(errorMessage, error)
                }
            }
        }.launchIn(this)

    }

    fun getUser(username: String, password: String) = viewModelScope.launch{
        user = userProfileRepository.getUser(username, password) ?: UserEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

}