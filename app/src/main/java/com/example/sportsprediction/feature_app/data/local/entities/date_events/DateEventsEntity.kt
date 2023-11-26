package com.example.sportsprediction.feature_app.data.local.entities.date_events

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.core.util.Constants.Date_Event_Entity
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents
import java.util.*

@Entity(tableName = Date_Event_Entity)
data class DateEventsEntity(
    @PrimaryKey val dateEventEntityId: Int? = null,
    val date: Date,
    val dateEvents: DateEvents?
){
    fun toPreferredEventsEntityList(): List<EventsEntity>{
        return dateEvents?.toPreferredEventsEntities(date) ?: emptyList()
    }
}
