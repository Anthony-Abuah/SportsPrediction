package com.example.sportsprediction.feature_app.di

import com.example.sportsprediction.core.util.Constants.BASE_URL
import com.example.sportsprediction.feature_app.data.local.Converters
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.data.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideTypeConverter(
        gsonParser: GsonParser
    ): Converters {
        return Converters(gsonParser)
    }



}