package com.example.myapplication.data.entities

data class PostsResult(
    val kind: String,
    val data: PostsData
)

data class PostsData(
    val children: List<Children>
)

data class Children(
    val data: Post
)