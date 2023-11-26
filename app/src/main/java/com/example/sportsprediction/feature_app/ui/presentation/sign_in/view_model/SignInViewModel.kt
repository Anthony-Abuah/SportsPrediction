package com.example.sportsprediction.feature_app.ui.presentation.sign_in.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsprediction.core.util.Constants.UnableToSignIn
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.domain.repository.AuthRepository
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.UserData
import com.example.sportsprediction.feature_app.ui.presentation.view_model.states.*
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _googleSignInState = mutableStateOf(SignInState())
    val googleSignInState: State<SignInState> = _googleSignInState

    private val _emailSignInState = mutableStateOf(SignInState())
    val emailSignInState: State<SignInState> = _emailSignInState

    private val _emailSignUpState = mutableStateOf(SignInState())
    val emailSignUpState: State<SignInState> = _emailSignUpState

    var userData: UserData? by mutableStateOf(null)
        private set


    fun getCurrentlySignedInUser() = viewModelScope.launch{
        userData = authRepository.getCurrentlySignedInUser()
    }

    fun signOut() = viewModelScope.launch {
        authRepository.signOut()
    }


    fun signInWithGoogle(credential: AuthCredential) = viewModelScope.launch{
        authRepository.signInWithGoogle(credential).onEach { result->
            when(result){
                is Resource.Success ->{
                    _googleSignInState.value = SignInState(
                        success = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Loading ->{
                    _googleSignInState.value = SignInState(
                        success = null,
                        isLoading = true,
                        error = null
                    )
                }
                is Resource.Error ->{
                    _googleSignInState.value = SignInState(
                        success = null,
                        isLoading = false,
                        error = result.message ?: UnableToSignIn
                    )
                }
            }
        }.launchIn(this)
    }
    /*
    fun signInWithGoogleAndGetUser(credential: AuthCredential) = viewModelScope.launch{
        authRepository.signInWithGoogleAndGetUser(credential).onEach { result->
            when(result){
                is Resource.Success ->{
                    _googleSignInResultState.value = GoogleSignInResultState(signInResult = result.data, isLoading = false)
                }
                is Resource.Loading ->{
                    _googleSignInResultState.value = GoogleSignInResultState(isLoading = true)
                }
                is Resource.Error ->{
                    _googleSignInResultState.value = GoogleSignInResultState(isLoading = false, signInResult = result.data, error = "Unable to sign in")
                }
            }
        }.launchIn(this)

    }
    */


    fun userLoginWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.userLoginWithEmailAndPassword(email, password).onEach{ result->
            when(result){
                is Resource.Success ->{
                    _emailSignInState.value = SignInState(
                        success = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Loading ->{
                    _emailSignInState.value = SignInState(
                        success = null,
                        isLoading = true,
                        error = null
                    )
                }
                is Resource.Error ->{
                    _emailSignInState.value = SignInState(
                        success = null,
                        isLoading = false,
                        error = result.message ?: UnableToSignIn
                    )
                }
            }
        }
    }
    /*
    fun userLoginWithEmailAndPasswordAndGetUser(email: String, password: String) = viewModelScope.launch {
        authRepository.userLoginWithEmailAndPasswordAndGetUser(email, password).collect{ result->
            when(result){
                is Resource.Success ->{
                   _emailSignInWithUserState.send(SignInResultState(signInResult = result.data))
                }
                is Resource.Loading ->{
                    _emailSignInWithUserState.send(SignInResultState(isLoading = true))
                }
                is Resource.Error ->{
                    _emailSignInWithUserState.send(SignInResultState(signInResult = result.data))
                }
            }
        }
    }
    */

    fun registerUserWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUserWithEmailAndPassword(email, password).onEach{ result->
            when(result){
                is Resource.Success ->{
                    _emailSignUpState.value = SignInState(
                        success = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Loading ->{
                    _emailSignUpState.value = SignInState(
                        success = null,
                        isLoading = true,
                        error = null
                    )
                }
                is Resource.Error ->{
                    _emailSignUpState.value = SignInState(
                        success = null,
                        isLoading = false,
                        error = result.message ?: UnableToSignIn
                    )
                }
            }
        }
    }

    /*
    fun registerUserWithEmailAndPasswordAndGetUser(email: String, password: String) = viewModelScope.launch {
        authRepository.registerUserWithEmailAndPasswordAndGetUser(email, password).collect{ result->
            when(result){
                is Resource.Success ->{
                    _emailSignUpWithUserState.send(SignUpResultState(signUpResult = result.data))
                }
                is Resource.Loading ->{
                    _emailSignUpWithUserState.send(SignUpResultState(isLoading = true))
                }
                is Resource.Error ->{
                    _emailSignUpWithUserState.send(SignUpResultState(signUpResult = result.data))
                }
            }
        }
    }
    */

    fun getUserSignedInState(userData: UserData?): Boolean = userData != null


}