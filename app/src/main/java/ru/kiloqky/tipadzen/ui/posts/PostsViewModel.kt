package ru.kiloqky.tipadzen.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.model.PostModel
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationController
import ru.kiloqky.tipadzen.ui.posts.interactor.PostsInteractor
import ru.kiloqky.tipadzen.ui.posts.state.Error
import ru.kiloqky.tipadzen.ui.posts.state.PostsState
import ru.kiloqky.tipadzen.ui.posts.state.Success
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val interactor: PostsInteractor,
    private val navController: NavigationController
) : ViewModel() {

    private val _uiState: MutableSharedFlow<PostsState> = MutableSharedFlow(replay = 1)
    val uiState: SharedFlow<PostsState> = _uiState

    fun start() {
        getPosts()
        subscribeToState()
    }

    private fun subscribeToState() {
        viewModelScope.launch {
            interactor.postsFlow
                .onEach { _uiState.tryEmit(Success(it)) }
                .launchIn(this)
            interactor.errorFlow
                .onEach { _uiState.tryEmit(Error(it)) }
                .launchIn(this)
        }
    }

    private fun getPosts() {
        interactor.getPosts()
    }

    fun postClicked(postModel: PostModel) {
        //todo
    }

    fun onMenuItemClicked(itemId: Int): Boolean {
        when (itemId) {
            R.id.add_post -> {
                navController.navigateToAddPost()
            }
        }
        return true
    }
}