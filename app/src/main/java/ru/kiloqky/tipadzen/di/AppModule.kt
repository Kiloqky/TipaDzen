package ru.kiloqky.tipadzen.di

import android.content.Context
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import ru.kiloqky.tipadzen.BuildConfig
import ru.kiloqky.tipadzen.data.api.ApiWorker
import ru.kiloqky.tipadzen.data.api.ApiWorkerImpl
import ru.kiloqky.tipadzen.data.db.AppDatabase
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationController
import ru.kiloqky.tipadzen.navigation.cicerone.navigationcontroller.NavigationControllerImpl
import ru.kiloqky.tipadzen.navigation.cicerone.screens.Screens
import ru.kiloqky.tipadzen.navigation.cicerone.screens.ScreensImpl
import java.util.concurrent.Executors
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    fun provideScreens(): Screens = ScreensImpl()

    @Provides
    fun provideNavigationController(
        cicerone: Cicerone<Router>,
        screens: Screens
    ): NavigationController =
        NavigationControllerImpl(cicerone.getNavigatorHolder(), cicerone.router, screens)

    @Provides
    @Singleton
    fun providePostsStorage(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun coroutineScope(): CoroutineScope {
        val context: CoroutineContext = Executors.newFixedThreadPool(3).asCoroutineDispatcher()
        return CoroutineScope(context)
    }

    @Provides
    fun provideApiWorker(): ApiWorker =
        ApiWorkerImpl(Firebase.database(BuildConfig.RT_DB_URL).reference)
}