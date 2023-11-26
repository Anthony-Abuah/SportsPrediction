package com.example.sportsprediction.feature_app.data.local.entities.event_odds

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.Constants.Event_Odds_Entity
import com.example.sportsprediction.feature_app.domain.model.event_odds.Choice
import java.util.*

@Entity(tableName = Event_Odds_Entity)
data class EventOddsEntity(
    @PrimaryKey val eventOddsEntityId: Int? = null,
    val date: Date?,
    val eventId: Int?,
    val homeTeamId: Int?,
    val awayTeamId: Int?,
    val marketName: String?,
    val choices: List<Choice>?,
    val choiceGroup: String?,
    val isLive: Boolean?,
    val isSuspended: Boolean?,
    val sourceId: Int?
)
