package com.example.fundametalsubmission.berkas.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundametalsubmission.berkas.api.RetrofitApi
import com.example.fundametalsubmission.berkas.room.FavoriteUser
import com.example.fundametalsubmission.berkas.room.FavoriteUserDao
import com.example.fundametalsubmission.berkas.room.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private  var userDao: FavoriteUserDao?
    private  var userDb : UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    val user = MutableLiveData<DetailRes>()

    fun setUserDetail(username: String) {
        RetrofitApi.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailRes> {
                override fun onResponse(call: Call<DetailRes>, response: Response<DetailRes>) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailRes>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailRes> {
        return user
    }

    fun addToFavorite(username: String, id : Int,avatarUrl:String){
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavoriteUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }
}