package com.example.sportsprediction.feature_app.data.local


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.sportsprediction.core.util.*
import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.NoValue_Double
import com.example.sportsprediction.core.util.Constants.nullDouble
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.events.EventsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_event.TeamEventEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestion.TeamSuggestionsEntity
import com.example.sportsprediction.feature_app.data.local.entities.team_suggestions.Suggestion
import com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes.Rating
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.data_classes.UserSubscription
import com.example.sportsprediction.feature_app.data.util.JsonParser
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvent
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents
import com.example.sportsprediction.feature_app.domain.model.event_odds.Choice
import com.example.sportsprediction.feature_app.domain.model.general.*
import com.example.sportsprediction.feature_app.domain.model.h2h_events.HeadToHeadEvent
import com.example.sportsprediction.feature_app.domain.model.team_event.TeamEvent
import com.google.gson.reflect.TypeToken
import java.util.*


@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {

    // Date Events Converter
    @TypeConverter
    fun fromDateEventJson(dateEvent: String): DateEvent {
        return jsonParser.fromJson<DateEvent>(
            dateEvent,
            object : TypeToken<DateEvent>(){}.type)
            ?: DateEvent(nullInteger, emptyString, nullInteger, emptyString, nullInteger, emptyString, nullInteger, nullInteger, nullInteger, nullInteger, emptyString, emptyString, emptyString, nullInteger, emptyString, null, null, null, null, null, null)
    }

    @TypeConverter
    fun toDateEventJson(dateEvent: DateEvent): String{
        return jsonParser.toJson(
            dateEvent,
            object : TypeToken<DateEvent>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromDateEventsJson(dateEvents: String): DateEvents {
        return jsonParser.fromJson<DateEvents>(
            dateEvents,
            object : TypeToken<DateEvents>(){}.type) ?: DateEvents(emptyList())
    }

    @TypeConverter
    fun toDateEventsJson(dateEvents: DateEvents): String{
        return jsonParser.toJson(
            dateEvents,
            object : TypeToken<DateEvents>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromDateEventsEntityJson(dateEventsEntity: String): DateEventsEntity {
        return jsonParser.fromJson<DateEventsEntity>(
            dateEventsEntity,
            object : TypeToken<DateEvents>(){}.type) ?: DateEventsEntity(null, Date(), DateEvents(emptyList())
        )
    }

    @TypeConverter
    fun toDateEventsEntityJson(dateEventsEntity: DateEventsEntity): String{
        return jsonParser.toJson(
            dateEventsEntity,
            object : TypeToken<DateEventsEntity>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromListOfDateEventJson(dateEventList: String): List<DateEvent>{
        return jsonParser.fromJson<ArrayList<DateEvent>>(
            dateEventList,
            object : TypeToken<ArrayList<DateEvent>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toListOfDateEventJson(dateEventList: List<DateEvent>): String{
        return jsonParser.toJson(
            dateEventList,
            object : TypeToken<ArrayList<DateEvent>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromListOfDateEventsJson(dateEventsList: String): List<DateEvents>{
        return jsonParser.fromJson<ArrayList<DateEvents>>(
            dateEventsList,
            object : TypeToken<ArrayList<DateEvents>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toListOfDateEventsJson(dateEventsList: List<DateEvents>): String{
        return jsonParser.toJson(
            dateEventsList,
            object : TypeToken<ArrayList<DateEvent>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromListOfDateEventsEntityJson(dateEventsEntityList: String): List<DateEventsEntity>{
        return jsonParser.fromJson<ArrayList<DateEventsEntity>>(
            dateEventsEntityList,
            object : TypeToken<ArrayList<DateEvents>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toListOfDateEventsEntityJson(dateEventEntityList: List<DateEventsEntity>): String{
        return jsonParser.toJson(
            dateEventEntityList,
            object : TypeToken<ArrayList<DateEventsEntity>>(){}.type
        ) ?: "[]"
    }


    // Team Events Converter
    @TypeConverter
    fun fromTeamEventJson(teamEvent: String): TeamEvent {
        return jsonParser.fromJson<TeamEvent>(
            teamEvent,
            object : TypeToken<TeamEvent>(){}.type) ?: TeamEvent(null, emptyString, nullInteger, emptyString, nullInteger, emptyString, nullInteger, nullInteger, nullInteger, nullInteger, emptyString, emptyString, emptyString, nullInteger, emptyString, null, null, null, null, null, null)
    }

    @TypeConverter
    fun toTeamEventJson(teamEvent: TeamEvent): String{
        return jsonParser.toJson(
            teamEvent,
            object : TypeToken<TeamEvent>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTeamEventListJson(teamEventList: String): List<TeamEvent>{
        return jsonParser.fromJson<List<TeamEvent>>(
            teamEventList,
            object : TypeToken<List<TeamEvent>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTeamEventListJson(teamEventList: List<TeamEvent>): String{
        return jsonParser.toJson(
            teamEventList,
            object : TypeToken<List<TeamEvent>>(){}.type
        ) ?: "[]"
    }




    // Team Name Events Entity Converter
    @TypeConverter
    fun fromTeamNameEventEntityJson(teamNameEventEntity: String): TeamEventEntity {
        return jsonParser.fromJson<TeamEventEntity>(
            teamNameEventEntity,
            object : TypeToken<TeamEventEntity>(){}.type) ?: TeamEventEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

    @TypeConverter
    fun toTeamNameEventEntityJson(teamEventEntity: TeamEventEntity): String{
        return jsonParser.toJson(
            teamEventEntity,
            object : TypeToken<TeamEventEntity>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTeamNameEventEntityListJson(teamNameEventEntityList: String): List<TeamEventEntity>{
        return jsonParser.fromJson<List<TeamEventEntity>>(
            teamNameEventEntityList,
            object : TypeToken<List<TeamEventEntity>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTeamNameEventEntityListJson(teamEventEntityList: List<TeamEventEntity>): String{
        return jsonParser.toJson(
            teamEventEntityList,
            object : TypeToken<List<TeamEventEntity>>(){}.type
        ) ?: "[]"
    }




    // Head To Head Events Converter
    @TypeConverter
    fun fromHeadToHeadEventJson(headToHeadEvent: String): HeadToHeadEvent{
        return jsonParser.fromJson<HeadToHeadEvent>(
            headToHeadEvent,
            object : TypeToken<HeadToHeadEvent>(){}.type) ?: HeadToHeadEvent(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

    @TypeConverter
    fun toHeadToHeadEventJson(headToHeadEvent: HeadToHeadEvent): String{
        return jsonParser.toJson(
            headToHeadEvent,
            object : TypeToken<HeadToHeadEvent>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromHeadToHeadEventListJson(headToHeadEventList: String): List<HeadToHeadEvent>{
        return jsonParser.fromJson<List<HeadToHeadEvent>>(
            headToHeadEventList,
            object : TypeToken<List<HeadToHeadEvent>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toHeadToHeadEventListJson(headToHeadEventList: List<HeadToHeadEvent>): String{
        return jsonParser.toJson(
            headToHeadEventList,
            object : TypeToken<List<HeadToHeadEvent>>(){}.type
        ) ?: "[]"
    }




    // Preferred Events Converter
    @TypeConverter
    fun fromPreferredEventJson(preferredEvent: String): EventsEntity {
        return jsonParser.fromJson<EventsEntity>(
            preferredEvent,
            object : TypeToken<EventsEntity>(){}.type) ?: EventsEntity(null, Date(), nullInteger, emptyString, nullInteger, emptyString, nullInteger, emptyString, nullInteger, nullInteger, nullInteger, nullInteger,  emptyString,  emptyString,  emptyString, nullInteger, emptyString, RoundInfo(nullInteger, emptyString, nullInteger, emptyString),
            Tournament(emptyString, nullInteger, emptyString, nullInteger, emptyString, emptyString, nullInteger), TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString), TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString),
            Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger), Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger))
    }


    @TypeConverter
    fun toPreferredEventJson(preferredEvent: EventsEntity): String{
        return jsonParser.toJson(preferredEvent, object : TypeToken<EventsEntity>(){}.type) ?: "[]"
    }


    @TypeConverter
    fun fromPreferredEventsEntityListJson(preferredEventList: String): List<EventsEntity>{
        return jsonParser.fromJson<ArrayList<EventsEntity>>(
            preferredEventList,
            object : TypeToken<ArrayList<EventsEntity>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toPreferredEventsEntityListJson(preferredEventsList: List<EventsEntity>): String{
        return jsonParser.toJson(
            preferredEventsList,
            object : TypeToken<ArrayList<EventsEntity>>(){}.type
        ) ?: "[]"
    }

    // Team SuggestionVariables Converter
    @TypeConverter
    fun fromTeamSuggestionJson(teamSuggestionList: String): TeamSuggestionsEntity {
        return jsonParser.fromJson<TeamSuggestionsEntity>(
            teamSuggestionList,
            object : TypeToken<TeamSuggestionsEntity>(){}.type) ?: TeamSuggestionsEntity(null, emptyString, Date(), nullInteger, emptyList())
    }

    @TypeConverter
    fun toTeamSuggestionJson(teamSuggestions: TeamSuggestionsEntity): String{
        return jsonParser.toJson(
            teamSuggestions,
            object : TypeToken<TeamSuggestionsEntity>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTeamSuggestionsEntityListJson(teamSuggestionList: String): List<TeamSuggestionsEntity>{
        return jsonParser.fromJson<ArrayList<TeamSuggestionsEntity>>(
            teamSuggestionList,
            object : TypeToken<ArrayList<TeamSuggestionsEntity>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTeamSuggestionsEntityListJson(teamSuggestionsList: List<TeamSuggestionsEntity>): String{
        return jsonParser.toJson(
            teamSuggestionsList,
            object : TypeToken<ArrayList<TeamSuggestionsEntity>>(){}.type
        ) ?: "[]"
    }



    // Team SuggestionVariables Converter
    @TypeConverter
    fun fromSuggestionJson(suggestion: String): Suggestion {
        return jsonParser.fromJson<Suggestion>(
            suggestion,
            object : TypeToken<Suggestion>(){}.type) ?: Suggestion(
            emptyString,
            nullInteger,
            emptyString,
            NoValue_Double,
            NoValue_Double,
            NoValue_Double,
            emptyString,
            emptyString,
            emptyString,
            emptyString,
            emptyString
        )
    }

    @TypeConverter
    fun toSuggestionJson(suggestion: Suggestion): String{
        return jsonParser.toJson(
            suggestion,
            object : TypeToken<Suggestion>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromSuggestionListJson(suggestion: String): List<Suggestion>{
        return jsonParser.fromJson<ArrayList<Suggestion>>(
            suggestion,
            object : TypeToken<ArrayList<Suggestion>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toSuggestionListJson(suggestion: List<Suggestion>): String{
        return jsonParser.toJson(
            suggestion,
            object : TypeToken<ArrayList<Suggestion>>(){}.type
        ) ?: "[]"
    }

    // Round Info Converter
    @TypeConverter
    fun fromRoundInfoJson(roundInfo: String): RoundInfo {
        return jsonParser.fromJson<RoundInfo>(
            roundInfo,
            object : TypeToken<RoundInfo>(){}.type) ?: RoundInfo(nullInteger, emptyString, nullInteger, emptyString)
    }

    @TypeConverter
    fun toRoundInfoJson(roundInfo: RoundInfo): String{
        return jsonParser.toJson(
            roundInfo,
            object : TypeToken<RoundInfo>(){}.type
        ) ?: "[]"
    }

/*

    // Round Info Converter
    @TypeConverter
    fun fromRoundInfoListJson(roundInfo: String): RoundInfo{
        return jsonParser.fromJson<RoundInfo>(
            roundInfo,
            object : TypeToken<RoundInfo>(){}.type) ?: RoundInfo(nullInteger, EmptyString, nullInteger, EmptyString)
    }

    @TypeConverter
    fun toRoundInfoListJson(roundInfo: RoundInfo): String{
        return jsonParser.toJson(
            roundInfo,
            object : TypeToken<RoundInfo>(){}.type
        ) ?: "[]"
    }
*/


    // Scores Converter
    @TypeConverter
    fun fromScoresJson(scores: String): Scores{
        return jsonParser.fromJson<Scores>(
            scores,
            object : TypeToken<Scores>(){}.type) ?: Scores(nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger, nullInteger)
    }

    @TypeConverter
    fun toScoresJson(scores: Scores): String{
        return jsonParser.toJson(
            scores,
            object : TypeToken<Scores>(){}.type
        ) ?: "[]"
    }


    // Colors Converter
    @TypeConverter
    fun fromTeamColorsJson(teamColors: String): TeamColors {
        return jsonParser.fromJson<TeamColors>(
            teamColors,
            object : TypeToken<TeamColors>(){}.type) ?: TeamColors(emptyString, emptyString, emptyString)
    }

    @TypeConverter
    fun toTeamColorsJson(teamColors: TeamColors): String{
        return jsonParser.toJson(
            teamColors,
            object : TypeToken<TeamColors>(){}.type
        ) ?: "[]"
    }

    // Team Info Converter
    @TypeConverter
    fun fromTeamInfoJson(teamInfo: String): TeamInfo {
        return jsonParser.fromJson<TeamInfo>(
            teamInfo,
            object : TypeToken<TeamInfo>(){}.type) ?: TeamInfo(emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString, emptyString, emptyString, nullInteger, emptyString, emptyString, emptyString)
    }

    @TypeConverter
    fun toTeamInfoJson(teamInfo: TeamInfo): String{
        return jsonParser.toJson(
            teamInfo,
            object : TypeToken<TeamInfo>(){}.type
        ) ?: "[]"
    }


    // Tournament Info Converter
    @TypeConverter
    fun fromTournamentJson(tournament: String): Tournament{
        return jsonParser.fromJson<Tournament>(
            tournament,
            object : TypeToken<Tournament>(){}.type) ?: Tournament(emptyString, nullInteger, emptyString, nullInteger, emptyString, emptyString, nullInteger)
    }

    @TypeConverter
    fun toTournamentJson(tournament: Tournament): String{
        return jsonParser.toJson(
            tournament,
            object : TypeToken<Tournament>(){}.type
        ) ?: "[]"
    }



    // Choice Converter
    @TypeConverter
    fun fromChoiceJson(choice: String): Choice {
        return jsonParser.fromJson<Choice>(
            choice,
            object : TypeToken<Choice>(){}.type) ?: Choice(nullInteger, nullDouble, nullDouble, emptyString, nullInteger, false)
    }

    @TypeConverter
    fun toChoiceJson(choice: Choice): String{
        return jsonParser.toJson(
            choice,
            object : TypeToken<Choice>(){}.type
        ) ?: "[]"
    }




    // Choice Converter
    @TypeConverter
    fun fromChoiceListJson(choiceList: String): List<Choice> {
        return jsonParser.fromJson<List<Choice>>(
            choiceList,
            object : TypeToken<List<Choice>>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toChoiceListJson(choiceList: List<Choice>): String{
        return jsonParser.toJson(
            choiceList,
            object : TypeToken<List<Choice>>(){}.type
        ) ?: "[]"
    }

    // Bank Account Converter
    @TypeConverter
    fun fromBankAccountsJson(bankAccount: String): BankAccounts {
        return jsonParser.fromJson<BankAccounts>(
            bankAccount,
            object : TypeToken<BankAccounts>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toBankAccountJson(bankAccounts: BankAccounts): String{
        return jsonParser.toJson(
            bankAccounts,
            object : TypeToken<BankAccounts>(){}.type
        ) ?: "[]"
    }


    // Comment Converter
    @TypeConverter
    fun fromCommentsJson(comment: String): Comments {
        return jsonParser.fromJson<Comments>(
            comment,
            object : TypeToken<Comments>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toCommentsJson(comments: Comments): String{
        return jsonParser.toJson(
            comments,
            object : TypeToken<Comments>(){}.type
        ) ?: "[]"
    }


    // Reactions Converter
    @TypeConverter
    fun fromReactionsJson(reaction: String): Reactions {
        return jsonParser.fromJson<Reactions>(
            reaction,
            object : TypeToken<Reactions>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toReactionsJson(reactions: Reactions): String{
        return jsonParser.toJson(
            reactions,
            object : TypeToken<Reactions>(){}.type
        ) ?: "[]"
    }

    // Subscribers Converter
    @TypeConverter
    fun fromSubscribersJson(subscriber: String): Subscribers {
        return jsonParser.fromJson<Subscribers>(
            subscriber,
            object : TypeToken<Subscribers>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toSubscribersJson(subscribers: Subscribers): String{
        return jsonParser.toJson(
            subscribers,
            object : TypeToken<Subscribers>(){}.type
        ) ?: "[]"
    }

    // Subscribers Converter
    @TypeConverter
    fun fromUserSubscriptionJson(userSubscription: String): UserSubscription {
        return jsonParser.fromJson<UserSubscription>(
            userSubscription,
            object : TypeToken<UserSubscription>(){}.type) ?: UserSubscription(null, null, null, null)
    }

    @TypeConverter
    fun toUserSubscriptionJson(userSubscription: UserSubscription): String{
        return jsonParser.toJson(
            userSubscription,
            object : TypeToken<UserSubscription>(){}.type
        ) ?: "[]"
    }
// Subscribers Converter
    @TypeConverter
    fun fromUserSubscriptionsJson(userSubscriptions: String): UserSubscriptions {
        return jsonParser.fromJson<UserSubscriptions>(
            userSubscriptions,
            object : TypeToken<UserSubscription>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toUserSubscriptionsJson(userSubscriptions: UserSubscriptions): String{
        return jsonParser.toJson(
            userSubscriptions,
            object : TypeToken<UserSubscriptions>(){}.type
        ) ?: "[]"
    }

    // Ratings Converter
    @TypeConverter
    fun fromRatingJson(rating: String): Rating {
        return jsonParser.fromJson<Rating>(
            rating,
            object : TypeToken<Rating>(){}.type) ?: Rating(null, null, null, null)
    }

    @TypeConverter
    fun toRatingJson(rating: Rating): String{
        return jsonParser.toJson(
            rating,
            object : TypeToken<Rating>(){}.type
        ) ?: "[]"
    }


    // Ratings Converter
    @TypeConverter
    fun fromRatingsJson(ratings: String): Ratings {
        return jsonParser.fromJson<Ratings>(
            ratings,
            object : TypeToken<Ratings>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toRatingsJson(ratings: Ratings): String{
        return jsonParser.toJson(
            ratings,
            object : TypeToken<Ratings>(){}.type
        ) ?: "[]"
    }

    // Subscribers Converter
    @TypeConverter
    fun fromUserEntityJson(userEntity: String): UserEntity {
        return jsonParser.fromJson<UserEntity>(
            userEntity,
            object : TypeToken<UserEntity>(){}.type) ?: UserEntity(null,null,null, null, null, null, null, null, null, null, null, null, null, null, null, null)
    }

    @TypeConverter
    fun toUserEntityJson(userEntity: UserEntity): String{
        return jsonParser.toJson(
            userEntity,
            object : TypeToken<UserEntity>(){}.type
        ) ?: "[]"
    }

    // Posts Converter
    @TypeConverter
    fun fromPostsJson(post: String): Posts {
        return jsonParser.fromJson<Posts>(
            post,
            object : TypeToken<Posts>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toPostsJson(posts: Posts): String{
        return jsonParser.toJson(
            posts,
            object : TypeToken<Posts>(){}.type
        ) ?: "[]"
    }

    // Tips Converter
    @TypeConverter
    fun fromTipsJson(tip: String): Tips {
        return jsonParser.fromJson<Tips>(
            tip,
            object : TypeToken<Tips>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTipsJson(tips: Tips): String{
        return jsonParser.toJson(
            tips,
            object : TypeToken<Tips>(){}.type
        ) ?: "[]"
    }

    // Tipster Packages Converter
    @TypeConverter
    fun fromTipsterPackagesJson(tipsterPackages: String): TipsterPackages {
        return jsonParser.fromJson<TipsterPackages>(
            tipsterPackages,
            object : TypeToken<TipsterPackages>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTipsterPackagesJson(tipsterPackages: TipsterPackages): String{
        return jsonParser.toJson(
            tipsterPackages,
            object : TypeToken<TipsterPackages>(){}.type
        ) ?: "[]"
    }

    // Tipster Reviews Converter
    @TypeConverter
    fun fromTipsterReviewsJson(tipsterReviews: String): TipsterReviews {
        return jsonParser.fromJson<TipsterReviews>(
            tipsterReviews,
            object : TypeToken<TipsterReviews>(){}.type) ?: emptyList()
    }

    @TypeConverter
    fun toTipsterReviewsJson(tipsterReviews: TipsterReviews): String{
        return jsonParser.toJson(
            tipsterReviews,
            object : TypeToken<TipsterReviews>(){}.type
        ) ?: "[]"
    }





}