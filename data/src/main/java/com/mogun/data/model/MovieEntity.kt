package com.mogun.data.model

import com.mogun.domain.model.Movie

data class MovieEntity(
    val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val rating: String,
    val rateCount: Int,
    val releasedAt: String,
) {
    fun toDomain(): Movie {
        return Movie(
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