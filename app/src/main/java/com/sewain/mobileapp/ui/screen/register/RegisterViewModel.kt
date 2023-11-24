package com.sewain.mobileapp.ui.screen.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sewain.mobileapp.data.SewainRepository
import com.sewain.mobileapp.data.UserRepository
import com.sewain.mobileapp.data.remote.model.Register
import com.sewain.mobileapp.data.remote.response.RegisterErrorResponse
import com.sewain.mobileapp.data.remote.response.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

        private val _signupMessage = MutableLiveData<String>()
        val signupMessage: LiveData<String>
            get() = _signupMessage

        private val _signupSuccess = MutableLiveData<Boolean>()
        val signupSuccess: LiveData<Boolean>
            get() = _signupSuccess

        private val _isSignupLoading = MutableLiveData<Boolean>()
        val isSignupLoading: LiveData<Boolean>
            get() = _isSignupLoading

        fun register(name: String, email: String, password: String) {
            viewModelScope.launch {
                _isSignupLoading.postValue(true)
                try {
                    //get success message
                    val message = repository.registerUser(name, email, password)
                    _signupMessage.postValue("Success: ${message.message}")
                    _signupSuccess.postValue(true)
                } catch (e: HttpException) {
                    //get error message
                    val jsonInString = e.response()?.errorBody()?.string()
                    val errorBody = Gson().fromJson(jsonInString, RegisterErrorResponse::class.java)
                    val errorMessage = errorBody.message
                    _signupMessage.postValue("Error: $errorMessage")
                    _signupSuccess.postValue(false)
                } catch (e: SocketTimeoutException) {
                    _signupMessage.postValue("Error: Timeout! ${e.message}")
                    _signupSuccess.postValue(false)
                }
                _isSignupLoading.postValue(false)
            }
        }
    }