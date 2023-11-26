package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsDao
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.domain.repository.PreferredEventRepository
import kotlinx.coroutines.flow.Flow
import java.util.*

class PreferredEventsRepositoryImpl(
    private val eventsDao: EventsDao
) : PreferredEventRepository {
    override fun allPreferredEvents(): Flow<ListOfEvents> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeamEvents(date: Date, teamId: Int): ListOfEvents? {
        return eventsDao.getTeamEvent(date, teamId)
    }

    override suspend fun getTeamEvents(date: Date, teamName: String): ListOfEvents? {
        return eventsDao.getTeamEvent(date, teamName)
    }

    override suspend fun getPreferredEvents(date: Date, tournamentId: Int): ListOfEvents? {
        return eventsDao.getThisDateEventsOrderedByAscendingStartTime(date, tournamentId)
    }

    override suspend fun getPreferredEvents(date: Date, tournamentName: String): ListOfEvents? {
        return eventsDao.getThisDateEventsOrderedByAscendingStartTime(date, tournamentName)
    }

    override suspend fun getPreferredEvents(date: Date): ListOfEvents? {
        return  eventsDao.getThisDateEventsOrderedByAscendingStartTime(date)
    }

    override suspend fun getPreferredEvent(eventId: Int): Flow<EventsEntity>? {
        return eventsDao.getEvent(eventId)
    }

}