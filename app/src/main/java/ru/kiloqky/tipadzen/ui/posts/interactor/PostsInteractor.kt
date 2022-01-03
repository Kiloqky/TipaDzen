package ru.kiloqky.tipadzen.ui.posts.interactor

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.kiloqky.tipadzen.data.api.ApiWorker
import ru.kiloqky.tipadzen.data.db.AppDatabase
import ru.kiloqky.tipadzen.data.db.entities.PostEntity
import ru.kiloqky.tipadzen.data.mappers.post.PostMapper
import timber.log.Timber
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val apiWorker: ApiWorker,
    private val appDatabase: AppDatabase,
    private val coroutineScope: CoroutineScope
) {

    val postsFlow = appDatabase.postDao().getAll().map { PostMapper.entityToModel(it) }

    val errorFlow = MutableSharedFlow<Throwable>()

    fun getPosts() {
        addPostsListener()
    }

    private fun addPostsListener() {
//        apiWorker.getPosts(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                coroutineScope.launch {
//                    dataSnapshot.children.forEach { snapshot ->
//                        snapshot.getValue<PostEntity>()?.let { appDatabase.postDao().insert(it) }
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                Timber.wtf(databaseError.toException())
//            }
//        })
        apiWorker.getPosts(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                coroutineScope.launch {
                    snapshot.getValue<PostEntity>()?.let { appDatabase.postDao().insert(it) }
                    Timber.i("added")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                coroutineScope.launch {
                    snapshot.getValue<PostEntity>()?.let { appDatabase.postDao().update(it) }
                    Timber.i("changed")
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                coroutineScope.launch {
                    snapshot.getValue<PostEntity>()?.let { appDatabase.postDao().delete(it) }
                    Timber.i("removed")
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Timber.wtf("moved")
            }

            override fun onCancelled(error: DatabaseError) {
                coroutineScope.launch {
                    errorFlow.emit(error.toException())
                }
            }
        })
    }
}