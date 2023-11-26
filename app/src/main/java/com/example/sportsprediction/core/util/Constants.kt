package com.example.sportsprediction.core.util

import com.example.sportsprediction.core.util.SuggestionVariables.BothTeams
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Both_Halves_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Corners_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Corners_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.First_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Full_Time_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Main_Team
import com.example.sportsprediction.core.util.SuggestionVariables.Opponent
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Goals_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Multigoals
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Offsides_Unders
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Both_Teams_To_Score
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Corner_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Double_Chance
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Goals_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Handicap
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_Clean_Sheets
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Main_Team_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Match_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_MultiGoals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Offsides_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Second_Half_Yellow_Cards_Totals
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Overs
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Result
import com.example.sportsprediction.core.util.SuggestionVariables.Yellow_Cards_Unders


object Constants {


    const val BASE_URL = "https://sofascores.p.rapidapi.com/"

    const val Header_Key = "X-RapidAPI-Key"
    const val Header_Host = "X-RapidAPI-Host"

    const val oddsFormat = "decimal"
    const val providerId = 1

    const val HttpExceptionErrorMessage = "Something went wrong"
    const val IO_ExceptionErrorMessage = "Couldn't connect to the server"

    const val SportsPredictionLocalDatabase = "appDatabase"

    const val key = "b5f909ac68msh753238fb0dac23dp15e922jsn9451f6c2f052"
    const val host = "sofasport.p.rapidapi.com"
    const val key1 = "c16e0692c7msh75c00be5d1cac2fp135739jsn7937d0c4690f"
    const val host1 = "sofascores.p.rapidapi.com"

    const val page = 0
    const val course_event = "last"


    const val longDateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val shortDateFormat = "yyyy-MM-dd"

    const val Home = "home"
    const val Away = "away"
    const val Neutral = "away"
    const val WinValue = 1.0
    const val LoseValue = 0.0
    const val DrawValue = 0.5
    const val NotAvailable = "N/A"

    const val HeadToHead = "Head To Head"
    const val MatchInfo = "Match Info"
    const val FormGuide = "Form Guide"

    const val Both_Teams_To_Score = "Both teams to score"
    const val Double_Chance = "Double chance"
    const val Offsides = "Offsides"
    const val Handicap = "Handicap"
    const val Corner_Kicks = "Corner kicks"
    const val Total_Shots = "Total shots"
    const val Shots_On_Target = "Shots on target"
    const val Goalkeeper_Saves = "Goalkeeper saves"
    const val Yellow_Cards = "Yellow cards"
    const val Match_Result = "Match result"

    const val Match_Goals = "Match goals"
    const val Cards_In_Match = "Cards in match"
    const val Corners_2_Way = "Corners 2-Way"

    const val gambleResponsibly = "Gamble responsibly 18+"
    const val moreOdds = "View more odds"

    const val Full_Time = "Full time"
    const val first_Half = "1st half"
    const val First_Half = "First half"
    const val second_Half = "2nd half"
    const val Second_Half = "Second half"

    const val FullTime = "ALL"
    const val FirstHalf = "1ST"
    const val SecondHalf = "2ND"

    const val DoesNotExist = -1
    const val nullInteger = -10
    const val nullDouble: Double = -10.0

    const val ANIMATION_TIME:Long = 3000
    const val DIALOG_BUILD_TIME:Long = 3000

    const val Football = 1


    const val NoValue_Double = 0.0
    const val emptyString = ""
    const val OK = "OK"
    const val CANCEL = "Cancel"
    const val Select_Date = "Select Date"

    const val Algorithm_Info = "Our App"
    const val No_Suggestions = "No suggestions meet this criteria"
    const val No_Stats = "No stats to show"



    const val User_Entity = "UserTable"
    const val Tipster_Entity = "TipsterTable"
    const val Date_Event_Entity = "DateEventTable"
    const val Event_Odds_Entity = "EventOddsTable"
    const val Events_Entity = "EventsTable"
    const val Team_Events_Entity = "TeamEventsTable"
    const val Team_Event_Entity = "TeamEventTable"
    const val Event_Stats_Entity = "EventStatsTable"
    const val Team_Suggestions_Entity = "TeamSuggestionsTable"

    const val Predictions = "Predictions"
    const val Soccer = "Football"
    const val Matches = " Matches"

    const val NumberOfMatches = "NumberOfMatches"
    const val Number_Of_Matches = "Number of matches"

    const val UserPreferences = "UserPreferences"

    const val MarketCategory = "marketCategory"
    const val MarketType = "marketType"
    const val MatchPeriod = "matchPeriod"
    const val Team = "Team"

    const val PercentageValue = 0.6
    const val NumberOfTeamEvents = 6
    const val NumberOfH2HEvents = 4

    const val All = "All"
    const val Ascending = "Ascending"
    const val Descending = "Descending"


    const val Male = "Male"
    const val Female = "Female"
    const val UnspecifiedGender = "Unspecified"

    const val NumberOfTeamEventsForEventStats = "NumberOfTeamEventsForEventStats"
    const val NumberOfH2HEventsForEventStats = "NumberOfH2HEventsForEventStats"
    const val PercentageFilterValue = "PercentageFilterValue"
    const val SuggestionsArrangementOrder = "SuggestionsArrangementOrder"
    const val DateEventsArrangementOrder = "DateEventsArrangementOrder"
    const val SuggestionsGrouping = "SuggestionsGrouping"

    const val Tournament = "Tournament"
    const val Percentage = "Percentage"
    const val Default_Category = "Default"
    const val Market_Category = "Market Category"
    const val Market_Type = "Market Type"
    const val Match_Period = "Match Period"
    const val Teams = "Teams"

    val ArrangementOrderList = listOf(Ascending, Descending)
    val SuggestionGroupingList = listOf(Market_Category, Market_Type, Match_Period, Team)
    val MarketCategoryList = listOf(
        All,
        Full_Time_Match_Result,
        First_Half_Match_Result,
        Second_Half_Match_Result,
        Full_Time_Corner_Result,
        First_Half_Corner_Result,
        Second_Half_Corner_Result,
        Full_Time_Offsides_Result,
        First_Half_Offsides_Result,
        Second_Half_Offsides_Result,
        Full_Time_Yellow_Cards_Result,
        First_Half_Yellow_Cards_Result,
        Second_Half_Yellow_Cards_Result,
        Full_Time_MultiGoals,
        First_Half_MultiGoals,
        Second_Half_MultiGoals,
        Full_Time_Main_Team_MultiGoals,
        First_Half_Main_Team_MultiGoals,
        Second_Half_Main_Team_MultiGoals,
        Full_Time_Main_Team_Clean_Sheets,
        First_Half_Main_Team_Clean_Sheets,
        Second_Half_Main_Team_Clean_Sheets,
        Full_Time_Both_Teams_To_Score,
        First_Half_Both_Teams_To_Score,
        Second_Half_Both_Teams_To_Score,
        Full_Time_Double_Chance,
        First_Half_Double_Chance,
        Second_Half_Double_Chance,
        Full_Time_Goals_Totals,
        First_Half_Goals_Totals,
        Second_Half_Goals_Totals,
        Full_Time_Corner_Totals,
        First_Half_Corner_Totals,
        Second_Half_Corner_Totals,
        Full_Time_Yellow_Cards_Totals,
        First_Half_Yellow_Cards_Totals,
        Second_Half_Yellow_Cards_Totals,
        Full_Time_Offsides_Totals,
        First_Half_Offsides_Totals,
        Second_Half_Offsides_Totals,
        Full_Time_Handicap,
        First_Half_Handicap,
        Second_Half_Handicap,
        Both_Halves_Totals
    )

    val MarketTypeList = listOf(
        All,
        Double_Chance,
        Match_Result,
        Corner_Result,
        Yellow_Cards_Result,
        Offsides_Result,
        Goals_Overs,
        Goals_Unders,
        Corners_Overs,
        Corners_Unders,
        Offsides_Overs,
        Offsides_Unders,
        Yellow_Cards_Overs,
        Both_Halves_Overs,
        Both_Halves_Unders,
        Yellow_Cards_Unders,
        Multigoals,
        Handicap,
        Clean_Sheets,
        Both_Teams_To_Score
    )
    val MatchPeriodList = listOf (All, Full_Time, First_Half, Second_Half)
    val TeamList = listOf (All, BothTeams, Main_Team, Opponent)
    val GenderList = listOf (UnspecifiedGender, Male, Female)
    val DaysOfTheMonthList = listOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", )
    val MonthOfTheYearList = listOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" )
    val YearList = listOf("1", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" )


    const val Suggestions_Arrangement_Order = "Order suggestions by"
    const val Suggestions_Grouping = "Group suggestions by"
    const val Percentage_Filter = "SuggestionVariables percentage should be above"
    const val Past_Events = "Select number of past matches to consider"
    const val Default_Past_Events_Value = 6
    const val Past_Head_To_Head_Events = "Select number of head to head matches to consider"
    const val Default_Past_Head_To_Head_Events_Value = 4

    const val ArrangementOrder = "ArrangementOrder"
    const val PercentageFilter = "PercentageFilter"
    const val NumberOfPastEvents = "NumberOfPastEvents"
    const val NumberOfHeadToHeadEvents = "NumberOfHeadToHeadEvents"
    const val SuggestionGroupOption = "SuggestionGroupOption"

    const val SportsPredictionPreferences = "SportsPredictionPreferences"

    const val SelectMarketCategory = "Select Market Category"


    fun getEventOddsChoiceName(marketName: String): List<String> {
        return when(marketName){
            Full_Time -> { listOf("1", "2") }
            Double_Chance -> { listOf("1X", "X2") }
            first_Half -> { listOf("1", "2") }
            Both_Teams_To_Score -> { listOf("Yes", "No") }
            Match_Goals -> { listOf("Over", "Under") }
            Cards_In_Match -> { listOf("Over", "Under") }
            Corners_2_Way -> { listOf("Over", "Under") }
            else -> listOf("","")
        }
    }



}
