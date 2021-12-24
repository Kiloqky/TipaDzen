package ru.kiloqky.tipadzen

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.kiloqky.tipadzen.data.api.ApiWorker
import ru.kiloqky.tipadzen.data.db.AppDatabase
import ru.kiloqky.tipadzen.data.db.entities.PostEntity
import timber.log.Timber
import java.security.PrivateKey
import javax.inject.Inject

class BoundService @Inject constructor(
    private val apiWorker: ApiWorker,
    private val coroutineScope: CoroutineScope,
    private val appDatabase: AppDatabase
) : Service() {

    override fun onCreate() {
        super.onCreate()
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
                Timber.wtf(error.toException())
            }
        })
    }

    override fun onBind(intent: Intent): IBinder {
        
    }
}