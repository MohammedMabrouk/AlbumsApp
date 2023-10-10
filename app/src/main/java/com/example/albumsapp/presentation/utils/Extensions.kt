package com.example.albumsapp.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun <T> Flow<T>.toStateFlow(initValue: T, viewModelScope: CoroutineScope): StateFlow<T> =
    this.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initValue)

fun <E> Fragment.observe(events: Flow<E>, actions: (it: E) -> Unit): Job {
    return lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {
            events.collect {
                actions.invoke(it)
            }
        }
    }
}