package com.mogun.movieinfo.data.source

import com.mogun.movieinfo.data.model.PopularMovieEntity

interface MovieRemoteDataSource {
    suspend fun getPopluarMovies(): List<PopularMovieEntity>
}