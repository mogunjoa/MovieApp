package com.mogun.movieinfo.remote.model

import com.google.gson.annotations.SerializedName
import com.mogun.movieinfo.data.model.PopularMovieEntity
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
    fun toData(): PopularMovieEntity {
        return PopularMovieEntity(
            id = id.toString(),
            title = title,
            overview = overview,
            posterPath = posterPath,
            rating = rating,
            rateCount = rateCount,
            releasedAt = releasedAt.toString()
        )
    }
}