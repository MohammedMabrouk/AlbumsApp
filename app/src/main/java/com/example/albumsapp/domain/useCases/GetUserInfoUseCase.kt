package com.example.albumsapp.domain.useCases

import com.example.albumsapp.domain.repository.AlbumsRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val repository: AlbumsRepository
) {
    suspend operator fun invoke(userId: Int) = repository.getUser(userId)
}
