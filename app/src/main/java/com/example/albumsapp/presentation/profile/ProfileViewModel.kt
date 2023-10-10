package com.example.albumsapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumsapp.data.dto.GetAlbumsResponseDto
import com.example.albumsapp.data.dto.GetUserResponseDto
import com.example.albumsapp.data.dto.toAlbumsList
import com.example.albumsapp.data.dto.toUser
import com.example.albumsapp.data.remote.NetworkResult
import com.example.albumsapp.domain.model.Album
import com.example.albumsapp.domain.model.User
import com.example.albumsapp.domain.useCases.GetAlbumsUseCase
import com.example.albumsapp.domain.useCases.GetUserInfoUseCase
import com.example.albumsapp.presentation.utils.ViewState
import com.example.albumsapp.presentation.utils.toStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    private val _event = Channel<ProfileEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val _userInfoState = MutableStateFlow<ViewState>(ViewState.Idle)
    val isUserInfoLoading: StateFlow<Boolean> = _userInfoState.map {
        it is ViewState.Loading
    }.toStateFlow(false, viewModelScope)

    val isUserInfoLoaded: StateFlow<Boolean> = _userInfoState.map {
        it is ViewState.Loaded
    }.toStateFlow(false, viewModelScope)

    private val _userAlbumsState = MutableStateFlow<ViewState>(ViewState.Idle)
    val isUserAlbumsLoading: StateFlow<Boolean> = _userAlbumsState.map {
        it is ViewState.Loading
    }.toStateFlow(false, viewModelScope)

    val isUserAlbumsLoaded: StateFlow<Boolean> = _userAlbumsState.map {
        it is ViewState.Loaded
    }.toStateFlow(false, viewModelScope)

    val userInfo: StateFlow<User?> = _userInfoState.map {
        if (it is ViewState.Loaded)
            (it.data as GetUserResponseDto).toUser()
        else
            null
    }.toStateFlow(null, viewModelScope)

    val albumsList: StateFlow<List<Album>?> = _userAlbumsState.map {
        if (it is ViewState.Loaded)
            (it.data as GetAlbumsResponseDto).toAlbumsList()
        else
            null
    }.toStateFlow(null, viewModelScope)

    fun loadData() {
        viewModelScope.launch {
            _userInfoState.value = ViewState.Loading
            _userAlbumsState.value = ViewState.Loading

            val userId = (1..10).random()
            getUserInfoUseCase(userId).let {
                when (it) {
                    is NetworkResult.Success -> _userInfoState.value = ViewState.Loaded(it.data)
                    is NetworkResult.Error -> _userInfoState.value = ViewState.Error(it.message)
                    is NetworkResult.Exception -> _userInfoState.value =
                        ViewState.Error(it.e.message)
                }
            }
            getUserAlbumsUseCase(userId).let {
                when (it) {
                    is NetworkResult.Success -> _userAlbumsState.value = ViewState.Loaded(it.data)
                    is NetworkResult.Error -> _userAlbumsState.value = ViewState.Error(it.message)
                    is NetworkResult.Exception -> _userAlbumsState.value =
                        ViewState.Error(it.e.message)
                }
            }
        }
    }

    fun navigateToAlbumDetails(album: Album?) {
        viewModelScope.launch {
            album?.let {
                _event.send(ProfileEvent.NavigateToAlbumDetails(it.id))
            }
        }
    }

    companion object {
        const val ARG_ALBUM_ID = "albumId"
    }
}