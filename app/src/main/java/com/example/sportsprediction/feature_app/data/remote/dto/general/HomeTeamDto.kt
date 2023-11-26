package com.example.sportsprediction.feature_app.data.remote.dto.general

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.TeamInfo

data class HomeTeamDto(
    val country: CountryDto?,
    val disabled: Boolean?,
    val gender: String?,
    val id: Int?,
    val name: String?,
    val nameCode: String?,
    val national: Boolean?,
    val shortName: String?,
    val slug: String?,
    val sport: SportDto?,
    val subTeams: List<Any>?,
    val teamColors: TeamColorsDto?,
    val type: Int?,
    val userCount: Int?
){
    fun toHomeTeamInfo(): TeamInfo {
        return TeamInfo(
            country = country?.name ?: emptyString,
            gender = gender ?: emptyString,
            id = id ?: nullInteger,
            name = name ?: emptyString,
            nameCode = nameCode ?: emptyString,
            shortName = shortName ?: emptyString,
            slug = slug ?: emptyString,
            sport = sport?.name ?: emptyString,
            userCount = userCount ?: nullInteger,
            primaryTeamColors = teamColors?.primary ?: emptyString,
            secondaryTeamColors = teamColors?.primary ?: emptyString,
            textTeamColors = teamColors?.primary ?: emptyString,
        )
    }
}