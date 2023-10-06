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

<<<<<<< HEAD

=======
>>>>>>> 98e431471e114ec9cf3b598dd07ac66e44f81c05
    fun setSearchUsers(query : String){
        RetrofitApi.apiInstance
            .getSearchUsers(query)
            .enqueue(object  : Callback<ResUser>{
                override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
                    if(response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
<<<<<<< HEAD
                    } else {
                        Log.d("Fail", "Error: ${response.code()} - ${response.message()}")

=======
>>>>>>> 98e431471e114ec9cf3b598dd07ac66e44f81c05
                    }
                }

                override fun onFailure(call: Call<ResUser>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }

            })
    }

<<<<<<< HEAD


    fun getSearchUser() : LiveData<ArrayList<User>>{
        return  listUsers
    }
    fun getUsersLiveData(): LiveData<ArrayList<User>>{
        return  listUsers
    }
=======
    fun getSearchUser() : LiveData<ArrayList<User>>{
        return  listUsers
    }
>>>>>>> 98e431471e114ec9cf3b598dd07ac66e44f81c05
}

