package com.fiap.rodrigo.github_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fiap.rodrigo.github_app.model.GithubUser
import com.fiap.rodrigo.github_app.service.GithubService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSearch.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()
                )
                .build()

            val service = retrofit.create<GithubService>(GithubService::class.java!!)

            service.find(inputUser.text.toString())
                .enqueue(object : Callback<GithubUser> {
                    override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                        if (response.isSuccessful) {
                            tvUser.text = response.body()?.name
                            Picasso.get()
                                .load(response.body()?.avatarURL)
                                .into(ivUser)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Não foi possivel realizar a operação",
                                Toast.LENGTH_LONG)
                            .show()
                        }
                    }

                    override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            t.message,
                            Toast.LENGTH_LONG)
                        .show()
                    }
                })
        }
    }
}
