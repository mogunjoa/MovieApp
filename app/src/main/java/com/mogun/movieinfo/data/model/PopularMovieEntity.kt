package com.mogun.movieinfo.data.model

import com.mogun.movieinfo.domain.model.PopularMovie

data class PopularMovieEntity(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: Float,
    val rateCount: Int,
    val releasedAt: String,
) {
    fun toDomain(): PopularMovie {
        return PopularMovie(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            rating = rating,
            rateCount = rateCount,
            releasedAt = releasedAt
        )
    }
}