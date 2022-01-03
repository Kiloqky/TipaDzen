package ru.kiloqky.tipadzen.ui.posts.state

import ru.kiloqky.tipadzen.model.PostModel

sealed class PostsState
data class Success(val list: List<PostModel>) : PostsState()
data class Error(val throwable: Throwable) : PostsState()
