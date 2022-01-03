package ru.kiloqky.tipadzen.ui.addpost.state

sealed class AddPostState
object Success : AddPostState()
data class Error(val throwable: Throwable) : AddPostState()
data class Warning(val message: String) : AddPostState()