package ru.kiloqky.tipadzen.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import ru.kiloqky.tipadzen.model.PostModel
import ru.kiloqky.tipadzen.ui.posts.interactor.PostsInteractor
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val interactor: PostsInteractor
) : ViewModel() {

    fun start() {
        getPosts()
    }

    private fun getPosts() {
        interactor.getPosts()
    }

    private val _postsFlow = interactor.postsFlow
        .shareIn(viewModelScope, replay = 1, started = SharingStarted.WhileSubscribed())
    val postsFlow: SharedFlow<List<PostModel>> = _postsFlow

    fun addPost(text: String) {
        interactor.addPost(
            UUID.randomUUID().toString(),
            text
        )
    }
}