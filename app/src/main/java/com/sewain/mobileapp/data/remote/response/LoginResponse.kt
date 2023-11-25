package com.sewain.mobileapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results: ResultsLogin? = null
)

data class ResultsLogin(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
