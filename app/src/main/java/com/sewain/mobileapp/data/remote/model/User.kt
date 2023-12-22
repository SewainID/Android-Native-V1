package com.sewain.mobileapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class User(
    val username: String,

    val email: String,

    @field:SerializedName("DetailsUser")
    val detailUser: DetailsUser,
)