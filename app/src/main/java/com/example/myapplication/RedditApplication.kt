package com.example.myapplication

import com.example.myapplication.di.ApiModule
import com.example.myapplication.di.ApplicationModule
import com.example.myapplication.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector

class RedditApplication : DaggerApplication(), HasActivityInjector {

    init {
        INSTANCE = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .apiModule(ApiModule(getString(R.string.base_url)))
            .applicationModule(ApplicationModule(this))
            .create(this)
    }

    companion object {
        @JvmStatic
        lateinit var INSTANCE: RedditApplication
    }
}