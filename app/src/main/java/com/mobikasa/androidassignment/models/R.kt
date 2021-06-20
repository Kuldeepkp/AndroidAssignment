package com.mobikasa.androidassignment.models
import com.google.gson.annotations.SerializedName

data class R(
    @SerializedName("has_menu_status")
    val hasMenuStatus: HasMenuStatus?,
    @SerializedName("is_grocery_store")
    val isGroceryStore: Boolean?,
    @SerializedName("res_id")
    val resId: Int?
)