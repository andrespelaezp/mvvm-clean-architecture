package com.example.myapplication.presentation.ui

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.data.entities.Post
import com.example.myapplication.presentation.base.BaseActivity
import com.example.myapplication.presentation.ui.screens.postDetail.PostDetailFragment
import com.example.myapplication.presentation.ui.screens.posts.PostsFragment

class MainActivity : BaseActivity(), PostHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.container, PostsFragment.newInstance()).commit()
            }
        }
    }

    override fun navigateToPost(post: Post) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, PostDetailFragment.newInstance(post)).addToBackStack(null).commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount >= 1)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }

}