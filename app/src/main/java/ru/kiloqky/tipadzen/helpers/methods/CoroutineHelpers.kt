package ru.kiloqky.tipadzen.helpers.methods

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO

fun BackgroundScope() = CoroutineScope(IO)
fun DefaultScope() = CoroutineScope(Default)