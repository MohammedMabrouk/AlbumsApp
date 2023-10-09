package com.example.albumsapp.domain.repository

import com.example.albumsapp.data.dto.GetAlbumsResponseDto
import com.example.albumsapp.data.dto.GetPhotosResponseDto
import com.example.albumsapp.data.dto.GetUserResponseDto
import com.example.albumsapp.data.remote.NetworkResult

interface AlbumsRepository {
    suspend fun getUser(userId: Int): NetworkResult<GetUserResponseDto>
    suspend fun getAlbums(userId: Int): NetworkResult<GetAlbumsResponseDto>
    suspend fun getAlbumPhotos(albumId: Int): NetworkResult<GetPhotosResponseDto>
}