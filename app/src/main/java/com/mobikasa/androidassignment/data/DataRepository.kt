package com.mobikasa.androidassignment.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobikasa.androidassignment.api.APIService
import com.mobikasa.androidassignment.models.Restaurant
import kotlinx.coroutines.flow.Flow

class DataRepository(var apiService: APIService) {

    fun fetchData(query: String,): Flow<PagingData<Restaurant>> {
        return Pager(
            PagingConfig(pageSize = 20,prefetchDistance = 10)
        ) {
            DataSource(query, apiService)
        }.flow
    }
}