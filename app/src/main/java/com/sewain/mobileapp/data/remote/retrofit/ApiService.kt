package com.sewain.mobileapp.data.remote.retrofit

import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST
    suspend fun register(@Body register: Register): RegisterResponse
}