package com.fiap.rodrigo.github_app

import android.app.Application
import com.facebook.stetho.Stetho

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

}