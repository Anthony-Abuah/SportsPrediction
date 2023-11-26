package com.example.sportsprediction.feature_app.domain.model.general

import java.util.*

data class LoadStatsParameters(
    val mainTeamName: String,
    val mainTeamId: Int,
    val headToHeadId: String,
    val eventId: Int,
    val date: Date,
    val numberOfPastEvents: Int,
    val numberOfHeadToHeadEvents: Int
)