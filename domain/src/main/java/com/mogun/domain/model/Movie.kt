package com.mogun.domain.model

data class Movie(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: String,
    val rateCount: Int,
    val releasedAt: String,
)
