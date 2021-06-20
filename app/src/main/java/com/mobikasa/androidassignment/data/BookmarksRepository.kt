package com.mobikasa.androidassignment.data

import com.mobikasa.androidassignment.db.BookMarkEntity
import com.mobikasa.androidassignment.db.BookmarkDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookmarksRepository(var mBookmarkDatabase: BookmarkDatabase) {

    suspend fun addData(mData: BookMarkEntity) {
        withContext(Dispatchers.IO) {
            mBookmarkDatabase.getBookMarkDao().addBookMarks(mData)
        }
    }

    suspend fun fetchAllData(): List<BookMarkEntity> {
        var mBookMarkData: List<BookMarkEntity>
        withContext(Dispatchers.IO) {
            mBookMarkData = mBookmarkDatabase.getBookMarkDao().getAllData()
        }
        return mBookMarkData
    }

}