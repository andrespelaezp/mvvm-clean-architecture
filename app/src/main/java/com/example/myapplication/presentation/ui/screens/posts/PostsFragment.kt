package com.example.myapplication.presentation.ui.screens.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentPostsBinding
import com.example.myapplication.di.ViewModelProviderFactory
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.ui.PostHandler
import com.example.myapplication.presentation.ui.adapters.PostAdapter
import javax.inject.Inject

class PostsFragment: BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: PostsViewModel
    lateinit var postsAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        viewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)

        if (savedInstanceState == null)
            viewModel.getPosts()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostsBinding.inflate(inflater, container, false)

        postsAdapter = PostAdapter {post ->
            activity?.let {
                if (it is PostHandler)
                    it.navigateToPost(post)
            }
        }

        binding.postsList.adapter = postsAdapter

        observeViewState()

        return binding.root
    }

    private fun observeViewState() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            handleViewState(viewState)
        })
    }

    private fun handleViewState(state: PostsViewState?) {
        if (state == null)
            return

        postsAdapter.submitList(state.posts?.children)
    }

    companion object {
        fun newInstance(): PostsFragment = PostsFragment()
    }
}