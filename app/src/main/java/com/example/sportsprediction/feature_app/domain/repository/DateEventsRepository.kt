package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface DateEventsRepository {

    fun getRemoteDateEvents(date: Date, sport_id: Int): Flow<Resource<List<EventsEntity>>>

    fun getMatchStartTimeEvents(listOfEvents: ListOfEvents, matchStartTime: Long): Flow<Resource<List<EventsEntity>>>
    fun getSearchedEvents(listOfEvents: ListOfEvents, searchValue: String): Flow<Resource<List<EventsEntity>>>
    fun getSortedEvents(listOfEvents: ListOfEvents, sortFilter: String): Flow<Resource<List<EventsEntity>>>
    fun getFilteredTournaments(listOfEvents: ListOfEvents, filteredTournaments: Map<String, String>): Flow<Resource<List<EventsEntity>>>


}

