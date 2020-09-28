package com.example.myapplication.data.entities

data class PostsResult(
    val kind: String,
    val data: List<PostsData>
)

data class PostsData(
    val children: List<Children>
)

data class Children(
    val posts: List<Post>
)