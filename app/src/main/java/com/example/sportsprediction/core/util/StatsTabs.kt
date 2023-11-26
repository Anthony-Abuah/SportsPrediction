package com.example.sportsprediction.core.util

import com.example.sportsprediction.core.util.Constants.Full_Time
import com.example.sportsprediction.core.util.Constants.first_Half
import com.example.sportsprediction.core.util.Constants.second_Half

enum class StatsTabs(val title: String) {
    FullTime(Full_Time),
    HalfTime(first_Half),
    SecondHalf(second_Half)
}