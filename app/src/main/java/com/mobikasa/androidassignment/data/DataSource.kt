package com.mobikasa.androidassignment.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobikasa.androidassignment.api.APIService
import com.mobikasa.androidassignment.models.Restaurant
import retrofit2.HttpException
import java.io.IOException

class DataSource(var query: String, var apiService: APIService) : PagingSource<Int, Restaurant>() {

    override fun getRefreshKey(state: PagingState<Int, Restaurant>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.fetchRestaurant(query, nextPageNumber)
            return if (response.code() == 200) {
                val data = response.body()
                val totalData = data?.resultsFound
                val nextKey = if (nextPageNumber >= totalData!!) null else nextPageNumber + 20
                LoadResult.Page(
                    data = data.restaurants!!,
                    prevKey = null,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(
                    Exception("Something went Wrong..")
                )
            }
        } catch (e: IOException) {
            return LoadResult.Error(
                e
            )
        } catch (e: HttpException) {
            return LoadResult.Error(
                e
            )
        }
    }
}