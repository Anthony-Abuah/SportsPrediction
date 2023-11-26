package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.HttpExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.IO_ExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.ascendingTime
import com.example.sportsprediction.core.util.Constants.descendingTime
import com.example.sportsprediction.core.util.Constants.host
import com.example.sportsprediction.core.util.Constants.key
import com.example.sportsprediction.core.util.Constants.popular
import com.example.sportsprediction.core.util.Functions.findDuplicates
import com.example.sportsprediction.core.util.Functions.orUnknownCountry
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.Functions.toDateString
import com.example.sportsprediction.core.util.Functions.toLocalDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsDao
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsDao
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.domain.repository.DateEventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

class DateEventsRepositoryImpl(
    private val sportsPredictionApi: SportsPredictionApi,
    private val dateEventsDao: DateEventsDao,
    private val eventsDao: EventsDao
): DateEventsRepository {
    override fun getRemoteDateEvents(date: Date, sport_id: Int): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        val newDate = date.toLocalDate().toDate() // This converts the date to the date value at the start of the day

        val preferredEvents = eventsDao.getThisDateEvents(newDate) ?: emptyList()
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
                val remoteDateEvents = sportsPredictionApi.getDateEvents(key, host, newDate.toDateString(), sport_id)
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

        val newPreferredEvents = eventsDao.getThisDateEvents(newDate)?: emptyList()
        emit(Resource.Success(newPreferredEvents))
    }

    override suspend fun getRemoteGroupedDateEvents(
        date: Date,
        sport_id: Int
    ): Flow<Resource< Map<String, List<EventsEntity>>>> = flow{
        withContext(Dispatchers.IO) {
            emit(Resource.Loading())
            val newDate = date.toLocalDate()
                .toDate() // This converts the date to the date value at the start of the day
            val preferredEvents = eventsDao.getThisDateEvents(newDate) ?: emptyList()
            val groupedEvents = preferredEvents.groupBy { "${it.country}, ${it.tournamentName}" }
            emit(Resource.Loading(groupedEvents))
            if (groupedEvents.isEmpty()) {
                try {
                    val dates = dateEventsDao.getDates() ?: emptyList()
                    val duplicatedDates = findDuplicates(dates)
                    if (duplicatedDates.isNotEmpty()) {
                        dateEventsDao.deleteDuplicateDateEvents()
                        eventsDao.deleteDuplicateEvents()
                    }
                    val isNewDate = !dates.contains(newDate)
                    if (isNewDate) {
                        val remoteDateEvents = sportsPredictionApi.getDateEvents(
                            key,
                            host,
                            newDate.toDateString(),
                            sport_id
                        )
                        val currentDateEvents = remoteDateEvents.toDateEvents()
                        val dateEventEntity = DateEventsEntity(null, newDate, currentDateEvents)
                        val preferredEntityList =
                            currentDateEvents.toPreferredEventsEntities(newDate)
                        dateEventsDao.insertDateEvents(dateEventEntity)
                        eventsDao.insertEvents(preferredEntityList)

                        val newGroupedEvents =
                            preferredEntityList.groupBy { ("${it.country}, ${it.tournamentName}").orUnknownCountry() }
                        emit(Resource.Success(newGroupedEvents))
                    }
                } catch (e: HttpException) {
                    emit(
                        Resource.Error(
                            message = HttpExceptionErrorMessage,
                            data = groupedEvents
                        )
                    )
                } catch (e: IOException) {
                    emit(
                        Resource.Error(
                            message = IO_ExceptionErrorMessage,
                            data = groupedEvents
                        )
                    )
                }
            } else emit(Resource.Success(groupedEvents))
        }
    }

    override fun getMatchStartTimeEvents(
        listOfEvents: ListOfEvents,
        matchStartTime: Long
    ): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        val localDateTimeNow = LocalDateTime.now()
        val now = localDateTimeNow.toEpochSecond(ZoneOffset.UTC)

        val in_1_hour = localDateTimeNow.plusHours(1).toEpochSecond(ZoneOffset.UTC)
        val in_2_hours = localDateTimeNow.plusHours(2).toEpochSecond(ZoneOffset.UTC)
        val in_3_hours = localDateTimeNow.plusHours(3).toEpochSecond(ZoneOffset.UTC)
        val in_6_hours = localDateTimeNow.plusHours(6).toEpochSecond(ZoneOffset.UTC)
        val in_12_hours = localDateTimeNow.plusHours(12).toEpochSecond(ZoneOffset.UTC)

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
            in_1_hour-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_1_hour)
                }
            }
            in_2_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_2_hours)
                }
            }
            in_3_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_3_hours)
                }
            }
            in_6_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_6_hours)
                }
            }
            in_12_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_12_hours)
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
                } } else listOfEvents
            }

        }

        emit(Resource.Success(matchStartTimeEvents))
    }

    override fun getMatchStartTimeEvents(
        eventsList: Map<String, List<EventsEntity>>,
        matchStartTime: Long
    ): Flow<Resource<Map<String, List<EventsEntity>>>> =flow{
        emit(Resource.Loading())

        val localDateTimeNow = LocalDateTime.now()
        val now = localDateTimeNow.toEpochSecond(ZoneOffset.UTC)

        val in_1_hour = localDateTimeNow.plusHours(1).toEpochSecond(ZoneOffset.UTC)
        val in_2_hours = localDateTimeNow.plusHours(2).toEpochSecond(ZoneOffset.UTC)
        val in_3_hours = localDateTimeNow.plusHours(3).toEpochSecond(ZoneOffset.UTC)
        val in_6_hours = localDateTimeNow.plusHours(6).toEpochSecond(ZoneOffset.UTC)
        val in_12_hours = localDateTimeNow.plusHours(12).toEpochSecond(ZoneOffset.UTC)

        val today = LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val tomorrow = LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val dayAfterTomorrow = LocalDate.now().plusDays(3).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val dayBeforeYesterday = LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val yesterday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val all = (-1).toLong()

        val listOfEvents = mutableListOf<EventsEntity>()
        eventsList.forEach { (key, _) ->
            eventsList[key]?.let { listOfEvents.addAll(it) }
        }
        val matchStartTimeEvents = when(matchStartTime){
            all-> {
                listOfEvents.groupBy { it.country.orUnknownCountry() }
            }
            today-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= yesterday) && ((preferredEvent.startTimestamp?: 1) <= today)
                }.groupBy { it.country.orUnknownCountry() }
            }
            in_1_hour-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_1_hour)
                }.groupBy { it.country.orUnknownCountry() }
            }
            in_2_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_2_hours)
                }.groupBy { it.country.orUnknownCountry() }
            }
            in_3_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_3_hours)
                }.groupBy { it.country.orUnknownCountry() }
            }
            in_6_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_6_hours)
                }.groupBy { it.country.orUnknownCountry() }
            }
            in_12_hours-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= now) && ((preferredEvent.startTimestamp?: 1) <= in_12_hours)
                }.groupBy { it.country.orUnknownCountry() }
            }
            tomorrow-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= today) && ((preferredEvent.startTimestamp?: 1) <= dayAfterTomorrow)
                }.groupBy { it.country.orUnknownCountry() }
            }
            yesterday-> {
                listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) >= dayBeforeYesterday) && ((preferredEvent.startTimestamp?: 1) <= yesterday)
                }.groupBy { it.country.orUnknownCountry() }
            }
            else -> {
                if (matchStartTime > 1.toLong()){listOfEvents.filter { preferredEvent->
                    ((preferredEvent.startTimestamp ?: 1) > now) && ((preferredEvent.startTimestamp?: 1) < matchStartTime)
                }.groupBy { it.country.orUnknownCountry() }
                } else listOfEvents.groupBy { it.country.orUnknownCountry() }
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

    override fun getSearchedEvents(
        eventsList: Map<String, List<EventsEntity>>,
        searchValue: String
    ): Flow<Resource<Map<String, List<EventsEntity>>>> =flow{
        emit(Resource.Loading())

        val listOfEvents = mutableListOf<EventsEntity>()
        eventsList.forEach { (key, _) ->
            eventsList[key]?.let { listOfEvents.addAll(it) }
        }

        val searchedEvents = if(searchValue.trim().isEmpty()) {listOfEvents.groupBy { it.country.orUnknownCountry() }}
        else {
            listOfEvents.filter{ preferredEvent ->
                (preferredEvent.country?.contains(searchValue.trim(), true) == true) ||
                        (preferredEvent.homeTeamName?.contains(searchValue.trim(), true) == true) ||
                        (preferredEvent.awayTeamName?.contains(searchValue.trim(), true) == true) ||
                        (preferredEvent.tournamentName?.contains(searchValue.trim(), true) == true)
            }.groupBy { it.country.orUnknownCountry() }
        }

        emit(Resource.Success(searchedEvents))
    }

    override fun getSortedEvents(
        listOfEvents: ListOfEvents,
        sortFilter: String
    ): Flow<Resource<ListOfEvents>> = flow{
        emit(Resource.Loading())

        val sortedEvents = when(sortFilter){
            popular -> {
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

    override fun getSortedEvents(
        eventsList: Map<String, List<EventsEntity>>,
        sortFilter: String
    ): Flow<Resource<Map<String, List<EventsEntity>>>> =flow{
        emit(Resource.Loading())
        val listOfEvents = mutableListOf<EventsEntity>()
        eventsList.forEach { (key, _) ->
            eventsList[key]?.let { listOfEvents.addAll(it) }
        }
        val sortedEvents = when(sortFilter){
            popular -> {
                listOfEvents.groupBy { it.country.orUnknownCountry() }
            }
            ascendingTime -> {
                listOfEvents.sortedBy { it.startTimestamp }.groupBy { it.country.orUnknownCountry() }
            }
            descendingTime -> {
                listOfEvents.sortedByDescending { it.startTimestamp }.groupBy { it.country.orUnknownCountry() }
            }
            else -> {
                listOfEvents.groupBy { it.country.orUnknownCountry() }
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

    override fun getFilteredTournaments(
        eventsList: Map<String, List<EventsEntity>>,
        filteredTournaments: Map<String, String>
    ): Flow<Resource<Map<String, List<EventsEntity>>>> =flow{
        emit(Resource.Loading())
        val listOfEvents = mutableListOf<EventsEntity>()
        eventsList.forEach { (key, _) ->
            eventsList[key]?.let { listOfEvents.addAll(it) }
        }
        val sortedEvents = if(filteredTournaments.isEmpty()) listOfEvents.groupBy { it.country.orUnknownCountry() }
        else listOfEvents.filter{ (filteredTournaments.keys.contains("${it.country}${it.tournamentName}") && (filteredTournaments.values.contains(it.tournamentName))) }.groupBy { it.country.orUnknownCountry() }

        emit(Resource.Success(sortedEvents))
    }
}