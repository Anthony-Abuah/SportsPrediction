package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEventStats
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.domain.model.general.LoadStatsParameters
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TeamEventStatsRepository {

    fun getAllTeamEventStats(mainTeamId: Int, headToHeadId: String, thisEventId: Int, date: Date, numberOfPastEvents: Int, numberOfHeadToHeadEvents: Int): Flow<Resource<ListOfEventStats>>

    fun getTeamEventStats(mainTeamId: Int): Flow<Resource<List<EventStatsEntity>>>

    fun getTeamEventStats(mainTeamId: Int, eventId: Int): Flow<Resource<String>>

    fun getEventStats(eventId: Int): Flow<Resource<EventStatsEntity?>>

    suspend fun getListOfTeamsPastEventsStats(listOfEvents: ListOfEvents, date: Date, numberOfPastEvents: Int, numberOfPastHeadToHeadEvents: Int) :Flow<Resource<String>>

    suspend fun loadSelectedTeamEventsStats(parameters: List<LoadStatsParameters>) :Flow<Resource<String>>



}
