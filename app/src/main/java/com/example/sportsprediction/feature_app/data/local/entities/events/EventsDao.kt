package com.example.sportsprediction.feature_app.data.local.entities.events

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Events_Entity
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDateEvents(dateEvents: DateEventsEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventsEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventsEntity>)

    @Query ("DELETE FROM $Events_Entity")
    suspend fun deleteEvents()

    @Query ("DELETE FROM $Events_Entity WHERE date LIKE :date")
    suspend fun deleteEvents(date: Date)

    @Query ("DELETE FROM $Events_Entity WHERE eventsId NOT IN (SELECT MIN(eventsId) FROM $Events_Entity GROUP BY date, homeTeamId, awayTeamId)")
    suspend fun deleteDuplicateEvents()

    @Query ("SELECT eventId FROM $Events_Entity WHERE homeTeamId LIKE :homeTeamId AND awayTeamId LIKE :awayTeamId AND eventId LIKE :eventId")
    suspend fun getEventIdForOdds(homeTeamId: Int, awayTeamId: Int, eventId: Int): Int?

    @Query ("SELECT * FROM $Events_Entity WHERE date LIKE :date")
    suspend fun getThisDateEventsOrderedByAscendingStartTime(date: Date): List<EventsEntity>?

    @Query ("SELECT * FROM $Events_Entity WHERE eventId LIKE :eventId")
    fun getEvent(eventId: Int): Flow<EventsEntity>?

    @Query ("SELECT eventId FROM $Events_Entity WHERE date LIKE :date")
    suspend fun getAllEventIdsAccordingToDate(date: Date): List<Int>?

    @Query ("SELECT eventId FROM $Events_Entity")
    suspend fun getAllEventIds(): List<Int>?

    @Query ("SELECT date FROM $Events_Entity")
    suspend fun getDates(): List<Date>?

    @Query ("SELECT * FROM $Events_Entity WHERE date LIKE :date AND tournamentName LIKE :tournamentName")
    suspend fun getThisDateEventsOrderedByAscendingStartTime(date: Date, tournamentName: String): List<EventsEntity>?

    @Query ("SELECT * FROM $Events_Entity WHERE date LIKE :date AND tournamentId LIKE :tournamentId")
    suspend fun getThisDateEventsOrderedByAscendingStartTime(date: Date, tournamentId: Int): List<EventsEntity>?

    @Query ("SELECT * FROM $Events_Entity WHERE date LIKE :date AND homeTeamName LIKE :teamName OR awayTeamName LIKE :teamName")
    suspend fun getTeamEvent(date: Date, teamName: String): List<EventsEntity>?


    @Query ("SELECT * FROM $Events_Entity WHERE date LIKE :date AND homeTeamId LIKE :teamId OR awayTeamId LIKE :teamId")
    suspend fun getTeamEvent(date: Date, teamId: Int): List<EventsEntity>?



}