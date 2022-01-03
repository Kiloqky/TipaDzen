package ru.kiloqky.tipadzen.data.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow
import ru.kiloqky.tipadzen.data.db.entities.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM post ORDER BY date DESC")
    fun getAll(): Flow<List<PostEntity>>

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(list: List<PostEntity>)

    @Insert(onConflict = IGNORE)
    suspend fun insert(post: PostEntity)

    @Delete
    suspend fun delete(post: PostEntity)

    @Update
    suspend fun update(post: PostEntity)

    @Query("DELETE FROM post WHERE sha == :sha")
    suspend fun deleteById(sha: String)

    @Query("SELECT * FROM post WHERE sha== :sha")
    suspend fun getById(sha: String): List<PostEntity>
}