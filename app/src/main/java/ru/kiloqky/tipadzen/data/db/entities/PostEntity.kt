package ru.kiloqky.tipadzen.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "post")
@IgnoreExtraProperties
data class PostEntity(
    @PrimaryKey
    @ColumnInfo(name = "sha")
    val sha: String = "",

    @ColumnInfo(name = "title")
    val title: String = "",

    @ColumnInfo(name = "text")
    val text: String = "",

    @ColumnInfo(name = "date")
    val date: Long = Date().time
) : Parcelable {

    @Exclude
    fun toMap(): Map<String, Any> =
        mapOf(
            "sha" to sha,
            "title" to title,
            "text" to text,
            "date" to date
        )

}