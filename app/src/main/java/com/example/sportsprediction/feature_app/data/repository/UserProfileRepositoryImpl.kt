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
    override fun registerUser(user: UserEntity): Flow<Resource<String>> = flow {

        val allUserNames = userDao.getUserNames() ?: emptyList()
        val allPasswords = userDao.getUserPasswords() ?: emptyList()
        val userName = user.userName ?: DoesNotExist.toString()
        val userPassword = user.password ?: DoesNotExist.toString()

        if (allUserNames.contains(userName)){
            emit(Resource.Error("Username already exists", "Please use a different userName"))
        }
        else if (allPasswords.contains(userPassword)){
            emit(Resource.Error("Password has already been used", "Please use a different password"))
        }
        else{
            userDao.insertUser(user)
            emit(Resource.Success("Successfully Registered"))
        }

    }

    override fun loginUser(username: String, password: String): Flow<Resource<String>> = flow {

        val allUserNames = userDao.getUserNames() ?: emptyList()
        val allPasswords = userDao.getUserPasswords() ?: emptyList()
        val user = userDao.getUser(username, password)
        val userName = user?.userName ?: DoesNotExist.toString()
        val userPassword = user?.password ?: DoesNotExist.toString()

        if (!allUserNames.contains(userName)){
            emit(Resource.Error("$username is not registered", "Please register and  log in"))
        }
        else if (!allPasswords.contains(userPassword)){
            emit(Resource.Error("Incorrect password", "Please try again"))
        }
        else if (user == null){
            emit(Resource.Error("User does not exist", "Please check credentials or register again"))
        }
        else emit(Resource.Success("Successfully Logged in"))
        }

    override suspend fun getUser(username: String, password: String): UserEntity? {
        return userDao.getUser(username, password)
    }


}