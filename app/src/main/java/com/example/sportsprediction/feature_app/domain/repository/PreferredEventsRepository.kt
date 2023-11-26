package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import kotlinx.coroutines.flow.Flow
import java.util.*



interface PreferredEventRepository {

    fun allPreferredEvents(): Flow<ListOfEvents>

    suspend fun getTeamEvents(date: Date, teamId: Int): ListOfEvents?

    suspend fun getTeamEvents(date: Date, teamName: String): ListOfEvents?

    suspend fun getPreferredEvents(date: Date, tournamentId: Int): ListOfEvents?

    suspend fun getPreferredEvents(date: Date, tournamentName: String): ListOfEvents?

    suspend fun getPreferredEvents(date: Date): ListOfEvents?

    suspend fun getPreferredEvent(eventId: Int): Flow<EventsEntity>?

}
