package com.mogun.movieinfo.domain.usecase

import com.mogun.movieinfo.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun getPopularMovies() = movieRepository.getPopularMovies()
    fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()
}