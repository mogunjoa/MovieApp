package com.mogun.movieinfo.data.impl

import com.mogun.movieinfo.data.source.MovieRemoteDataSource
import com.mogun.movieinfo.domain.model.Movie
import com.mogun.movieinfo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(movieRemoteDataSource.getPopularMovies().map { it.toDomain() })
    }

    override fun getNowPlayingMovies(): Flow<List<Movie>> = flow {
        emit(movieRemoteDataSource.getNowPlayingMovies().map { it.toDomain() })
    }

    override fun getMoviesWithGenre(genreId: Int): Flow<List<Movie>> = flow {
        emit(movieRemoteDataSource.getMoviesWithGenre(genreId).map { it.toDomain() })
    }
}