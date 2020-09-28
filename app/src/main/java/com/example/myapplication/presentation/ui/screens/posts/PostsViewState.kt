package com.example.myapplication.presentation.ui.screens.posts

import com.example.myapplication.data.entities.PostsData

data class PostsViewState(
    var loading: Boolean = true,
    var posts: PostsData? = null
)