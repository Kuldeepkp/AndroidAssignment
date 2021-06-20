package com.mobikasa.androidassignment

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private lateinit var mInstance: MyApp

        @Synchronized
        fun getInstance(): MyApp {
            return mInstance
        }
    }


}