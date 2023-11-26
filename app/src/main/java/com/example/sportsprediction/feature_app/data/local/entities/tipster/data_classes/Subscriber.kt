package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity


data class Subscriber(
    val user: UserEntity?,
    val tipsterSubscription: TipsterSubscription?,

)
