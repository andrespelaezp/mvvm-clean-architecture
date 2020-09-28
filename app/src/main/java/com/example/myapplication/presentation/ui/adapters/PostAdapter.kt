package com.example.myapplication.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.entities.Children
import com.example.myapplication.data.entities.Post
import com.example.myapplication.databinding.ListItemPostBinding

class PostAdapter constructor(
    val clickListener: (post: Post) -> Unit
): ListAdapter<Children, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CropViewHolder(ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as CropViewHolder).bind(plant)
    }

    class CropViewHolder(
        private val binding: ListItemPostBinding,
        val clickListener: (post: Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.post?.let { post ->
                    navigateToPlant(post, it)
                }
            }
        }

        private fun navigateToPlant(
            post: Children,
            view: View
        ) {
            clickListener(post.data)
        }

        fun bind(item: Children) {
            binding.apply {
                post = item
                executePendingBindings()
            }
        }
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<Children>() {

    override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem.data.id == newItem.data.id
    }

    override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }
}