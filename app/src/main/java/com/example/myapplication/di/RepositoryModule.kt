package com.example.myapplication.di

import com.example.myapplication.api.PostService
import com.example.myapplication.data.repository.PostsRepositoryImpl
import com.example.myapplication.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(postService: PostService): PostsRepository {
        return PostsRepositoryImpl(postService)
    }

}