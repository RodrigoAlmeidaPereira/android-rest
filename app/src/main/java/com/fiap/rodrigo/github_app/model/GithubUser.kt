package com.fiap.rodrigo.github_app.model

import com.google.gson.annotations.SerializedName

data class GithubUser (

    @SerializedName("name") val name: String = "Username",
    @SerializedName("avatar_url") val avatarURL: String

)
