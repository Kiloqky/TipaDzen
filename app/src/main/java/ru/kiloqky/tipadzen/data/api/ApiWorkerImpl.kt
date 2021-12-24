package ru.kiloqky.tipadzen.data.api

import com.google.android.gms.tasks.Task
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import ru.kiloqky.tipadzen.data.db.entities.PostEntity
import javax.inject.Inject


class ApiWorkerImpl(private val reference: DatabaseReference) : ApiWorker {

    override fun getPosts(childEvenListenner: ChildEventListener) =
        reference.child("posts").addChildEventListener(childEvenListenner)

    override fun getPosts(valueEventListener: ValueEventListener) =
        reference.child("posts").addValueEventListener(valueEventListener)

    override suspend fun addPost(postEntity: PostEntity): Task<Void> {
        val key: String? = reference.child("posts").push().key
        val postValues: Map<String, Any> = postEntity.toMap()
        val childUpdates: MutableMap<String, Any> = HashMap()
        childUpdates["/posts/$key"] = postValues
        return reference.updateChildren(childUpdates)
    }

}