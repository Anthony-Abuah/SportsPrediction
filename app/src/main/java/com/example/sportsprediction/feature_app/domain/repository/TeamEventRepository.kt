package com.example.sportsprediction.feature_app.domain.repository

import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.ListOfTeamEvent
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TeamEventRepository {
    fun getRemoteTeamEvents(teamId: Int, teamName: String, date: Date, headToHeadEventId: String): Flow<Resource<ListOfTeamEvent>>

    suspend fun getHeadToHeadTeamEvents(teamId: Int, headToHeadEventId: String): Flow<ListOfTeamEvent>?

    suspend fun getTeamNameEventsList(teamId: Int): ListOfTeamEvent?

    suspend fun getTeamNameFormPercentage(teamId: Int, headToHeadEventId: String): Double

    suspend fun getTeamFormPercentage(teamId: Int): Flow<Resource<Double>>

    suspend fun getOpponentFormMultiplierValue(teamId: Int, headToHeadEventId: String): Double

    suspend fun getTeamNameEventEntity(eventId: Int, teamId: Int): Flow<Resource<ListOfTeamEvent>>

    suspend fun getTeamsPastEvents(listOfEvents: ListOfEvents, date: Date) :Flow<Resource<List<TeamEventEntity>>>

    suspend fun showTeamPastEvents(listOfEvents: ListOfEvents, date: Date) :Flow<Resource<List<TeamEventEntity>>>

}
