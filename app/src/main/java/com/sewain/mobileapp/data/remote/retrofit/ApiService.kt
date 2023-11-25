package com.sewain.mobileapp.data.remote.retrofit

import com.sewain.mobileapp.data.remote.model.Login
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.response.LoginResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v1/register")
    suspend fun register(@Body register: Register): RegisterResponse

    @POST("/api/v1/login")
    suspend fun login(@Body login: Login): LoginResponse
}