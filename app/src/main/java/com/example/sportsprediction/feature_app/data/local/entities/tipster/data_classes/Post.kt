package com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes

import com.example.sportsprediction.core.util.Comments
import com.example.sportsprediction.core.util.Reactions
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import java.util.Date


data class Post(
    val tipster: TipsterEntity?,
    val title: String?,
    val content: String?,
    val description: String?,
    val date: Date?,
    val reactions: Reactions?,
    val comments: Comments?,

)
