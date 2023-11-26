package com.example.sportsprediction.feature_app.data.repository

import com.example.sportsprediction.core.util.Constants.DoesNotExist
import com.example.sportsprediction.core.util.Resource
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserDao
import com.example.sportsprediction.feature_app.data.local.entities.user_profile.UserEntity
import com.example.sportsprediction.feature_app.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserProfileRepositoryImpl(
    private val userDao: UserDao
): UserProfileRepository {
    override fun insertUser(user: UserEntity): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        userDao.insertUser(user)
        emit(Resource.Success("User info successfully added"))
    }

    override fun loginUser(username: String, email: String): Flow<Resource<String>> = flow {

        val allUserNames = userDao.getUserNames() ?: emptyList()
        val user = userDao.getUser(username, email)
        val userName = user?.userName ?: DoesNotExist.toString()

        if (!allUserNames.contains(userName)){
            emit(Resource.Error("$username is not registered", "Please register and  log in"))
        }

        else if (user == null){
            emit(Resource.Error("User does not exist", "Please check credentials or register again"))
        }
        else emit(Resource.Success("Successfully Logged in"))
        }

    override suspend fun getUser(username: String, email: String): UserEntity? {
        return userDao.getUser(username, email)
    }


}