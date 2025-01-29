package com.mogun.movieinfo.remote.model

import com.google.gson.annotations.SerializedName
import com.mogun.movieinfo.data.model.MovieEntity
import com.mogun.movieinfo.remote.network.Constants
import java.util.Date

data class PopularMovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("vote_count")
    val rateCount: Int,
    @SerializedName("release_date")
    val releasedAt: Date,
) {
    val prefixPosterUrl: String
        get() = Constants.POSTER_IMAGE_PREFIX_URL + posterPath

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