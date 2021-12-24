package ru.kiloqky.tipadzen.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Entity(tableName = "post")
@Parcelize
@IgnoreExtraProperties
data class PostEntity(
    @PrimaryKey
    @ColumnInfo(name = "sha")
    val sha: String = "",

    @ColumnInfo(name = "text")
    val text: String = ""
) : Parcelable {

    @Exclude
    fun toMap(): Map<String, Any> =
        mapOf(
            "sha" to sha,
            "text" to text
        )

}