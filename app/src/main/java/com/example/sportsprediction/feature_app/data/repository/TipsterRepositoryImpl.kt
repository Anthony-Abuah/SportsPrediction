package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.DoesNotExist
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterDao
import com.example.sportsprediction.feature_app.data.local.entities.tipster.TipsterEntity
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserDao
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.domain.repository.TipsterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TipsterRepositoryImpl(
    private val tipsterDao: TipsterDao,
    private val userDao: UserDao
): TipsterRepository {
    override fun registerTipster(tipster: TipsterEntity): Flow<Resource<String>> = flow {

        val allUsers = userDao.getAllUsers() ?: emptyList()
        val user = tipster.user ?: UserEntity(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
        val userHasSubscribed = user.userSubscription?.isSubscribed ?: false

        val allTipsterPasswords = tipsterDao.getAllTipsterPasswords() ?: emptyList()
        val allTipsterNames = tipsterDao.getAllTipsterNames() ?: emptyList()
        val tipsterName = tipster.tipsterName ?: DoesNotExist.toString()
        val tipsterPassword = tipster.tipsterPassword ?: DoesNotExist.toString()

        if (!allUsers.contains(user)){
            emit(Resource.Error("You are not registered as a user", "Please register as a user"))
        }
        else if (userHasSubscribed){
            emit(Resource.Error("You need to subscribe to be a user", "Please subscribe to any of the plans"))
        }
        else if (allTipsterPasswords.contains(tipsterPassword)){
            emit(Resource.Error("Password has already been used", "Please use a different password"))
        }
        else if (allTipsterNames.contains(tipsterName)){
            emit(Resource.Error("This name has already been taken", "Please use a different name"))
        }
        else{
            tipsterDao.insertTipster(tipster)
            emit(Resource.Success("Successfully Registered"))
        }

    }


}