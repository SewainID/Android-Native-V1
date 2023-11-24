package com.sewain.mobileapp.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sewain.mobileapp.data.local.model.SessionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SessionPreferences private constructor(private val datastore: DataStore<Preferences>) {
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val TOKEN_KEY = stringPreferencesKey("token")
    private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

    fun getSession(): Flow<SessionModel> {
        return datastore.data.map { data ->
            SessionModel(
                data[USERNAME_KEY] ?: "",
                data[TOKEN_KEY] ?: "",
                data[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun saveSession(sessionModel: SessionModel) {
        datastore.edit { data ->
            data[USERNAME_KEY] = sessionModel.username
            data[TOKEN_KEY] = sessionModel.token
            data[IS_LOGIN_KEY] = sessionModel.isLogin
        }
    }

    suspend fun logout() {
        datastore.edit { data ->
            data.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SessionPreferences? = null

        fun getInstance(datastore: DataStore<Preferences>): SessionPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreferences(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}