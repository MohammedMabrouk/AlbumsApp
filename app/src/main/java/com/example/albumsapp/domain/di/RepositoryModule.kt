package com.example.albumsapp.domain.di

import com.example.albumsapp.data.remote.AlbumsApi
import com.example.albumsapp.data.repository.AlbumsRepositoryImpl
import com.example.albumsapp.domain.repository.AlbumsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAlbumsRepository(
        api: AlbumsApi
    ): AlbumsRepository {
        return AlbumsRepositoryImpl(api)
    }
}