package com.example.myapplication.data.entities

data class Post(
    val title: String,
    val numComments: Int,
    val thumbnail: String,
    val score: Int,
    val id :String,
    val author: String
) {

    fun commentsText(): String = "$numComments comments"
}