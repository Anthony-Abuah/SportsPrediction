package com.example.sportsprediction.feature_app.domain.repository

import android.content.Intent
import android.content.IntentSender
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.SignInResult
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import java.util.*

interface AuthRepository {

    fun userLoginWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    //fun userLoginWithEmailAndPasswordAndGetUser(email: String, password: String): Flow<Resource<SignInResult>>

    fun registerUserWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    //fun registerUserWithEmailAndPasswordAndGetUser(email: String, password: String): Flow<Resource<SignInResult>>

    fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>>

    //fun signInWithGoogleAndGetUser(credential: AuthCredential): Flow<Resource<SignInResult>>

    fun getCurrentlySignedInUser(): UserData?

    suspend fun signOut()
}

