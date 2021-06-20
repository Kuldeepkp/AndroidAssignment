package com.mobikasa.androidassignment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobikasa.androidassignment.api.RetrofitManager
import com.mobikasa.androidassignment.data.BookmarksRepository
import com.mobikasa.androidassignment.data.DataRepository
import com.mobikasa.androidassignment.db.BookMarkEntity
import com.mobikasa.androidassignment.db.BookmarkDatabase
import com.mobikasa.androidassignment.models.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val dataRepository = DataRepository(RetrofitManager.getService())
    private val mBookmarksRepository =
        BookmarksRepository(BookmarkDatabase.getDataBase())

    fun getData(query: String): Flow<PagingData<Restaurant>> {
        return dataRepository.fetchData(query).cachedIn(viewModelScope)
    }

    fun addDataIntoDatabase(mData: BookMarkEntity) {
        viewModelScope.launch {
            mBookmarksRepository.addData(mData)
        }
    }

    var mData1 = MutableLiveData<List<BookMarkEntity>>()
    fun fetchAllData() {
        viewModelScope.launch {
            mData1.postValue(mBookmarksRepository.fetchAllData())
        }
    }
}