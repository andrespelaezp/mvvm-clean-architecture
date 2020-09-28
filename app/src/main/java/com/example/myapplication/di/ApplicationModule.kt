package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.RedditApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(
    private val application: RedditApplication
) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = application

}