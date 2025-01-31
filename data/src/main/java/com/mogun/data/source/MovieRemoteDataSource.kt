package com.mogun.data.source

import com.mogun.data.model.MovieEntity

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): List<MovieEntity>
    suspend fun getNowPlayingMovies(): List<MovieEntity>
    suspend fun getMoviesWithGenre(genreId: Int): List<MovieEntity>
}