package com.sewain.mobileapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetUserbyIDResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("results")
	val results: GetDetailUserResults? = null
)

data class GetDetailUserResults(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("detail_user")
	val detailUser: DetailUser? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class DetailUser(

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

	@field:SerializedName("photo_url")
	val photoUrl: Any? = null,

	@field:SerializedName("number_phone")
	val numberPhone: String? = null
)
