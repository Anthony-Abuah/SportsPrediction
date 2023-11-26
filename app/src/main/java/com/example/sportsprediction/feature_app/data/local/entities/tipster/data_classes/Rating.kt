package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import java.util.Date


data class Rating(
    val uniqueUserId: String?,
    val uniqueTipsterId: String?,
    val ratingDate: Date?,
    val userRating: Double?

)
