package com.sewain.mobileapp.data

import com.sewain.mobileapp.data.local.model.SessionModel
import com.sewain.mobileapp.data.local.preferences.SessionPreferences
import com.sewain.mobileapp.data.remote.model.Login
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.response.LoginResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import com.sewain.mobileapp.data.remote.retrofit.ApiService
import com.sewain.mobileapp.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: SessionPreferences,
    private val appExecutors: AppExecutors,
    private val apiService: ApiService
) {

    suspend fun saveSession(token: String, username: String, isLogin: Boolean) {
        userPreference.saveSession(SessionModel(token, username, isLogin))
    }

    fun getSession(): Flow<SessionModel> {
        return userPreference.getSession()
    }

    suspend fun registerUser(username: String, email: String, password: String): RegisterResponse {
        return apiService.register(Register(username, email, password))
    }

    suspend fun loginUser(email: String, password: String): LoginResponse {
        return apiService.login(Login(email, password))
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: SessionPreferences,
            appExecutors: AppExecutors,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, appExecutors, apiService)
            }.also { instance = it }
    }
}