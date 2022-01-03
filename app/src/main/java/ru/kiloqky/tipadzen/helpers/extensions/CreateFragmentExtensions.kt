package ru.kiloqky.tipadzen.helpers.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T : Fragment> T.initArguments(putArguments: Bundle.() -> Unit): T = apply {
    arguments = Bundle().apply(putArguments)
}