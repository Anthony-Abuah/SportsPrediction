package com.example.sportsprediction.feature_app.domain.model.general


data class TeamInfo(
    val country: String?,
    val gender: String?,
    val id: Int?,
    val name: String?,
    val nameCode: String?,
    val shortName: String?,
    val slug: String?,
    val sport: String?,
    val userCount: Int?,
    val primaryTeamColors: String?,
    val secondaryTeamColors: String?,
    val textTeamColors: String?
)