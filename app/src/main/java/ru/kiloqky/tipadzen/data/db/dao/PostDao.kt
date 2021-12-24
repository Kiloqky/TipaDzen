package ru.kiloqky.tipadzen.data.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow
import ru.kiloqky.tipadzen.data.db.entities.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAll(): Flow<List<PostEntity>>

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(list: List<PostEntity>)

    @Insert(onConflict = IGNORE)
    suspend fun insert(post: PostEntity)

    @Delete
    suspend fun delete(post: PostEntity)

    @Update
    suspend fun update(post: PostEntity)
}