package com.sewain.mobileapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateDetailUserResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results: DetailUserResults? = null
)

data class DetailUserResults(

	@field:SerializedName("results")
	val results: Results? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("social_media_id")
	val socialMediaId: String? = null,

	@field:SerializedName("address_user_id")
	val addressUserId: String? = null,

	@field:SerializedName("detail_shop_id")
	val detailShopId: String? = null,

	@field:SerializedName("users_id")
	val usersId: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("number_phone")
	val numberPhone: String? = null
)
