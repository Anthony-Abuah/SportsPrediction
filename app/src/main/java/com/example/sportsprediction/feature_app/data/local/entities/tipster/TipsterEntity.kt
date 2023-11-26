package com.example.sportsprediction.feature_app.data.local.entities.tipster

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.*
import com.example.sportsprediction.core.util.Constants.Tipster_Entity
import com.example.sportsprediction.feature_app.data.local.entities.tipster.data_classes.Rating
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import java.util.*

@Entity(tableName = Tipster_Entity)
data class TipsterEntity(
    @PrimaryKey(autoGenerate = true)
    val tipsterId: Int?,
    val user: UserEntity?,
    val bankAccount: BankAccounts?,

    // Profile Details
    val tipsterName: String?,
    val tipsterPassword: String?,
    val dateJoined: Date?,
    val tipsterProfilePicture: String?,
    val subscribers: Subscribers?,
    val posts: Posts?,
    val paidSubscribers: Subscribers?,
    val rating: Rating?,
    val tips: Tips?,
    val reviews: TipsterReviews?,
    val packages: TipsterPackages?,
    val isVerified: Boolean?,

)
