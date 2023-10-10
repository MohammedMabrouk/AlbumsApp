package com.example.albumsapp.domain.useCases

import com.example.albumsapp.domain.repository.AlbumsRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    suspend operator fun invoke(userId: Int) = repository.getAlbums(userId)
}
