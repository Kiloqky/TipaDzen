package ru.kiloqky.tipadzen.helpers.methods.extensions

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchWhenStarted(viewLifecycleOwner: LifecycleOwner) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@launchWhenStarted.collect()
        }
    }
}

fun <T> Flow<T>.launchWhenCreated(viewLifecycleOwner: LifecycleOwner) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            this@launchWhenCreated.collect()
        }
    }
}

fun <T> Flow<T>.launchWhenResumed(viewLifecycleOwner: LifecycleOwner) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            this@launchWhenResumed.collect()
        }
    }
}