package com.example.sportsprediction.feature_app.data.local.entities.date_events

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Date_Event_Entity
import java.util.*

@Dao
interface DateEventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDateEvents(dateEvents: DateEventsEntity)

    @Query ("DELETE FROM $Date_Event_Entity")
    suspend fun deleteDateEvents()

    @Query ("DELETE FROM $Date_Event_Entity WHERE dateEventEntityId NOT IN (SELECT MIN(dateEventEntityId) FROM $Date_Event_Entity GROUP BY date)")
    suspend fun deleteDuplicateDateEvents()

    @Query ("SELECT * FROM $Date_Event_Entity")
    suspend fun getDateEvents(): List<DateEventsEntity>?

    @Query ("SELECT date FROM $Date_Event_Entity")
    suspend fun getDates(): List<Date>?

    @Query ("SELECT * FROM $Date_Event_Entity WHERE date LIKE :date")
    suspend fun getDateEvent(date: Date): DateEventsEntity?



}