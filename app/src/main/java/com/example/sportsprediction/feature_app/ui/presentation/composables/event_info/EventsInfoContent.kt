package com.example.sportsprediction.feature_app.ui.presentation.composables.event_info

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.sportsprediction.R
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
import com.example.sportsprediction.core.util.Functions.longDateFormatter
import com.example.sportsprediction.core.util.Functions.shortDateFormatter
import com.example.sportsprediction.feature_app.data.local.entities.event_odds.EventOddsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.BasicScreenColumn
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.LoadStatsButton
import com.example.sportsprediction.feature_app.ui.presentation.composables.components.TeamPastEventText
import com.example.sportsprediction.feature_app.ui.theme.LocalSpacing
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private var odds1: String = emptyString
private var oddsX: String = emptyString
private var odds2: String = emptyString

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
    loadHomeTeamStats: (mainTeamId: Int, headToHeadId: String, eventId: Int, date: Date, numberOfPastEvents: Int, numberOfHeadToHeadEvents: Int) -> Unit,
    loadAwayTeamStats: (mainTeamId: Int, headToHeadId: String, eventId: Int, date: Date, numberOfPastEvents: Int, numberOfHeadToHeadEvents: Int) -> Unit,
    navigateToPredictionsScreen: (mainTeamName: String, mainTeamId: String, opponentTeamName: String, opponentTeamId: String, mainTeamPlayingLocation: String, headToHeadId: String, eventId: String, tournamentInfo: String) -> Unit,
    homeTeamStatsHaveBeenLoaded: Boolean,
    awayTeamStatsHaveBeenLoaded: Boolean,
    isLoadingHomeTeamStats: Boolean,
    isLoadingAwayTeamStats: Boolean,
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


    eventOdds.forEach{eventOddsEntity ->
        if ((eventOddsEntity.marketName?.lowercase(Locale.ROOT) ?: emptyString) == Full_Time.lowercase(Locale.ROOT)){
            fullTimeOdds = eventOddsEntity
            val fullTimeChoices = fullTimeOdds.choices ?: emptyList()
            fullTimeChoices.forEach {choice->
                if (choice.name == "1"){odds1 = "${choice.fractionalValue}"}
                if (choice.name == "X"){oddsX = "${choice.fractionalValue}"}
                if (choice.name == "2"){odds2 = "${choice.fractionalValue}"}
            }
        }
        else {
            fullTimeOdds = EventOddsEntity(null, Date(), 0, 0, 0, emptyString, null, emptyString, false, isSuspended = false, sourceId = 0)
            val fullTimeChoices = fullTimeOdds.choices ?: emptyList()
            fullTimeChoices.forEach {choice->
                if (choice.name == "1"){odds1 = "${choice.fractionalValue}"}
                if (choice.name == "X"){oddsX = "${choice.fractionalValue}"}
                if (choice.name == "2"){odds2 = "${choice.fractionalValue}"}
            }
        }
    }


    val timeStamp = preferredEvent.startTimestamp?.toLong() ?: 1693587600
    val timeStampAsDateTimeString = DateTimeFormatter.ISO_INSTANT.format(timeStamp.let { Instant.ofEpochSecond(it) })

    val thisLocalDate = LocalDate.parse(timeStampAsDateTimeString, longDateFormatter)
    val time = LocalTime.parse(timeStampAsDateTimeString, longDateFormatter)
    val day = thisLocalDate.dayOfWeek.toString().lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val dateToLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val dateString = shortDateFormatter.format(dateToLocalDate)

    val roundName = preferredEvent.roundInfo?.name ?: emptyString
    val roundNumber = preferredEvent.roundInfo?.round ?: nullInteger
    val roundInfo = if (roundName.isEmpty() && roundNumber == nullInteger){ emptyString }
    else if (roundName.isNotEmpty() && roundNumber == nullInteger){", $roundName"}
    else if (roundName.isEmpty() && roundNumber != nullInteger){", Round $roundNumber"}
    else {", $roundName, Round $roundNumber"}

    val tournamentName = if (preferredEvent.tournamentName.isNullOrBlank()) emptyString else {", ${preferredEvent.tournamentName}"}
    val theCountry = if(preferredEvent.tournament?.categoryName.isNullOrBlank()) emptyString else {"${preferredEvent.tournament?.categoryName}"}

    val tournamentInfo = "$theCountry$tournamentName$roundInfo"

    // Scrollable Column
    BasicScreenColumn {
        EventTeamsCard(
            day = day,
            date = dateString,
            time = "$time",
            homeTeam = homeTeam,
            awayTeam = awayTeam
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = LocalSpacing.current.small),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.padding(start = LocalSpacing.current.default),
                contentAlignment = Alignment.CenterStart
            ){
                Icon(painter = painterResource(id = R.drawable.football),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = emptyString
                )
            }
            Box(modifier = Modifier.padding(LocalSpacing.current.small),
                contentAlignment = Alignment.CenterStart){
                Text(
                    text = tournamentInfo,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        EventOddsCard(
            odds1 = odds1,
            oddsX = oddsX,
            odds2 = odds2
        )

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        FormGuideCard(
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            homeFormPercentage = homeFormPercentage,
            awayFormPercentage = awayFormPercentage,
            homeTeamEvents = homeTeamNameEvents,
            awayTeamEvents = awayTeamNameEvents
        )

        Divider(color = MaterialTheme.colorScheme.onBackground,
            thickness = LocalSpacing.current.thinBorder,
            modifier = Modifier.padding(top = LocalSpacing.current.medium))

        // Default Team Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = LocalSpacing.current.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TeamPastEventText(text = "$homeTeam's Past Matches")
        }

        // Default Team Previous Events
        PreviousEventCard(
            isLoading = loadingHomeTeamEvents,
            tournamentName = preferredEvent.tournamentName ?: emptyString,
            teamNameEvents = homeTeamNameEvents
        )

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))

        Box(modifier = Modifier.padding(LocalSpacing.current.medium),
            contentAlignment = Alignment.Center
        ) {
            LoadStatsButton(
                onClick = {
                    if (!homeTeamStatsHaveBeenLoaded) {
                        loadHomeTeamStats(
                            homeTeamId.toInt(),
                            headToHeadId,
                            eventId.toInt(),
                            date,
                            numberOfPastEvents,
                            numberOfPastHeadToHeadEvents
                        )
                    } else {
                        navigateToPredictionsScreen(
                            homeTeam,
                            homeTeamId,
                            awayTeam,
                            awayTeamId,
                            Home,
                            headToHeadId,
                            eventId,
                            tournamentInfo
                        )
                    }
                },
                isLoadingStats = isLoadingHomeTeamStats,
                buttonName = if (homeTeamStatsHaveBeenLoaded) "Get $homeTeam suggestions" else "Load $homeTeam stats"
            )
        }

        Divider(
            modifier = Modifier.padding(top = LocalSpacing.current.medium),
            color = MaterialTheme.colorScheme.onSurface,
            thickness = LocalSpacing.current.thinBorder
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
            isLoading = loadingAwayTeamEvents,
            tournamentName = preferredEvent.tournamentName ?: emptyString,
            teamNameEvents = awayTeamNameEvents
        )

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))

        Box(modifier = Modifier.padding(LocalSpacing.current.medium),
            contentAlignment = Alignment.Center
        ) {
            LoadStatsButton(
                onClick = {
                    if (!awayTeamStatsHaveBeenLoaded) {
                        loadAwayTeamStats(
                            awayTeamId.toInt(),
                            headToHeadId,
                            eventId.toInt(),
                            date,
                            numberOfPastEvents,
                            numberOfPastHeadToHeadEvents
                        )
                    } else {
                        navigateToPredictionsScreen(
                            awayTeam,
                            awayTeamId,
                            homeTeam,
                            homeTeamId,
                            Away,
                            headToHeadId,
                            eventId,
                            tournamentInfo
                        )
                    }
                },
                isLoadingStats = isLoadingAwayTeamStats,
                buttonName = if (awayTeamStatsHaveBeenLoaded) "Get $awayTeam suggestions" else "Load $awayTeam stats"
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
        HeadToHeadCard(teamNameEvents = headToHeadEvents, loadingHomeTeamEvents)

    }




}