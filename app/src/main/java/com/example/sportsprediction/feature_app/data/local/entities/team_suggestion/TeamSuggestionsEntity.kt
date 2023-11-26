package com.example.sportsprediction.feature_app.data.local.entities.team_suggestion

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.Constants.Team_Suggestions_Entity
import com.example.sportsprediction.core.util.ListOfSuggestions
import java.util.*


@Entity(tableName = Team_Suggestions_Entity)
data class TeamSuggestionsEntity(
    @PrimaryKey(autoGenerate = true)
    val teamSuggestionEntityId: Int?,
    val teamName: String?,
    val date: Date,
    val teamId: Int?,
    val suggestions: ListOfSuggestions
)
