package ru.kiloqky.tipadzen.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kiloqky.tipadzen.BuildConfig
import ru.kiloqky.tipadzen.data.db.dao.PostDao
import ru.kiloqky.tipadzen.data.db.entities.PostEntity

@Database(
    version = BuildConfig.DB_VERSION,
    exportSchema = false,
    entities = [
        PostEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}