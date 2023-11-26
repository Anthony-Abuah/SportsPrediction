package com.example.sportsprediction.feature_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsDao
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsDao
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsDao
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsDao
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_events.TeamEventsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_events.TeamEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventDao
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterDao
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserDao
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity

@Database(
    entities = [
        DateEventsEntity::class,
        EventsEntity::class,
        TeamEventsEntity::class,
        EventStatsEntity::class,
        TeamEventEntity::class,
        EventOddsEntity::class,
        UserEntity::class,
        TipsterEntity::class,
        TeamSuggestionsEntity::class
        ],
    version = 1
)
@TypeConverters(Converters::class, DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract val eventsDao: EventsDao
    abstract val dateEventsDao: DateEventsDao
    abstract val teamEventsDao: TeamEventsDao
    abstract val eventStatsDao: EventStatsDao
    abstract val teamNameEventsDao: TeamEventDao
    abstract val teamSuggestionsDao: TeamSuggestionsDao
    abstract val eventOddsDao: EventOddsDao
    abstract val userDao: UserDao
    abstract val tipsterDao: TipsterDao


}