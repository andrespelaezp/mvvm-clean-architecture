package com.example.myapplication.presentation.ui.screens.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.data.entities.Post
import com.example.myapplication.databinding.FragmentPostsBinding
import com.example.myapplication.di.ViewModelProviderFactory
import com.example.myapplication.presentation.base.BaseFragment
import com.example.myapplication.presentation.ui.PostHandler
import com.example.myapplication.presentation.ui.adapters.PostAdapter
import kotlinx.android.synthetic.main.list_item_post.*
import javax.inject.Inject

class PostsFragment: BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

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

        postsAdapter = PostAdapter({
                handleClick(it)
            },
            { post ->
                handleDismiss(post)
            }
        )

        binding.postsList.adapter = postsAdapter
        binding.swipeContainer.setOnRefreshListener(this)
        binding.txtDismiss.setOnClickListener {
            dismissPosts()
        }

        observeViewState(binding)

        return binding.root
    }

    private fun observeViewState(binding: FragmentPostsBinding) {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {viewState ->
            handleViewState(binding, viewState)
        })
    }

    private fun handleViewState(binding: FragmentPostsBinding, state: PostsViewState?) {
        if (state == null)
            return

        postsAdapter.submitList(state.posts?.children)

        if (!state.loading)
            binding.swipeContainer.isRefreshing = false
    }

    override fun onRefresh() {
        viewModel.getPosts()
    }

    fun handleClick(post: Post) {
        activity?.let { if (it is PostHandler) it.navigateToPost(post) }
    }

    fun handleDismiss(post: Post) {
        viewModel.viewState.value?.posts?.children?.let {
            val copyList = it
            var itemIndex: Int = 0
            postsAdapter.currentList.mapIndexed { index, children ->
                if (children.data.id == post.id) {
                    itemIndex = index
                }
            }
            copyList.removeAt(itemIndex)
            postsAdapter.submitList(copyList)
            postsAdapter.notifyItemRemoved(itemIndex)
        }
    }

    private fun dismissPosts() {
        viewModel.viewState.value?.posts?.children?.clear()
        postsAdapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): PostsFragment = PostsFragment()
    }
}