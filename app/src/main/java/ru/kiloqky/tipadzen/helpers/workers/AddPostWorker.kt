package ru.kiloqky.tipadzen.helpers.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import ru.kiloqky.tipadzen.ui.addpost.interactor.AddPostInteractor
import timber.log.Timber
import java.util.*

@HiltWorker
class AddPostWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    var interactor: AddPostInteractor
) : CoroutineWorker(context, params) {


    private val title by lazy {
        inputData.getString(TITLE) ?: ""
    }

    private val text by lazy {
        inputData.getString(TEXT) ?: ""
    }

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            val sha = this@AddPostWorker.id.toString()
            return@withContext try {
                val task = interactor.addPost(sha, title, text)
                //fixme костыль, не придумал как сделать
                delay(3000)
                when {
                    task.isSuccessful -> Result.success()
                    else -> Result.retry()
                }
            } catch (t: Throwable) {
                Timber.wtf(t)
                Result.retry()
            }
        }

    companion object {
        const val TITLE = "title"
        const val TEXT = "text"
    }
}