package com.example.albumsapp.presentation.albumDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.data.dto.GetPhotosResponseDto
import com.example.albumsapp.data.dto.toAlbumPhotosList
import com.example.albumsapp.data.remote.NetworkResult
import com.example.albumsapp.domain.model.AlbumPhoto
import com.example.albumsapp.domain.useCases.GetAlbumPhotosUseCase
import com.example.albumsapp.presentation.profile.ProfileViewModel
import com.example.albumsapp.presentation.utils.ViewState
import com.example.albumsapp.presentation.utils.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsDetailsViewModel @Inject constructor(
    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val albumId = savedStateHandle[ProfileViewModel.ARG_ALBUM_ID] ?: 0

    private val _albumPhotosState = MutableStateFlow<ViewState>(ViewState.Idle)
    val isAlbumPhotosLoading: StateFlow<Boolean> = _albumPhotosState.map {
        it is ViewState.Loading
    }.toStateFlow(false, viewModelScope)

    val albumsPhotosList: StateFlow<List<AlbumPhoto>?> = _albumPhotosState.map {
        if (it is ViewState.Loaded)
            (it.data as GetPhotosResponseDto).toAlbumPhotosList()
        else
            null
    }.toStateFlow(null, viewModelScope)

    init {
        viewModelScope.launch {
            _albumPhotosState.value = ViewState.Loading

            getAlbumPhotosUseCase(albumId).let {
                when (it) {
                    is NetworkResult.Success -> _albumPhotosState.value = ViewState.Loaded(it.data)
                    is NetworkResult.Error -> _albumPhotosState.value = ViewState.Error(it.message)
                    is NetworkResult.Exception -> _albumPhotosState.value =
                        ViewState.Error(it.e.message)
                }
            }
        }
    }
}