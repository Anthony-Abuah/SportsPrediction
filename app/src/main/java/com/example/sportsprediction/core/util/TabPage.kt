package com.example.sportsprediction.core.util

import com.example.sportsprediction.core.util.Constants.Default_Category
import com.example.sportsprediction.core.util.Constants.MarketCategory
import com.example.sportsprediction.core.util.Constants.MarketType
import com.example.sportsprediction.core.util.Constants.MatchPeriod
import com.example.sportsprediction.core.util.Constants.Teams

enum class TabPage(val title: String) {
    Default(Default_Category),
    MarketTypeEnum(MarketType),
    MarketCat(MarketCategory),
    MatchPeriodEnum(MatchPeriod),
    TeamsEnum(Teams)
}