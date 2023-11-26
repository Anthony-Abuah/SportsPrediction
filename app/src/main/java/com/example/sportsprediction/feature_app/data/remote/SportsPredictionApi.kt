package com.example.sportsprediction.feature_app.data.remote

import com.example.sportsprediction.core.util.Constants.Header_Host
import com.example.sportsprediction.core.util.Constants.Header_Key
import com.example.sportsprediction.feature_app.data.remote.dto.date_events.DateEventsDto
import com.example.sportsprediction.feature_app.data.remote.dto.event_odds.EventOddsDto
import com.example.sportsprediction.feature_app.data.remote.dto.event_stats.EventStatsDto
import com.example.sportsprediction.feature_app.data.remote.dto.h2h_events.HeadToHeadEventsDto
import com.example.sportsprediction.feature_app.data.remote.dto.team_events.TeamEventsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SportsPredictionApi {


    @GET("/v1/events/schedule/date")
    suspend fun getDateEvents(
        @Header(Header_Key) key: String,
        @Header(Header_Host) host: String,
        @Query("date") date: String,
        @Query("sport_id") sport_id: Int
    ): DateEventsDto


    @GET("/v1/teams/events")
    suspend fun getTeamEvents(
        @Header(Header_Key) key: String,
        @Header(Header_Host) host: String,
        @Query("team_id") team_id: Int,
        @Query("page") page: Int,
        @Query("course_events") course_events: String
    ): TeamEventsDto


    @GET("/v1/events/statistics")
    suspend fun getEventStats(
        @Header(Header_Key) key: String,
        @Header(Header_Host) host: String,
        @Query("event_id") event_id: Int,
    ): EventStatsDto


    @GET("/v1/events/h2h-events")
    suspend fun getHeadToHeadEvents(
        @Header(Header_Key) key: String,
        @Header(Header_Host) host: String,
        @Query("custom_event_id") custom_event_id: String,
    ): HeadToHeadEventsDto



    @GET("/v1/events/odds/all")
    suspend fun getEventOdds(
        @Header(Header_Key) key: String,
        @Header(Header_Host) host: String,
        @Query("event_id") eventId: Int,
        @Query("odds_format") oddsFormat: String,
        @Query("provider_id") providerId: Int,
    ): EventOddsDto





}