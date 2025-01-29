package com.mogun.movieinfo.remote.api

import com.mogun.movieinfo.remote.model.MoviesResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<MoviesResponseWrapper>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<MoviesResponseWrapper>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<MoviesResponseWrapper>

    @GET("discover/movie")
    suspend fun getMoviesWithGenre(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int,
    ): Response<MoviesResponseWrapper>
}