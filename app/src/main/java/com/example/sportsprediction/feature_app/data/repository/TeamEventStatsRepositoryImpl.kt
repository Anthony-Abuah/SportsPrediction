package com.example.sportsprediction.feature_app.data.repository

import android.util.Log
import com.example.sportsprediction.core.util.*
import com.example.sportsprediction.core.util.Constants.DoesNotExist
import com.example.sportsprediction.core.util.Constants.HttpExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.IO_ExceptionErrorMessage
import com.example.sportsprediction.core.util.Constants.host
import com.example.sportsprediction.core.util.Constants.key
import com.example.sportsprediction.core.util.Functions.toDate
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsDao
import com.example.sportsprediction.feature_app.data.local.entities.event_stats.EventStatsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventDao
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.remote.SportsPredictionApi
import com.example.sportsprediction.feature_app.domain.repository.TeamEventStatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class TeamEventStatsRepositoryImpl(
    private val sportsPredictionApi: SportsPredictionApi,
    private val teamEventDao: TeamEventDao,
    private val eventStatsDao: EventStatsDao
) : TeamEventStatsRepository {

    override fun getAllTeamEventStats(mainTeamId: Int, headToHeadId: String, thisEventId: Int, date: Date, numberOfPastEvents: Int, numberOfHeadToHeadEvents: Int): Flow<Resource<List<EventStatsEntity>>> = flow{
        emit(Resource.Loading())

        val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val dateString = Functions.shortDateFormatter.format(dateToLocalDate)
        val localDate = LocalDate.parse(dateString, Functions.shortDateFormatter)
        val longDate = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond

        val teamEventStatsList = eventStatsDao.getAllOfThisTeamStats(mainTeamId)
        emit(Resource.Loading(data = teamEventStatsList))

        /*
        * We want to make sure we obtain only the most current stats
        * In order to do that, we have to delete the old ones and use the stats of the events presently contained in the TeamNameEventsEntity
        * so we will get all the current eventId from our TeamEventEntity and com.example.sportsprediction.feature_app.data.local.entities.team_event_stats.EventStatsEntity
        * then we will subtract the eventIds of the com.example.sportsprediction.feature_app.data.local.entities.team_event_stats.EventStatsEntity from the TeamEventEntity to get the list of unwanted(old) eventIds
        * then we will delete that old eventId
        */


        val theNumberOfPastEvents = if (numberOfPastEvents > 10) 10 else if (numberOfPastEvents < 1) 6 else numberOfPastEvents
        val theNumberOfHeadToHeadEvents = if (numberOfHeadToHeadEvents > 10) 10 else if (numberOfHeadToHeadEvents < 1) 4 else numberOfHeadToHeadEvents

        val headToHeadEventIdsForThisTeam = teamEventDao.getThisTeamHeadToHeadEventIdsInDescendingOrder(mainTeamId, headToHeadId, longDate.toInt()) ?: emptyList()
        val previousEventIdsForThisTeam = teamEventDao.getThisTeamEventIdsInDescendingOrder(mainTeamId, longDate.toInt())?.minus(headToHeadEventIdsForThisTeam.toSet()) ?: emptyList()

        val teamNameHeadToHeadEventsIds = headToHeadEventIdsForThisTeam.toSet().toList().minus(thisEventId).take(theNumberOfHeadToHeadEvents)
        val teamNameEventsIds = previousEventIdsForThisTeam.minus(teamNameHeadToHeadEventsIds.toSet()).minus(thisEventId).take(theNumberOfPastEvents)
        val mergedTeamNameEventsIds = teamNameEventsIds.plus(teamNameHeadToHeadEventsIds)
        val teamEventStatsEventIds = eventStatsDao.getThisTeamEventIds(mainTeamId) ?: emptyList()

        teamNameEventsIds.forEach { eventId->
            Log.d("eventId", "team Event Id: $eventId")
        }
        teamNameHeadToHeadEventsIds.forEach {eventId->
            Log.d("eventId", "headToHead Event Id: $eventId")
        }
        mergedTeamNameEventsIds.forEach { eventId->
            Log.d("eventId", "merged Team Event Id: $eventId")
        }

        Log.d("teamNameEventIds", "MarketTypeEnum: " + teamNameEventsIds.count())
        Log.d("HeadToHeadEventIds", "MarketTypeEnum: " + teamNameHeadToHeadEventsIds.count())
        Log.d("mergedTeamNameEventIds", "MarketTypeEnum: " + mergedTeamNameEventsIds.count())



        val unwantedEventStatIds = teamEventStatsEventIds.minus(mergedTeamNameEventsIds.toSet())
        unwantedEventStatIds.forEach{unwantedEventStatId->
            eventStatsDao.deleteEventStatsForThisEvent(unwantedEventStatId)
        }

        try {

            /*
            * Now we are going to check if the stats for this particular eventId exists in the database
            * If it exists, we will just keep it but if it does not, we will query the api for that particular stats
            */

            mergedTeamNameEventsIds.forEach { eventId->
                var loopCheck = 3

                val teamEvent = teamEventDao.getTeamEvent(eventId, mainTeamId)

                if (teamEvent != null) {
                    val teamEventStatsEventId = eventStatsDao.getThisEventId(eventId, mainTeamId) ?: DoesNotExist

                    if (teamEventStatsEventId == DoesNotExist) {
                        do {
                            val remoteEventStats = sportsPredictionApi.getEventStats(key, host, eventId)
                            val currentEventStats = remoteEventStats.toEventStats()
                            val currentTeamStatsEntity = currentEventStats.toTeamStats(
                                teamEvent.mainTeamName,
                                teamEvent.mainTeamId,
                                teamEvent.mainTeamPlayingLocation,
                                teamEvent.eventId,
                                teamEvent.date,
                                teamEvent.roundInfo,
                                teamEvent.tournament,
                                teamEvent.tournamentName,
                                ((teamEvent.homeScore!!.plus(teamEvent.awayScore!!)).toDouble()),
                                (((teamEvent.homeScores!!.period1!!).plus((teamEvent.awayScores!!.period1!!))).toDouble()),
                                (((teamEvent.homeScores.period2!!).plus((teamEvent.awayScores.period2!!))).toDouble()),
                                (((teamEvent.homeScores.period1!!).plus((teamEvent.homeScores.period2))).toDouble()),
                                (((teamEvent.homeScores.period1)).toDouble()),
                                (((teamEvent.homeScores.period2)).toDouble()),
                                (((teamEvent.awayScores.period1!!).plus((teamEvent.awayScores.period2))).toDouble()),
                                (((teamEvent.awayScores.period1)).toDouble()),
                                (((teamEvent.awayScores.period2)).toDouble())
                            ).toTeamStatsEntity(
                                teamEvent.homeTeamName,
                                teamEvent.homeTeamId,
                                teamEvent.awayTeamName,
                                teamEvent.awayTeamId,
                                teamEvent.startTimestamp
                            )
                            eventStatsDao.insertThisEventStats(currentTeamStatsEntity)
                            val thisTeamEventStatsEventId = eventStatsDao.getThisEventId(eventId) ?: DoesNotExist
                            loopCheck -= 1
                            if (loopCheck < 0) {
                                break
                            }
                        } while (thisTeamEventStatsEventId == DoesNotExist)

                    }

                }

            }

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = teamEventStatsList
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't connect to the server",
                    data = teamEventStatsList
                )
            )
        }

        val newTeamEventStatsList = eventStatsDao.getAllOfThisTeamStats(mainTeamId) ?: emptyList()
        emit(Resource.Success(newTeamEventStatsList))

    }

    override fun getTeamEventStats(mainTeamId: Int): Flow<Resource<ListOfEventStats>> = flow{
        emit(Resource.Loading())
        val teamEventStats = eventStatsDao.getAllOfThisTeamStats(mainTeamId) ?: emptyList()
        emit(Resource.Success(teamEventStats))
    }

    override fun getTeamEventStats(
        mainTeamId: Int,
        eventId: Int
    ): Flow<Resource<String>> = flow{

        val teamStatsEventId = eventStatsDao.getThisEventId(eventId) ?: DoesNotExist
        val teamNameEvent = teamEventDao.getTeamEvent(eventId, mainTeamId)

        try {
            if (teamNameEvent != null && ( teamStatsEventId == DoesNotExist || teamStatsEventId != eventId)){
                val remoteEventStats = sportsPredictionApi.getEventStats(key, host, eventId)
                val currentEventStats = remoteEventStats.toEventStats()
                val currentTeamStatsEntity = currentEventStats.toTeamStats(
                    teamNameEvent.mainTeamName,
                    teamNameEvent.mainTeamId,
                    teamNameEvent.mainTeamPlayingLocation,
                    teamNameEvent.eventId,
                    teamNameEvent.date,
                    teamNameEvent.roundInfo,
                    teamNameEvent.tournament,
                    teamNameEvent.tournamentName,
                    ((teamNameEvent.homeScore!!.plus(teamNameEvent.awayScore!!)).toDouble()),
                    (((teamNameEvent.homeScores!!.period1!!).plus((teamNameEvent.awayScores!!.period1!!))).toDouble()),
                    (((teamNameEvent.homeScores.period2!!).plus((teamNameEvent.awayScores.period2!!))).toDouble()),
                    (((teamNameEvent.homeScore)).toDouble()),
                    (((teamNameEvent.homeScores.period1))?.toDouble()),
                    (((teamNameEvent.homeScores.period2)).toDouble()),
                    (((teamNameEvent.awayScore)).toDouble()),
                    (((teamNameEvent.awayScores.period1))?.toDouble()),
                    (((teamNameEvent.awayScores.period2)).toDouble())
                ).toTeamStatsEntity(
                    teamNameEvent.homeTeamName,
                    teamNameEvent.homeTeamId,
                    teamNameEvent.awayTeamName,
                    teamNameEvent.awayTeamId,
                    teamNameEvent.startTimestamp)

                eventStatsDao.insertThisEventStats(currentTeamStatsEntity)

            }

        }catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = HttpExceptionErrorMessage,
                    data = HttpExceptionErrorMessage
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = IO_ExceptionErrorMessage,
                    data = IO_ExceptionErrorMessage
                )
            )
        }

        val newTeamEventStats = eventStatsDao.getThisEventStatsForThisTeam(mainTeamId, eventId)

        /*val mainTeamName = newTeamEventStats?.mainTeamName
        val opponentTeam = newTeamEventStats?.opponentName
        val thisEventId = newTeamEventStats?.eventId*/

        val message = if (newTeamEventStats == null)  "Failed to Add"
        else "Added Successfully"

        emit(Resource.Success(message))

    }

    override fun getEventStats(eventId: Int): Flow<Resource<EventStatsEntity?>> = flow{
        emit(Resource.Loading())

        val eventStats = eventStatsDao.getThisTeamEventStats(eventId)
        emit(Resource.Loading(eventStats))

        val teamEvent = teamEventDao.getTeamEvent(eventId)

        try {
            if (eventStats == null && teamEvent != null){
                val remoteEventStats = sportsPredictionApi.getEventStats(key, host, eventId)
                val currentEventStats = remoteEventStats.toEventStats()
                val currentEventStatsEntity = currentEventStats.toTeamStats(
                    teamEvent.mainTeamName,
                    teamEvent.mainTeamId,
                    teamEvent.mainTeamPlayingLocation,
                    teamEvent.eventId,
                    teamEvent.date,
                    teamEvent.roundInfo,
                    teamEvent.tournament,
                    teamEvent.tournamentName,
                    ((teamEvent.homeScore!!.plus(teamEvent.awayScore!!)).toDouble()),
                    (((teamEvent.homeScores!!.period1!!).plus((teamEvent.awayScores!!.period1!!))).toDouble()),
                    (((teamEvent.homeScores.period2!!).plus((teamEvent.awayScores.period2!!))).toDouble()),
                    (((teamEvent.homeScores.period1!!).plus((teamEvent.homeScores.period2))).toDouble()),
                    (((teamEvent.homeScores.period1)).toDouble()),
                    (((teamEvent.homeScores.period2)).toDouble()),
                    (((teamEvent.awayScores.period1!!).plus((teamEvent.awayScores.period2))).toDouble()),
                    (((teamEvent.awayScores.period1)).toDouble()),
                    (((teamEvent.awayScores.period2)).toDouble())
                ).toTeamStatsEntity(
                    teamEvent.homeTeamName,
                    teamEvent.homeTeamId,
                    teamEvent.awayTeamName,
                    teamEvent.awayTeamId,
                    teamEvent.startTimestamp
                )

                eventStatsDao.insertThisEventStats(currentEventStatsEntity)

            }

        }
        catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = HttpExceptionErrorMessage,
                    data = eventStats
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = IO_ExceptionErrorMessage,
                    data = eventStats
                )
            )
        }
        val newEventStats = eventStatsDao.getThisTeamEventStats(eventId)
        emit(Resource.Success(newEventStats))

    }

    override suspend fun getListOfTeamsPastEventsStats(listOfEvents: ListOfEvents, date: Date, numberOfPastEvents: Int, numberOfPastHeadToHeadEvents: Int): Flow<Resource<String>> = flow{

        emit(Resource.Loading("Loading the teams statistics"))

        listOfEvents.forEach { eventsEntity ->
            val homeTeamName = eventsEntity.homeTeamName ?: Constants.emptyString
            val awayTeamName = eventsEntity.awayTeamName ?: Constants.emptyString
            val homeTeamId = eventsEntity.homeTeamId ?: Constants.nullInteger
            val awayTeamId = eventsEntity.awayTeamId ?: Constants.nullInteger
            val headToHeadEventId = eventsEntity.headToHeadId ?: Constants.emptyString

            val theNumberOfPastEvents = if (numberOfPastEvents >= 10) 10 else if (numberOfPastEvents <= 1) 1 else numberOfPastEvents
            val theNumberOfHeadToHeadEvents = if (numberOfPastHeadToHeadEvents >= 10) 10 else if (numberOfPastHeadToHeadEvents <= 1) 1 else numberOfPastHeadToHeadEvents

            val pastHomeTeamHeadToHeadEvents = teamEventDao.getHeadToHeadEventsList(homeTeamId, headToHeadEventId)?.toSet()?.toList()?.sortedByDescending { it.startTimestamp }?.take(theNumberOfHeadToHeadEvents) ?: emptyList()
            val pastHomeTeamEvents = teamEventDao.getTeamFormEvents(homeTeamId)?.asSequence()?.minus(pastHomeTeamHeadToHeadEvents.toSet())?.toSet()?.toList()?.sortedByDescending { it.startTimestamp }?.take(theNumberOfPastEvents)?.toList() ?: emptyList()
            val previousHomeTeamEvents = pastHomeTeamEvents.plus(pastHomeTeamHeadToHeadEvents)

            val pastAwayTeamHeadToHeadEvents = teamEventDao.getHeadToHeadEventsList(awayTeamId, headToHeadEventId)?.toSet()?.toList()?.sortedByDescending { it.startTimestamp }?.take(theNumberOfHeadToHeadEvents) ?: emptyList()
            val pastAwayTeamEvents = teamEventDao.getTeamFormEvents(awayTeamId)?.asSequence()?.minus(pastAwayTeamHeadToHeadEvents.toSet())?.toSet()?.toList()?.sortedByDescending { it.startTimestamp }?.take(theNumberOfPastEvents)?.toList() ?: emptyList()
            val previousAwayTeamEvents = pastAwayTeamEvents.plus(pastAwayTeamHeadToHeadEvents)

            val homeTeamEventsStatsList = eventStatsDao.getAllOfThisTeamStats(homeTeamId)
            val awayTeamEventsStatsList = eventStatsDao.getAllOfThisTeamStats(awayTeamId)

            try {
                if (pastHomeTeamEvents.isNotEmpty()) {
                    previousHomeTeamEvents.forEachIndexed { index, homeTeamPreviousEvent ->
                        val thisEventId = homeTeamPreviousEvent.eventId ?: DoesNotExist
                        val thisHomeTeamPastEventStatExists =
                            homeTeamEventsStatsList?.any { eventStatsEntity -> (eventStatsEntity.mainTeamId == homeTeamId) && (eventStatsEntity.eventId == thisEventId) } ?: false

                        if (!thisHomeTeamPastEventStatExists) {
                            val thisEventStats = sportsPredictionApi.getEventStats(key, host, thisEventId).toEventStats()
                            val thisEventStatsEntity = thisEventStats.toTeamStats(
                                homeTeamPreviousEvent.mainTeamName,
                                homeTeamPreviousEvent.mainTeamId,
                                homeTeamPreviousEvent.mainTeamPlayingLocation,
                                homeTeamPreviousEvent.eventId,
                                homeTeamPreviousEvent.date,
                                homeTeamPreviousEvent.roundInfo,
                                homeTeamPreviousEvent.tournament,
                                homeTeamPreviousEvent.tournamentName,
                                ((homeTeamPreviousEvent.homeScore!!.plus(homeTeamPreviousEvent.awayScore!!)).toDouble()),
                                (((homeTeamPreviousEvent.homeScores!!.period1!!).plus((homeTeamPreviousEvent.awayScores!!.period1!!))).toDouble()),
                                (((homeTeamPreviousEvent.homeScores.period2!!).plus((homeTeamPreviousEvent.awayScores.period2!!))).toDouble()),
                                (((homeTeamPreviousEvent.homeScore)).toDouble()),
                                (((homeTeamPreviousEvent.homeScores.period1))?.toDouble()),
                                (((homeTeamPreviousEvent.homeScores.period2)).toDouble()),
                                (((homeTeamPreviousEvent.awayScore)).toDouble()),
                                (((homeTeamPreviousEvent.awayScores.period1))?.toDouble()),
                                (((homeTeamPreviousEvent.awayScores.period2)).toDouble())
                            ).toTeamStatsEntity(
                                homeTeamPreviousEvent.homeTeamName,
                                homeTeamPreviousEvent.homeTeamId,
                                homeTeamPreviousEvent.awayTeamName,
                                homeTeamPreviousEvent.awayTeamId,
                                homeTeamPreviousEvent.startTimestamp
                            )
                            eventStatsDao.insertThisEventStats(thisEventStatsEntity)
                        }
                        emit(Resource.Success("$homeTeamName past event statistics ${index.plus(1)} is successfully loaded into database"))
                    }
                }

                if (pastAwayTeamEvents.isNotEmpty()) {
                    previousAwayTeamEvents.forEachIndexed { index, awayTeamPreviousEvent ->
                        val thisEventId = awayTeamPreviousEvent.eventId ?: Constants.nullInteger
                        val thisAwayTeamPastEventStatExists =
                            awayTeamEventsStatsList?.any { eventStatsEntity ->
                                (eventStatsEntity.mainTeamId == awayTeamId) && (eventStatsEntity.eventId == thisEventId)
                            } ?: false

                        if (!thisAwayTeamPastEventStatExists) {
                            val thisEventStats =
                                sportsPredictionApi.getEventStats(key, host, thisEventId)
                                    .toEventStats()
                            val thisEventStatsEntity = thisEventStats.toTeamStats(
                                awayTeamPreviousEvent.mainTeamName,
                                awayTeamPreviousEvent.mainTeamId,
                                awayTeamPreviousEvent.mainTeamPlayingLocation,
                                awayTeamPreviousEvent.eventId,
                                awayTeamPreviousEvent.date,
                                awayTeamPreviousEvent.roundInfo,
                                awayTeamPreviousEvent.tournament,
                                awayTeamPreviousEvent.tournamentName,
                                ((awayTeamPreviousEvent.homeScore!!.plus(awayTeamPreviousEvent.awayScore!!)).toDouble()),
                                (((awayTeamPreviousEvent.homeScores!!.period1!!).plus((awayTeamPreviousEvent.awayScores!!.period1!!))).toDouble()),
                                (((awayTeamPreviousEvent.homeScores.period2!!).plus((awayTeamPreviousEvent.awayScores.period2!!))).toDouble()),
                                (((awayTeamPreviousEvent.homeScore)).toDouble()),
                                (((awayTeamPreviousEvent.homeScores.period1))?.toDouble()),
                                (((awayTeamPreviousEvent.homeScores.period2)).toDouble()),
                                (((awayTeamPreviousEvent.awayScore)).toDouble()),
                                (((awayTeamPreviousEvent.awayScores.period1))?.toDouble()),
                                (((awayTeamPreviousEvent.awayScores.period2)).toDouble())
                            ).toTeamStatsEntity(
                                awayTeamPreviousEvent.homeTeamName,
                                awayTeamPreviousEvent.homeTeamId,
                                awayTeamPreviousEvent.awayTeamName,
                                awayTeamPreviousEvent.awayTeamId,
                                awayTeamPreviousEvent.startTimestamp
                            )
                            eventStatsDao.insertThisEventStats(thisEventStatsEntity)

                        }
                        emit(Resource.Success("$awayTeamName past event statistics ${index.plus(1)} is successfully loaded into database"))

                    }
                }

            }catch (e: HttpException){
                emit(Resource.Error(
                    message = "Something went wrong",
                    data = "Couldn't fetch data from network")
                )
            }catch (e: IOException){
                emit(Resource.Error(
                    message = "Couldn't connect to the server",
                    data = "Please check your internet connection")
                )
            }

        }

        emit(Resource.Success("Loading complete"))

    }

}