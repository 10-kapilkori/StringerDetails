package com.task.stringerlisttest.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {

    fun getInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .header(
                        "Authorization",
                        "tpbf49bdlr9202103tsptatps"
                    )
                    .header(
                        "ShopID",
                        "1"
                    )
                    .build()
                chain.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl("https://tennistest.azurewebsites.net/interview/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}