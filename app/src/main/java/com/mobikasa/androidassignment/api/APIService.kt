package com.mobikasa.androidassignment.api

import com.mobikasa.androidassignment.models.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("search")
    suspend fun fetchRestaurant(
        @Query("q") query: String,
        @Query("start") start: Int,
        @Query("count") count: Int = 20
    ): Response<ServiceResponse>
}