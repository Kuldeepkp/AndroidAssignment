package com.mobikasa.androidassignment.models
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("review")
    val review: List<Any>?
)