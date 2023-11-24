package com.sewain.mobileapp.di

import android.content.Context
import com.sewain.mobileapp.data.SewainRepository
import com.sewain.mobileapp.data.local.preferences.SessionPreferences
import com.sewain.mobileapp.data.local.preferences.datastore
import com.sewain.mobileapp.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): SewainRepository {
        val sessionPreferences = SessionPreferences.getInstance(context.datastore)
        val user = runBlocking { sessionPreferences.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return SewainRepository.getInstance(apiService)
    }
}