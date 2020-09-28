package com.example.myapplication.presentation.ui.screens.posts

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.usecase.GetPostsUseCase
import com.example.myapplication.presentation.base.BaseViewModel
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase
) : BaseViewModel() {

    var viewState: MutableLiveData<PostsViewState> = MutableLiveData()

    init {
        viewState.value = PostsViewState()
    }

    fun getPosts() {
        viewState.value?.copy(loading = true)

        addDisposable(postsUseCase.observable()
            .subscribe({ posts ->
                viewState.value?.let {
                    val newState = this.viewState.value?.copy(loading = false, posts = posts.data)
                    this.viewState.value = newState
                }
            }, {
                viewState.value = viewState.value?.copy(loading = false)
            }))
    }

}