package com.example.sportsprediction.feature_app.data.local.entities.user_profile

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.User_Entity
import com.example.sportsprediction.core.util.Users
import java.util.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query ("DELETE FROM $User_Entity")
    suspend fun deleteAllUsers()

    @Query ("DELETE FROM $User_Entity WHERE userId NOT IN (SELECT MIN(userId) FROM $User_Entity GROUP BY dateJoined)")
    suspend fun deleteDuplicateUsers()

    @Query ("SELECT * FROM $User_Entity")
    suspend fun getAllUsers(): Users?

    @Query ("SELECT * FROM $User_Entity WHERE userName LIKE :userName AND email LIKE :email")
    suspend fun getUser(userName: String, email: String): UserEntity?

    @Query ("SELECT email FROM $User_Entity")
    suspend fun getUserEmails(): List<String>?

    @Query ("SELECT userName FROM $User_Entity")
    suspend fun getUserNames(): List<String>?






}