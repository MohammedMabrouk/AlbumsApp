package com.example.albumsapp.presentation.utils

sealed class ViewState{
    object Idle : ViewState()
    class Loaded(val data: Any) : ViewState()
    object Loading : ViewState()
    class Error(val message: String?) : ViewState()
}