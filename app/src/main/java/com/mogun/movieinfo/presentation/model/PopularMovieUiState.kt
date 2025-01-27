package com.mogun.movieinfo.presentation.model

import com.mogun.movieinfo.domain.model.PopularMovie

data class PopularMovieUiState(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: String,
)

fun PopularMovie.toUiState(): PopularMovieUiState {
    return PopularMovieUiState(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        rating = rating,
        rateCount = rateCount,
        releasedAt = releasedAt,
    )
}