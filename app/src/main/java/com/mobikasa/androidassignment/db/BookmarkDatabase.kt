package com.mobikasa.androidassignment.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobikasa.androidassignment.MyApp

@Database(entities = [BookMarkEntity::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun getBookMarkDao(): BookMarksDao

    companion object {
        fun getDataBase(): BookmarkDatabase {
            return Room.databaseBuilder(
                MyApp.getInstance().applicationContext,
                BookmarkDatabase::class.java,
                "bookmark-db"
            ).build()
        }
    }
}