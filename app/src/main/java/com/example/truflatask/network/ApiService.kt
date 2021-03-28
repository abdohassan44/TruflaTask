package com.example.truflatask.network

import com.example.truflatask.database.Repo
import com.example.truflatask.presentaion.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constant.SERVICE_REPOS)
    fun getRepos(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int? = 15
    ): Call<List<Repo>>
}