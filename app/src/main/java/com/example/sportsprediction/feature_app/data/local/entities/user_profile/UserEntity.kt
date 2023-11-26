package com.example.sportsprediction.feature_app.data.local.entities.user_profile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportsprediction.core.util.*
import com.example.sportsprediction.core.util.Constants.User_Entity
import java.util.*

@Entity(tableName = User_Entity)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int?,
    val uniqueUserId: String?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: Date?,
    val gender: String?,
    val email: String?,
    val countryCode: String?,
    val phoneNumber: String?,
    val countryOfResidence: String?,
    val userName: String?,
    val dateJoined: Date?,
    val profilePicture: String?,
    val comments: Comments?,
    val userSubscriptions: UserSubscriptions?,
    val bankAccounts: BankAccounts?

)
