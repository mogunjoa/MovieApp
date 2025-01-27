package com.mogun.movieinfo.remote.api

import com.mogun.movieinfo.remote.model.PopularMoviesResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?&sort_by=popularity.desc")
    suspend fun getPopluarMovies(
        @Query("language") language: String,
    ): Response<PopularMoviesResponseWrapper>
}