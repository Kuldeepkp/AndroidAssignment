package com.mobikasa.androidassignment.models
import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("text")
    val text: String?
)