package ru.kiloqky.tipadzen.data.api

import com.google.android.gms.tasks.Task
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.Flow
import ru.kiloqky.tipadzen.data.db.entities.PostEntity

interface ApiWorker {
    suspend fun addPost(postEntity: PostEntity): Task<Void>
    fun getPosts(childEvenListenner: ChildEventListener): ChildEventListener
    fun getPosts(valueEventListener: ValueEventListener): ValueEventListener
}