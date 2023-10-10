package com.example.albumsapp.data.dto

import com.example.albumsapp.domain.model.AlbumPhoto

class GetPhotosResponseDto : ArrayList<GetPhotosResponseDtoItem>()

data class GetPhotosResponseDtoItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun GetPhotosResponseDto.toAlbumPhotosList(): List<AlbumPhoto> {
    return this.map { item ->
        AlbumPhoto(item.title, item.thumbnailUrl, item.url)
    }
}