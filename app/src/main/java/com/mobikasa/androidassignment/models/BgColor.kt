package com.mobikasa.androidassignment.models
import com.google.gson.annotations.SerializedName

data class BgColor(
    @SerializedName("tint")
    val tint: String?,
    @SerializedName("type")
    val type: String?
)