package ru.kiloqky.tipadzen.helpers.methods.extensions

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "dd:MM:yyyy HH:mm"

fun Long.toDateFormat(): String = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(this))
