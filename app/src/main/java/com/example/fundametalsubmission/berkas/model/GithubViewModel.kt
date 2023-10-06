package com.example.fundametalsubmission.berkas.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundametalsubmission.berkas.ResUser
import com.example.fundametalsubmission.berkas.api.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()


    fun setSearchUsers(query : String){
        RetrofitApi.apiInstance
            .getSearchUsers(query)
            .enqueue(object  : Callback<ResUser>{
                override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    } else {
                        Log.d("Fail", "Error: ${response.code()} - ${response.message()}")

                    }
                }

                override fun onFailure(call: Call<ResUser>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }

            })
    }



    fun getSearchUser() : LiveData<ArrayList<User>>{
        return  listUsers
    }
    fun getUsersLiveData(): LiveData<ArrayList<User>>{
        return  listUsers
    }
}

