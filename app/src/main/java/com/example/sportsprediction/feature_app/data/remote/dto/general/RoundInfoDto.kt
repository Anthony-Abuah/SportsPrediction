package com.example.sportsprediction.feature_app.data.remote.dto.general

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.core.util.Constants.nullInteger
import com.example.sportsprediction.feature_app.domain.model.general.RoundInfo

data class RoundInfoDto(
    val cupRoundType: Int?,
    val name: String?,
    val round: Int?,
    val slug: String?
){
    fun toRoundInfo(): RoundInfo {
        return RoundInfo(
            cupRoundType ?: nullInteger, name ?: emptyString, round ?: nullInteger, slug ?: emptyString
        )
    }
}