package com.mobikasa.androidassignment.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookMarkEntity(@PrimaryKey val uid:String,val name:String?,val avg_price:String?)