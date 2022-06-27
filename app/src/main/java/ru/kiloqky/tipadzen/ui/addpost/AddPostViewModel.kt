package ru.kiloqky.tipadzen.ui.addpost

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.kiloqky.tipadzen.R
import ru.kiloqky.tipadzen.helpers.workers.AddPostWorker
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationController
import ru.kiloqky.tipadzen.ui.addpost.state.AddPostState
import ru.kiloqky.tipadzen.ui.addpost.state.Success
import ru.kiloqky.tipadzen.ui.addpost.state.Warning
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AddPostViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val navController: NavigationController,
    private val resources: Resources
) : ViewModel() {

    private val workManager = WorkManager.getInstance(context)

    val titleFlow = MutableStateFlow("")

    val textFlow = MutableStateFlow("")

    private val _uiState: MutableSharedFlow<AddPostState> = MutableSharedFlow(1)
    val uiState = _uiState.asSharedFlow()

    fun onItemSelected(itemId: Int) =
        when (itemId) {
            R.id.checked -> {
                addPost()
                true
            }
            android.R.id.home -> {
                navController.back()
                true
            }
            else -> false
        }


    private fun addPost() {
        viewModelScope.launch {
            if (validatePost()) {
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED).build()
                val workRequest = OneTimeWorkRequestBuilder<AddPostWorker>()
                    .setInputData(
                        workDataOf(
                            AddPostWorker.TEXT to textFlow.value,
                            AddPostWorker.TITLE to titleFlow.value
                        )
                    )
                    .setBackoffCriteria(BackoffPolicy.LINEAR, 1, TimeUnit.SECONDS)
                    .setConstraints(constraints)
                    .build()

                workManager.enqueueUniqueWork(
                    UUID.randomUUID().toString(),
                    ExistingWorkPolicy.APPEND_OR_REPLACE,
                    workRequest
                )

                _uiState.tryEmit(Success)
            }
        }
    }

    private fun validatePost() =
        if (titleFlow.value.isNotBlank() && textFlow.value.isNotBlank()) {
            true
        } else {
            _uiState.tryEmit(Warning(resources.getString(R.string.error_field_is_empty)))
            false
        }


    fun handleSuccess() {
        navController.back()
    }
}