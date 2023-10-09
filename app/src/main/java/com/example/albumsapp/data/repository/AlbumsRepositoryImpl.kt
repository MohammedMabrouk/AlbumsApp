package com.example.albumsapp.data.repository

import com.example.albumsapp.data.remote.AlbumsApi
import com.example.albumsapp.data.remote.handleApi
import com.example.albumsapp.domain.repository.AlbumsRepository
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val api: AlbumsApi
) : AlbumsRepository {
    override suspend fun getUser(userId: Int) = handleApi { api.getUser(userId) }

    override suspend fun getAlbums(userId: Int) = handleApi { api.getAlbums(userId) }

    override suspend fun getAlbumPhotos(albumId: Int) = handleApi { api.getAlbumPhotos(albumId) }
}