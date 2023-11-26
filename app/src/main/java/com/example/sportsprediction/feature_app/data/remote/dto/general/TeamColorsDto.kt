package com.example.sportsprediction.feature_app.data.remote.dto.general

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.domain.model.general.TeamColors

data class TeamColorsDto(
    val primary: String?,
    val secondary: String?,
    val text: String?
){
    fun toTeamColors(): TeamColors {
        return TeamColors(
            primary ?: emptyString, secondary ?: emptyString, text ?: emptyString
        )
    }
}