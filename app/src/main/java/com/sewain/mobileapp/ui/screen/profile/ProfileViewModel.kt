package com.sewain.mobileapp.ui.screen.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.Gson
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.remote.response.ChangePasswordResponse
import com.sewain.mobileapp.data.remote.response.UpdateSocialMediaErrorResponse
import com.sewain.mobileapp.data.remote.response.UpdateUserByIDErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.File
import java.net.SocketTimeoutException

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
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

    private val _imageString = MutableStateFlow("")
    val imageString: StateFlow<String>
        get() = _imageString

    private val _usernameShop = MutableStateFlow("")
    val usernameShop: StateFlow<String>
        get() = _usernameShop

    private val _shopName = MutableStateFlow("")
    val shopName: StateFlow<String>
        get() = _shopName

    private val _shopId = MutableStateFlow("")
    val shopId: StateFlow<String>
        get() = _shopId

    private val _facebook = MutableStateFlow("")
    val facebook: StateFlow<String>
        get() = _facebook

    private val _instagram = MutableStateFlow("")
    val instagram: StateFlow<String>
        get() = _instagram

    private val _tiktok = MutableStateFlow("")
    val tiktok: StateFlow<String>
        get() = _tiktok

    private val _socialMediaId = MutableStateFlow("")
    val socialMediaId: StateFlow<String>
        get() = _socialMediaId

    private val _longitude = MutableStateFlow(0.00)
    val longitude: StateFlow<Double>
        get() = _longitude

    private val _latitude = MutableStateFlow(0.00)
    val latitude: StateFlow<Double>
        get() = _latitude

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun setSession(id: String, token: String, isShop: Boolean) {
        viewModelScope.launch {
            repository.saveSession(id, token, isShop)
        }
    }

    fun getUserById(id: String) {
        viewModelScope.launch {
            val data = repository.getUserById(id).userResults
            _username.value = data?.username.toString()
            _email.value = data?.email.toString()
            _fullName.value = data?.detailsUser?.fullName.toString()
            _imageString.value = data?.detailsUser?.photoUrl.toString()
            _shopId.value = data?.detailsUser?.detailShop?.id.toString()
            _shopName.value = data?.detailsUser?.detailShop?.name.toString()
            _usernameShop.value = data?.detailsUser?.detailShop?.username.toString()
            _socialMediaId.value = data?.detailsUser?.socialMediaUser?.id.toString()
            _facebook.value = data?.detailsUser?.socialMediaUser?.facebookUsername.toString()
            _instagram.value = data?.detailsUser?.socialMediaUser?.instagramUsername.toString()
            _tiktok.value = data?.detailsUser?.socialMediaUser?.tiktokUsername.toString()
        }
    }

    suspend fun updateUser(id: String, fullName: String, username: String, email: String, photoUrl: String) {
        _loading.value = true
        try {
            //get success message
            val message = repository.updateUserById(id, fullName, username, email, photoUrl)
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
        _loading.value = true
        try {
            val data = repository.uploadImage(imageFile)
            _imageString.value = data.attachmentUrl.orEmpty()
            _message.value = "Success: upload image successful."
        } catch (e: HttpException) {
            // get error message
            _message.value = "Error: upload image failure."
        }
    }

    suspend fun changePassword(
        id: String,
        currentPassword: String,
        newPassword: String,
        confirmPassword: String,
    ) {
        _loading.value = true
        try {
            val data = repository.changePassword(id, currentPassword, newPassword, confirmPassword)
            _message.value = "Success: ${data.message}"
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ChangePasswordResponse::class.java)
            val errorMessage = errorBody.message
            _message.value = "Error: $errorMessage"
        } catch (e: SocketTimeoutException) {
            _message.value = "Error: Timeout! ${e.message}"
        }
    }

    suspend fun updateSocialMedia(
        id: String,
        facebook: String,
        instagram: String,
        tiktok: String,
    ) {
        _loading.value = true
        try {
            val data = repository.updateSocialMedia(id, facebook, instagram, tiktok)
            _message.value = "Success: ${data.message}"
            _facebook.value = data.results?.facebookUsername.toString()
            _instagram.value = data.results?.instagramUsername.toString()
            _tiktok.value = data.results?.tiktokUsername.toString()
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, UpdateSocialMediaErrorResponse::class.java)
            val errorMessage = errorBody.message
            _message.value = "Error: $errorMessage"
        } catch (e: SocketTimeoutException) {
            _message.value = "Error: Timeout! ${e.message}"
        }
    }

    fun getDeviceLocation(
        fusedLocationProviderClient: FusedLocationProviderClient
    ) {
        try {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("Test", "Latitude : ${location.latitude}")
                    Log.d("Test", "Longitude : ${location.longitude}")
                    _latitude.value = location.latitude
                    _longitude.value = location.longitude
                }
            }
        } catch (e: SecurityException) {
            // Show error or something
        }
    }
}