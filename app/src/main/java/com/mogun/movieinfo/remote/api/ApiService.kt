package com.mogun.movieinfo.remote.api

import com.mogun.movieinfo.remote.model.PopularMoviesResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponseWrapper>


    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoviesResponseWrapper>
}