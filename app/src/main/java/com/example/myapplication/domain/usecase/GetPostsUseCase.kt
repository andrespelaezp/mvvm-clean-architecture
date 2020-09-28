package com.example.myapplication.domain.usecase

import com.example.myapplication.data.entities.PostsResult
import com.example.myapplication.domain.repository.PostsRepository
import com.example.myapplication.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    transformer: ObservableTransformer<PostsResult, PostsResult>,
    private val postService: PostsRepository
): UseCase<PostsResult>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<PostsResult> {
        return postService.getPosts()
    }

}