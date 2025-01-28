package com.mogun.movieinfo.domain.repository

import com.mogun.movieinfo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<List<Movie>>
    fun getNowPlayingMovies(): Flow<List<Movie>>
}