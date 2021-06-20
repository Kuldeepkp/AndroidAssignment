package com.mobikasa.androidassignment.models
import com.google.gson.annotations.SerializedName

data class RatingObj(
    @SerializedName("bg_color")
    val bgColor: BgColor?,
    @SerializedName("title")
    val title: Title?
)