package com.example.sportsprediction.feature_app.data.local.entities.team_event

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Team_Event_Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfTeamEvent(teamEvents: List<TeamEventEntity>)

    @Query ("DELETE FROM $Team_Event_Entity")
    suspend fun deleteAllTeamNameEvent()

    @Query ("SELECT * FROM $Team_Event_Entity")
    suspend fun getListOfTeamEventsForThisParticularTeam(): List<TeamEventEntity>

    @Query ("DELETE FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun deleteThisParticularListOfTeamEvent(mainTeamId: Int)


    @Query ("DELETE FROM $Team_Event_Entity WHERE mainTeamName LIKE :mainTeamName")
    suspend fun deleteThisParticularListOfTeamEvent(mainTeamName: String)

    @Query ("SELECT * FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId ORDER BY startTimeStamp DESC")
    suspend fun getListOfTeamEventsForThisParticularTeam(mainTeamId: Int): List<TeamEventEntity>?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE mainTeamName LIKE :mainTeamName")
    suspend fun getListOfTeamEventsForThisParticularTeam(mainTeamName: String): List<TeamEventEntity>?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId ORDER BY startTimeStamp DESC")
    suspend fun getTeamFormEvents(mainTeamId: Int): List<TeamEventEntity>?


    @Query ("SELECT * FROM $Team_Event_Entity WHERE eventId LIKE :eventId")
    suspend fun getTeamEvent(eventId: Int): TeamEventEntity?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE eventId LIKE :eventId AND mainTeamId LIKE :mainTeamId")
    suspend fun getTeamEvent(eventId: Int, mainTeamId: Int): TeamEventEntity?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE eventId LIKE :eventId AND mainTeamId LIKE :mainTeamId")
    suspend fun getTeamNameEventEntity(eventId: Int, mainTeamId: Int): List<TeamEventEntity>?

    @Query ("SELECT eventId FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId")
    suspend fun getEventIds(mainTeamId: Int): List<Int>?

    @Query ("SELECT eventId FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId ORDER BY startTimestamp DESC")
    suspend fun getThisTeamEventIdsInDescendingOrder(mainTeamId: Int): List<Int>?

    @Query ("SELECT eventId FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId AND startTimestamp < :startTimestamp ORDER BY startTimestamp DESC")
    suspend fun getThisTeamEventIdsInDescendingOrder(mainTeamId: Int, startTimestamp: Int): List<Int>?

    @Query ("SELECT eventId FROM $Team_Event_Entity")
    suspend fun getAllEventIds(): List<Int>?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId AND headToHeadId LIKE :headToHeadId")
    fun getHeadToHeadEvents(mainTeamId: Int, headToHeadId: String): Flow<List<TeamEventEntity>>?

    @Query ("SELECT * FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId AND headToHeadId LIKE :headToHeadId ORDER BY startTimestamp DESC")
    suspend fun getHeadToHeadEventsList(mainTeamId: Int, headToHeadId: String): List<TeamEventEntity>?

    @Query ("SELECT eventId FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId AND headToHeadId LIKE :headToHeadId ORDER BY startTimestamp DESC")
    suspend fun getThisTeamHeadToHeadEventIdsInDescendingOrder(mainTeamId: Int, headToHeadId: String): List<Int>?

    @Query ("SELECT eventId FROM $Team_Event_Entity WHERE mainTeamId LIKE :mainTeamId AND headToHeadId LIKE :headToHeadId AND startTimestamp < :startTimestamp ORDER BY startTimestamp DESC")
    suspend fun getThisTeamHeadToHeadEventIdsInDescendingOrder(mainTeamId: Int, headToHeadId: String, startTimestamp: Int): List<Int>?



}