package com.sewain.mobileapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class DetailUser(
    @field:SerializedName("users_id")
    val usersId: String = "",

    @field:SerializedName("full_name")
    val fullName: String,

    @field:SerializedName("number_phone")
    val numberPhone: String = "+62 888 8888 8888",

    @field:SerializedName("social_media_id")
    val socialMediaId: String = "adaf94af-e9bc-42f6-af11-d50deb092f35",

    @field:SerializedName("address_user_id")
    val addressUserId: String = "adaf94af-e9bc-42f6-af11-d50deb092f35",

    @field:SerializedName("detail_shop_id")
    val detailShopId: String = "3ebeb09b-4402-4a78-aa0f-a253db06fcb4",
)
