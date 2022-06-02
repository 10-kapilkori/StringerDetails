package com.task.stringerlisttest.model

import com.google.gson.annotations.SerializedName

data class StringerRequestModel(
    @SerializedName("Address")
    val address: String,
    @SerializedName("Age")
    val age: Int,
    @SerializedName("CloseTiming")
    val closeTiming: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PhoneNumber")
    val phoneNumber: String,
    @SerializedName("StartTiming")
    val startTiming: String,
    @SerializedName("ShopID")
    val shopId: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("InsertedBy")
    val insertedBy: String,
    @SerializedName("UpdatedBy")
    val updatedBy: Any? = null
)

data class StringerRequestModel2(
    @SerializedName("Address")
    val address: String = "",
    @SerializedName("Age")
    val age: Int = 19,
    @SerializedName("CloseTiming")
    val closeTiming: String = "",
    @SerializedName("Name")
    val name: String = "",
    @SerializedName("PhoneNumber")
    val phoneNumber: String = "",
    @SerializedName("StartTiming")
    val startTiming: String = "",
    @SerializedName("StringerID")
    val stringerId: Int,
    @SerializedName("Password")
    val password: String = "",
    @SerializedName("UpdatedBy")
    val updatedBy: String = "0"
)