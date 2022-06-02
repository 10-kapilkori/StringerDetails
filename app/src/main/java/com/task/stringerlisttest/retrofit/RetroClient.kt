package com.task.stringerlisttest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://tennistest.azurewebsites.net/interview/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}