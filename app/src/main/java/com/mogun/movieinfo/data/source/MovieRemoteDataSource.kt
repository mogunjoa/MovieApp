package com.mogun.movieinfo.data.source

import com.mogun.movieinfo.data.model.MovieEntity

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): List<MovieEntity>
    suspend fun getNowPlayingMovies(): List<MovieEntity>
    suspend fun getMoviesWithGenre(genreId: Int): List<MovieEntity>
}