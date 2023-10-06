package com.example.fundametalsubmission.berkas.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundametalsubmission.berkas.api.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetailViewModel: ViewModel() {

    val user = MutableLiveData<DetailRes>()

    fun setUserDetail(username : String){
        RetrofitApi.apiInstance
            .getUserDetail(username)
            .enqueue(object  : Callback<DetailRes>{
                override fun onResponse(call: Call<DetailRes>, response: Response<DetailRes>) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailRes>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailRes>{
        return user
    }

}