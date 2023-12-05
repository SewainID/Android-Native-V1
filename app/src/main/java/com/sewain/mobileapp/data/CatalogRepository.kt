package com.sewain.mobileapp.data

import androidx.lifecycle.MediatorLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sewain.mobileapp.data.local.entity.CatalogEntity
import com.sewain.mobileapp.data.local.room.CatalogDao
import com.sewain.mobileapp.data.local.room.SewainDatabase
import com.sewain.mobileapp.data.remote.retrofit.ApiService
import com.sewain.mobileapp.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class CatalogRepository private constructor(
    private val apiService: ApiService,
    private val catalogDao: CatalogDao,
    private val appExecutors: AppExecutors,
    private val sewainDatabase: SewainDatabase,
) {
    private val result = MediatorLiveData<Result<List<CatalogEntity>>>()

    fun getCatalogs(): Flow<PagingData<CatalogEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = StoryRemoteMediator(sewainDatabase, apiService),
            pagingSourceFactory = {
                sewainDatabase.catalogDao().getAllCatalogs()
            }
        ).flow
    }


//    fun addCatalog(imageFile: File, description: String) = liveData {
//        emit(Result.Loading)
//        val requestBody = description.toRequestBody("text/plain".toMediaType())
//        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
//        try {
//            val successResponse = apiService.addNewStory(multipartBody, requestBody)
//            emit(Result.Success(successResponse))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val errorResponse = Gson().fromJson(errorBody, AddStoryResponse::class.java)
//            emit(Result.Error(errorResponse.message))
//        } catch (e: SocketTimeoutException) {
//            emit(Result.Error("Request To Server Timeout"))
//        }
//
//    }

    companion object {
        @Volatile
        private var instance: CatalogRepository? = null
        fun getInstance(
            apiService: ApiService,
            catalogDao: CatalogDao,
            appExecutors: AppExecutors,
            sewainDatabase: SewainDatabase,
        ): CatalogRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogRepository(apiService, catalogDao, appExecutors, sewainDatabase )
            }.also { instance = it }
    }
}