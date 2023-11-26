package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.core.util.Functions.toDate
import java.time.LocalDate
import java.util.*

private val localDateNow: LocalDate = LocalDate.now()
private val currentDate = localDateNow.toDate()

data class TipsterSubscription(
    val dateSubscribed: Date?,
    val subscriptionExpiryDate: Date?,
    val isSubscribed: Boolean? = (subscriptionExpiryDate ?: currentDate) > currentDate,
    val subscribedPackage: TipsterPackage?

)
