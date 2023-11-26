package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEventOdds
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface EventOddsRepository {

    fun getRemoteEventOdds(homeTeamId: Int?, awayTeamId: Int?, date: Date?, eventId: Int?): Flow<Resource<ListOfEventOdds>>

    fun getRemoteOdds(homeTeamId: Int?, homeTeamName: String?, awayTeamId: Int?,  awayTeamName: String?, date: Date?, eventId: Int?): Flow<Resource<String>>

    suspend fun getLocalEventOdds(homeTeamId: Int?, awayTeamId: Int?, category: String, choiceName1: String, choiceName2: String, group: String, eventId: Int?): Map<String, Double>

}


