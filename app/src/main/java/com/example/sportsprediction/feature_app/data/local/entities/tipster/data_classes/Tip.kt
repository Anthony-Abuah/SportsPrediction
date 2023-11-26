package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import java.util.*


data class Tip(
    val date: Date,
    val suggestion: Suggestion?,
    val eventsEntity: EventsEntity?,
    val tip: String,
    val odds: String?,
    val isTipFree: Boolean?,
    val eventStatsEntity: EventStatsEntity?,
    val tipIsWinning: Boolean?,

)
