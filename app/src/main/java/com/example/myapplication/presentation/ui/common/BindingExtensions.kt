package com.example.myapplication.presentation.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .onlyRetrieveFromCache(true)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.reddit_thumbnail)
        .into(view)
}