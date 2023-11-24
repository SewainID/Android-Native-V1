package com.sewain.mobileapp.data

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.sewain.mobileapp.data.remote.response.ErrorResponse
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.retrofit.ApiService
import com.sewain.mobileapp.ui.common.UiState
import retrofit2.HttpException

class SewainRepository(
    private val apiService: ApiService
) {
    companion object {
        @Volatile
        private var instance: SewainRepository? = null

        fun getInstance(
            apiService: ApiService
        ): SewainRepository =
            instance ?: synchronized(this) {
                SewainRepository(apiService).apply {
                    instance = this
                }
            }
    }
}