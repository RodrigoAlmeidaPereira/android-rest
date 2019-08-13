package com.fiap.rodrigo.github_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.fiap.rodrigo.github_app.model.GithubUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_loading.*


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btSearch.setOnClickListener {
            mainViewModel.find(inputUser.text.toString())
        }

        registerObserver()
    }

    private fun registerObserver() {
        mainViewModel.userResponse.observe(this, androidx.lifecycle.Observer {
            setUser(it)
        })
        mainViewModel.loading.observe(this, androidx.lifecycle.Observer {
            if(it == true)
                showLoading()
            else
                hideLoading()
        })
        mainViewModel.messageError.observe(this, androidx.lifecycle.Observer {
            if(it != "")
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setUser(user: GithubUser) {
        tvUser.text = user?.name
        Picasso.get()
            .load(user?.avatarURL)
            .into(ivUser)
    }

    private fun showLoading() {
        containerLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        containerLoading.visibility = View.GONE
""
    }
}
