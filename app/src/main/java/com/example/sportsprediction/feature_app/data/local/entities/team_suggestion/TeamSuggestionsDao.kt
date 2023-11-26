package com.example.sportsprediction.feature_app.data.local.entities.team_suggestion

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Team_Suggestions_Entity
import java.util.*


@Dao
interface TeamSuggestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamSuggestions(teamSuggestions: TeamSuggestionsEntity)

    @Query ("DELETE FROM $Team_Suggestions_Entity")
    suspend fun deleteAllTeamSuggestions()

    @Query ("SELECT * FROM $Team_Suggestions_Entity")
    suspend fun getTeamSuggestions(): List<TeamSuggestionsEntity>?

    @Query ("DELETE FROM $Team_Suggestions_Entity WHERE teamId LIKE :teamId")
    suspend fun deleteTeamSuggestions(teamId: Int)

    @Query ("SELECT * FROM $Team_Suggestions_Entity WHERE teamId LIKE :teamId")
    suspend fun getThisTeamSuggestions(teamId: Int): TeamSuggestionsEntity?

    @Query ("SELECT * FROM $Team_Suggestions_Entity WHERE teamId LIKE :teamId AND date LIKE :date")
    suspend fun getThisTeamSuggestions(teamId: Int, date: Date): TeamSuggestionsEntity?


    @Query ("SELECT * FROM $Team_Suggestions_Entity WHERE date LIKE :date AND teamID LIKE :teamId")
    suspend fun getThisParticularTeamSuggestionEntity(date: Date, teamId: Int): TeamSuggestionsEntity?


    @Query ("SELECT COUNT(teamId) FROM $Team_Suggestions_Entity WHERE teamId LIKE :teamId")
    suspend fun getNumberOfTeamSuggestions(teamId: Int): Int?








}


