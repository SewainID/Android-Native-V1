package com.sewain.mobileapp.di

import android.content.Context
import com.sewain.mobileapp.data.SewainRepository
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.local.preferences.SessionPreferences
import com.sewain.mobileapp.data.local.preferences.datastore
import com.sewain.mobileapp.data.remote.retrofit.ApiConfig
import com.sewain.mobileapp.utils.AppExecutors
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val sessionPreferences = SessionPreferences.getInstance(context.datastore)
        val appExecutors = AppExecutors()
        val user = runBlocking { sessionPreferences.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(sessionPreferences,appExecutors,apiService)
    }
}