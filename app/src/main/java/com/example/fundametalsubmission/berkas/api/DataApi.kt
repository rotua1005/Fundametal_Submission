package com.example.fundametalsubmission.berkas.api

import com.example.fundametalsubmission.berkas.Config
import com.example.fundametalsubmission.berkas.ResUser
import com.example.fundametalsubmission.berkas.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {

    @GET("users/{username}")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getUserByUsername(
        @Path("username") username : String
    ):Call<User>

    @GET("search/users")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResUser>
}
