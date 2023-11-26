package com.example.sportsprediction.feature_app.data.local.entities.user_profile.data_classes

import com.example.sportsprediction.core.util.Reactions
import com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes.Post
import java.util.*


data class Comment(
    val uniqueCommentId: String?,
    val date: Date?,
    val dateEdited: Date?,
    val comment: String?,
    val repliedTo: String?,
    val commenterId: String?,
    val post: Post?,
    val reactions: Reactions?
)
