package com.example.sportsprediction.feature_app.di

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.BASE_URL
import com.example.sportsprediction.core.util.Constants.web_client_Id
import com.example.sportsprediction.feature_app.SportsPredictionApp
import com.example.sportsprediction.feature_app.data.local.Converters
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.data.util.GsonParser
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSureBetPredictionApi(): SportsPredictionApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SportsPredictionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBeginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions
                    .builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId(web_client_Id)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }




    @Provides
    @Singleton
    fun provideTypeConverter(
        gsonParser: GsonParser
    ): Converters {
        return Converters(gsonParser)
    }


    @Provides
    @Singleton
    fun provideFirebaseAuth() =  FirebaseAuth.getInstance()



    @Provides
    @Singleton
    fun provideOneTapClient(application: SportsPredictionApp): SignInClient = Identity.getSignInClient(application.applicationContext)
    // I need to change the return type of application to Application if there is an error



}