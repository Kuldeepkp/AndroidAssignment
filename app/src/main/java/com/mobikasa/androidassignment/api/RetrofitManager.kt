package com.mobikasa.androidassignment.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {

    companion object {
        private const val BASE_URL = "https://developers.zomato.com/api/v2.1/"
        private const val API_KEY = "41a5401d2f4bb7aba885fe8d6d1f3d69"

        private val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val request =
                it.request().newBuilder().addHeader("user-key", API_KEY)
                    .build()
            it.proceed(request)
        }.build()

        private val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(
                    okHttpClient
                ).build()

        fun getService(): APIService {
            return retrofit.create(APIService::class.java)
        }
    }
}