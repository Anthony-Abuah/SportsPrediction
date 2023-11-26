package com.example.sportsprediction.feature_app.ui.presentation.view_model.states

import com.example.sportsprediction.core.util.Constants.emptyString
import com.example.sportsprediction.feature_app.data.local.entities.date_events.DateEventsEntity
import com.example.sportsprediction.feature_app.domain.model.date_events.DateEvents
import java.util.*


data class UserProfileState (
    val success: String? = emptyString,
    val isSaving: Boolean = false,
    val error: String? = emptyString

)