package com.mogun.movieinfo.remote.impl

import com.mogun.movieinfo.data.model.PopularMovieEntity
import com.mogun.movieinfo.data.source.MovieRemoteDataSource
import com.mogun.movieinfo.remote.api.ApiService
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieRemoteDataSource {
    override suspend fun getPopluarMovies(): List<PopularMovieEntity> {
        val response = apiService.getPopluarMovies(language = "ko")

        if (response.isSuccessful) {
            return response.body()?.results?.map {
                it.toData()
            } ?: emptyList()
        } else {
            val message = response.errorBody()?.string() ?: "unknown error"
            throw Exception(message)
        }
    }
}