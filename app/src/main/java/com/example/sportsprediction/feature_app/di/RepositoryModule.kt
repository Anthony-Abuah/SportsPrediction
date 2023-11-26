package com.example.sportsprediction.feature_app.di


import com.example.sportsprediction.feature_app.data.local.AppDatabase
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.data.repository.*
import com.example.sportsprediction.feature_app.domain.repository.*
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDateEventsRepository(
        db: AppDatabase,
        api: SportsPredictionApi
    ): DateEventsRepository {
        return DateEventsRepositoryImpl(api, db.dateEventsDao, db.eventsDao)
    }


    @Provides
    @Singleton
    fun providePreferredEventsRepository(
        db: AppDatabase
    ): PreferredEventRepository {
        return PreferredEventsRepositoryImpl(db.eventsDao)
    }

    @Provides
    @Singleton
    fun provideTeamEventStatsRepository(
        db: AppDatabase,
        api: SportsPredictionApi
    ): TeamEventStatsRepository {
        return TeamEventStatsRepositoryImpl(api, db.teamNameEventsDao, db.eventStatsDao)
    }


    @Provides
    @Singleton
    fun provideTeamNameEventsRepository(
        db: AppDatabase,
        api: SportsPredictionApi
    ): TeamEventRepository {
        return TeamEventRepositoryImpl(
            api,
            db.teamEventsDao,
            db.teamNameEventsDao)
    }

    @Provides
    @Singleton
    fun provideEventsOddsRepository(
        db: AppDatabase,
        api: SportsPredictionApi
    ): EventOddsRepository {
        return EventOddsRepositoryImpl(
            api,
            db.eventOddsDao,
            db.eventsDao
        )
    }


    @Provides
    @Singleton
    fun provideTeamSuggestionsRepository(
        db: AppDatabase
    ): TeamSuggestionsRepository {
        return TeamSuggestionsRepositoryImpl(
            db.teamSuggestionsDao,
            db.eventStatsDao,
            db.eventOddsDao,
            db.teamNameEventsDao
        )
    }


    @Provides
    @Singleton
    fun provideUserProfileRepository(
        db: AppDatabase,
    ): UserProfileRepository {
        return UserProfileRepositoryImpl(db.userDao)
    }


    @Provides
    @Singleton
    fun provideTipsterRepository(
        db: AppDatabase,
    ): TipsterRepository {
        return TipsterRepositoryImpl(db.tipsterDao, db.userDao)
    }


    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLoadSuggestionsRepository(
        db: AppDatabase
    ): LoadSuggestionsRepository {
        return LoadSuggestionsRepositoryImpl(
            db.eventStatsDao
        )
    }


}



