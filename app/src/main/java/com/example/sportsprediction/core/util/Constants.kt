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


    const val ShowMore = "Show more"
    const val ShowLess = "Show less"
    const val ThisTournament = "This Tournament"


    const val Remarks = "Remarks"
    const val Form = "Form"
    const val Time = "Time"
    const val Leagues = "Leagues"
    const val SortOrder = "Sort Order"

    const val MainTeam = "Main Team"
    const val NextOpponent = "Next Opponent"
    const val PlayingLocation = "Next Match Location"
    const val TournamentInfo = "Tournament Info"
    const val TeamInformation = "Team Information"

    const val Bet_Suggestions = "Bet Suggestions"
    const val Remove_All = "Remove All"


    const val Both_Teams = "Both teams"

    const val NoValue_Double = 0.0
    const val emptyString = ""
    const val OK = "OK"
    const val CANCEL = "Cancel"
    const val Select_Date = "Select Date"

    const val web_client_Id = "329299843179-p2oq7ljp8uu0qugcgreduhll342e6o62.apps.googleusercontent.com"

    const val UnableToSignIn = "Unable to sign in"
    const val UnableToRegister = "Unable to register"
    const val UnknownError = "Unknown Error"
    const val LoadingCompleted = "Loading completed"
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
    const val In_1_Hour = "In 1 hour"
    const val In_2_Hours = "In 2 hours"
    const val In_3_Hours = "In 3 hours"
    const val In_6_Hours = "In 6 hours"
    const val In_12_Hours = "In 12 hours"
    const val Today = "Today"
    const val Yesterday = "Yesterday"
    const val Tomorrow = "Tomorrow"
    const val AFewDaysAgo = "A Few Days Ago"
    const val InAFewDays = "In A Few Days"

    const val popular = "Popular"
    const val ascendingTime = "Ascending Start Time"
    const val descendingTime = "Descending Start Time"

    const val Countries = "Countries"
    const val SelectLeagues = "Select Leagues"
    const val UnknownTournament = "Unknown Tournament"
    const val UnknownCountry = "Unknown Country"

    const val Reset = "Reset"
    const val Accept = "Accept"

    const val NothingToShow = "Nothing to show"



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
    val SuggestionGroupingList = listOf(Market_Category, Market_Type, Match_Period, Teams)
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


    const val Order_Suggestions_By = "Order suggestions by"
    const val GroupSuggestionBy = "Group suggestions by"
    const val Percentage_Filter = "Suggestion percentage(%)"
    const val Select_Percentage_Filter = "Default suggestion percentage(%)"
    const val Past_Events = "Past Events"
    const val Select_Past_Events = "Number of past events to select for computations"
    const val Default_Past_Events_Value = 6
    const val Past_Head_To_Head_Events = "Head to head events"
    const val Select_Past_Head_To_Head_Events = "Number of past head to head events to select for computations"
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

    val countries = listOf("Afghanistan",
        "Albania",
        "Algeria",
        "American Samoa",
        "Andorra",
        "Angola",
        "Anguilla",
        "Antarctica",
        "Antigua and Barbuda",
        "Argentina",
        "Armenia",
        "Aruba",
        "Australia",
        "Austria",
        "Azerbaijan",
        "Bahamas",
        "Bahrain",
        "Bangladesh",
        "Barbados",
        "Belarus",
        "Belgium",
        "Belize",
        "Benin",
        "Bermuda",
        "Bhutan",
        "Bolivia",
        "Bosnia and Herzegovina",
        "Botswana",
        "Brazil",
        "British Indian Ocean Territory",
        "British Virgin Islands",
        "Brunei",
        "Bulgaria",
        "Burkina Faso",
        "Burundi",
        "Cambodia",
        "Cameroon",
        "Canada",
        "Cape Verde",
        "Cayman Islands",
        "Central African Republic",
        "Chad",
        "Chile",
        "China",
        "Christmas Island",
        "Cocos Islands",
        "Colombia",
        "Comoros",
        "Cook Islands",
        "Costa Rica",
        "Croatia",
        "Cuba",
        "Curacao",
        "Cyprus",
        "Czech Republic",
        "Democratic Republic of the Congo",
        "Denmark",
        "Djibouti",
        "Dominica",
        "Dominican Republic",
        "East Timor",
        "Ecuador",
        "Egypt",
        "El Salvador",
        "Equatorial Guinea",
        "Eritrea",
        "Estonia",
        "Ethiopia",
        "Falkland Islands",
        "Faroe Islands",
        "Fiji",
        "Finland",
        "France",
        "French Polynesia",
        "Gabon",
        "Gambia",
        "Georgia",
        "Germany",
        "Ghana",
        "Gibraltar",
        "Greece",
        "Greenland",
        "Grenada",
        "Guam",
        "Guatemala",
        "Guernsey",
        "Guinea",
        "Guinea-Bissau",
        "Guyana",
        "Haiti",
        "Honduras",
        "Hong Kong",
        "Hungary",
        "Iceland",
        "India",
        "Indonesia",
        "Iran",
        "Iraq",
        "Ireland",
        "Isle of Man",
        "Israel",
        "Italy",
        "Ivory Coast",
        "Jamaica",
        "Japan",
        "Jersey",
        "Jordan",
        "Kazakhstan",
        "Kenya",
        "Kiribati",
        "Kosovo",
        "Kuwait",
        "Kyrgyzstan",
        "Laos",
        "Latvia",
        "Lebanon",
        "Lesotho",
        "Liberia",
        "Libya",
        "Liechtenstein",
        "Lithuania",
        "Luxembourg",
        "Macau",
        "Macedonia",
        "Madagascar",
        "Malawi",
        "Malaysia",
        "Maldives",
        "Mali",
        "Malta",
        "Marshall Islands",
        "Mauritania",
        "Mauritius",
        "Mayotte",
        "Mexico",
        "Micronesia",
        "Moldova",
        "Monaco",
        "Mongolia",
        "Montenegro",
        "Montserrat",
        "Morocco",
        "Mozambique",
        "Myanmar",
        "Namibia",
        "Nauru",
        "Nepal",
        "Netherlands",
        "Netherlands Antilles",
        "New Caledonia",
        "New Zealand",
        "Nicaragua",
        "Niger",
        "Nigeria",
        "Niue",
        "North Korea",
        "Northern Mariana Islands",
        "Norway",
        "Oman",
        "Pakistan",
        "Palau",
        "Palestine",
        "Panama",
        "Papua New Guinea",
        "Paraguay",
        "Peru",
        "Philippines",
        "Pitcairn",
        "Poland",
        "Portugal",
        "Puerto Rico",
        "Qatar",
        "Republic of the Congo",
        "Reunion",
        "Romania",
        "Russia",
        "Rwanda",
        "Saint Barthelemy",
        "Saint Helena",
        "Saint Kitts and Nevis",
        "Saint Lucia",
        "Saint Martin",
        "Saint Pierre and Miquelon",
        "Saint Vincent and the Grenadines",
        "Samoa",
        "San Marino",
        "Sao Tome and Principe",
        "Saudi Arabia",
        "Senegal",
        "Serbia",
        "Seychelles",
        "Sierra Leone",
        "Singapore",
        "Sint Maarten",
        "Slovakia",
        "Slovenia",
        "Solomon Islands",
        "Somalia",
        "South Africa",
        "South Korea",
        "South Sudan",
        "Spain",
        "Sri Lanka",
        "Sudan",
        "Suriname",
        "Svalbard and Jan Mayen",
        "Swaziland",
        "Sweden",
        "Switzerland",
        "Syria",
        "Taiwan",
        "Tajikistan",
        "Tanzania",
        "Thailand",
        "Togo",
        "Tokelau",
        "Tonga",
        "Trinidad and Tobago",
        "Tunisia",
        "Turkey",
        "Turkmenistan",
        "Turks and Caicos Islands",
        "Tuvalu",
        "U.S. Virgin Islands",
        "Uganda",
        "Ukraine",
        "United Arab Emirates",
        "United Kingdom",
        "United States",
        "Uruguay",
        "Uzbekistan",
        "Vanuatu",
        "Vatican",
        "Venezuela",
        "Vietnam",
        "Wallis and Futuna",
        "Western Sahara",
        "Yemen",
        "Zambia",
        "Zimbabwe")
    val threeLetterCountryCode = listOf("AFG",
        "ALB",
        "DZA",
        "ASM",
        "AND",
        "AGO",
        "AIA",
        "ATA",
        "ATG",
        "ARG",
        "ARM",
        "ABW",
        "AUS",
        "AUT",
        "AZE",
        "BHS",
        "BHR",
        "BGD",
        "BRB",
        "BLY",
        "BEL",
        "BLZ",
        "BEN",
        "BMU",
        "BTN",
        "BOL",
        "BIH",
        "BWA",
        "BRA",
        "IOT",
        "VGB",
        "BRN",
        "BGR",
        "BFA",
        "BDI",
        "KHM",
        "CMR",
        "CAN",
        "CPV",
        "CYM",
        "CAF",
        "TCD",
        "CHL",
        "CHN",
        "CXR",
        "CCK",
        "COL",
        "COM",
        "COK",
        "CRI",
        "HRV",
        "CUB",
        "CUW",
        "CYP",
        "CZE",
        "COD",
        "DNK",
        "DJI",
        "DMA",
        "DOM",
        "TLS",
        "ECU",
        "EGY",
        "SLV",
        "GNQ",
        "ERI",
        "EST",
        "ETH",
        "FLK",
        "FRO",
        "FJI",
        "FIN",
        "FRA",
        "PYF",
        "GAB",
        "GMB",
        "GEO",
        "DEU",
        "GHA",
        "GIB",
        "GRC",
        "GRL",
        "GRD",
        "GUM",
        "GTM",
        "GGY",
        "GIN",
        "GNB",
        "GUY",
        "HTI",
        "HND",
        "HKG",
        "HUN",
        "ISL",
        "IND",
        "IDN",
        "IRN",
        "IRQ",
        "IRL",
        "IMN",
        "ISR",
        "ITA",
        "CIV",
        "JAM",
        "JPN",
        "JEY",
        "JOR",
        "KAZ",
        "KEN",
        "KIR",
        "XKX",
        "KWT",
        "KGZ",
        "LAO",
        "LVA",
        "LBN",
        "LSO",
        "LBR",
        "LBY",
        "LIE",
        "LTU",
        "LUX",
        "MAC",
        "MKD",
        "MDG",
        "MWI",
        "MYS",
        "MDV",
        "MLI",
        "MLT",
        "MHL",
        "MRT",
        "MUS",
        "MYT",
        "MEX",
        "FSM",
        "MDA",
        "MCO",
        "MNG",
        "MNE",
        "MSR",
        "MAR",
        "MOZ",
        "MMR",
        "NAM",
        "NRU",
        "NPL",
        "NLD",
        "ANT",
        "NCL",
        "NZL",
        "NIC",
        "NER",
        "NGA",
        "NIU",
        "PRK",
        "MNP",
        "NOR",
        "OMN",
        "PAK",
        "PLW",
        "PSE",
        "PAN",
        "PNG",
        "PRY",
        "PER",
        "PHL",
        "PCN",
        "POL",
        "PRT",
        "PRI",
        "QAT",
        "COG",
        "REU",
        "ROU",
        "RUS",
        "RWA",
        "BLM",
        "SHN",
        "KNA",
        "LCA",
        "MAF",
        "SPM",
        "VCT",
        "WSM",
        "SMR",
        "STP",
        "SAU",
        "SEN",
        "SRB",
        "SYC",
        "SLE",
        "SGP",
        "SXM",
        "SVK",
        "SVN",
        "SLB",
        "SOM",
        "ZAF",
        "KOR",
        "SSD",
        "ESP",
        "LKA",
        "SDN",
        "SUR",
        "SJM",
        "SWZ",
        "SWE",
        "CHE",
        "SYR",
        "TWN",
        "TJK",
        "TZA",
        "THA",
        "TGO",
        "TKL",
        "TON",
        "TTO",
        "TUN",
        "TUR",
        "TKM",
        "TCA",
        "TUV",
        "VIR",
        "UGA",
        "UKR",
        "ARE",
        "GBR",
        "USA",
        "URY",
        "UZB",
        "VUT",
        "VAT",
        "VEN",
        "VNM",
        "WLF",
        "ESH",
        "YEM",
        "ZMB",
        "ZWE")
    val twoLetterCountryCode = listOf("AF",
        "AL",
        "DZ",
        "AS",
        "AD",
        "AO",
        "AI",
        "AQ",
        "AG",
        "AR",
        "AM",
        "AW",
        "AU",
        "AT",
        "AZ",
        "BS",
        "BH",
        "BD",
        "BB",
        "BY",
        "BE",
        "BZ",
        "BJ",
        "BM",
        "BT",
        "BO",
        "BA",
        "BW",
        "BR",
        "IO",
        "VG",
        "BN",
        "BG",
        "BF",
        "BI",
        "KH",
        "CM",
        "CA",
        "CV",
        "KY",
        "CF",
        "TD",
        "CL",
        "CN",
        "CX",
        "CC",
        "CO",
        "KM",
        "CK",
        "CR",
        "HR",
        "CU",
        "CW",
        "CY",
        "CZ",
        "CD",
        "DK",
        "DJ",
        "DM",
        "DO",
        "TL",
        "EC",
        "EG",
        "SV",
        "GQ",
        "ER",
        "EE",
        "ET",
        "FK",
        "FO",
        "FJ",
        "FI",
        "FR",
        "PF",
        "GA",
        "GM",
        "GE",
        "DE",
        "GH",
        "GI",
        "GR",
        "GL",
        "GD",
        "GU",
        "GT",
        "GG",
        "GN",
        "GW",
        "GY",
        "HT",
        "HN",
        "HK",
        "HU",
        "IS",
        "IN",
        "ID",
        "IR",
        "IQ",
        "IE",
        "IM",
        "IL",
        "IT",
        "CI",
        "JM",
        "JP",
        "JE",
        "JO",
        "KZ",
        "KE",
        "KI",
        "XK",
        "KW",
        "KG",
        "LA",
        "LV",
        "LB",
        "LS",
        "LR",
        "LY",
        "LI",
        "LT",
        "LU",
        "MO",
        "MK",
        "MG",
        "MW",
        "MY",
        "MV",
        "ML",
        "MT",
        "MH",
        "MR",
        "MU",
        "YT",
        "MX",
        "FM",
        "MD",
        "MC",
        "MN",
        "ME",
        "MS",
        "MA",
        "MZ",
        "MM",
        "NA",
        "NR",
        "NP",
        "NL",
        "AN",
        "NC",
        "NZ",
        "NI",
        "NE",
        "NG",
        "NU",
        "KP",
        "MP",
        "NO",
        "OM",
        "PK",
        "PW",
        "PS",
        "PA",
        "PG",
        "PY",
        "PE",
        "PH",
        "PN",
        "PL",
        "PT",
        "PR",
        "QA",
        "CG",
        "RE",
        "RO",
        "RU",
        "RW",
        "BL",
        "SH",
        "KN",
        "LC",
        "MF",
        "PM",
        "VC",
        "WS",
        "SM",
        "ST",
        "SA",
        "SN",
        "RS",
        "SC",
        "SL",
        "SG",
        "SX",
        "SK",
        "SI",
        "SB",
        "SO",
        "ZA",
        "KR",
        "SS",
        "ES",
        "LK",
        "SD",
        "SR",
        "SJ",
        "SZ",
        "SE",
        "CH",
        "SY",
        "TW",
        "TJ",
        "TZ",
        "TH",
        "TG",
        "TK",
        "TO",
        "TT",
        "TN",
        "TR",
        "TM",
        "TC",
        "TV",
        "VI",
        "UG",
        "UA",
        "AE",
        "GB",
        "US",
        "UY",
        "UZ",
        "VU",
        "VA",
        "VE",
        "VN",
        "WF",
        "EH",
        "YE",
        "ZM",
        "ZW")
    val countryCallCode = listOf("93",
        "355",
        "213",
        "1-684",
        "376",
        "244",
        "1-264",
        "672",
        "1-268",
        "54",
        "374",
        "297",
        "61",
        "43",
        "994",
        "1-242",
        "973",
        "880",
        "1-246",
        "375",
        "32",
        "501",
        "229",
        "1-441",
        "975",
        "591",
        "387",
        "267",
        "55",
        "246",
        "1-284",
        "673",
        "359",
        "226",
        "257",
        "855",
        "237",
        "1",
        "238",
        "1-345",
        "236",
        "235",
        "56",
        "86",
        "61",
        "61",
        "57",
        "269",
        "682",
        "506",
        "385",
        "53",
        "599",
        "357",
        "420",
        "243",
        "45",
        "253",
        "1-767",
        "1-809",
        "670",
        "593",
        "20",
        "503",
        "240",
        "291",
        "372",
        "251",
        "500",
        "298",
        "679",
        "358",
        "33",
        "689",
        "241",
        "220",
        "995",
        "49",
        "233",
        "350",
        "30",
        "299",
        "1-473",
        "1-671",
        "502",
        "44-1481",
        "224",
        "245",
        "592",
        "509",
        "504",
        "852",
        "36",
        "354",
        "91",
        "62",
        "98",
        "964",
        "353",
        "44-1624",
        "972",
        "39",
        "225",
        "1-876",
        "81",
        "44-1534",
        "962",
        "7",
        "254",
        "686",
        "383",
        "965",
        "996",
        "856",
        "371",
        "961",
        "266",
        "231",
        "218",
        "423",
        "370",
        "352",
        "853",
        "389",
        "261",
        "265",
        "60",
        "960",
        "223",
        "356",
        "692",
        "222",
        "230",
        "262",
        "52",
        "691",
        "373",
        "377",
        "976",
        "382",
        "1-664",
        "212",
        "258",
        "95",
        "264",
        "674",
        "977",
        "31",
        "599",
        "687",
        "64",
        "505",
        "227",
        "234",
        "683",
        "850",
        "1-670",
        "47",
        "968",
        "92",
        "680",
        "970",
        "507",
        "675",
        "595",
        "51",
        "63",
        "64",
        "48",
        "351",
        "1-787",
        "974",
        "242",
        "262",
        "40",
        "7",
        "250",
        "590",
        "290",
        "1-869",
        "1-758",
        "590",
        "508",
        "1-784",
        "685",
        "378",
        "239",
        "966",
        "221",
        "381",
        "248",
        "232",
        "65",
        "1-721",
        "421",
        "386",
        "677",
        "252",
        "27",
        "82",
        "211",
        "34",
        "94",
        "249",
        "597",
        "47",
        "268",
        "46",
        "41",
        "963",
        "886",
        "992",
        "255",
        "66",
        "228",
        "690",
        "676",
        "1-868",
        "216",
        "90",
        "993",
        "1-649",
        "688",
        "1-340",
        "256",
        "380",
        "971",
        "44",
        "1",
        "598",
        "998",
        "678",
        "379",
        "58",
        "84",
        "681",
        "212",
        "967",
        "260",
        "263")

}
