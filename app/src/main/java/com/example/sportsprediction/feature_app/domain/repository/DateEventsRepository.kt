package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*

interface DateEventsRepository {

    fun getRemoteDateEvents(date: Date, sport_id: Int): Flow<Resource<List<EventsEntity>>>
    suspend fun getRemoteGroupedDateEvents(date: Date, sport_id: Int): Flow<Resource<Map<String, List<EventsEntity>>>>

    fun getMatchStartTimeEvents(listOfEvents: ListOfEvents, matchStartTime: Long): Flow<Resource<List<EventsEntity>>>
    fun getMatchStartTimeEvents(eventsList: Map<String, List<EventsEntity>>, matchStartTime: Long): Flow<Resource<Map<String, List<EventsEntity>>>>
    fun getSearchedEvents(listOfEvents: ListOfEvents, searchValue: String): Flow<Resource<List<EventsEntity>>>
    fun getSearchedEvents(eventsList: Map<String, List<EventsEntity>>, searchValue: String): Flow<Resource<Map<String, List<EventsEntity>>>>
    fun getSortedEvents(listOfEvents: ListOfEvents, sortFilter: String): Flow<Resource<List<EventsEntity>>>
    fun getSortedEvents(eventsList: Map<String, List<EventsEntity>>, sortFilter: String): Flow<Resource<Map<String, List<EventsEntity>>>>
    fun getFilteredTournaments(listOfEvents: ListOfEvents, filteredTournaments: Map<String, String>): Flow<Resource<List<EventsEntity>>>
    fun getFilteredTournaments(eventsList: Map<String, List<EventsEntity>>, filteredTournaments: Map<String, String>): Flow<Resource<Map<String, List<EventsEntity>>>>


}

