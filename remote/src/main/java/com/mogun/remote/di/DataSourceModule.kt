package com.mogun.remote.di

import com.mogun.data.source.MovieRemoteDataSource
import com.mogun.remote.impl.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(repo: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}