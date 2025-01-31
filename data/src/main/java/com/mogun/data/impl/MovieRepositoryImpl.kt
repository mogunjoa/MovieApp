package com.mogun.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mogun.data.page.MoviePagingSource
import com.mogun.data.source.MovieRemoteDataSource
import com.mogun.domain.model.Movie
import com.mogun.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

enum class MovieType {
    POPULAR,
    NOW_PLAYING,
    GENRE
}

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {
    private fun getMovies(movieType: MovieType, genreId: Int? = null): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    remote = movieRemoteDataSource,
                    movieType = movieType,
                    genreId = genreId
                )
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<Movie>> =
        getMovies(MovieType.POPULAR)

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> =
        getMovies(MovieType.NOW_PLAYING)

    override fun getMoviesWithGenre(genreId: Int): Flow<PagingData<Movie>> =
        getMovies(MovieType.GENRE, genreId)
}