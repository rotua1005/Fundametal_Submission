package com.example.fundametalsubmission.berkas.api

import com.example.fundametalsubmission.berkas.config.Config
import com.example.fundametalsubmission.berkas.ResUser
import com.example.fundametalsubmission.berkas.detail.DetailRes
import com.example.fundametalsubmission.berkas.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {

    @GET("users/{username}")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getUserDetail(
        @Path("username") username : String
    ):Call<DetailRes>

    @GET("search/users")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getFollowers(
        @Path("username") username : String
    ):Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: ${Config.GITHUB_API_TOKEN}")
    fun getFollowing(
        @Path("username") username : String
    ):Call<ArrayList<User>>

}
