package com.example.myapplication.di

import com.example.myapplication.presentation.ui.screens.posts.PostsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun bindPostsActivity(): PostsActivity

}