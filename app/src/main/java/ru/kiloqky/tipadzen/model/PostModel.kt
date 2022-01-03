package ru.kiloqky.tipadzen.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    val sha: String = "",
    val title: String = "",
    var text: String = "",
    val date: Long = 0
): Parcelable
