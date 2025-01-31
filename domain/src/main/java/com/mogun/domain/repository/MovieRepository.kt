package com.mogun.domain.repository

import androidx.paging.PagingData
import com.mogun.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    fun getMoviesWithGenre(genreId: Int): Flow<PagingData<Movie>>
}