package com.sewain.mobileapp.data.remote.retrofit

import com.sewain.mobileapp.data.remote.model.Login
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.response.CatalogItem
import com.sewain.mobileapp.data.remote.response.CatalogsResponse
import com.sewain.mobileapp.data.remote.response.LoginResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/api/v1/register")
    suspend fun register(@Body register: Register): RegisterResponse

    @POST("/api/v1/login")
    suspend fun login(@Body login: Login): LoginResponse

    @GET("/api/v1/catalogs")
    suspend fun getCatalogs(
        @Query("page") page: Int = 1,
        @Query("per_page") size: Int = 20,
        @Query("search") search: String? = null
    ): CatalogsResponse

    @GET("/api/v1/catalogs/{id}")
    suspend fun getCatalogById(@Path("id") catalogId: String): Response<CatalogItem>
}