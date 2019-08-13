package com.fiap.rodrigo.github_app.service

import com.fiap.rodrigo.github_app.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {

    @GET("users/{username}")
    fun find(@Path("username") user: String): Call<GithubUser>

}