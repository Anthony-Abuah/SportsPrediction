package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TipsterRepository {

    fun registerTipster(tipster: TipsterEntity): Flow<Resource<String>>



}

