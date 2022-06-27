package ru.kiloqky.tipadzen.helpers.methods.extensions

import android.view.View

inline fun View.click(crossinline action: (view: View) -> Unit) {
    setOnClickListener {
        action.invoke(it)
    }
}