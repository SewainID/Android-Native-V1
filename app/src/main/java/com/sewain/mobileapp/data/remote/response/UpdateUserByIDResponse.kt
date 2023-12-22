package com.sewain.mobileapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UpdateUserByIDResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results: Results? = null
)

data class Results(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
