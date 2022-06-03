package com.task.stringerlisttest.retrofit

import com.task.stringerlisttest.model.*
import retrofit2.Call
import retrofit2.http.*

interface API {
    @GET("stringerlist")
    fun getStringerList(): Call<List<StringerModel>>

    @POST("stringerDetail")
    fun addStringer(@Body model: StringerRequestModel): Call<StringerResponseModel>

    @PUT("stringerDetail")
    fun updateStringer(@Body model: StringerRequestModel2): Call<StringerResponseModel>

    @HTTP(method = "DELETE", path = "stringerDetail", hasBody = true)
    fun deleteStringer(@Body id: StringerResponseModel): Call<StringerResponseModel>
}