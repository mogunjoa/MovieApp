package com.mogun.movieinfo.domain.repository

import androidx.paging.PagingData
import com.mogun.movieinfo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    fun getMoviesWithGenre(genreId: Int): Flow<PagingData<Movie>>
}