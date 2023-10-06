package com.example.fundametalsubmission.berkas.api

import com.example.fundametalsubmission.berkas.ResUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DataApi {
    @GET("search/users")
    @Headers("Authorization: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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<ResUser>
}
