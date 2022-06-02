package com.task.stringerlisttest.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.stringerlisttest.retrofit.API
import com.task.stringerlisttest.retrofit.RetroClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "StringerViewModel"

class StringerViewModel : ViewModel() {
    val listMutableLiveData = MutableLiveData<List<StringerModel>>()
    val errorMutableLiveData = MutableLiveData<String>()

    val successMutableLiveData = MutableLiveData<StringerResponseModel>()
    val failureMutableLiveData = MutableLiveData<String>()

    fun getList() {
        val api = RetroClient.getInstance().create(API::class.java)
        val call = api.getStringerList()

        call.enqueue(object : Callback<List<StringerModel>> {
            override fun onResponse(
                call: Call<List<StringerModel>>,
                response: Response<List<StringerModel>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()
                    listMutableLiveData.postValue(list)
                }
            }

            override fun onFailure(call: Call<List<StringerModel>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                errorMutableLiveData.postValue(t.message)
            }
        })
    }

//    TODO Something's wrong here which idk (guessing the null thing with "UpdatedBy" attribute)
    fun addStringer(model: StringerRequestModel) {
        val api = RetroClient.getInstance().create(API::class.java)
        val call = api.addStringer(model)

        call.enqueue(object : Callback<StringerResponseModel> {
            override fun onResponse(
                call: Call<StringerResponseModel>,
                response: Response<StringerResponseModel>
            ) {
                Log.i(TAG, "onResponse: ${response.code()}")
                Log.i(TAG, "onResponse: ${response.body()}")
                Log.i(TAG, "onResponse: ${response.raw()}")
                Log.i(TAG, "onResponse: ${response.message()}")
                Log.i(TAG, "onResponse: ${response.headers()}")
            }

            override fun onFailure(call: Call<StringerResponseModel>, t: Throwable) {

            }
        })
    }

    fun updateStringer(model: StringerRequestModel2) {
        val api = RetroClient.getInstance().create(API::class.java)
        val call = api.updateStringer(model)

        call.enqueue(object : Callback<StringerResponseModel> {
            override fun onResponse(
                call: Call<StringerResponseModel>,
                response: Response<StringerResponseModel>
            ) {
                Log.i(TAG, "onResponse: ${response.body()}")
                Log.i(TAG, "onResponse: ${response.code()}")
                if (response.isSuccessful) {
                    successMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<StringerResponseModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun deleteStringer(id: StringerResponseModel) {
        val api = RetroClient.getInstance().create(API::class.java)
        val call = api.deleteStringer(id)

        call.enqueue(object : Callback<StringerResponseModel> {
            override fun onResponse(
                call: Call<StringerResponseModel>,
                response: Response<StringerResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: ${response.body()}")
                    successMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<StringerResponseModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}