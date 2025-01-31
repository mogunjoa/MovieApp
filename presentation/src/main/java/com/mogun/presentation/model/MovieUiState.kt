package com.mogun.presentation.model

import com.mogun.domain.model.Movie

data class MovieUiState(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: String,
    val rateCount: Int,
    val releasedAt: String,
)

fun Movie.toUiState(): MovieUiState {
    return MovieUiState(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        rating = rating,
        rateCount = rateCount,
        releasedAt = releasedAt,
    )
}