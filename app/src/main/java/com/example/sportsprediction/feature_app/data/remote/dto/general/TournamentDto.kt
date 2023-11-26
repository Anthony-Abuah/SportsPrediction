package com.example.sportsprediction.feature_app.data.remote.dto.general

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.Tournament

data class TournamentDto(
    val category: CategoryDto?,
    val id: Int?,
    val name: String?,
    val priority: Int?,
    val slug: String?,
    val uniqueTournament: UniqueTournamentDto?
){
    fun toTournament(): Tournament {
        return Tournament(
            categoryName = category?.name ?: emptyString,
            tournamentId = id ?: nullInteger,
            name = name ?: emptyString,
            priority = priority ?: nullInteger,
            slug = slug ?: emptyString,
            sport = category?.sport?.name ?: emptyString,
            uniqueTournamentId = uniqueTournament?.id ?: nullInteger
        )
    }
}