package com.example.sportsprediction.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.sportsprediction.core.util.Constants.DateEventsArrangementOrder
import com.example.sportsprediction.core.util.Constants.Descending
import com.example.sportsprediction.core.util.Constants.MarketCategory
import com.example.sportsprediction.core.util.Constants.NumberOfH2HEvents
import com.example.sportsprediction.core.util.Constants.NumberOfH2HEventsForEventStats
import com.example.sportsprediction.core.util.Constants.NumberOfTeamEvents
import com.example.sportsprediction.core.util.Constants.NumberOfTeamEventsForEventStats
import com.example.sportsprediction.core.util.Constants.PercentageFilterValue
import com.example.sportsprediction.core.util.Constants.PercentageValue
import com.example.sportsprediction.core.util.Constants.SuggestionsArrangementOrder
import com.example.sportsprediction.core.util.Constants.SuggestionsGrouping
import com.example.sportsprediction.core.util.Constants.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(UserPreferences)
        val SUGGESTIONS_ARRANGEMENT_ORDER = stringPreferencesKey(SuggestionsArrangementOrder)
        val SUGGESTIONS_GROUPING = stringPreferencesKey(SuggestionsGrouping)
        val PERCENTAGE_FILTER_VALUE = doublePreferencesKey(PercentageFilterValue)
        val NUMBER_OF_TEAM_EVENTS_FOR_STATS = intPreferencesKey(NumberOfTeamEventsForEventStats)
        val NUMBER_OF_H2H_EVENTS_FOR_STATS = intPreferencesKey(NumberOfH2HEventsForEventStats)
        val DATE_EVENTS_ARRANGEMENT_ORDER = stringPreferencesKey(DateEventsArrangementOrder)
    }

    //get the Suggestion Arrangement Order
    val getSuggestionsArrangementOrder: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[SUGGESTIONS_ARRANGEMENT_ORDER] ?: Descending }

    //save the Suggestion Arrangement Order into datastore
    suspend fun saveSuggestionArrangementOrder(order: String) {
        context.dataStore.edit { preferences -> preferences[SUGGESTIONS_ARRANGEMENT_ORDER] = order }
    }


    //get the Suggestion Grouping
    val getSuggestionsGrouping: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[SUGGESTIONS_GROUPING] ?: MarketCategory }

    //save the Suggestion Arrangement Order into datastore
    suspend fun saveSuggestionGrouping(group: String) {
        context.dataStore.edit { preferences -> preferences[SUGGESTIONS_GROUPING] = group }
    }


    //get the TeamsEnum Filter MarketTypeEnum
    val getPercentageFilterValue: Flow<Double?> = context.dataStore.data
        .map { preferences -> preferences[PERCENTAGE_FILTER_VALUE] ?: PercentageValue }

    //save the TeamsEnum Filter MarketTypeEnum into datastore
    suspend fun savePercentageFilterValue(value: Double) {
        context.dataStore.edit { preferences -> preferences[PERCENTAGE_FILTER_VALUE] = value }
    }

    //get the number of Team Events for Stats
    val getNumberOfTeamEventsForStats: Flow<Int?> = context.dataStore.data
        .map { preferences -> preferences[NUMBER_OF_TEAM_EVENTS_FOR_STATS] ?: NumberOfTeamEvents }

    //save the number of Team Events for Stats into datastore
    suspend fun saveNumberOfTeamEventsForStats(number: Int) {
        context.dataStore.edit { preferences -> preferences[NUMBER_OF_TEAM_EVENTS_FOR_STATS] = number }
    }



    //get the number of H2H Events for Stats
    val getNumberOfH2HEventsForStats: Flow<Int?> = context.dataStore.data
        .map { preferences -> preferences[NUMBER_OF_H2H_EVENTS_FOR_STATS] ?: NumberOfH2HEvents }

    //save the number of H2H Events for Stats into datastore
    suspend fun saveNumberOfH2HEventsForStats(number: Int) {
        context.dataStore.edit { preferences -> preferences[NUMBER_OF_H2H_EVENTS_FOR_STATS] = number }
    }



    //get the Date Events Arrangement Order
    val getDateEventsArrangementOrder: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[DATE_EVENTS_ARRANGEMENT_ORDER] ?: Descending }

    //save the Date Events Arrangement Order into datastore
    suspend fun saveDateEventsArrangementOrder(order: String) {
        context.dataStore.edit { preferences -> preferences[DATE_EVENTS_ARRANGEMENT_ORDER] = order }
    }







}