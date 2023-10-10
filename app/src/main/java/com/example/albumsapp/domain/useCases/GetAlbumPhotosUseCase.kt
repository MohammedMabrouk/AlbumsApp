package com.example.albumsapp.domain.useCases

import com.example.albumsapp.domain.repository.AlbumsRepository
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    suspend operator fun invoke(albumId: Int) = repository.getAlbumPhotos(albumId)
}
