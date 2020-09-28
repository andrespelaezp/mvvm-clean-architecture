package com.example.myapplication.api

import com.example.myapplication.data.entities.PostsResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("/r/all/top/.json")
    fun getPosts(@Query("t") t: String = "all", @Query("limit") limit: Int = 10): Observable<PostsResult>

}