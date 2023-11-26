package com.example.sportsprediction.feature_app.domain.model.build_a_bet

import com.example.sportsprediction.core.util.ListOfEvents
import java.util.*

data class SelectedEventsAndDate(
    val selectedEvents: ListOfEvents?,
    val selectedDate: Date,
)
