package com.example.myapplication.di

import com.example.myapplication.presentation.ui.screens.postDetail.PostDetailFragment
import com.example.myapplication.presentation.ui.screens.posts.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindPostsFragment(): PostsFragment

    @ContributesAndroidInjector
    internal abstract fun bindPostDetailFragment(): PostDetailFragment

}