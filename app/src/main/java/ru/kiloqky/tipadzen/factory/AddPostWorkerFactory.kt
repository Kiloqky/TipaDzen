package ru.kiloqky.tipadzen.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import ru.kiloqky.tipadzen.helpers.workers.AddPostWorker
import ru.kiloqky.tipadzen.ui.addpost.interactor.AddPostInteractor
import javax.inject.Inject

class AddPostWorkerFactory @Inject constructor(
    private val interactor: AddPostInteractor
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = AddPostWorker(appContext, workerParameters, interactor)
}