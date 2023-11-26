package com.example.sportsprediction.feature_app.data.repository

import android.util.Log
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.DoesNotExist
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.course_event
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.host
import com.example.sportsprediction.core.util.Constants.key
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.core.util.Constants.page
import com.example.sportsprediction.core.util.Functions.getOpponentForm
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.core.util.ListOfEvents
import com.example.sportsprediction.core.util.ListOfTeamEvent
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.team_events.TeamEventsDao
import com.example.sportsprediction.feature_app.data.local.entities.team_events.TeamEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventDao
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.domain.model.h2h_events.HeadToHeadEvent
import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent
import com.example.sportsprediction.feature_app.domain.repository.TeamEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class TeamEventRepositoryImpl(
    private val sportsPredictionApi: SportsPredictionApi,
    private val teamEventsDao: TeamEventsDao,
    private val teamEventDao: TeamEventDao
): TeamEventRepository{

    override fun getRemoteTeamEvents(teamId: Int, teamName: String, date: Date, headToHeadEventId: String): Flow<Resource<List<TeamEventEntity>>> = flow{
        emit(Resource.Loading())


        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val longDate = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val newDate = localDate.toDate()

        val listOfTeamNameEventsEntity = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId)
        emit(Resource.Loading(data = listOfTeamNameEventsEntity))

        try {

            /*
            *   Check if that particular team has team events loaded in the database
            *   If team events are not loaded in database, then you load it from API and save it in Room
            *   Same will be done for Head to Head events as well

            The date will ensure that only the latest team events exists in the database

            */

            val teamEventsOfThisDate = teamEventsDao.getTeamEventUsingTeamIdAndDate(teamId, newDate)

            if(teamEventsOfThisDate.isNullOrEmpty()){

                // We will need to the the current Date in order  to make sure we select head to head events that have not happened yet

                // We query  the API for the team Events and Head to Head Events
                val remoteTeamEvents = sportsPredictionApi.getTeamEvents(key, host, teamId, page, course_event)
                val remoteHeadToHeadEvents = sportsPredictionApi.getHeadToHeadEvents(key, host, headToHeadEventId)

                // We get the remote team  Events and convert it to our useful team events
                val currentTeamEvents = remoteTeamEvents.toListOfTeamEvents().reversed()
                val mutableCurrentTeamEvents = mutableListOf<TeamEvent>()
                mutableCurrentTeamEvents.clear()

                // We want to add only past team events that's why we check the start date and compare it with our current date
                currentTeamEvents.forEach { teamEvent->
                    if (teamEvent.startTimestamp!! < longDate){
                        mutableCurrentTeamEvents.add(teamEvent)
                    }
                }

                // We get the remote head to head  Events and convert it to our useful team events
                val currentHeadToHeadEvents = remoteHeadToHeadEvents.toListOfHeadToHeadEvents()
                val mutableHeadToHeadEvents = mutableListOf<HeadToHeadEvent>()
                mutableHeadToHeadEvents.clear()

                // We want to add only past head to head events that's why we check the start date and compare it with our current date
                currentHeadToHeadEvents.forEach{headToHeadEvent ->
                    if (headToHeadEvent.startTimestamp!! < longDate){
                        mutableHeadToHeadEvents.add(headToHeadEvent)
                    }
                }

                val mergedTeamEvents = mutableListOf<TeamEvent>()
                mergedTeamEvents.clear()
                mergedTeamEvents.addAll(mutableCurrentTeamEvents)
                mergedTeamEvents.addAll(mutableHeadToHeadEvents.map { it.toTeamEvent() })

                val currentTeamEventsEntity = TeamEventsEntity(null, teamId, newDate, teamName, mergedTeamEvents.toList())

                /*
                * In order to ensure that only the most recent team events exists in our database, we delete the all old team events
                * Then we insert the new team events into the database
                * We do same for the team Name events
                */

                teamEventsDao.deleteThisParticularTeamEvents(teamId)
                teamEventsDao.insertTeamEvents(currentTeamEventsEntity)

                teamEventDao.deleteThisParticularListOfTeamEvent(teamId)
                teamEventDao.insertListOfTeamEvent(currentTeamEventsEntity.toListOfTeamNameEvents())

            }

        }catch (e: HttpException){
            emit(Resource.Error(
                message = "Something went wrong",
                data = listOfTeamNameEventsEntity
            ))
        }catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't connect to the server",
                data = listOfTeamNameEventsEntity
            ))
        }

        val newListOfTeamNameEventEntity = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId) ?: emptyList()
        emit(Resource.Success(newListOfTeamNameEventEntity))

    }

    override suspend fun getHeadToHeadTeamEvents(
        teamId: Int,
        headToHeadEventId: String
    ): Flow<ListOfTeamEvent>? {
        return teamEventDao.getHeadToHeadEvents(teamId, headToHeadEventId)
    }

    override suspend fun getTeamNameEventsList(teamId: Int): ListOfTeamEvent? {
        return teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId)
    }

    override suspend fun getTeamNameFormPercentage(teamId: Int, headToHeadEventId: String): Double{
        val headToHeadEvents  = teamEventDao.getHeadToHeadEventsList(teamId, headToHeadEventId) ?: emptyList()
        val previousEvents  = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId) ?: emptyList()
        val formEvents = mutableListOf<TeamEventEntity>()
        formEvents.clear()
        formEvents.addAll(previousEvents.toSet().take(6))
        formEvents.addAll(headToHeadEvents.toSet().take(4))

        var formPercentageValue = 0.0
        var totalFormValues = 0.0

        formEvents.forEach { formEvent->
            val playingLocation = formEvent.mainTeamPlayingLocation?.lowercase(Locale.ROOT) ?: Constants.Home
            val mainTeamScores = if(playingLocation == Constants.Away.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore  ?: nullInteger
            val opponentTeamScores = if(playingLocation == Home.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore ?: nullInteger

            val isNotNull = !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
            val totalValue = if (isNotNull) 1.0 else 0.0
            val resultValue = if (isNotNull && ( mainTeamScores > opponentTeamScores)) {
                1.0
            } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                0.0
            } else if (isNotNull) {
                0.5
            } else {
                0.0
            }
            totalFormValues += totalValue
            formPercentageValue += resultValue
        }
        val formPercentage = try {
            (formPercentageValue.div(totalFormValues)).times(100.0)
        }catch (e: ArithmeticException){
            0.0
        }

        return formPercentage
    }

    override suspend fun getTeamFormPercentage(teamId: Int): Flow<Resource<Double>> = flow{
        emit(Resource.Loading())
        val formEvents  = teamEventDao.getTeamFormEvents(teamId)?.take(5) ?: emptyList()

        var formPercentageValue = 0.0
        var totalFormValues = 0.0

        formEvents.forEach { formEvent->
            val playingLocation = formEvent.mainTeamPlayingLocation?.lowercase(Locale.ROOT) ?: Home
            val mainTeamScores = if(playingLocation == Away.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore  ?: nullInteger
            val opponentTeamScores = if(playingLocation == Home.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore ?: nullInteger

            val isNotNull = !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
            val totalValue = if (isNotNull) 1.0 else 0.0
            val resultValue = if (isNotNull && ( mainTeamScores > opponentTeamScores)) {
                1.0
            } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                0.0
            } else if (isNotNull) {
                0.5
            } else {
                0.0
            }
            totalFormValues += totalValue
            formPercentageValue += resultValue
        }
        val formPercentage = try {
            (formPercentageValue.div(totalFormValues)).times(100.0)
        }catch (e: ArithmeticException){
            0.0
        }
        emit(Resource.Success(formPercentage))
    }

    override suspend fun getOpponentFormMultiplierValue(teamId: Int, headToHeadEventId: String): Double{
        val headToHeadEvents  = teamEventDao.getHeadToHeadEventsList(teamId, headToHeadEventId) ?: emptyList()
        val previousEvents  = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId) ?: emptyList()
        val formEvents = mutableListOf<TeamEventEntity>()
        formEvents.clear()
        formEvents.addAll(previousEvents.toSet().take(6))
        formEvents.addAll(headToHeadEvents.toSet().take(4))

        var formPercentageValue = 0.0
        var totalFormValues = 0.0

        formEvents.forEach { formEvent->
            val playingLocation = formEvent.mainTeamPlayingLocation?.lowercase(Locale.ROOT) ?: Constants.Home
            val mainTeamScores = if(playingLocation == Constants.Away.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore  ?: nullInteger
            val opponentTeamScores = if(playingLocation == Home.lowercase(Locale.ROOT)) formEvent.awayScore ?: nullInteger else formEvent.homeScore ?: nullInteger

            val isNotNull = !(mainTeamScores == nullInteger || opponentTeamScores == nullInteger)
            val totalValue = if (isNotNull) 1.0 else 0.0
            val resultValue = if (isNotNull && ( mainTeamScores > opponentTeamScores)) {
                1.0
            } else if (isNotNull && (mainTeamScores < opponentTeamScores)) {
                0.0
            } else {
                0.5
            }
            totalFormValues += totalValue
            formPercentageValue += resultValue
        }
        val formPercentage = try {
            (formPercentageValue.div(totalFormValues)).times(100.0)
        }catch (e: ArithmeticException){
            0.0
        }

        return getOpponentForm(formPercentage)

    }

    override suspend fun getTeamNameEventEntity(eventId: Int, teamId: Int): Flow<Resource<ListOfTeamEvent>> = flow{
        emit(Resource.Loading())

        val teamNameEventEntity =  teamEventDao.getTeamNameEventEntity(eventId, teamId) ?: emptyList()
        emit(Resource.Success(data = teamNameEventEntity))

    }

    override suspend fun getTeamsPastEvents(listOfEvents: ListOfEvents, date: Date): Flow<Resource<List<TeamEventEntity>>> = flow{

        emit(Resource.Loading())

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, shortDateFormatter)
        val longDate = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
        val newDate = localDate.toDate()

        val allTeamEventEntityIds = mutableListOf<Int>()

        listOfEvents.forEach { eventsEntity ->
            val homeTeamId =  eventsEntity.homeTeamId ?: nullInteger
            val awayTeamId =  eventsEntity.awayTeamId ?: nullInteger

            allTeamEventEntityIds.add(homeTeamId)
            allTeamEventEntityIds.add(awayTeamId)
        }

        val allTeamEventEntities = mutableListOf<TeamEventEntity>()
        allTeamEventEntityIds.forEach { teamId->
            val teamEvents = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId) ?: emptyList()
            allTeamEventEntities.addAll(teamEvents)
        }

        emit(Resource.Loading(allTeamEventEntities))

        listOfEvents.forEach { eventsEntity ->
            val homeTeamName = eventsEntity.homeTeamName ?: emptyString
            val awayTeamName = eventsEntity.awayTeamName ?: emptyString
            val homeTeamId = eventsEntity.homeTeamId ?: nullInteger
            val awayTeamId = eventsEntity.awayTeamId ?: nullInteger
            val headToHeadEventId = eventsEntity.headToHeadId ?: emptyString

            val homeTeamEventsOfThisDate = teamEventsDao.getTeamEventUsingTeamIdAndDate(homeTeamId, newDate)
            val homeTeamEventsIsNullOrEmpty = (homeTeamEventsOfThisDate.isNullOrEmpty())
            val awayTeamEventsOfThisDate = teamEventsDao.getTeamEventUsingTeamIdAndDate(awayTeamId, newDate)
            val awayTeamEventsIsNullOrEmpty = (awayTeamEventsOfThisDate.isNullOrEmpty())


            try {
                val remoteHeadToHeadEvents = if ( headToHeadEventId.trim() != emptyString && (homeTeamEventsIsNullOrEmpty || awayTeamEventsIsNullOrEmpty) ){
                    sportsPredictionApi.getHeadToHeadEvents(key, host, headToHeadEventId).toListOfHeadToHeadEvents() }
                else {emptyList()}

                if (homeTeamEventsIsNullOrEmpty) {
                    val homeTeamEvents = if (homeTeamId != nullInteger || homeTeamId != DoesNotExist) sportsPredictionApi.getTeamEvents(key, host, homeTeamId, page, course_event).toListOfTeamEvents() else emptyList()
                    val mergedHomeTeamEvents = mutableListOf<TeamEvent>()

                    homeTeamEvents.forEach { teamEvent ->
                        if (teamEvent.startTimestamp!! < longDate) {
                            mergedHomeTeamEvents.add(teamEvent)
                        }
                    }
                    remoteHeadToHeadEvents.forEach { headToHeadEvent ->
                        if (headToHeadEvent.startTimestamp!! < longDate) {
                            mergedHomeTeamEvents.add(headToHeadEvent.toTeamEvent())
                        }
                    }
                    val currentHomeTeamEventsEntity = TeamEventsEntity(
                        null,
                        homeTeamId,
                        newDate,
                        homeTeamName,
                        mergedHomeTeamEvents.toList()
                    )

                    teamEventsDao.deleteThisParticularTeamEvents(homeTeamId)
                    teamEventsDao.insertTeamEvents(currentHomeTeamEventsEntity)

                    teamEventDao.deleteThisParticularListOfTeamEvent(homeTeamId)
                    teamEventDao.insertListOfTeamEvent(currentHomeTeamEventsEntity.toListOfTeamNameEvents())

                }

                if (awayTeamEventsIsNullOrEmpty) {

                    val awayTeamEvents = if (awayTeamId != nullInteger || awayTeamId != DoesNotExist) sportsPredictionApi.getTeamEvents(key, host, awayTeamId, page, course_event).toListOfTeamEvents() else emptyList()
                    val mergedAwayTeamEvents = mutableListOf<TeamEvent>()

                    awayTeamEvents.forEach { teamEvent ->
                        if (teamEvent.startTimestamp!! < longDate) {
                            mergedAwayTeamEvents.add(teamEvent)
                        }
                    }
                    remoteHeadToHeadEvents.forEach { headToHeadEvent ->
                        if (headToHeadEvent.startTimestamp!! < longDate) {
                            mergedAwayTeamEvents.add(headToHeadEvent.toTeamEvent())
                        }
                    }
                    val currentAwayTeamEventsEntity = TeamEventsEntity(
                        null,
                        awayTeamId,
                        newDate,
                        awayTeamName,
                        mergedAwayTeamEvents.toList()
                    )

                    teamEventsDao.deleteThisParticularTeamEvents(awayTeamId)
                    teamEventsDao.insertTeamEvents(currentAwayTeamEventsEntity)

                    teamEventDao.deleteThisParticularListOfTeamEvent(awayTeamId)
                    teamEventDao.insertListOfTeamEvent(currentAwayTeamEventsEntity.toListOfTeamNameEvents())

                }


            }catch (e: HttpException){
                emit(Resource.Error(
                    message = "Something went wrong",
                    data = allTeamEventEntities
                ))
            }catch (e: IOException){
                emit(Resource.Error(
                    message = "Couldn't connect to the server",
                    data = allTeamEventEntities
                ))
            }

        }

        allTeamEventEntities.clear()
        allTeamEventEntityIds.forEach { teamId->
            val teamEvents = teamEventDao.getListOfTeamEventsForThisParticularTeam(teamId) ?: emptyList()
            allTeamEventEntities.addAll(teamEvents)
        }

        emit(Resource.Success(allTeamEventEntities))

    }

    override suspend fun showTeamPastEvents(
        listOfEvents: ListOfEvents,
        date: Date
    ): Flow<Resource<List<TeamEventEntity>>> = flow{
        emit(Resource.Loading())

        Log.d("TeamEventRepositoryImpl", "showPastEventsFunction: this piece of code gets called")
        Log.d("TeamEventRepositoryImpl", "list of events: $listOfEvents is empty")
        Log.d("TeamEventRepositoryImpl", "date: $date is empty")

        val allTeamEvents = mutableListOf<TeamEventEntity>()

        listOfEvents.forEach { eventsEntity ->
            val homeTeamId = eventsEntity.homeTeamId ?: nullInteger
            val awayTeamId = eventsEntity.awayTeamId ?: nullInteger
            val headToHeadEventId = eventsEntity.headToHeadId ?: emptyString

            val previousHomeTeamHeadToHeadEvents = teamEventDao.getHeadToHeadEventsList(homeTeamId, headToHeadEventId)?.toSet()?.toList()?.take(10) ?: emptyList()
            val previousHomeTeamEvents = teamEventDao.getTeamFormEvents(homeTeamId)?.toSet()?.toList()?.take(10) ?: emptyList()

            allTeamEvents.addAll(previousHomeTeamEvents.plus(previousHomeTeamHeadToHeadEvents))

            val previousAwayTeamHeadToHeadEvents = teamEventDao.getHeadToHeadEventsList(awayTeamId, headToHeadEventId)?.toSet()?.toList()?.take(10) ?: emptyList()
            val previousAwayTeamEvents = teamEventDao.getTeamFormEvents(awayTeamId)?.toSet()?.toList()?.take(10) ?: emptyList()

            allTeamEvents.addAll(previousAwayTeamEvents.plus(previousAwayTeamHeadToHeadEvents))

            Log.d("TeamEventRepositoryImpl", "list of events: ${eventsEntity.homeTeamName} vs ${eventsEntity.awayTeamName}")

        }

        allTeamEvents.forEach { teamEvent ->
            Log.d("TeamEventRepositoryImpl", "show events: ${teamEvent.mainTeamName} vs ${teamEvent.opponentName}")
        }

        emit(Resource.Success(allTeamEvents.toList()))

    }


}