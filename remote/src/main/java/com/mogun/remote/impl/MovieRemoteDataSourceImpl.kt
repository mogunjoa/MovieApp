package com.mogun.remote.impl

import com.mogun.data.model.MovieEntity
import com.mogun.data.source.MovieRemoteDataSource
import com.mogun.remote.api.ApiHelper
import com.mogun.remote.api.ApiService
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiHelper: ApiHelper,
) : MovieRemoteDataSource {

    override suspend fun getPopularMovies(): List<MovieEntity> {
        return apiHelper.safeApiCall(
            apiCall = { apiService.getPopularMovies(language = "ko", page = 1) },
            mapToData = { body -> body.results.map { it.toData() } }
        )
    }

    override suspend fun getNowPlayingMovies(): List<MovieEntity> {
        return apiHelper.safeApiCall(
            apiCall = { apiService.getNowPlayingMovies(language = "ko", page = 1) },
            mapToData = { body -> body.results.map { it.toData() } }
        )
    }

    override suspend fun getMoviesWithGenre(genreId: Int): List<MovieEntity> {
        return apiHelper.safeApiCall(
            apiCall = { apiService.getMoviesWithGenre(language = "ko", page = 1, genreId = genreId) },
            mapToData = { body -> body.results.map { it.toData() } }
        )
    }
}