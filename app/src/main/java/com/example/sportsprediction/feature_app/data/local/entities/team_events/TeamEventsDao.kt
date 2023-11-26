package com.example.sportsprediction.feature_app.data.local.entities.team_events

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Team_Events_Entity
import java.util.*

@Dao
interface TeamEventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamEvents(teamEvents: TeamEventsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamEvents(teamEvents: List<TeamEventsEntity>)

    @Query ("DELETE FROM $Team_Events_Entity")
    suspend fun deleteAllTeamEvents()

    @Query ("SELECT * FROM $Team_Events_Entity")
    suspend fun getTeamEvent(): List<TeamEventsEntity>

    @Query ("DELETE FROM $Team_Events_Entity WHERE teamId LIKE :teamId")
    suspend fun deleteThisParticularTeamEvents(teamId: Int)

    @Query ("DELETE FROM $Team_Events_Entity WHERE teamName LIKE :teamName")
    suspend fun deleteThisParticularTeamEvents(teamName: String)

    @Query ("SELECT * FROM $Team_Events_Entity WHERE teamId LIKE :teamId")
    suspend fun getTeamEvent(teamId: Int): TeamEventsEntity?

    @Query ("SELECT * FROM $Team_Events_Entity WHERE teamId LIKE :teamId AND date LIKE :date")
    suspend fun getTeamEventUsingTeamIdAndDate(teamId: Int, date: Date): List<TeamEventsEntity>?


}