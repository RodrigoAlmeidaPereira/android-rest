package com.fiap.rodrigo.github_app.repository

import com.fiap.rodrigo.github_app.model.GithubUser

interface GithubUserRepository {

    fun find(
        username: String,
        onComplete: (GithubUser?) -> Unit,
        onError: (Throwable?) -> Unit
    )

}