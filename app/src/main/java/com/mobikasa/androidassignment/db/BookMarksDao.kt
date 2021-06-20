package com.mobikasa.androidassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookMarksDao {

    @Query("Select * from bookmarkentity")
    suspend fun getAllData(): List<BookMarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookMarks(vararg mData: BookMarkEntity)
}