package com.sewain.mobileapp.data.remote.model

import androidx.datastore.preferences.preferencesDataStore

data class User(
    val username: String,
    val email: String,
)