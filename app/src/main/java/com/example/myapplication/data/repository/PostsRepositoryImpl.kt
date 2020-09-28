package com.example.myapplication.data.repository

import com.example.myapplication.api.PostService
import com.example.myapplication.data.entities.PostsResult
import com.example.myapplication.domain.repository.PostsRepository
import io.reactivex.Observable
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postService: PostService
): PostsRepository {

    override fun getPosts(): Observable<PostsResult> {
        return postService.getPosts()
    }

}