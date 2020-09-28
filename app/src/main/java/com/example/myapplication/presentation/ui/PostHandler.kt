package com.example.myapplication.presentation.ui

import com.example.myapplication.data.entities.Post

interface PostHandler {

    fun navigateToPost(post: Post)
}