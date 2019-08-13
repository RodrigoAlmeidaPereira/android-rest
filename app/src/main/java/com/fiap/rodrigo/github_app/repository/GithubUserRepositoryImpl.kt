package com.fiap.rodrigo.github_app.repository

import com.fiap.rodrigo.github_app.model.GithubUser
import com.fiap.rodrigo.github_app.service.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserRepositoryImpl(val service: GithubService) : GithubUserRepository {

    override fun find(username: String, onComplete: (GithubUser?) -> Unit, onError: (Throwable?) -> Unit) {

        service.find(username)
            .enqueue(object : Callback<GithubUser> {
                override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível carregar os dados"))
                    }
                }

                override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                    onError(t)
                }
            })
    }

}