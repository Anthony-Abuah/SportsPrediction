package com.example.sportsprediction.feature_app.data.local.entities.tipster

import androidx.room.*
import com.example.sportsprediction.core.util.Constants.Tipster_Entity
import com.example.sportsprediction.core.util.Tipsters
import com.example.sportsprediction.core.util.Users
import java.util.*

@Dao
interface TipsterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTipster(tipster: TipsterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTipsters(tipsters: Tipsters)

    @Query ("DELETE FROM $Tipster_Entity")
    suspend fun deleteTipsters()

    @Query ("DELETE FROM $Tipster_Entity WHERE tipsterId NOT IN (SELECT MIN(tipsterId) FROM $Tipster_Entity GROUP BY dateJoined)")
    suspend fun deleteDuplicateTipsters()

    @Query ("SELECT * FROM $Tipster_Entity")
    suspend fun getAllTipsters(): Tipsters?
    
    @Query ("SELECT tipsterPassword FROM $Tipster_Entity")
    suspend fun getAllTipsterPasswords(): List<String>?

    @Query ("SELECT tipsterName FROM $Tipster_Entity")
    suspend fun getAllTipsterNames(): List<String>?





}