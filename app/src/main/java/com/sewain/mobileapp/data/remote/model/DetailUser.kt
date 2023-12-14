package com.sewain.mobileapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class DetailUser(
    @field:SerializedName("full_name")
    val fullName: String
)
