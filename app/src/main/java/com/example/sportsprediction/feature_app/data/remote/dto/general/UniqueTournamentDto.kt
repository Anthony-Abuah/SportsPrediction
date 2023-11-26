package com.example.sportsprediction.feature_app.data.remote.dto.general

data class UniqueTournamentDto(
    val category: CategoryDto?,
    val country: CountryDto?,
    val crowdsourcingEnabled: Boolean?,
    val displayInverseHomeAwayTeams: Boolean?,
    val hasEventPlayerStatistics: Boolean?,
    val hasPerformanceGraphFeature: Boolean?,
    val id: Int?,
    val name: String?,
    val slug: String?,
    val userCount: Int?
)