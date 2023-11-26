package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import java.util.Date

data class TipsterReview(
    val date: Date,
    val user: UserEntity?,
    val review: String?,
    val ratings: Double?
)
