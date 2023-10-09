package com.example.fundametalsubmission.berkas.saveFavorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fundametalsubmission.berkas.room.FavoriteUser
import com.example.fundametalsubmission.berkas.room.FavoriteUserDao
import com.example.fundametalsubmission.berkas.room.UserDatabase

class FavoriteModel(application: Application):AndroidViewModel(application){

    private  var userDao: FavoriteUserDao?
    private  var userDb : UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>?{
        return  userDao?.getFavoriteUser()
    }

}