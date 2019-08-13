package com.fiap.rodrigo.github_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiap.rodrigo.github_app.model.GithubUser
import com.fiap.rodrigo.github_app.repository.GithubUserRepository
import com.fiap.rodrigo.github_app.repository.GithubUserRepositoryImpl


class MainViewModel : ViewModel() {
    var userRepository: GithubUserRepository = GithubUserRepositoryImpl(gitHubService)

    val userResponse = MutableLiveData<GithubUser>()
    val loading = MutableLiveData<Boolean>()
    val messageError = MutableLiveData<String>()

    fun find(username: String) {
        loading.value = true
        userRepository.find(username,
            onComplete = {
                userResponse.value = it
                messageError.value = ""
                loading.value = false
            },
            onError = {
                messageError.value = it?.message
                loading.value = false
            })
    }
}
