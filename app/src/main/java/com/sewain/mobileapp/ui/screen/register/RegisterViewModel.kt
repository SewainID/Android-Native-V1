package com.sewain.mobileapp.ui.screen.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.remote.response.RegisterErrorResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import com.sewain.mobileapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    private val _signupMessage = MutableStateFlow("")
    val signupMessage: StateFlow<String>
        get() = _signupMessage

    private val _signupSuccess = MutableLiveData<Boolean>()
    val signupSuccess: LiveData<Boolean>
        get() = _signupSuccess

    private val _isSignupLoading = MutableLiveData<Boolean>()
    val isSignupLoading: LiveData<Boolean>
        get() = _isSignupLoading

    suspend fun register(name: String, email: String, password: String) {
        _isSignupLoading.postValue(true)
        try {
            //get success message
            val message = repository.registerUser(name, email, password)
            _signupMessage.value = "Success: ${message.message}"
            _signupSuccess.postValue(true)
        } catch (e: HttpException) {
            //get error message
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, RegisterErrorResponse::class.java)
            val errorMessage = errorBody.message
            _signupMessage.value = "Error: $errorMessage"
            _signupSuccess.postValue(false)
        } catch (e: SocketTimeoutException) {
            _signupMessage.value = "Error: Timeout! ${e.message}"
            _signupSuccess.postValue(false)
        }
        _isSignupLoading.postValue(false)
    }
}