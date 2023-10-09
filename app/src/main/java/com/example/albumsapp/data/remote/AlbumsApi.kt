package com.example.albumsapp.data.remote

import com.example.albumsapp.data.dto.GetAlbumsResponseDto
import com.example.albumsapp.data.dto.GetPhotosResponseDto
import com.example.albumsapp.data.dto.GetUserResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumsApi {
    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): Response<GetUserResponseDto>

    @GET("/albums")
    suspend fun getAlbums(@Query("userId") userId: Int): Response<GetAlbumsResponseDto>

    @GET("/photos")
    suspend fun getAlbumPhotos(@Query("albumId") albumId: Int): Response<GetPhotosResponseDto>
}