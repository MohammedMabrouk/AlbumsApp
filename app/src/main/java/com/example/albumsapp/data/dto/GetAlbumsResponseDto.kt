package com.example.albumsapp.data.dto

import com.example.albumsapp.domain.model.Album

class GetAlbumsResponseDto : ArrayList<GetAlbumsResponseDtoItem>()

data class GetAlbumsResponseDtoItem(
    val id: Int,
    val title: String,
    val userId: Int
)

fun GetAlbumsResponseDto.toAlbumsList(): List<Album> {
    return this.map { item ->
        Album(item.id, item.title)
    }
}