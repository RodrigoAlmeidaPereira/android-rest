package com.fiap.rodrigo.github_app

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fiap.rodrigo.github_app.service.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build())
    .build()

val gitHubService = retrofit.create(GithubService::class.java)
