package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.domain.repository.AuthRepository
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.SignInResult
import com.example.sportsprediction.feature_app.ui.presentation.sign_in.UserData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun userLoginWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> = flow{
        emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    /*
    override fun userLoginWithEmailAndPasswordAndGetUser(email: String, password: String): Flow<Resource<SignInResult>> = flow {
        emit(Resource.Loading())
        try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        email = email,
                        phoneNumber = phoneNumber,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            } else {
                SignInResult(
                    data = null,
                    errorMessage = e.message
                )
            }
        }
    }
    */

    override fun registerUserWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(result))

    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    /*
    override fun registerUserWithEmailAndPasswordAndGetUser(email: String, password: String): Flow<Resource<SignInResult>> = flow {
        emit(Resource.Loading())
        try {
            val user = firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        email = email,
                        phoneNumber = phoneNumber,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            } else {
                SignInResult(
                    data = null,
                    errorMessage = e.message
                )
            }
        }
    }
    */

    override fun signInWithGoogle(credential: AuthCredential): Flow<Resource<AuthResult>> = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        val result = firebaseAuth.signInWithCredential(credential).await()
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error(it.message.toString()))
    }

    /*
    override fun signInWithGoogleAndGetUser(credential: AuthCredential): Flow<Resource<SignInResult>> = flow {
        emit(Resource.Loading())
        try {
            val user = firebaseAuth.signInWithCredential(credential).await().user
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        email = email,
                        username = displayName,
                        phoneNumber = phoneNumber,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            } else {
                SignInResult(
                    data = null,
                    errorMessage = e.message
                )
            }
        }
    }
*/

    override fun getCurrentlySignedInUser() : UserData? = firebaseAuth.currentUser?.run{
        UserData(
            userId = uid,
            email = email,
            phoneNumber = phoneNumber,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }


    override suspend fun signOut(){
        try {
            firebaseAuth.signOut()
        } catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }



}