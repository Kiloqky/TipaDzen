package ru.kiloqky.tipadzen

import android.app.Application
import androidx.work.Configuration
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp
import ru.kiloqky.tipadzen.factory.AddPostWorkerFactory
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: AddPostWorkerFactory

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(applicationContext)
        Timber.plant(DebugTree())
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}