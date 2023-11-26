package com.example.sportsprediction.feature_app.data.local.entities.event_odds

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Event_Odds_Entity
import java.util.*

@Dao
interface EventOddsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDateEvent(eventOdds: EventOddsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDateEvents(eventOdds: List<EventOddsEntity>)


    @Query ("DELETE FROM $Event_Odds_Entity")
    suspend fun deleteAllEventOdds()


    @Query ("DELETE FROM $Event_Odds_Entity WHERE eventId LIKE :eventId")
    suspend fun deleteThisEventOdds(eventId: Int)


    @Query ("SELECT * FROM $Event_Odds_Entity")
    suspend fun getAllEventOdds(): List<EventOddsEntity>


    @Query ("SELECT * FROM $Event_Odds_Entity WHERE homeTeamId LIKE :homeTeamId AND awayTeamId LIKE :awayTeamId AND  eventId LIKE :eventId")
    suspend fun getTeamEventOdds(homeTeamId: Int, awayTeamId: Int, eventId: Int): EventOddsEntity?

    @Query ("SELECT eventId FROM $Event_Odds_Entity WHERE homeTeamId LIKE :homeTeamId AND awayTeamId LIKE :awayTeamId")
    suspend fun getThisMatchEventId(homeTeamId: Int, awayTeamId: Int): List<Int>?


    @Query ("SELECT * FROM $Event_Odds_Entity WHERE homeTeamId LIKE :homeTeamId AND awayTeamId LIKE :awayTeamId AND  eventId LIKE :eventId")
    suspend fun getListOfTeamEventOdds(homeTeamId: Int, awayTeamId: Int, eventId: Int): List<EventOddsEntity>?



}