package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.DoesNotExist
import com.example.sportsprediction.core.util.Constants.HttpExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.IO_ExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.host
import com.example.sportsprediction.core.util.Constants.key
import com.example.sportsprediction.core.util.Constants.oddsFormat
import com.example.sportsprediction.core.util.Constants.providerId
import com.example.sportsprediction.core.util.Constants.shortDateFormat
import com.example.sportsprediction.core.util.Functions.getTeamOddsMultiplierValue
import com.example.sportsprediction.core.util.ListOfEventOdds
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsDao
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsDao
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.domain.repository.EventOddsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class EventOddsRepositoryImpl(
    private val sportsPredictionApi: SportsPredictionApi,
    private val eventOddsDao: EventOddsDao,
    private val eventsDao: EventsDao
): EventOddsRepository {
    override fun getRemoteEventOdds(
        homeTeamId: Int?,
        awayTeamId: Int?,
        date: Date?,
        eventId: Int?
    ): Flow<Resource<ListOfEventOdds>> = flow{
        emit(Resource.Loading())

        fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val shortDateFormatter = DateTimeFormatter.ofPattern(shortDateFormat)

        val dateToLocalDate = date!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()

        val eventOddsEntity = eventOddsDao.getListOfTeamEventOdds(homeTeamId!!, awayTeamId!!, eventId!!)
        emit(Resource.Loading(eventOddsEntity))

        try {

            val preferredEventId = eventsDao.getEventIdForOdds(homeTeamId, awayTeamId, eventId) ?: DoesNotExist
            val oddsEventIds = eventOddsDao.getThisMatchEventId(homeTeamId, awayTeamId) ?: emptyList()
            val allEventIdsAreNotEqualToThisPreferredEventId = !oddsEventIds.all { oddsEventId-> oddsEventId == preferredEventId}


            if (preferredEventId != DoesNotExist && (oddsEventIds.isNotEmpty() && allEventIdsAreNotEqualToThisPreferredEventId) || oddsEventIds.isEmpty()){
                eventOddsDao.deleteThisEventOdds(eventId)

                val remoteEventOdds = sportsPredictionApi.getEventOdds(key, host, eventId, oddsFormat, providerId)
                val listOfEventOdds = remoteEventOdds.toListOfEventOddsEntity(newDate, eventId, homeTeamId, awayTeamId)

                eventOddsDao.insertDateEvents(listOfEventOdds)

            }

        }catch (e: HttpException){
            emit(Resource.Error(
                message = HttpExceptionErrorMessage,
                data = eventOddsEntity)
            )
        }catch (e: IOException){
            emit(Resource.Error(
                message = IO_ExceptionErrorMessage,
                data = eventOddsEntity)
            )
        }

        val newEventOddsEntity = eventOddsDao.getListOfTeamEventOdds(homeTeamId, awayTeamId, eventId) ?: emptyList()
        emit(Resource.Success(newEventOddsEntity))
    }

    override fun getRemoteOdds(
        homeTeamId: Int?,
        homeTeamName: String?,
        awayTeamId: Int?,
        awayTeamName: String?,
        date: Date?,
        eventId: Int?
    ): Flow<Resource<String>> = flow{
        val message = "Checking if $homeTeamName vs $awayTeamName odds are loaded in database..."
        emit(Resource.Loading(message))

        fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val shortDateFormatter = DateTimeFormatter.ofPattern(shortDateFormat)

        val dateToLocalDate = date!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()

        try {

            val preferredEventId = eventsDao.getEventIdForOdds(homeTeamId!!, awayTeamId!!, eventId!!) ?: DoesNotExist
            val oddsEventIds = eventOddsDao.getThisMatchEventId(homeTeamId, awayTeamId) ?: emptyList()
            val allEventIdsAreNotEqualToThisPreferredEventId = !oddsEventIds.all { oddsEventId-> oddsEventId == preferredEventId}



            if (preferredEventId != DoesNotExist && (oddsEventIds.isNotEmpty() && allEventIdsAreNotEqualToThisPreferredEventId) || oddsEventIds.isEmpty()){
                emit(Resource.Loading("Loading $homeTeamName vs $awayTeamName odds into database..."))
                eventOddsDao.deleteThisEventOdds(eventId)

                val remoteEventOdds = sportsPredictionApi.getEventOdds(key, host, eventId, oddsFormat, providerId)
                val listOfEventOdds = remoteEventOdds.toListOfEventOddsEntity(newDate, eventId, homeTeamId, awayTeamId)

                eventOddsDao.insertDateEvents(listOfEventOdds)


                val eventOddsEntity = eventOddsDao.getListOfTeamEventOdds(homeTeamId, awayTeamId, eventId)
                if (!eventOddsEntity.isNullOrEmpty()){
                    emit(Resource.Loading("Couldn't load $homeTeamName vs $awayTeamName odds into database..."))
                    eventOddsDao.deleteThisEventOdds(eventId)

                    val newRemoteEventOdds = sportsPredictionApi.getEventOdds(key, host, eventId, oddsFormat, providerId)
                    val newListOfEventOdds = newRemoteEventOdds.toListOfEventOddsEntity(newDate, eventId, homeTeamId, awayTeamId)

                    eventOddsDao.insertDateEvents(newListOfEventOdds)
                }

            }
            else emit(Resource.Loading("$homeTeamName vs $awayTeamName odds are already loaded in database..."))


        }catch (e: NullPointerException){
            emit(Resource.Error(
                message = "Invalid Event Id",
                data = "")
            )
        }catch (e: HttpException){
            emit(Resource.Error(
                message = HttpExceptionErrorMessage,
                data = "")
            )
        }catch (e: IOException){
            emit(Resource.Error(
                message = IO_ExceptionErrorMessage,
                data = "eventOddsEntity")
            )
        }

    }


    override suspend fun getLocalEventOdds(
        homeTeamId: Int?,
        awayTeamId: Int?,
        category: String,
        choiceName1: String,
        choiceName2: String,
        group: String,
        eventId: Int?
    ): Map<String, Double> {

        val oddsList = eventOddsDao.getListOfTeamEventOdds(homeTeamId!!, awayTeamId!!, eventId!!) ?: emptyList()
        var odds1 = 0.0
        var odds2 = 0.0
        oddsList.forEach { eventOddsEntity ->
            if (eventOddsEntity.marketName?.lowercase(Locale.ROOT) == category.lowercase(Locale.ROOT) ) {
                if (eventOddsEntity.choiceGroup.isNullOrEmpty()) {
                    eventOddsEntity.choices?.forEach { choice ->
                        if (choice.name?.lowercase(Locale.ROOT) == choiceName1.lowercase(Locale.ROOT)) {
                            odds1 = choice.fractionalValue ?: 0.0
                        }
                        if (choice.name?.lowercase(Locale.ROOT) == choiceName2.lowercase(Locale.ROOT)) {
                            odds2 = choice.fractionalValue ?: 0.0
                        }
                    }
                }
                else if(eventOddsEntity.choiceGroup.lowercase(Locale.ROOT) == group.lowercase(Locale.ROOT)) {
                    eventOddsEntity.choices?.forEach { choice ->
                        if (choice.name?.lowercase(Locale.ROOT) == choiceName1.lowercase(Locale.ROOT)) {
                            odds1 = choice.fractionalValue ?: 0.0
                        }
                        if (choice.name?.lowercase(Locale.ROOT) == choiceName2.lowercase(Locale.ROOT)) {
                            odds2 = choice.fractionalValue ?: 0.0
                        }
                    }
                }

            }
        }

        val odds1Multiplier = getTeamOddsMultiplierValue(odds1)
        val odds2Multiplier = getTeamOddsMultiplierValue(odds2)

        return mapOf(choiceName1 to odds1Multiplier, choiceName2 to odds2Multiplier)

    }

}