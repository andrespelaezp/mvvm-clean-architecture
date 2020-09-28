package com.example.myapplication.di

import com.example.myapplication.domain.common.ASyncTransformer
import com.example.myapplication.domain.repository.PostsRepository
import com.example.myapplication.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providePostsUseCase(postsRepository: PostsRepository): GetPostsUseCase {
        return GetPostsUseCase(ASyncTransformer(), postsRepository)
    }

}