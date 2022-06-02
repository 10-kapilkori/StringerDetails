package com.task.stringerlisttest.model

import com.google.gson.annotations.SerializedName

data class StringerModel(
    @SerializedName("Address")
    val address: String,
    @SerializedName("Age")
    val age: String,
    @SerializedName("CloseTiming")
    val closeTiming: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("StartTiming")
    val startTiming: String,
    @SerializedName("StringerID")
    val stringerId: Int,
    @SerializedName("PhoneNumber")
    val phoneNumber: String
)

data class StringerResponseModel(
    @SerializedName("StringerID")
    val stringerId: Int
)