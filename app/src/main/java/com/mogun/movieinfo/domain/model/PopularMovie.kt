package com.mogun.movieinfo.domain.model

data class PopularMovie(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: String,
)
