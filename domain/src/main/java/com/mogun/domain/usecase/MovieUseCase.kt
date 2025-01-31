package com.mogun.domain.usecase

import com.mogun.domain.repository.MovieRepository

class MovieUseCase @javax.inject.Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun getPopularMovies() = movieRepository.getPopularMovies()
    fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()
    fun getMoviesWithGenre(genreId: Int) = movieRepository.getMoviesWithGenre(genreId)
}