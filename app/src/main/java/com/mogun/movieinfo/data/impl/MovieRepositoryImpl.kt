package com.mogun.movieinfo.data.impl

import com.mogun.movieinfo.data.source.MovieRemoteDataSource
import com.mogun.movieinfo.domain.model.PopularMovie
import com.mogun.movieinfo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getPopularMovies(): Flow<List<PopularMovie>> = flow {
        emit(movieRemoteDataSource.getPopluarMovies().map { it.toDomain() })
    }
}