package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.HttpExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.IO_ExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.host
import com.example.sportsprediction.core.util.Constants.key
import com.example.sportsprediction.core.util.Constants.shortDateFormat
import com.example.sportsprediction.core.util.Functions.findDuplicates
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsDao
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsDao
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.domain.repository.DateEventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class DateEventsRepositoryImpl(
    private val sportsPredictionApi: SportsPredictionApi,
    private val dateEventsDao: DateEventsDao,
    private val eventsDao: EventsDao
): DateEventsRepository {
    override fun getRemoteDateEvents(date: Date, sport_id: Int): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        fun LocalDate.toDate(): Date = Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val shortDateFormatter = DateTimeFormatter.ofPattern(shortDateFormat)


        /*
        * We want to get a unique date that that we can will uniquely characterize the preferred events
        * To do that we have to
        * First, we will convert the date the user chooses/picks to localDate
        * then we will obviously get the string format of that date which we will feed to our API
        * then we will convert that string format back to localDate and then to Date()
        *  */


        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val newDate = localDate.toDate()

        val preferredEvents = eventsDao.getThisDateEventsOrderedByAscendingStartTime(newDate) ?: emptyList()
        emit(Resource.Loading(data = preferredEvents))

        try {
            val dates = dateEventsDao.getDates() ?: emptyList()
            val duplicatedDates = findDuplicates(dates)
            if (duplicatedDates.isNotEmpty()){
                dateEventsDao.deleteDuplicateDateEvents()
                eventsDao.deleteDuplicateEvents()
            }
            val isNewDate = !dates.contains(newDate)

            if(isNewDate){
                val remoteDateEvents = sportsPredictionApi.getDateEvents(key, host, dateString, sport_id)
                val currentDateEvents = remoteDateEvents.toDateEvents()
                val dateEventEntity = DateEventsEntity(null, newDate, currentDateEvents)
                val preferredEntityList = currentDateEvents.toPreferredEventsEntities(newDate)
                dateEventsDao.insertDateEvents(dateEventEntity)
                eventsDao.insertEvents(preferredEntityList)
            }

        }catch (e: HttpException){
            emit(Resource.Error(
                message = HttpExceptionErrorMessage,
                data = preferredEvents)
            )
        }catch (e: IOException){
            emit(Resource.Error(
                message = IO_ExceptionErrorMessage,
                data = preferredEvents)
            )
        }

        val newPreferredEvents = eventsDao.getThisDateEventsOrderedByAscendingStartTime(newDate)?: emptyList()
        emit(Resource.Success(newPreferredEvents))

    }

    override fun getMatchStartTimeEvents(
        listOfEvents: ListOfEvents,
        matchStartTime: Long
    ): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        val localDateTimeNow = LocalDateTime.now()
        val now = localDateTimeNow.toEpochSecond(ZoneOffset.UTC)
        val today = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val tomorrow = LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val dayAfterTomorrow = LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val dayBeforeYesterday = LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val yesterday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val all = (-1).toLong()

        val matchStartTimeEvents = when(matchStartTime){
            all-> {
                listOfEvents
            }
            today-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= yesterday) && ((preferredEvent.startTimestamp?: 1) <= today)
                }
            }
            tomorrow-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= today) && ((preferredEvent.startTimestamp?: 1) <= dayAfterTomorrow)
                }
            }
            yesterday-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= dayBeforeYesterday) && ((preferredEvent.startTimestamp?: 1) <= yesterday)
                }
            }
            else -> {
                if (matchStartTime > 1.toLong()){listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) > now) && ((preferredEvent.startTimestamp?: 1) < matchStartTime)
                }} else listOfEvents
            }

        }

        emit(Resource.Success(matchStartTimeEvents))
    }

    override fun getSearchedEvents(
        listOfEvents: ListOfEvents,
        searchValue: String
    ): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        val searchedEvents = if(searchValue.trim().isEmpty()) {listOfEvents}
            else {
                listOfEvents.filter{ preferredEvent ->
                    (preferredEvent.country?.contains(searchValue.trim(), true) == true) ||
                            (preferredEvent.homeTeamName?.contains(searchValue.trim(), true) == true) ||
                            (preferredEvent.awayTeamName?.contains(searchValue.trim(), true) == true) ||
                            (preferredEvent.tournamentName?.contains(searchValue.trim(), true) == true)
                }
            }

        emit(Resource.Success(searchedEvents))

    }

    override fun getSortedEvents(
        listOfEvents: ListOfEvents,
        sortFilter: String
    ): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())


        val default = "Default"
        val ascendingTime = "Ascending Start Time"
        val descendingTime = "Descending Start Time"

        val sortedEvents = when(sortFilter){
            default -> {
                listOfEvents
            }
            ascendingTime -> {
                listOfEvents.sortedBy { it.startTimestamp }
            }
            descendingTime -> {
                listOfEvents.sortedByDescending { it.startTimestamp }
            }
            else -> {
                listOfEvents
            }
        }

        emit(Resource.Success(sortedEvents))

    }

    override fun getFilteredTournaments(
        listOfEvents: ListOfEvents,
        filteredTournaments: Map<String, String>
    ): Flow<Resource<ListOfEvents>>  = flow{
        emit(Resource.Loading())

        val sortedEvents = if(filteredTournaments.isEmpty()) listOfEvents else listOfEvents.filter{ (filteredTournaments.keys.contains("${it.country}${it.tournamentName}") && (filteredTournaments.values.contains(it.tournamentName))) }

        emit(Resource.Success(sortedEvents))

    }


}