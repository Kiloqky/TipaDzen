package ru.kiloqky.tipadzen.helpers.extensions

import android.view.View

inline fun View.click(crossinline action: (view: View) -> Unit) {
    setOnClickListener {
        action.invoke(it)
    }
}