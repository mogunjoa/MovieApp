package com.mogun.movieinfo.data.di

import com.mogun.movieinfo.data.impl.MovieRepositoryImpl
import com.mogun.movieinfo.domain.repository.MovieRepository
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