package com.sewain.mobileapp.data.remote.retrofit

import com.google.gson.annotations.SerializedName
import com.sewain.mobileapp.data.remote.model.DetailUser
import com.sewain.mobileapp.data.remote.model.Login
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.model.User
import com.sewain.mobileapp.data.remote.response.AddAttachmentsResponse
import com.sewain.mobileapp.data.remote.response.CatalogsResponse
import com.sewain.mobileapp.data.remote.response.GetUserbyIDResponse
import com.sewain.mobileapp.data.remote.response.LoginResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import com.sewain.mobileapp.data.remote.response.UpdateUserByIDResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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
        @Query("per_page") size: Int = 20
    ): CatalogsResponse

    @GET("/api/v1/users/{id}")
    suspend fun getUserById(@Path("id") id: String): GetUserbyIDResponse

    @PUT("/api/v1/users/{id}")
    suspend fun updateUserById(
        @Path("id") id: String,
        @Body user: User,
    ): UpdateUserByIDResponse

    @Multipart
    @POST("/api/v1/attachments")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): AddAttachmentsResponse
}