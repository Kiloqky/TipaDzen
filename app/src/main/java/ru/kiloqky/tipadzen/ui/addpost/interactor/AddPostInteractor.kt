package ru.kiloqky.tipadzen.ui.addpost.interactor

import com.google.android.gms.tasks.Task
import ru.kiloqky.tipadzen.data.api.ApiWorker
import ru.kiloqky.tipadzen.data.db.entities.PostEntity
import javax.inject.Inject

class AddPostInteractor @Inject constructor(
    private val apiWorker: ApiWorker
) {

    suspend fun addPost(sha: String, title: String, text: String): Task<Void> {
        val post = PostEntity(sha, title, text)
        return apiWorker.addPost(post)
    }
}
