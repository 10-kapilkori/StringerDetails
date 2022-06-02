package com.task.stringerlisttest.retrofit

import com.task.stringerlisttest.model.*
import retrofit2.Call
import retrofit2.http.*

interface API {
    @Headers("Authorization: tpbf49bdlr9202103tsptatps", "ShopID: 1")
    @GET("stringerlist")
    fun getStringerList(): Call<List<StringerModel>>

    @POST("stringerDetail")
    @Headers("Authorization: tpbf49bdlr9202103tsptatps", "ShopID: 1")
    fun addStringer(@Body model: StringerRequestModel): Call<StringerResponseModel>

    @Headers("Authorization: tpbf49bdlr9202103tsptatps", "ShopID: 1")
    @PUT("stringerDetail")
    fun updateStringer(@Body model: StringerRequestModel2): Call<StringerResponseModel>

    @Headers("Authorization: tpbf49bdlr9202103tsptatps", "ShopID: 1")
    @HTTP(method = "DELETE", path = "stringerDetail", hasBody = true)
    fun deleteStringer(@Body id: StringerResponseModel): Call<StringerResponseModel>
}