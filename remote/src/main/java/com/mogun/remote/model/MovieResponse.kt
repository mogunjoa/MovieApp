package com.mogun.remote.model

import com.mogun.data.model.MovieEntity
import java.util.Date
import kotlin.text.format

data class MovieResponse(
    @com.google.gson.annotations.SerializedName("id")
    val id: Int,
    @com.google.gson.annotations.SerializedName("title")
    val title: String,
    @com.google.gson.annotations.SerializedName("overview")
    val overview: String,
    @com.google.gson.annotations.SerializedName("poster_path")
    val posterPath: String,
    @com.google.gson.annotations.SerializedName("vote_average")
    val rating: Float,
    @com.google.gson.annotations.SerializedName("vote_count")
    val rateCount: Int,
    @com.google.gson.annotations.SerializedName("release_date")
    val releasedAt: Date,
) {
    val prefixPosterUrl: String
        get() = com.mogun.remote.network.Constants.POSTER_IMAGE_PREFIX_URL + posterPath

    val ratingToString: String
        get() = String.format("%.1f", rating)

    fun toData(): MovieEntity {
        return MovieEntity(
            id = id.toString(),
            title = title,
            overview = overview,
            posterPath = prefixPosterUrl,
            rating = ratingToString,
            rateCount = rateCount,
            releasedAt = releasedAt.toString()
        )
    }
}