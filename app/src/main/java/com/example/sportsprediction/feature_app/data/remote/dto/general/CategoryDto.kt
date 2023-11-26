package com.example.sportsprediction.feature_app.data.remote.dto.general

data class CategoryDto(
    val alpha2: String?,
    val flag: String?,
    val id: Int?,
    val name: String?,
    val slug: String?,
    val sport: SportDto?
)