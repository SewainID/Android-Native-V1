package com.sewain.mobileapp.ui.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sewain.mobileapp.data.SewainRepository
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.remote.response.RegisterErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.net.SocketTimeoutException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    private val _signInMessage = MutableStateFlow("")
    val signInMessage: StateFlow<String>
        get() = _signInMessage

    private val _signInSuccess = MutableLiveData<Boolean>()
    val signInSuccess: LiveData<Boolean>
        get() = _signInSuccess

    private val _isSignInLoading = MutableLiveData<Boolean>()
    val isSignInLoading: LiveData<Boolean>
        get() = _isSignInLoading

    suspend fun login(email: String, password: String) {
        _isSignInLoading.postValue(true)
        try {
            //get success message
            val message = repository.loginUser(email, password)
            _signInMessage.value = "Success: ${message.message}"
            _signInSuccess.postValue(true)
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, RegisterErrorResponse::class.java)
            val errorMessage = errorBody.message
            _signInMessage.value = "Error: $errorMessage"
            _signInSuccess.postValue(false)
        } catch (e: SocketTimeoutException) {
            _signInMessage.value = "Error: Timeout! ${e.message}"
            _signInSuccess.postValue(false)
        }
        _isSignInLoading.postValue(false)
    }
}