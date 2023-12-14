package com.sewain.mobileapp.ui.screen.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.local.model.SessionModel
import com.sewain.mobileapp.data.remote.response.RegisterErrorResponse
import com.sewain.mobileapp.data.remote.response.UpdateUserByIDErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.net.SocketTimeoutException

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    private val _imageFile = MutableStateFlow("")
    val imageFile: StateFlow<String>
        get() = _imageFile

    private val _message = MutableStateFlow("")
    val message: StateFlow<String>
        get() = _message

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean>
        get() = _success

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean>
        get() = _loading

    private val _username = MutableStateFlow("")
    val username: StateFlow<String>
        get() = _username

    private val _email = MutableStateFlow("")
    val email: StateFlow<String>
        get() = _email

    private val _fullName = MutableStateFlow("")
    val fullName: StateFlow<String>
        get() = _fullName

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getSession(): Flow<SessionModel> {
        return repository.getSession()
    }

    fun getUserById(id: String) {
        viewModelScope.launch {
            val data = repository.getUserById(id).results
            _username.value = data?.username.toString()
            _email.value = data?.email.toString()
            _fullName.value = data?.detailUser?.fullName.toString()
        }
    }

    suspend fun updateUser(id: String, name: String, email: String) {
        _loading.value = true
        try {
            //get success message
            val message = repository.updateUserById(id, name, email)
            _message.value = "Success: ${message.message}"
            _success.value = true
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, UpdateUserByIDErrorResponse::class.java)
            val errorMessage = errorBody.message
            _message.value = "Error: $errorMessage"
            _success.value = false
        } catch (e: SocketTimeoutException) {
            _message.value = "Error: Timeout! ${e.message}"
            _success.value = false
        }

    }

    suspend fun uploadImage(imageFile: File) {
        val message = repository.uploadImage(imageFile)
        _imageFile.value = "Success: ${message.attachmentUrl}"
    }
}