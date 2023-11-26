package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity

data class TipsterPackage(
    val tipster: TipsterEntity,
    val packageName: String?,
    val packagePrice: Double?,
    val packageDurationInDays: Int?,
    val packageDescription: String?
)
