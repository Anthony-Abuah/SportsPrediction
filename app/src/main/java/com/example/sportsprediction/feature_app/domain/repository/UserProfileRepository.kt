package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface UserProfileRepository {

    fun insertUser(user: UserEntity): Flow<Resource<String>>

    fun loginUser(username: String, password: String): Flow<Resource<String>>

    suspend fun getUser(username: String, password: String): UserEntity?



}

