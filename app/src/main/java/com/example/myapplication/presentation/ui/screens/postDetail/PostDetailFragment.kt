package com.example.myapplication.presentation.ui.screens.postDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.data.entities.Post
import com.example.myapplication.databinding.FragmentPostDetailBinding
import com.example.myapplication.presentation.base.BaseFragment

class PostDetailFragment: BaseFragment() {

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {bundle ->
            bundle.getParcelable<Post>(postParam)?.let {
                post = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        binding.post = post

        return binding.root
    }

    companion object {
        private const val postParam = "Param:Post"

        fun newInstance(post: Post): PostDetailFragment = PostDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(postParam, post)
            }
        }
    }

}