package com.example.myapplication.presentation.ui.screens.posts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPostsBinding
import com.example.myapplication.di.ViewModelProviderFactory
import com.example.myapplication.presentation.ui.adapters.PostAdapter
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: PostsViewModel
    lateinit var postsAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)
        val binding: ActivityPostsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        postsAdapter = PostAdapter()
        binding.postsList.adapter = postsAdapter

        if (savedInstanceState == null) {
            observeViewState()
            viewModel.getPosts()
        } else {
            viewModel.getPosts()
        }
    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer {viewState ->
            handleViewState(viewState)
        })
    }

    private fun handleViewState(state: PostsViewState?) {
        if (state == null)
            return

        postsAdapter.submitList(state.posts?.children)
    }
}