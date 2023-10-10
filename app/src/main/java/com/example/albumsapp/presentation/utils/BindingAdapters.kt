package com.example.albumsapp.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:goneUnless")
    fun View.goneUnless(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:invisibleUnless")
    fun View.invisibleUnless(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}