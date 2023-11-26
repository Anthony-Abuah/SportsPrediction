package com.example.sportsprediction.feature_app.data.local.entities.events

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.Constants.Events_Entity
import com.example.sportsprediction.feature_app.domain.model.general.RoundInfo
import com.example.sportsprediction.feature_app.domain.model.general.Scores
import com.example.sportsprediction.feature_app.domain.model.general.TeamInfo
import com.example.sportsprediction.feature_app.domain.model.general.Tournament
import java.util.*

@Entity(tableName = Events_Entity)
data class EventsEntity(
    @PrimaryKey val eventsId: Int? = null,
    val date: Date?,
    val awayScore: Int?,
    val awayTeamName: String?,
    val awayTeamId: Int?,
    val headToHeadId: String?,
    val homeScore: Int?,
    val homeTeamName: String?,
    val homeTeamId: Int?,
    val eventId: Int?,
    val previousLegEventId: Int?,
    val startTimestamp: Int?,
    val status: String?,
    val tournamentName: String?,
    val country: String?,
    val tournamentId: Int?,
    val roundName: String?,
    val roundInfo: RoundInfo?,
    val tournament: Tournament?,
    val homeTeamInfo: TeamInfo?,
    val awayTeamInfo: TeamInfo?,
    val homeScores: Scores?,
    val awayScores: Scores?
)

