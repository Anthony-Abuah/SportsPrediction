package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.sportsprediction.core.util.Constants
import com.example.sportsprediction.core.util.Constants.Away
import com.example.sportsprediction.core.util.Constants.Default_Past_Events_Value
import com.example.sportsprediction.core.util.Constants.Default_Past_Head_To_Head_Events_Value
import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.HeadToHead
import com.example.sportsprediction.core.util.Constants.Home
import com.example.sportsprediction.core.util.Constants.NumberOfHeadToHeadEvents
import com.example.sportsprediction.core.util.Constants.NumberOfPastEvents
import com.example.sportsprediction.core.util.Constants.SportsPredictionPreferences
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicButton
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicScreenColumn
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TeamPastEventText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import com.example.sportsprediction.feature_app.ui.theme.MainBackgroundColor
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun EventsInfoContent(
    homeTeam: String,
    homeTeamId: String,
    awayTeam: String,
    awayTeamId: String,
    homeFormPercentage: Double,
    awayFormPercentage: Double,
    eventId: String,
    headToHeadId: String,
    date: Date,
    preferredEvent: EventsEntity,
    getTeamStats: (mainTeamId: Int, headToHeadId: String, eventId: Int, date: Date, numberOfPastEvents: Int, numberOfHeadToHeadEvents: Int) -> Unit,
    navigateToPredictionsScreen: (mainTeamName: String, mainTeamId: String, opponentTeamName: String, opponentTeamId: String, mainTeamPlayingLocation: String, headToHeadId: String, eventId: String, tournamentInfo: String) -> Unit,
    loadingHomeTeamEvents: Boolean,
    homeTeamNameEvents: List<TeamEventEntity>,
    loadingAwayTeamEvents: Boolean,
    awayTeamNameEvents: List<TeamEventEntity>,
    headToHeadEvents: List<TeamEventEntity>,
    eventOdds: List<EventOddsEntity>
) {

    var fullTimeOdds = EventOddsEntity(null, Date(), nullInteger, nullInteger, nullInteger, emptyString, null, emptyString, false, false, nullInteger)


    val context = LocalContext.current
    val sportPredictionPreferences = context.getSharedPreferences(SportsPredictionPreferences, Context.MODE_PRIVATE)

    val numberOfPastEvents = sportPredictionPreferences.getString(NumberOfPastEvents, "$Default_Past_Events_Value")?.toInt() ?: Default_Past_Events_Value
    val numberOfPastHeadToHeadEvents = sportPredictionPreferences.getString(NumberOfHeadToHeadEvents, "$Default_Past_Head_To_Head_Events_Value")?.toInt() ?: Default_Past_Head_To_Head_Events_Value


    val theAwayTeamNameEvents = awayTeamNameEvents.take(6)
    val theHomeTeamNameEvents = homeTeamNameEvents.take(6)


    var odds1: String = emptyString
    var oddsX: String = emptyString
    var odds2: String = emptyString

    eventOdds.forEach{eventOddsEntity ->
        if ((eventOddsEntity.marketName?.lowercase(Locale.ROOT) ?: emptyString) == Full_Time.lowercase(Locale.ROOT)
        ){
            fullTimeOdds = eventOddsEntity
            for (choice in fullTimeOdds.choices ?: emptyList()) {
                if (choice.name == "1"){odds1 = "${choice.fractionalValue}"}
                if (choice.name == "X"){oddsX = "${choice.fractionalValue}"}
                if (choice.name == "2"){odds2 = "${choice.fractionalValue}"}
            }
        }
    }


    val longFormat = DateTimeFormatter.ofPattern(Constants.longDateFormat)
    val timeStamp = preferredEvent.startTimestamp?.toLong() ?: 1693587600
    val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp.let { Instant.ofEpochSecond(it) })

    val thisLocalDate = LocalDate.parse(timeStampAsDateTimeString, longFormat)
    val time = LocalTime.parse(timeStampAsDateTimeString, longFormat)
    val day = thisLocalDate.dayOfWeek.toString().lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val roundName = preferredEvent.roundInfo?.name ?: emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == nullInteger){ emptyString }
    else if (roundName.isNotEmpty() && roundNumber == nullInteger){", $roundName"}
    else if (roundName.isEmpty() && roundNumber != nullInteger){", Round $roundNumber"}
    else {", $roundName, Round $roundNumber"}

    val sport = if(preferredEvent.tournament?.sport.isNullOrBlank()){emptyString} else  preferredEvent.tournament?.sport
    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournamentName}"}
    val country = if(preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournament?.categoryName}"}
    val theCountry = if(preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {"${preferredEvent.tournament?.categoryName}"}

    val tournamentInfo = "$theCountry$tournamentName$roundInfo"

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        // Scrollable Column
        BasicScreenColumn {
            EventTeamsCard(
                date = "$day, $date",
                time = "$time",
                competition = "$sport$country$tournamentName$roundInfo",
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            EventOddsCard(
                odds1 = odds1,
                oddsX = oddsX,
                odds2 = odds2,
                contentColor = Color.Black,
                backgroundColor = MainBackgroundColor
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            FormGuideCard(homeTeam, awayTeam, homeFormPercentage, awayFormPercentage, homeTeamNameEvents, awayTeamNameEvents)

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            Column(modifier = Modifier.background(MainBackgroundColor)) {

                // Default Team Name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = LocalSpacing.current.default),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TeamPastEventText(text = "$homeTeam's Past Matches")
                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.default))

                // Default Team Previous Events
                PreviousEventCard(
                    teamNameEvents = theHomeTeamNameEvents,
                    backgroundColor = Color.White,
                    isLoading = loadingHomeTeamEvents
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(
                    start = LocalSpacing.current.smallMedium,
                    end = LocalSpacing.current.smallMedium,
                    top = LocalSpacing.current.small,
                    bottom = LocalSpacing.current.large),
                    contentAlignment = Alignment.Center) {
                    BasicButton(
                        onClick = {
                            getTeamStats(homeTeamId.toInt(), headToHeadId, eventId.toInt(), date,numberOfPastEvents, numberOfPastHeadToHeadEvents)
                            navigateToPredictionsScreen(homeTeam, homeTeamId, awayTeam, awayTeamId, Home, headToHeadId, eventId, tournamentInfo)
                        },
                        buttonName = "Load $homeTeam stats"
                    )
                }

                Divider(
                    modifier = Modifier.padding(top = LocalSpacing.current.medium),
                    color = Color.LightGray,
                    thickness = LocalSpacing.current.borderStroke
                )



                // Away Team Name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.default),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TeamPastEventText(text = "$awayTeam's Past Matches")
                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.default))

                // Away Team Previous Events
                PreviousEventCard(
                    teamNameEvents = theAwayTeamNameEvents,
                    backgroundColor = Color.White,
                    isLoading = loadingAwayTeamEvents
                )

                Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                Box(modifier = Modifier.padding(start = LocalSpacing.current.smallMedium,
                    end = LocalSpacing.current.smallMedium,
                    top = LocalSpacing.current.small,
                    bottom = LocalSpacing.current.large),
                    contentAlignment = Alignment.Center) {
                    BasicButton(
                        onClick = {
                            getTeamStats(awayTeamId.toInt(), headToHeadId, eventId.toInt(), date, numberOfPastEvents, numberOfPastHeadToHeadEvents)
                            navigateToPredictionsScreen(awayTeam, awayTeamId, homeTeam, homeTeamId, Away, headToHeadId, eventId, tournamentInfo)
                        },
                        buttonName = "Load $awayTeam stats"
                    )
                }

                Divider(
                    modifier = Modifier.padding(
                        top = LocalSpacing.current.medium,
                    ),
                    color = Color.LightGray,
                    thickness = LocalSpacing.current.borderStroke
                )

                // Head to Head
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(LocalSpacing.current.default),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TeamPastEventText(text = HeadToHead)
                }

                Spacer(modifier = Modifier.height(LocalSpacing.current.default))

                // Previous Head to Head Events
                HeadToHeadCard(teamNameEvents = headToHeadEvents.take(5))

            }

        }

    }


}