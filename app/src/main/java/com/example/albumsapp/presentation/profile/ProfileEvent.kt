package com.example.albumsapp.presentation.profile

sealed class ProfileEvent {
    class NavigateToAlbumDetails(val albumId: Int) : ProfileEvent()
}
