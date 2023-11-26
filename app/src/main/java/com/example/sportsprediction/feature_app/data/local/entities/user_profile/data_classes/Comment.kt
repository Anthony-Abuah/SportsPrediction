package com.example.sportsprediction.feature_app.data.local.entities.user_profile.data_classes

import com.example.sportsprediction.core.util.Reactions
import com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes.Post
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import java.util.*


data class Comment(
    val date: Date?,
    val comment: String?,
    val commentRecipient: UserEntity?,
    val post: Post?,
    val reactions: Reactions?
)
