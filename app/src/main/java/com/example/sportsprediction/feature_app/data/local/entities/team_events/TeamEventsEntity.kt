package com.example.sportsprediction.feature_app.data.local.entities.team_events

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.Team_Events_Entity
import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent
import java.util.*


@Entity(tableName = Team_Events_Entity)
data class TeamEventsEntity(
    @PrimaryKey val teamEventsEntityId: Int? = null,
    val teamId: Int?,
    val date: Date?,
    val teamName: String?,
    val teamEvents: List<TeamEvent>?
){
    fun toListOfTeamEvent(): List<TeamEvent>{
        return teamEvents ?: emptyList()
    }

    fun toListOfTeamNameEvents(): List<TeamEventEntity>{
        return teamEvents?.map { it.toTeamNameEventEntity(teamId ?: 0,date ?: Date(), teamName ?: emptyString) }
            ?: emptyList()
    }
}
