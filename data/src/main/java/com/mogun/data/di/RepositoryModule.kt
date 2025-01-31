package com.mogun.data.di

import com.mogun.data.impl.MovieRepositoryImpl
import com.mogun.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repo: MovieRepositoryImpl): MovieRepository
}