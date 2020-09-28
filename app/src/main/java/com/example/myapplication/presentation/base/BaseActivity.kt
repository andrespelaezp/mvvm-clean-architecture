package com.example.myapplication.presentation.base

import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity: DaggerAppCompatActivity() {

    override fun onStart() {
        AndroidInjection.inject(this)
        super.onStart()
    }

}