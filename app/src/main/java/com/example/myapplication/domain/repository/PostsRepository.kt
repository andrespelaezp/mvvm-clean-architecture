package com.example.myapplication.domain.repository

import com.example.myapplication.data.entities.PostsResult
import io.reactivex.Observable

interface PostsRepository {

    fun getPosts(): Observable<PostsResult>
}